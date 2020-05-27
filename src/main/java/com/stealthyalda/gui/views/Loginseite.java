package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Views;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;



public class Loginseite extends VerticalLayout implements View {

    public void setUp(){

//Gesamtgröße des Bildschirms auf komplette Größe beziehen
        this.setSizeFull();


//Textfeld Login
        final TextField userLogin = new TextField();
        userLogin.setCaption("Email: ");


//Textfelt Passwort
        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort: ");


// add image
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        FileResource resource = new FileResource(new File(basepath +
                "/Image/stealthyalda.png"));
        Image Logo = new Image("", resource);
        System.out.println(basepath + "/Image/stealthyalda.png");

        FileResource resource3 = new FileResource(new File(basepath +
                "/Image/background_home.jpg"));


        GridLayout Maingrid = new GridLayout(6, 6);
        Maingrid.setSizeFull();


        Panel panel = new Panel( "Stealthy_Alda");
        panel.addStyleName("login");
//Vertikales Layout + Hinzufügen der Textfelder
        GridLayout layout = new GridLayout(4, 11);
        Link link5 = new Link("Anmelden",
                new ExternalResource("http://localhost:8080/#!login"));
        Button butonReg = new Button("Registrieren");
        butonReg.addStyleName(ValoTheme.BUTTON_LINK);
        butonReg.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.REGISTER);
            }
        });
        layout.addComponent(link5,0,0);
        layout.setComponentAlignment(link5, Alignment.MIDDLE_CENTER);
        layout.addComponent(Logo,1,0,2,0);
        layout.setComponentAlignment(Logo, Alignment.MIDDLE_CENTER);
        layout.addComponent(butonReg,3,0);
        layout.setComponentAlignment(butonReg, Alignment.MIDDLE_CENTER);
        Label label = new Label(    "Willkommen zurück!",  ContentMode.PREFORMATTED);
        layout.addComponent(label, 0, 1,3,1);
        layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        Label label1 = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label1,0,2,3,2);

        layout.addComponent(userLogin,0,3,3,3);
        layout.setComponentAlignment(userLogin, Alignment.MIDDLE_LEFT);
        layout.addComponent(passwordField,0,4,3,4);
        layout.setComponentAlignment(passwordField, Alignment.MIDDLE_LEFT);

        Button butonPasswortvergessen = new Button("Passwort vergessen?");
        butonPasswortvergessen.addStyleName(ValoTheme.BUTTON_LINK);
        butonPasswortvergessen.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.PASSWORTVERGESSEN);
            }
        });
        layout.addComponent(butonPasswortvergessen,2,5,3,5);
        layout.setComponentAlignment(butonPasswortvergessen, Alignment.MIDDLE_RIGHT);
        userLogin.setWidth("500px");
        passwordField.setWidth("500px");


        CheckBox checkbox1 = new CheckBox("Angemeldet bleiben");
        layout.addComponent(checkbox1, 0, 5, 1, 5);
        layout.setComponentAlignment(checkbox1, Alignment.MIDDLE_LEFT);

//Platzhalter
        Label label2 = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label2,0,6,3,6);

//Button zum Login + Symbol auf Button

        Button butonLogin = new Button("Anmelden");
        butonLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);


        layout.addComponent(butonLogin,0,7,3,7);
        layout.setComponentAlignment(butonLogin, Alignment.MIDDLE_CENTER);
        Label label3= new Label("oder",  ContentMode.TEXT);
        layout.addComponent(label3, 0, 8,3,8);
        layout.setComponentAlignment(label3, Alignment.MIDDLE_CENTER);
        Button butonLoginMitGoogle = new Button("Mit Google Anmelden");
        layout.addComponent(butonLoginMitGoogle, 0,9,3,9);
        layout.setComponentAlignment(butonLoginMitGoogle, Alignment.MIDDLE_CENTER);

//       butonLoginMitGoogle.addClickListener(null);
        Label label4 = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label4,0,10,3,10);
        panel.setContent(layout);
        panel.setSizeUndefined();


        butonLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
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
            }
        });


        Maingrid.addComponent(panel,3,2,4,4);

        this.addComponent(Maingrid);
        this.setComponentAlignment(Maingrid,Alignment.MIDDLE_CENTER);
    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

//        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);

        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
        if( user != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        } else {
            this.setUp();
        }
    }
}