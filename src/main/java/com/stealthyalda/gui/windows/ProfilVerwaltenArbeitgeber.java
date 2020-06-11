/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.services.util.ImageUploader;
import com.vaadin.ui.*;

/**
 * @author WINDOWS
 */
public class ProfilVerwaltenArbeitgeber extends Window {
    public ProfilVerwaltenArbeitgeber(Benutzer user) {
        super("Profil Verwalten"); //set window caption
        center();
        this.setHeight("600px");
        this.setWidth("1000px");


        GridLayout layout = new GridLayout(11, 14);
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

        ImageUploader receiver = new ImageUploader();

        // Create the upload with a caption and set receiver later
        Upload upload = new Upload("", receiver);
        upload.addSucceededListener(receiver);
        upload.setButtonCaption("Logo hochladen");
        upload.setImmediateMode(false);

        layout.addComponent(upload, 1, 0);
        layout.setComponentAlignment(upload, Alignment.TOP_LEFT);


        // Create the area
        TextArea area = new TextArea("Kurze Beschreibung des Unternehmens:");
        layout.addComponent(area, 1, 2, 4, 4);
        layout.setComponentAlignment(area, Alignment.MIDDLE_LEFT);
        area.setWidth("300px");


        TextArea area1 = new TextArea("Kontaktdaten");
        layout.addComponent(area1, 6, 2, 9, 4);
        layout.setComponentAlignment(area1, Alignment.MIDDLE_LEFT);
        area1.setWidth("288px");


        TextArea area2 = new TextArea("Stellenanzeige");
        layout.addComponent(area2, 1, 5, 9, 9);
        layout.setComponentAlignment(area2, Alignment.MIDDLE_LEFT);
        area2.setWidth("600px");

        TextArea area3 = new TextArea("Anfahrt");

        layout.addComponent(area3, 1, 10, 4, 12);
        layout.setComponentAlignment(area3, Alignment.MIDDLE_LEFT);
        area3.setWidth("300px");


        Button buttonProfilAendern = new Button("Speichern");
        layout.addComponent(buttonProfilAendern, 10, 13);

        this.setContent(layout);


    }
}