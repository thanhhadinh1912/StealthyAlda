package com.stealthyalda.gui.components;

import com.stealthyalda.ai.model.dao.AdresseDAO;
import com.stealthyalda.ai.model.dao.ArbeitgeberDAO;
import com.stealthyalda.ai.model.dao.StudentDAO;
import com.stealthyalda.ai.model.dtos.Adresse;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.services.util.ImageUploader;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;

public class ProfilVerwaltenArbeitgeber extends VerticalLayout {
    public ProfilVerwaltenArbeitgeber(Benutzer user) {
        Arbeitgeber current = ArbeitgeberDAO.getInstance().getArbeitgeber(user.getEmail());
        VerticalLayout main = new VerticalLayout();
        HorizontalLayout logoandname = new HorizontalLayout();
        ImageUploader receiver = new ImageUploader();
        Upload upload = new Upload("", receiver);
        upload.addSucceededListener(receiver);
        upload.setButtonCaption("Logo hochladen");
        upload.setImmediateMode(true);
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown_profil.png"));
        Image logo = new Image("", resource);
        logoandname.addComponent(logo);
        TextField name = new TextField();
        String nameunternehmen = current.getUnternehmen();
        name.setPlaceholder("Name des Unternehmens");
        if(nameunternehmen.length()!=0) name.setValue(nameunternehmen);
        name.setWidth("580px");
        name.setHeight("60px");
        logoandname.addComponent(name);
        logoandname.setComponentAlignment(name, Alignment.BOTTOM_CENTER);
        logoandname.setComponentAlignment(logo, Alignment.TOP_LEFT);


        logoandname.addComponent(upload);
        logoandname.setComponentAlignment(upload, Alignment.BOTTOM_RIGHT);

        main.addComponent(logoandname);

        HorizontalLayout beschreibungandkontakt = new HorizontalLayout();
        beschreibungandkontakt.setWidth("1000px");
        // Create the area
        TextArea area = new TextArea("Kurze Beschreibung des Unternehmens");
        String beschreibung = current.getBeschreibung();
        if(beschreibung!=null) {
            area.setValue(current.getBeschreibung());
        }
        area.setHeight("75px");
        area.setWidth("650px");
        beschreibungandkontakt.addComponent(area);
        beschreibungandkontakt.setComponentAlignment(area, Alignment.TOP_LEFT);
        TextArea kontakte = new TextArea("Shortlink mit Icons");
        kontakte.setValue(current.getTelefonnummer());
        kontakte.setHeight("75px");
        kontakte.setWidth("300px");
        beschreibungandkontakt.addComponent(kontakte);
        beschreibungandkontakt.setComponentAlignment(kontakte, Alignment.TOP_RIGHT);
        main.addComponent(beschreibungandkontakt);

        TextArea stellenanzeige = new TextArea("Stellenanzeige");
        stellenanzeige.setWidth("1000px");
        stellenanzeige.setHeight("200px");
        main.addComponent(stellenanzeige);

        TextArea anfahrt = new TextArea("Adresse");
        Adresse a = AdresseDAO.getInstance().getAdresse(current.getId());
        if(a!=null)        anfahrt.setValue(a.toString());
        anfahrt.setWidth("1000px");
        anfahrt.setHeight("100px");
        main.addComponent(anfahrt);

        Button speichern = new Button("Speichern");
        main.addComponent(speichern);
        main.setComponentAlignment(speichern, Alignment.BOTTOM_CENTER);

        this.addComponent(main);
        this.setHeight("700px");
        this.setComponentAlignment(main, Alignment.TOP_CENTER);


    }
}
