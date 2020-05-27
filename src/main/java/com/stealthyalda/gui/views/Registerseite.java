package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.RegisterControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.TopPanelStartSeite;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.Roles;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.Collections;

import java.util.logging.Level;
import java.util.logging.Logger;

// TODO - Validation seems to ignore invalid fields
// TODO - Alternative registeration for "Unternehmen"
// TODO - redirect to login after successful registration
public class Registerseite extends VerticalLayout implements View {
    public void setUp() {
        // validation experiment
        Binder<Benutzer> binder = new Binder<>();
        // end validation experiment

//        this.setSizeFull();

        final TextField userRegister = new TextField();
        userRegister.setCaption("Email");


        final PasswordField passwordRegister = new PasswordField();
        passwordRegister.setCaption("Passwort");

        final TextField userVorname = new TextField();
        userVorname.setCaption("Vorname");

        final TextField userNachname = new TextField();
        userNachname.setCaption("Nachname");

        final TextField userTelefonNummer = new TextField();
        userTelefonNummer.setCaption("Tel.Nr");

        final NativeSelect<String> userAnrede = new NativeSelect<>("Anrede");

        // Add some items
        userAnrede.setItems("Herr", "Frau");

//        final TextField userAnrede = new TextField();
//        userAnrede.setCaption("Anrede");
        // validators
        // invalid email
        binder.forField(userRegister).asRequired("Sie müssen eine Email Adresse eingeben")
                .withValidator(new EmailValidator("Ungültige Email Adresse"))
                .bind(Benutzer::getEmail, Benutzer::setEmail);

        // password too $hort ;)
        binder.forField(passwordRegister).asRequired("Sie müssen ein Passwort eingeben")
                .withValidator(new StringLengthValidator("Passwort muss zwischen 6 und 20 Zeichen lang sein", 6, 20))
                .bind(Benutzer::getPasswort, Benutzer::setPasswort);


        // Phone number fake?
        binder.forField(userTelefonNummer)
                .withValidator(new RegexpValidator("Ungültige Telefonnumer", "^\\d+"))
                .bind(Benutzer::getPasswort, Benutzer::setPasswort);

        // Require Nachname
        binder.forField(userNachname).asRequired()
                .withValidator(new StringLengthValidator("Bitte Nachname eingeben", 1, 30))
                .bind(Benutzer::getPasswort, Benutzer::setPasswort);
        
        RadioButtonGroup<String> single = new RadioButtonGroup<>();
        single.setItems("Arbeitgeber", "Student");
        single.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
//        CheckBox roleField = new CheckBox();
//        binder.forField(roleField)
//                .withConverter(role -> role ? Roles.STUDENT : Roles.ARBEITGEBER,
//                        role -> Roles.STUDENT.equals(role));

        this.addComponent(new TopPanelStartSeite());

        setMargin(true);


//Vertikales Layout + Hinzufügen der Textfelder
        VerticalLayout layout = new VerticalLayout();


        layout.addComponent(userRegister);
        layout.addComponent(passwordRegister);
        layout.addComponent(userAnrede);
        layout.addComponent(userVorname);
        layout.addComponent(userNachname);
        layout.addComponent(userTelefonNummer);
        layout.addComponent(single);

        Label label = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(label);
//Erstellen und Hinzufügen eines Panels + Platzierung in die Mitte
        Panel panel = new Panel("Erstellen sie ihr Stealthy_Alda Konto: ");
        panel.addStyleName("login");

//Button zum Registrieren

        Button butonRegister = new Button("Registrieren", FontAwesome.SIGN_IN);
        butonRegister.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        layout.addComponent(butonRegister);
        layout.setComponentAlignment(butonRegister, Alignment.MIDDLE_CENTER);
        panel.setContent(layout);
        panel.setSizeUndefined();


        butonRegister.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String register = userRegister.getValue();
                String password = passwordRegister.getValue();
                String vorname = userVorname.getValue();
                String nachname = userNachname.getValue();
                String telefonNummer = userTelefonNummer.getValue();
                String anrede = userAnrede.getValue();
                String role = single.getValue(); 
                
                //String role = roleField.getValue();

                // instance of control
                RegisterControl r = new RegisterControl();

                boolean allChecksOkay = false;
                try {
                    allChecksOkay = r.checkUserExists(register);
                } catch (UserExistsException | DatabaseException ex) {
                    Notification.show("Fehler", "Registrierung konnte nicht abgeschlossen werden", Notification.Type.ERROR_MESSAGE);

                    Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, "Failed on : " + ex);
                    userRegister.setValue("");
                    passwordRegister.setValue("");
                }
                if (allChecksOkay) {
                    allChecksOkay = false;
                    try {
                        allChecksOkay = r.registerUser(register, password, vorname, nachname, telefonNummer,anrede, role);
                    } catch (DatabaseException ex) {
                        // TODO update to actually get the reason
                        Notification.show("Fehler", "Registrierung konnte nicht abgeschlossen werden" + ex.getReason(), Notification.Type.ERROR_MESSAGE);
                        // TODO CSS update for success messages
                        Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, "Failed on : " + ex);
                        userRegister.setValue("");
                        passwordRegister.setValue("");

                    }

                }
                if (allChecksOkay) {
                    Notification.show("Success", "Registrierung abgeschlossen!", Notification.Type.HUMANIZED_MESSAGE);
                }
            }
        });

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        this.setUp();

    }
}