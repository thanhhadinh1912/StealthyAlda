/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.RegisterControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.TopPanelStartSeite;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.gui.windows.ConfirmReg;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author tdinh2s
 */
public class RegWeiterArbeitgeber extends RegWeiter {
    private static final String WIDTH = "500px";
    transient Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

    public void setUp() {

        TopPanelStartSeite panel = new TopPanelStartSeite();
        panel.setHeight("50px");
        this.addComponent(panel);
        setMargin(true);
        Label label = new Label("<b> Richten Sie Ihr Konto ein! </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        label.addStyleName(ValoTheme.LABEL_H1);
        this.addComponent(label);
        this.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        final NativeSelect<String> userAnrede = new NativeSelect<>();
//
// Add some items
        userAnrede.setItems("Herr", "Frau");
        userAnrede.setCaption("Anrede");
        userAnrede.setWidth(WIDTH);
        this.addComponent(userAnrede);
        this.setComponentAlignment(userAnrede, Alignment.MIDDLE_CENTER);

        final TextField name = new TextField();
        name.setPlaceholder("Unternehmensname mit Rechtsform");
        name.setWidth(WIDTH);
        this.addComponent(name);
        this.setComponentAlignment(name, Alignment.MIDDLE_CENTER);

        HorizontalLayout hl2 = new HorizontalLayout();
        hl2.setWidth(WIDTH);
        final TextField email = new TextField();
        email.setWidth("250px");
        email.setValue(user.getEmail());
        email.setReadOnly(true);
        hl2.addComponent(email);
        hl2.setComponentAlignment(email, Alignment.MIDDLE_LEFT);

        final PasswordField passwort = new PasswordField();
        passwort.setValue(user.getPasswort());
        passwort.setWidth("230px");
        passwort.setReadOnly(true);
        hl2.addComponent(passwort);
        hl2.setComponentAlignment(passwort, Alignment.MIDDLE_RIGHT);

        this.addComponent(hl2);
        this.setComponentAlignment(hl2, Alignment.MIDDLE_CENTER);

        HorizontalLayout hl3 = new HorizontalLayout();
        hl3.setWidth(WIDTH);
        final TextField strasse = new TextField();
        strasse.setPlaceholder("Straße");
        strasse.setWidth("250px");
        hl3.addComponent(strasse);
        hl3.setComponentAlignment(strasse, Alignment.MIDDLE_LEFT);

        final TextField plz = new TextField();
        plz.setPlaceholder("PLZ");
        plz.setWidth("230px");
        hl3.addComponent(plz);
        hl3.setComponentAlignment(plz, Alignment.MIDDLE_RIGHT);

        this.addComponent(hl3);
        this.setComponentAlignment(hl3, Alignment.MIDDLE_CENTER);

        final TextField ort = new TextField();
        ort.setPlaceholder("Ort");
        ort.setWidth(WIDTH);
        this.addComponent(ort);
        this.setComponentAlignment(ort, Alignment.MIDDLE_CENTER);

        final TextField telefon = new TextField();
        telefon.setPlaceholder("Telefon");
        telefon.setWidth(WIDTH);
        this.addComponent(telefon);
        this.setComponentAlignment(telefon, Alignment.MIDDLE_CENTER);

        final Label label2 = new Label("&nbsp;", ContentMode.HTML);
        this.addComponent(label2);

        final Button ubermitteln = new Button();
        ubermitteln.setCaption("Übermitteln");
        ubermitteln.setWidth(WIDTH);
        ubermitteln.addClickListener(event -> {
            String anrede = userAnrede.getValue();
            String unternehmen = name.getValue();
            String userwohnort = strasse.getValue();
            StringBuilder userstrasse = new StringBuilder();
            StringBuilder hausnummer = new StringBuilder();
            // TODO - add a field for "Hausnummer"
            if (userwohnort != null && !userwohnort.isEmpty()) {
                for (char c : userwohnort.toCharArray()) {
                    if (Character.isDigit(c)) {

                        hausnummer.append(c);
                    } else {
                        userstrasse.append(c);
                    }
                }
            }
            String userort = ort.getValue();
            int userplz = Integer.parseInt(plz.getValue());
            String usertelefon = telefon.getValue();
            // instance of control
            RegisterControl r = new RegisterControl();
            try {
                r.registerArbeitgeber(anrede, unternehmen, userstrasse.toString(), userplz, userort, hausnummer.toString(), usertelefon);
            } catch (DatabaseException ex) {
                Logger.getLogger(RegWeiterArbeitgeber.class.getName()).log(Level.SEVERE, null, ex);
            }
            ConfirmReg window = new ConfirmReg("Registrierung abgeschlossen! Zurück zur Loginseite!");
            UI.getCurrent().addWindow(window);

        });


        this.addComponent(ubermitteln);
        this.setComponentAlignment(ubermitteln, Alignment.MIDDLE_CENTER);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }
}