package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.AnforderungDAO;
import com.stealthyalda.ai.model.dtos.Anforderung;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComponentJobAusschreibung {

    public VerticalLayout getJobAusschreibung(StellenanzeigeDTO jobangebot) {
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout top = new HorizontalLayout();

        VerticalLayout titel = new VerticalLayout();
        Label jt = new Label(jobangebot.getTitel());
        jt.setWidth("600px");
        titel.addComponent(jt);

        Label ju = new Label(jobangebot.getUnternehmen().getUnternehmen() + " - " + jobangebot.getOrt());
        ju.setWidth("600px");
        titel.addComponent(ju);

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown_profil.png"));
        Image profilbild = new Image("", resource);

        VerticalLayout right = new VerticalLayout();
        right.addComponent(profilbild);
        right.setComponentAlignment(profilbild, Alignment.TOP_CENTER);
        right.addComponent(new Label(jobangebot.getStatus()));
        right.setComponentAlignment(profilbild, Alignment.TOP_CENTER);
        right.addComponent(new Label(jobangebot.getDatum().toString()));
        Button arbeitgeber = new Button(VaadinIcons.HOME);


        top.addComponent(titel);
        top.addComponent(right);
        top.addComponent(arbeitgeber);
        top.setComponentAlignment(arbeitgeber, Alignment.MIDDLE_CENTER);
        top.setHeight("175px");

        content.addComponent(top);
        content.setComponentAlignment(top, Alignment.TOP_CENTER);


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
            Logger.getLogger(ComponentJobAusschreibung.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        anforderung.setHeight("150px");
        anforderung.setWidth("700px");
        content.addComponent(anforderung);
        content.setComponentAlignment(anforderung, Alignment.TOP_CENTER);
        return content;
    }
}
