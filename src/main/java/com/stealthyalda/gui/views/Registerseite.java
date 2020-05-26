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
// TODO - Alternative registeration for "Unternehmen"
public class Registerseite extends VerticalLayout implements View {
    public void setUp(){

        this.setSizeFull();

        final TextField userRegister = new TextField();
        userRegister.setCaption("UserID");

        final PasswordField passwordRegister = new PasswordField();
        passwordRegister.setCaption("Passwort");

        final TextField vornameRegister = new TextField();
        vornameRegister.setCaption("Vorname");

        final TextField nachnameRegister = new TextField();
        nachnameRegister.setCaption("Nachname");

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        FileResource resource = new FileResource(new File(basepath +
                "/Image/stealthyalda.png"));
        Image Logo = new Image("", resource);
        System.out.println(basepath + "/Image/stealthyalda.png");

        FileResource resource3 = new FileResource(new File(basepath +
                "/Image/background_home.jpg"));

        Image background = new Image("", resource3);

        Link link = new Link("Arbeitgeber", new ExternalResource(""));

        GridLayout RegisterGrid = new GridLayout(6, 6);
        RegisterGrid.setSizeFull();

        GridLayout gridTop = new GridLayout(4, 1);
        gridTop.setSizeFull();

        gridTop.addComponent(Logo,0,0);
        gridTop.setComponentAlignment(Logo, Alignment.TOP_LEFT);

        gridTop.addComponent(link);
        gridTop.setComponentAlignment(link, Alignment.BOTTOM_RIGHT);

        //grid.setWidthFull();
        Panel panel2 = new Panel( "Stealthy_Alda");
        panel2.addStyleName("login");

        panel2.setContent(gridTop);

        //grid bottom
        GridLayout gridBottom = new GridLayout(4, 1);
        gridBottom.setWidth("40%");


//Vertikales Layout + Hinzufügen der Textfelder
        VerticalLayout layout = new VerticalLayout();


        layout.addComponent(userRegister);
        layout.addComponent(passwordRegister);
        layout.addComponent(vornameRegister);
        layout.addComponent(nachnameRegister);
        layout.addComponent(nachnameRegister);

        Label label = new Label ( "&nbsp;", ContentMode.HTML);
        layout.addComponent(label);
//Erstellen und Hinzufügen eines Panels + Platzierung in die Mitte
        Panel panel = new Panel( "Erstellen sie ihr Stealthy_Alda Konto: ");
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

        RegisterGrid.addComponent(panel2,0,0,5,0);
        RegisterGrid.addComponent(panel,3,2,4,4);
        RegisterGrid.addComponent(gridBottom,3,5,4,5);

        this.addComponent(RegisterGrid);
        this.setComponentAlignment(RegisterGrid,Alignment.MIDDLE_CENTER);
    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        this.setUp();

    }
}