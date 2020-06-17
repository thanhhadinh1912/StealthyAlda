package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.TopPanelStartSeite;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Views;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;


public class Loginseite extends VerticalLayout implements View {

    public void setUp() {

//Gesamtgröße des Bildschirms auf komplette Größe beziehen


//Textfeld Login
        final TextField userLogin = new TextField();
        userLogin.setPlaceholder("E-Mail ");


//Textfelt Passwort
        final PasswordField passwordField = new PasswordField();
        passwordField.setPlaceholder("Passwort ");


// add image
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        FileResource resource = new FileResource(new File(basepath +
                "/Image/stealthyalda1.png"));
        Image logo = new Image("", resource);

        GridLayout mainGrid = new GridLayout(6, 6);
        mainGrid.setSizeFull();


        Panel panel = new Panel();
        panel.addStyleName("login");
//Vertikales Layout + Hinzufügen der Textfelder
        GridLayout layout = new GridLayout(4, 12);
        Button buttonAn = new Button("Anmelden");
        Button butonReg = new Button("Registrieren");
        buttonAn.addStyleName(ValoTheme.BUTTON_LINK);
        buttonAn.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.LOGIN));
        butonReg.addStyleName(ValoTheme.BUTTON_LINK);
        butonReg.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
        layout.addComponent(buttonAn, 0, 0);
        layout.setComponentAlignment(buttonAn, Alignment.MIDDLE_CENTER);
        layout.addComponent(logo, 1, 0, 2, 0);
        layout.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        layout.addComponent(butonReg, 3, 0);
        layout.setComponentAlignment(butonReg, Alignment.MIDDLE_CENTER);
        Label label = new Label("Willkommen zurück!", ContentMode.PREFORMATTED);
        layout.addComponent(label, 0, 1, 3, 1);
        layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        layout.addComponent(userLogin, 0, 3, 3, 3);
        layout.setComponentAlignment(userLogin, Alignment.MIDDLE_LEFT);
        Label label5 = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(label5, 0, 4, 3, 4);

        layout.addComponent(passwordField, 0, 5, 3, 5);
        layout.setComponentAlignment(passwordField, Alignment.MIDDLE_LEFT);


        Button butonPasswortvergessen = new Button("Passwort vergessen?");
        butonPasswortvergessen.addStyleName(ValoTheme.BUTTON_LINK);
        butonPasswortvergessen.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.PASSWORTVERGESSEN));
        layout.addComponent(butonPasswortvergessen, 2, 6, 3, 6);
        layout.setComponentAlignment(butonPasswortvergessen, Alignment.MIDDLE_RIGHT);
        userLogin.setWidth("400px");
        passwordField.setWidth("400px");


        CheckBox checkbox1 = new CheckBox("Angemeldet bleiben");
        layout.addComponent(checkbox1, 0, 6, 1, 6);
        layout.setComponentAlignment(checkbox1, Alignment.MIDDLE_LEFT);

//Platzhalter
        Label label2 = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(label2, 0, 7, 3, 7);

//Button zum Login + Symbol auf Button

        Button butonLogin = new Button("Anmelden");
        butonLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);


        layout.addComponent(butonLogin, 0, 8, 3, 8);
        layout.setComponentAlignment(butonLogin, Alignment.MIDDLE_CENTER);
        Label label3 = new Label("oder", ContentMode.TEXT);
        layout.addComponent(label3, 0, 9, 3, 9);
        layout.setComponentAlignment(label3, Alignment.MIDDLE_CENTER);
        Button butonLoginMitGoogle = new Button("Mit Google Anmelden");
        layout.addComponent(butonLoginMitGoogle, 0, 10, 3, 10);
        layout.setComponentAlignment(butonLoginMitGoogle, Alignment.MIDDLE_CENTER);


        Label label4 = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(label4, 0, 11, 3, 11);
        panel.setContent(layout);
        panel.setSizeUndefined();


        butonLogin.addClickListener(clickEvent -> {
            String login = userLogin.getValue();
            String password = passwordField.getValue();

            try {
                LoginControl.checkAuthentification(login, password);
            } catch (NoSuchUserOrPassword ex) {
                Notification.show("Fehler", "Login oder Passwort falsch", Notification.Type.ERROR_MESSAGE);
                userLogin.setValue("");
                passwordField.setValue("");
            } catch (DatabaseException ex) {
                Notification.show("DB-Fehler", ex.getReason(), Notification.Type.ERROR_MESSAGE);
                userLogin.setValue("");
                passwordField.setValue("");
            }
        });


        mainGrid.addComponent(panel, 3, 2, 4, 4);
        this.addComponent(new TopPanelStartSeite());
        this.addComponent(mainGrid);
        this.setComponentAlignment(mainGrid, Alignment.MIDDLE_CENTER);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
        if (user != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);

        } else {
            this.setUp();
        }
    }
}