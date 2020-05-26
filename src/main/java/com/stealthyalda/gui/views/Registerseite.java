package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.RegisterControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.db.JDBCConnection;
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
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO - Add "Vorname", "Nachname", "Telefonnummer" to the fields
// TODO - Validate user input on client side (*valid* email address)
// TODO - Alternative registeation für "Unternehmen"
public class Registerseite extends VerticalLayout implements View {
    public void setUp(){

        this.setSizeFull();

        final TextField userRegister = new TextField();
        userRegister.setCaption("UserID:");
        final PasswordField passwordRegister = new PasswordField();
        passwordRegister.setCaption("Passwort:");

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        FileResource resource = new FileResource(new File(basepath +
                "/Image/stealthyalda.png"));
        Image Logo = new Image("", resource);
        System.out.println(basepath + "/Image/stealthyalda.png");

        FileResource resource3 = new FileResource(new File(basepath +
                "/Image/background_home.jpg"));

        Image background = new Image("", resource3);

        Link link = new Link("Arbeitgeber", new ExternalResource(""));

//      Erstellung des Layouts

        GridLayout RegisterGrid = new GridLayout(6, 6);
        RegisterGrid.setSizeFull();

        //Panel panel2 = new Panel( "Stealthy_Alda");
        //panel2.addStyleName("login");

        Panel panel = new Panel( "Erstellen sie ihr Stealthy_Alda Konto: ");
        panel.addStyleName("login");

        GridLayout layout = new GridLayout(4, 11);

        layout.addComponent(Logo,0,0);
        layout.setComponentAlignment(Logo, Alignment.MIDDLE_CENTER);
        layout.addComponent(link,3,0);
        layout.setComponentAlignment(link, Alignment.MIDDLE_CENTER);
        layout.addComponent(userRegister,0,3,3,3);
        layout.setComponentAlignment(userRegister, Alignment.MIDDLE_CENTER);
        Label label1 = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label1,0,4,3,4);
        layout.addComponent(passwordRegister,0,5,3,5);
        layout.setComponentAlignment(passwordRegister, Alignment.MIDDLE_CENTER);
        Label label2 = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label2,0,6,3,6);
        Label label = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label,0,2,3,2);

        Label label4 = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label4,0,10,3,10);

        userRegister.setWidth("500px");
        passwordRegister.setWidth("500px");

//Button zum Registrieren

        Button butonRegister = new Button("Registrieren", FontAwesome.SIGN_IN);
        butonRegister.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        layout.addComponent(butonRegister,0,7,3,7);
        layout.setComponentAlignment(butonRegister, Alignment.BOTTOM_CENTER);
        panel.setContent(layout);
        panel.setSizeUndefined();

        butonRegister.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String register = userRegister.getValue();
                String password = passwordRegister.getValue();
                boolean allChecksOkay = false;
                try {
                    RegisterControl.checkUserExists(register);
                    allChecksOkay = true;
                } catch (UserExistsException | DatabaseException ex) {
                    Notification.show("Fehler", "Registrierung konnte nicht abgeschlossen werden", Notification.Type.ERROR_MESSAGE);

                    Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, "Failed on : " + ex);
                    userRegister.setValue("");
                    passwordRegister.setValue("");
                }
                if(allChecksOkay == true) {
                    try {
                        RegisterControl.registerUser(register,password);
                    } catch (DatabaseException ex) {
                        // TODO update to actually get the reason
                        Notification.show("Fehler", "Registrierung konnte nicht abgeschlossen werden" + ex.getReason(), Notification.Type.ERROR_MESSAGE);
                        // TODO CSS update for success messages
                        Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, "Failed on : " + ex);
                        userRegister.setValue("");
                        passwordRegister.setValue("");

                    }

                }
                Notification.show("Success", "Registrierung abgeschlossen!", Notification.Type.HUMANIZED_MESSAGE);
            }
        });


        RegisterGrid.addComponent(panel,3,2,4,4);

        this.addComponent(RegisterGrid);
        this.setComponentAlignment(RegisterGrid,Alignment.MIDDLE_CENTER);
    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        this.setUp();

    }
}