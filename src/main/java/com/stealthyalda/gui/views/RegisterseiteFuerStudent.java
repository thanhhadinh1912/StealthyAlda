package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.RegisterControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.TopPanelStartSeite;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.gui.windows.ConfirmRegStudent;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.Views;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterseiteFuerStudent extends Register {
    public void setUp() {
        final TextField userRegister = new TextField();
        userRegister.setPlaceholder("E-Mail Adresse");


        final PasswordField passwordRegister = new PasswordField();
        passwordRegister.setPlaceholder("Passwort");

        RadioButtonGroup<String> single = new RadioButtonGroup<>();
        single.setItems(ARBEITGEBER, STUDENT);
        single.setValue(STUDENT);
        single.setItemEnabledProvider(role -> !ARBEITGEBER.equals(role));
        single.setValue(STUDENT);
        single.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

        // validators
        // invalid email
        binder.forField(userRegister).asRequired("Sie müssen eine Email Adresse eingeben")
                .withValidator(new EmailValidator("Ungültige Email Adresse"))
                .bind(Benutzer::getEmail, Benutzer::setEmail);

        // password too $hort ;)
        binder.forField(passwordRegister).asRequired("Sie müssen ein Passwort eingeben")
                .withValidator(new StringLengthValidator("Passwort muss zwischen 6 und 20 Zeichen lang sein", 6, 20))
                .bind(Benutzer::getPasswort, Benutzer::setPasswort);

        this.addComponent(new TopPanelStartSeite());

        setMargin(true);


//Vertikales Layout + Hinzufügen der Textfelder
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout holayout = new HorizontalLayout();
        Button anmelden = new Button("Anmelden");
        anmelden.addStyleName(ValoTheme.BUTTON_LINK);
        anmelden.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.LOGIN));
        holayout.addComponent(anmelden);
        holayout.setComponentAlignment(anmelden, Alignment.MIDDLE_LEFT);
        Button registerb = new Button("Registrieren");
        registerb.addStyleName(ValoTheme.BUTTON_LINK);
        registerb.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
        holayout.addComponent(registerb);
        holayout.setComponentAlignment(registerb, Alignment.MIDDLE_RIGHT);
        holayout.setWidth(HORIZONTAL_WIDTH);
        holayout.setHeight("50px");
        layout.addComponent(holayout);
        Label e = new Label("Erstellen sie ihr Stealthy_Alda Konto: ");
        layout.addComponent(e);
        Label label = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(label);
        layout.addComponent(userRegister);
        layout.addComponent(passwordRegister);
        userRegister.setWidth(HORIZONTAL_WIDTH);
        passwordRegister.setWidth(HORIZONTAL_WIDTH);
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


        buttonReg.addClickListener(clickEvent -> {
            String register = userRegister.getValue();
            String password = passwordRegister.getValue();
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
                    Notification.show("Fehler", "Registrierung konnte nicht abgeschlossen werden" + ex.getReason(), Notification.Type.ERROR_MESSAGE);
                    Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, "Failed on : " + ex);
                    userRegister.setValue(register);
                    passwordRegister.setValue("");

                }

            }

            if (allChecksOkay) {
                Benutzer current = new Benutzer();
                current.setEmail(register);
                current.setPasswort(password);
                current.setRole(role);
                current.setId(new Integer(VaadinSession.getCurrent().getAttribute("userId").toString()));
                ((MyUI) UI.getCurrent()).setBenutzer(current);
                ConfirmRegStudent window = new ConfirmRegStudent("Richten Sie Ihr Konto ein!");
                UI.getCurrent().addWindow(window);

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