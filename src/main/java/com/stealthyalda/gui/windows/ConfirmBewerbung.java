package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.control.BewerbungControl;
import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Student;
import com.vaadin.ui.*;


public class ConfirmBewerbung extends Window {
    public ConfirmBewerbung(StellenanzeigeDTO a, BewerbungCollAtHBRSDTO b, Student s) {
        center();

        //Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new com.vaadin.ui.Label("Sind Sie sicher, dass Sie die Bewerbung \t jetzt abschicken möchten? "));
        content.setMargin(true);
        setContent(content);

        HorizontalLayout janein = new HorizontalLayout();
        janein.setWidth("600px");

        com.vaadin.ui.Button nein = new com.vaadin.ui.Button("Nein");
        janein.addComponent(nein);
        nein.addClickListener(clickEvent -> close());
        janein.setComponentAlignment(nein, Alignment.MIDDLE_LEFT);

        com.vaadin.ui.Button ja = new com.vaadin.ui.Button("Ja");
        janein.addComponent(ja);
        ja.addClickListener(clickEvent -> {
            new BewerbungControl().createbewerbung(a, b, s);
            com.vaadin.ui.Window confirm = new com.vaadin.ui.Window();
            confirm.setContent(new Label("Super! Die Bewerbung wurde versendet."));
            confirm.center();
            confirm.setWidth("600px");
            UI.getCurrent().addWindow(confirm);
            close();
        });
        janein.setComponentAlignment(ja, Alignment.MIDDLE_RIGHT);

        content.addComponent(janein);
        content.setComponentAlignment(janein, Alignment.BOTTOM_CENTER);
    }
}
