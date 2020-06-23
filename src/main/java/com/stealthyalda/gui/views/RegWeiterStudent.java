/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.RegisterControl;
import com.stealthyalda.ai.model.dtos.Adresse;
import com.stealthyalda.ai.model.dtos.StudentDTO;
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
 * @author WINDOWS
 */
public class RegWeiterStudent extends Register {
    private static final String WIDTH = "500px";
    transient Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
    private final String w = "250px";
    private final String w2 = "230px";

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


        HorizontalLayout hl1 = new HorizontalLayout();
        hl1.setWidth(WIDTH);
        final TextField vorname = new TextField();
        vorname.setPlaceholder("Vorname");
        vorname.setWidth(w);
        hl1.addComponent(vorname);
        hl1.setComponentAlignment(vorname, Alignment.MIDDLE_LEFT);

        final TextField nachname = new TextField();
        nachname.setPlaceholder("Nachname");
        nachname.setWidth(w2);
        hl1.addComponent(nachname);
        hl1.setComponentAlignment(nachname, Alignment.MIDDLE_RIGHT);

        this.addComponent(hl1);
        this.setComponentAlignment(hl1, Alignment.MIDDLE_CENTER);

        HorizontalLayout hl2 = new HorizontalLayout();
        hl2.setWidth(WIDTH);
        final TextField email = new TextField();
        email.setPlaceholder("E-Mail");
        email.setWidth(w);
        email.setValue(user.getEmail());
        email.setReadOnly(true);
        hl2.addComponent(email);
        hl2.setComponentAlignment(email, Alignment.MIDDLE_LEFT);

        final PasswordField passwort = new PasswordField();
        passwort.setPlaceholder("Passwort");
        passwort.setValue(user.getPasswort());
        passwort.setWidth(w2);
        passwort.setReadOnly(true);
        hl2.addComponent(passwort);
        hl2.setComponentAlignment(passwort, Alignment.MIDDLE_RIGHT);

        this.addComponent(hl2);
        this.setComponentAlignment(hl2, Alignment.MIDDLE_CENTER);

        /// start edits
        HorizontalLayout hl3 = new HorizontalLayout();
        hl3.setWidth(WIDTH);
        hl3.setSpacing(true);

        HorizontalLayout streetNrPlz = new HorizontalLayout();
        streetNrPlz.setSpacing(true);
        streetNrPlz.setWidth(w);


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

        /// end edits

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
        binder.forField(vorname).asRequired("Sie müssen Ihre Vorname(n) eingeben")
                .withValidator(new StringLengthValidator("Vorname(n) zu kurz", 3, 30))
                .bind(Benutzer::getRole, Benutzer::setRole);
        binder.forField(nachname).asRequired("Sie müssen Ihre Nachname(n) eingeben")
                .withValidator(new StringLengthValidator("Nachname(n) zu kurz", 3, 30))
                .bind(Benutzer::getRole, Benutzer::setRole);

        binder.forField(telefon).asRequired("Sie müssen eine Telefonnummer eingeben")
                .withValidator(new RegexpValidator("Telefonnummer darf nur Zahlen enthalten", "^[0-9]*$"))
                .bind(Benutzer::getRole, Benutzer::setRole);

        ubermitteln.addClickListener(event -> {
            binder.validate();
            if (binder.validate().isOk()) {


                String anrede = userAnrede.getValue();
                String uservorname = vorname.getValue();
                String username = nachname.getValue();
                String userstrasse = strasse.getValue();
                String hausnummer = nummer.getValue();
                String userort = ort.getValue();
                int userplz = Integer.parseInt(plz.getValue());
                String usertelefon = telefon.getValue();
                // instance of control
                RegisterControl r = new RegisterControl();
                StudentDTO studi = new StudentDTO();
                try {
                    studi.setAnrede(anrede);
                    studi.setVorname(uservorname);
                    studi.setNachname(username);
                    studi.setTelefonnummer(usertelefon);
                    studi.setAdresse(new Adresse(userstrasse, userplz, hausnummer, userort));
                    r.registerStudent(studi);
                } catch (Exception e) {
                    Logger.getLogger(RegWeiterStudent.class.getName()).log(Level.SEVERE, e.getMessage(), e);
                }
                ConfirmReg window = new ConfirmReg("Registrierung abgeschlossen!");
                UI.getCurrent().addWindow(window);
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
