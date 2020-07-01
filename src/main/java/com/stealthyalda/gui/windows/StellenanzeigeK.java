package com.stealthyalda.gui.windows;


import com.stealthyalda.ai.control.StellenanzeigeControl;
import com.stealthyalda.ai.control.ToogleRouter;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.gui.components.ComponentJobAusschreibung;
import com.vaadin.ui.*;

public class StellenanzeigeK extends Window {
    public StellenanzeigeK(Stellenanzeige stellenanzeige) {
        center();
        StellenanzeigeDTO jobangebot = new StellenanzeigeControl().get(stellenanzeige.getStellenanzeigeID());

        VerticalLayout content = new ComponentJobAusschreibung().getJobAusschreibung(jobangebot);


        HorizontalLayout button = new HorizontalLayout();
        button.setWidth("800px");

        Button zuruck = new Button("Zurück");
        zuruck.setWidth("150px");
        zuruck.addClickListener(clickEvent -> close());
        button.addComponent(zuruck);
        button.setComponentAlignment(zuruck, Alignment.MIDDLE_LEFT);

        Button bewerben = new Button("Jetzt Bewerben!");
        bewerben.addClickListener(clickEvent -> {
            BewerbungWindow window = new BewerbungWindow(stellenanzeige);
            UI.getCurrent().addWindow(window);
        });
        //bewerben.setWidth("170px");
        try {
            if (new ToogleRouter().isEnabled("bewerbung")) {
                button.addComponent(bewerben);
                button.setComponentAlignment(bewerben, Alignment.MIDDLE_RIGHT);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        content.addComponent(button);
        content.setComponentAlignment(button, Alignment.TOP_CENTER);

        this.setContent(content);


    }


}
