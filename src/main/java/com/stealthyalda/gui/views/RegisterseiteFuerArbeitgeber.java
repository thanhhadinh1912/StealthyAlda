package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.RegisterControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.TopPanelStartSeite;
import com.stealthyalda.gui.windows.ConfirmRegArbeitgeber;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.Views;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.logging.Level;
import java.util.logging.Logger;

// TODO - Validation seems to ignore invalid fields
public class RegisterseiteFuerArbeitgeber extends VerticalLayout implements View {
    public void setUp() {
        // validation experiment
        Binder<Benutzer> binder = new Binder<>();

        // end validation experiment

        final TextField userRegister = new TextField();
        userRegister.setPlaceholder("E-Mail Adresse");


        final PasswordField passwordRegister = new PasswordField();
        passwordRegister.setPlaceholder("Passwort");


//        final TextField userName = new TextField();
//        userName.setCaption("Name");
//
//        final TextField userTelefonNummer = new TextField();
//        userTelefonNummer.setCaption("Tel.Nr");
//
//        final NativeSelect<String> userAnrede = new NativeSelect<>("Anrede");
//
//        // Add some items
//        userAnrede.setItems("Herr", "Frau");
        RadioButtonGroup<String> single = new RadioButtonGroup<>();
        single.setItems("Arbeitgeber", "Student");
        single.setValue("Student");
        single.setItemEnabledProvider(role -> !"Student".equals(role));
        single.setValue("Arbeitgeber");
        single.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

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


//        // Phone number fake?
//        binder.forField(userTelefonNummer)
//                .withValidator(new RegexpValidator("Ungültige Telefonnumer", "^\\d+"))
//                .bind(Benutzer::getPasswort, Benutzer::setPasswort);
//
//        // Require Nachname
//        binder.forField(userName).asRequired()
//                .withValidator(new StringLengthValidator("Bitte Name eingeben", 1, 100))
//                .bind(Benutzer::getPasswort, Benutzer::setPasswort);


//        CheckBox roleField = new CheckBox();
//        binder.forField(roleField)
//                .withConverter(role -> role ? Roles.STUDENT : Roles.ARBEITGEBER,
//                        role -> Roles.STUDENT.equals(role));

        this.addComponent(new TopPanelStartSeite());

        setMargin(true);


//Vertikales Layout + Hinzufügen der Textfelder
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout holayout = new HorizontalLayout();
        Button anmelden = new Button("Anmelden");
        anmelden.addStyleName(ValoTheme.BUTTON_LINK);
        anmelden.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
            }
        });
        holayout.addComponent(anmelden);
        holayout.setComponentAlignment(anmelden, Alignment.MIDDLE_LEFT);
        Button registerb = new Button("Registrieren");
        registerb.addStyleName(ValoTheme.BUTTON_LINK);
        registerb.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.REGISTER);
            }
        });
        holayout.addComponent(registerb);
        holayout.setComponentAlignment(registerb, Alignment.MIDDLE_RIGHT);
        holayout.setWidth("400px");
        holayout.setHeight("50px");
        layout.addComponent(holayout);
        Label e = new Label("Erstellen sie ihr Stealthy_Alda Konto: ");
        layout.addComponent(e);
        Label label = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(label);
        layout.addComponent(userRegister);
        layout.addComponent(passwordRegister);
        userRegister.setWidth("400px");
        passwordRegister.setWidth("400px");
//        layout.addComponent(userAnrede);
//        layout.addComponent(userName);
//        layout.addComponent(userTelefonNummer);
        layout.addComponent(single);
        layout.setComponentAlignment(single, Alignment.MIDDLE_CENTER);
//Erstellen und Hinzufügen eines Panels + Platzierung in die Mitte
        Panel panel = new Panel();

        panel.addStyleName("login");

//Button zum Registrieren
        Button buttonReg = new Button("Registrieren");
        buttonReg.setClickShortcut(ShortcutAction.KeyCode.ENTER);


        layout.addComponent(buttonReg);
        layout.setComponentAlignment(buttonReg, Alignment.MIDDLE_CENTER);
        Label label3 = new Label("oder", ContentMode.TEXT);
        layout.addComponent(label3);
        layout.setComponentAlignment(label3, Alignment.MIDDLE_CENTER);
        Button butonLoginMitGoogle = new Button("Mit Google Anmelden");
        layout.addComponent(butonLoginMitGoogle);
        layout.setComponentAlignment(butonLoginMitGoogle, Alignment.MIDDLE_CENTER);

        panel.setContent(layout);
        panel.setSizeUndefined();


        buttonReg.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String register = userRegister.getValue();
                String password = passwordRegister.getValue();
//                String name = userName.getValue();
//                String telefonNummer = userTelefonNummer.getValue();
//                String anrede = userAnrede.getValue();
                String role = single.getValue();

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
                        allChecksOkay = r.registerUser(register, password, role);
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
                    //Notification.show("Success", "Registrierung abgeschlossen!", Notification.Type.HUMANIZED_MESSAGE);

                    ConfirmRegArbeitgeber windowa = new ConfirmRegArbeitgeber("Richten Sie Ihr Konto ein!");
                    UI.getCurrent().addWindow(windowa);

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