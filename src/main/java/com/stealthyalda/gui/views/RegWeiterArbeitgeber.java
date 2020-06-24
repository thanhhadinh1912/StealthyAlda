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
    private static final String WIDTHB1 = "250px";
    private static final String WIDTHB2 = "230px";


    transient Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

    public void setUp() {

        VerticalLayout main = new VerticalLayout();
        main.setWidth(WIDTH);

        TopPanelStartSeite panel = new TopPanelStartSeite();
        panel.setHeight("50px");
        this.addComponent(panel);
        setMargin(true);
        Label label = new Label("<b> Richten Sie Ihr Konto ein! </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        label.addStyleName(ValoTheme.LABEL_H1);
        main.addComponent(label);
        final ComboBox userAnrede = new ComboBox();
//
// Add some items
        userAnrede.setItems("Herr", "Frau");
        userAnrede.setPlaceholder("Anrede");
        userAnrede.setWidth(WIDTH);
        main.addComponent(userAnrede);

        final TextField name = new TextField();
        name.setPlaceholder("Unternehmensname mit Rechtsform");
        name.setWidth(WIDTH);
        main.addComponent(name);

        HorizontalLayout hl2 = new HorizontalLayout();
        hl2.setWidth(WIDTH);
        final TextField email = new TextField();
        email.setWidth("250px");
        email.setValue(user.getEmail());
        email.setReadOnly(true);
        hl2.addComponent(email);

        final PasswordField passwort = new PasswordField();
        passwort.setValue(user.getPasswort());
        passwort.setWidth("230px");
        passwort.setReadOnly(true);
        hl2.addComponent(passwort);
        hl2.setComponentAlignment(passwort, Alignment.MIDDLE_RIGHT);

        main.addComponent(hl2);

        HorizontalLayout streetNr = new HorizontalLayout();
        streetNr.setSpacing(true);
        streetNr.setWidth("515px");


        final TextField strasse = new TextField();
        strasse.setPlaceholder("Straße");
        strasse.setWidth(WIDTHB1);
        streetNr.addComponent(strasse);


        final TextField nummer = new TextField();
        nummer.setPlaceholder("Nr");
        nummer.setWidth(WIDTHB2);
        streetNr.addComponent(nummer);
        streetNr.setComponentAlignment(nummer,Alignment.MIDDLE_RIGHT);

        final HorizontalLayout plzort = new HorizontalLayout();
        plzort.setWidth("515px");

        final TextField plz = new TextField();
        plz.setPlaceholder("PLZ");
        plz.setWidth(WIDTHB1);
        plzort.addComponent(plz);



        main.addComponent(streetNr);

        final TextField ort = new TextField();
        ort.setPlaceholder("Ort");
        ort.setWidth(WIDTHB2);
        plzort.addComponent(ort);
        plzort.setComponentAlignment(ort, Alignment.MIDDLE_RIGHT);

        main.addComponent(plzort);

        final TextField telefon = new TextField();
        telefon.setPlaceholder("Telefon");
        telefon.setWidth(WIDTH);
        main.addComponent(telefon);

        final Label label2 = new Label("&nbsp;", ContentMode.HTML);
        main.addComponent(label2);

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


        main.addComponent(ubermitteln);
        this.addComponent(main);
        this.setComponentAlignment(main, Alignment.MIDDLE_CENTER);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }
}
