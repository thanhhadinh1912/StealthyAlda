/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.RegisterControl;
import com.stealthyalda.ai.model.dtos.Adresse;
import com.stealthyalda.ai.model.dtos.UnternehmenDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.TopPanelStartSeite;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.gui.windows.ConfirmReg;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
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
        final ComboBox userAnrede = new ComboBox();
//
// Add some items
        userAnrede.setItems("Herr", "Frau");
        userAnrede.setPlaceholder("Anrede");
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
        hl3.setSpacing(true);

        HorizontalLayout streetNrPlz = new HorizontalLayout();
        streetNrPlz.setSpacing(true);
        streetNrPlz.setWidth("250px");


        final TextField strasse = new TextField();
        strasse.setPlaceholder("Straße");
        strasse.setWidth("75%");
        hl3.addComponent(strasse);
        hl3.setComponentAlignment(strasse, Alignment.MIDDLE_LEFT);

        final TextField plz = new TextField();
        plz.setPlaceholder("PLZ");
        plz.setWidth("75%");
        streetNrPlz.addComponent(plz);
        streetNrPlz.setComponentAlignment(plz, Alignment.MIDDLE_LEFT);


        final TextField nummer = new TextField();
        nummer.setPlaceholder("Nr");
        nummer.setWidth("55%");
        streetNrPlz.addComponent(nummer);
        streetNrPlz.setComponentAlignment(nummer, Alignment.MIDDLE_CENTER);


        hl3.addComponent(streetNrPlz);
        hl3.setComponentAlignment(streetNrPlz, Alignment.MIDDLE_LEFT);


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


        binder.forField(strasse).asRequired("Sie müssen eine Gültige Strassenname eingeben")
                .withValidator(new StringLengthValidator("Eingebene Straße nicht gültig", 3, 30))
                .bind(Benutzer::getRole, Benutzer::setRole);

        binder.forField(plz).asRequired("Sie müssen eine Gültige PLZ eingeben")
                .withValidator(new StringLengthValidator("Eingebene PLZ nicht gültig", 4, 5))
                .withValidator(new RegexpValidator("PLZ darf nur Zahlen enthalten", "^[0-9]*$"))
                .bind(Benutzer::getRole, Benutzer::setRole);

        binder.forField(nummer).asRequired("Sie müssen eine Gültige Hausnummer eingeben")
                .withValidator(new StringLengthValidator("Eingebene Hausnummer nicht gültig", 1, 4))
                .bind(Benutzer::getRole, Benutzer::setRole);

        binder.forField(ort).asRequired("Sie müssen ein Ort eingeben")
                .withValidator(new StringLengthValidator("Ort ist nicht gültig", 3, 30))
                .bind(Benutzer::getRole, Benutzer::setRole);
        binder.forField(name).asRequired("Sie müssen eine Name eingeben")
                .withValidator(new StringLengthValidator("Vorname(n) zu kurz", 3, 30))
                .bind(Benutzer::getRole, Benutzer::setRole);

        binder.forField(telefon).asRequired("Sie müssen eine Telefonnummer eingeben")
                .withValidator(new RegexpValidator("Telefonnummer darf nur Zahlen enthalten", "^[0-9]*$"))
                .bind(Benutzer::getRole, Benutzer::setRole);

        ubermitteln.addClickListener(event -> {
            binder.validate();

            if (binder.validate().isOk()) {
                String anrede = (String) userAnrede.getValue();
                String unternehmen = name.getValue();
                String userstrasse = strasse.getValue();
                String hausnummer = nummer.getValue();
                String userort = ort.getValue();
                int userplz = Integer.parseInt(plz.getValue());
                String usertelefon = telefon.getValue();

                // instance of control
                RegisterControl r = new RegisterControl();
                UnternehmenDTO ag = new UnternehmenDTO();
                try {
                    ag.setAdresse(new Adresse(userstrasse, userplz, hausnummer, userort));
                    ag.setTelefonnummer(usertelefon);
                    ag.setUnternehmen(unternehmen);

                    r.registerArbeitgeber(ag, anrede);
                    ConfirmReg window = new ConfirmReg("Registrierung abgeschlossen! ");
                    ((MyUI) UI.getCurrent()).setBenutzer(null);
                    UI.getCurrent().addWindow(window);
                } catch (Exception ex) {
                    Logger.getLogger(RegWeiterArbeitgeber.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    Notification.show(FEHLER, "Ihre Daten konnten nicht gespeichert werden: " + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
                    telefon.setValue(usertelefon);
                    ort.setValue(userort);
                    plz.setValue(String.valueOf(userplz));
                    strasse.setValue(userstrasse);
                }
            } else {
                Notification.show(FEHLER, "Beachten Sie die Hinweise neben den Feldern!", Notification.Type.ERROR_MESSAGE);
            }

        });


        this.addComponent(ubermitteln);
        this.setComponentAlignment(ubermitteln, Alignment.MIDDLE_CENTER);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }
}
