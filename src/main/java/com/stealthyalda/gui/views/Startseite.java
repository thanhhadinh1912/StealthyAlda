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
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.io.File;



public class Startseite extends VerticalLayout implements View {

    public void setUp(){

//Gesamtgröße des Bildschirms auf komplette Größe beziehen
        this.setSizeFull();


//Textfeld Login
        final TextField userLogin = new TextField();
        userLogin.setCaption("Email");

//Textfelt Passwort
        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort");

// add image
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        FileResource resource = new FileResource(new File(basepath +
                "/Image/Logo3.png"));
        Image Logo = new Image("", resource);
        System.out.println(basepath + "/Image/Logo3.png");

        FileResource resource3 = new FileResource(new File(basepath +
                "/Image/BImage.jpg"));


        GridLayout Maingrid = new GridLayout(6, 6);
        Maingrid.setSizeFull();

        GridLayout gridTop = new GridLayout(4, 1);
        gridTop.setSizeFull();



        Button butonReg = new Button("Registrieren", FontAwesome.SIGN_IN);

        gridTop.addComponent(butonReg,3,0);

        gridTop.addComponent(Logo,0,0);


        gridTop.setComponentAlignment(butonReg, Alignment.BOTTOM_RIGHT);


        gridTop.setComponentAlignment(Logo, Alignment.TOP_LEFT);

        //grid.setWidthFull();
        Panel panel2 = new Panel( "Stealthy_Alda");
        panel2.addStyleName("login");
        panel2.setContent(gridTop);

        //grid bottom
        GridLayout gridBottom = new GridLayout(4, 1);
        gridBottom.setWidth("40%");

        Link link = new Link("Stealthy_Alda",
                new ExternalResource("http://vaadin.com/"));

        Link link1 = new Link("© 2020",
                new ExternalResource("http://vaadin.com/"));

        Link link2 = new Link("Info",
                new ExternalResource("http://vaadin.com/"));

        Link link3 = new Link("Hilfe",
                new ExternalResource("http://vaadin.com/"));

        Link link4 = new Link("Passwort vergessen?",
                new ExternalResource("http://vaadin.com/"));


        gridBottom.addComponent(link,0,0);
        gridBottom.addComponent(link1,1,0);
        gridBottom.addComponent(link2,2,0);
        gridBottom.addComponent(link3,3,0);


//Vertikales Layout + Hinzufügen der Textfelder
        VerticalLayout layout = new VerticalLayout();


        layout.addComponent(userLogin);
        layout.addComponent(passwordField);

//Platzhalter
        Label label = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label);
//Erstellen und Hinzufügen eines Panels + Platzierung in die Mitte
        Panel panel = new Panel( "Willkommen Zurück: ");
        panel.addStyleName("login");

//Button zum Login + Symbol auf Button

        Button butonLogin = new Button("Anmelden", FontAwesome.SEARCH);
        butonLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        layout.addComponent(butonLogin);
        layout.setComponentAlignment(butonLogin, Alignment.MIDDLE_CENTER);
        layout.addComponent(link4);
        layout.setComponentAlignment(link4, Alignment.MIDDLE_CENTER);
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

        Maingrid.addComponent(panel2,0,0,5,0);
        Maingrid.addComponent(panel,3,2,4,4);
        Maingrid.addComponent(gridBottom,3,5,4,5);

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
