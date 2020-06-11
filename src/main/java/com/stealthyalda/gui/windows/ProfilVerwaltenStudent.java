package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.services.util.ImageUploader;
import com.vaadin.ui.*;


public class ProfilVerwaltenStudent extends Window {
    public ProfilVerwaltenStudent(Benutzer benutzer) {
        super("Profil Verwalten"); //set window caption
        center();
        this.setHeight("600px");
        this.setWidth("1000px");


        GridLayout layout = new GridLayout(10, 12);
        layout.setMargin(true);
        Button buttonDashboard = new Button("Dashboard");
        layout.addComponent(buttonDashboard, 0, 1);
        layout.setComponentAlignment(buttonDashboard, Alignment.MIDDLE_LEFT);
        Button buttonProfilVerwalten = new Button("Profil Verwalten");
        layout.addComponent(buttonProfilVerwalten, 0, 2);
        layout.setComponentAlignment(buttonProfilVerwalten, Alignment.MIDDLE_LEFT);
        Button buttonBenachrichtigungen = new Button("Benachrichtigungen");
        layout.addComponent(buttonBenachrichtigungen, 0, 3);
        layout.setComponentAlignment(buttonBenachrichtigungen, Alignment.MIDDLE_LEFT);

        Button buttonKonto = new Button("Konto");
        layout.addComponent(buttonKonto, 0, 4);
        layout.setComponentAlignment(buttonKonto, Alignment.MIDDLE_LEFT);


        // Create a text field
        TextField tfname = new TextField("Name");
        String name = benutzer.getName();
        if (name != null) {
            tfname.setValue(name);
        }
        layout.addComponent(tfname, 1, 1, 4, 1);
        tfname.setWidth("500px");


        ImageUploader receiver = new ImageUploader();

        // Create the upload with a caption and set receiver later
        Upload upload = new Upload("", receiver);
        upload.addSucceededListener(receiver);
        upload.setButtonCaption("Profilbild hochladen");
        upload.setImmediateMode(false);

        layout.addComponent(upload, 5, 1, 8, 4);
        layout.setComponentAlignment(upload, Alignment.BOTTOM_RIGHT);

        // Create the area
        TextArea area = new TextArea("Job Erfahrungen:");
        layout.addComponent(area, 1, 3, 4, 6);
        layout.setComponentAlignment(area, Alignment.MIDDLE_LEFT);
        area.setWidth("500px");


        TextArea area1 = new TextArea("Hobby");
        layout.addComponent(area1, 1, 7, 2, 10);
        layout.setComponentAlignment(area, Alignment.MIDDLE_LEFT);

        TextArea area2 = new TextArea("Fachkompetenz");
        layout.addComponent(area2, 3, 7, 5, 10);
        layout.setComponentAlignment(area, Alignment.MIDDLE_LEFT);

        Button buttonProfilAendern = new Button("Speichern");
        layout.addComponent(buttonProfilAendern, 7, 11);

        this.setContent(layout);


    }
}