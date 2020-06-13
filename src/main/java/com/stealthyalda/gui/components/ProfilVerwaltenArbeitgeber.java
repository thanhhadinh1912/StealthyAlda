package com.stealthyalda.gui.components;

import com.stealthyalda.services.util.ImageUploader;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;

public class ProfilVerwaltenArbeitgeber extends VerticalLayout {
    public ProfilVerwaltenArbeitgeber() {
        VerticalLayout main = new VerticalLayout();
        HorizontalLayout logoandname = new HorizontalLayout();
        ImageUploader receiver = new ImageUploader();
        Upload upload = new Upload("", receiver);
        upload.addSucceededListener(receiver);
        upload.setButtonCaption("Logo hochladen");
        upload.setImmediateMode(true);
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown_logo.png"));
        Image logo = new Image("", resource);
        logoandname.addComponent(logo);
        TextField name = new TextField();
        name.setPlaceholder("Vorname Nachname");
        name.setWidth("580px");
        name.setHeight("60px");
        logoandname.addComponent(name);
        logoandname.setComponentAlignment(name, Alignment.BOTTOM_CENTER);
        logoandname.setComponentAlignment(logo, Alignment.TOP_LEFT);


        logoandname.addComponent(upload);
        logoandname.setComponentAlignment(upload, Alignment.BOTTOM_RIGHT);

        main.addComponent(logoandname);
        //this.setComponentAlignment(logoandname, Alignment.TOP_LEFT);

        HorizontalLayout beschreibungandkontakt = new HorizontalLayout();
        beschreibungandkontakt.setWidth("1000px");
        // Create the area
        TextArea area = new TextArea("Kurze Beschreibung des Unternehmens");
        area.setHeight("75px");
        area.setWidth("650px");
        beschreibungandkontakt.addComponent(area);
        beschreibungandkontakt.setComponentAlignment(area, Alignment.TOP_LEFT);
        TextArea kontakte = new TextArea("Shortlink mit Icons");
        kontakte.setHeight("75px");
        kontakte.setWidth("300px");
        beschreibungandkontakt.addComponent(kontakte);
        beschreibungandkontakt.setComponentAlignment(kontakte, Alignment.TOP_RIGHT);
        main.addComponent(beschreibungandkontakt);

        TextArea stellenanzeige = new TextArea("Stellenanzeige");
        stellenanzeige.setWidth("1000px");
        stellenanzeige.setHeight("200px");
        main.addComponent(stellenanzeige);

        TextArea anfahrt = new TextArea("Anfahrt");
        anfahrt.setWidth("600px");
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
