package com.stealthyalda.gui.components;


import com.stealthyalda.ai.control.StellenanzeigeControl;
import com.stealthyalda.ai.control.ToogleRouter;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.AnforderungDAO;
import com.stealthyalda.ai.model.dao.SoftskillDAO;
import com.stealthyalda.ai.model.dtos.Anforderung;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Softskill;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;
import java.util.List;

public class StellenanzeigeK extends Window {
    public StellenanzeigeK(Stellenanzeige stellenanzeige){
        center();
        StellenanzeigeDTO jobangebot = new StellenanzeigeControl().get(stellenanzeige.getStellenanzeigeID());


        VerticalLayout content = new VerticalLayout();
        HorizontalLayout top = new HorizontalLayout();

        VerticalLayout titel = new VerticalLayout();
        Label jt = new Label(jobangebot.getTitel());
        jt.setWidth("600px");
        titel.addComponent(jt);

        Label ju = new Label(jobangebot.getUnternehmen().getUnternehmen() + " - " +jobangebot.getOrt());
        ju.setWidth("600px");
        titel.addComponent(ju);

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown_profil.png"));
        Image profilbild = new Image("", resource);

        VerticalLayout right = new VerticalLayout();
        right.addComponent(profilbild);
        right.setComponentAlignment(profilbild,Alignment.TOP_CENTER);
        right.addComponent(new Label(jobangebot.getStatus()));
        right.setComponentAlignment(profilbild,Alignment.TOP_CENTER);
        right.addComponent(new Label(jobangebot.getDatum().toString()));
        Button arbeitgeber = new Button(VaadinIcons.HOME);


        top.addComponent(titel);
        top.addComponent(right);
        top.addComponent(arbeitgeber);
        top.setComponentAlignment(arbeitgeber, Alignment.MIDDLE_CENTER);
        top.setHeight("175px");

        content.addComponent(top);
        content.setComponentAlignment(top,Alignment.TOP_CENTER);


        TextArea beschreibung = new TextArea("Jobbeschreibung");
        beschreibung.setValue(jobangebot.getBeschreibung());
        beschreibung.setReadOnly(true);
        beschreibung.setHeight("150px");
        beschreibung.setWidth("700px");
        content.addComponent(beschreibung);
        content.setComponentAlignment(beschreibung, Alignment.TOP_CENTER);

        TextArea anforderung = new TextArea("Anforderungen");
        anforderung.setReadOnly(true);
        try {
            List<Anforderung> a = AnforderungDAO.getInstance().getAnforderungForStellenanzeige(jobangebot.getStellenanzeigeID());
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < a.size(); i++) {
                print.append(a.get(i).getAnforderung());
                print.append("\n");
            }
            anforderung.setValue(String.valueOf(print));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        anforderung.setHeight("150px");
        anforderung.setWidth("700px");
        content.addComponent(anforderung);
        content.setComponentAlignment(anforderung, Alignment.TOP_CENTER);

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
        bewerben.setWidth("150px");
        try {
            if(new ToogleRouter().isEnabled("bewerbung")){
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
