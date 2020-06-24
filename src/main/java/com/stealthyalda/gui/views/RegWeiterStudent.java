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
import com.stealthyalda.gui.components.*;
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
        VerticalLayout haupt = new VerticalLayout();
        haupt.setWidth(WIDTH);
        panel.setHeight("50px");
        this.addComponent(panel);
        setMargin(true);
        Label label = new Label("<b> Richten Sie Ihr Konto ein! </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        label.addStyleName(ValoTheme.LABEL_H1);
        haupt.addComponent(label);
        haupt.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        final ComboBox<String> userAnrede = new AnredeField();
        haupt.addComponent(userAnrede);

        HorizontalLayout hl1 = new HorizontalLayout();
        hl1.setWidth("515px");
        final TextFieldForRegWeiter vorname = new TextFieldForRegWeiter("Vorname",w);
        hl1.addComponent(vorname);
        hl1.setComponentAlignment(vorname, Alignment.MIDDLE_LEFT);

        final TextFieldForRegWeiter nachname = new TextFieldForRegWeiter("Nachname", w2);
        hl1.addComponent(nachname);
        hl1.setComponentAlignment(nachname, Alignment.MIDDLE_RIGHT);

        haupt.addComponent(hl1);

        EmailPasworrtField hl2 = new EmailPasworrtField(user);

        haupt.addComponent(hl2);

        /// start edits
        HorizontalLayout streetNr = new HorizontalLayout();
        streetNr.setSpacing(true);
        streetNr.setWidth("515px");


        final TextFieldForRegWeiter strasse = new TextFieldForRegWeiter("Straße", w);
        streetNr.addComponent(strasse);


        final TextFieldForRegWeiter nummer = new TextFieldForRegWeiter("Hausnr.", w2);
        streetNr.addComponent(nummer);
        streetNr.setComponentAlignment(nummer,Alignment.MIDDLE_RIGHT);

        final HorizontalLayout plzort = new HorizontalLayout();
        plzort.setWidth("515px");

        final TextFieldForRegWeiter plz = new TextFieldForRegWeiter("PLZ", w);
        plzort.addComponent(plz);

        haupt.addComponent(streetNr);

        final TextFieldForRegWeiter ort = new TextFieldForRegWeiter("Ort", w2);
        plzort.addComponent(ort);
        plzort.setComponentAlignment(ort, Alignment.MIDDLE_RIGHT);

        haupt.addComponent(plzort);

        final TextFieldForRegWeiter telefon = new TextFieldForRegWeiter("Telefon", WIDTH);
        haupt.addComponent(telefon);


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
        haupt.addComponent(ubermitteln);
        haupt.setComponentAlignment(ubermitteln, Alignment.MIDDLE_CENTER);
        this.addComponent(haupt);
        this.setComponentAlignment(haupt, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }
}
