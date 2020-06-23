package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.control.KontoControl;
import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KontoDeleteWindow extends Window {

    public KontoDeleteWindow(String mail, String password) {
        super(); //Set Window caption
        center();

        //Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        Label top = new Label("Sind Sie sicher, dass Sie Ihr Konto löschen wollen?");
        content.addComponent(top);
        content.setComponentAlignment(top, Alignment.TOP_CENTER);

        Label label = new Label("Schade das Sie nicht mehr ein Teil von StealtyAlda sein möchten.");
        content.addComponent(label);

        HorizontalLayout janein = new HorizontalLayout();
        janein.setWidth("600px");

        Button nein = new Button("Nein");
        janein.addComponent(nein);
        janein.setComponentAlignment(nein, Alignment.MIDDLE_LEFT);

        Button ja = new Button("Ja");
        janein.addComponent(ja);
        janein.setComponentAlignment(ja, Alignment.MIDDLE_RIGHT);

        Label platz = new Label("&nbsp", ContentMode.HTML);
        content.addComponent(platz);

        content.addComponent(janein);
        content.setComponentAlignment(janein, Alignment.MIDDLE_CENTER);

        content.setMargin(true);
        setContent(content);

        ja.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        nein.addClickListener(clickEvent -> {
            close();
        });
        ja.addClickListener(clickEvent -> {
            KontoControl kc = new KontoControl();
            try {
                kc.deletekonto(mail, password);
                LoginControl.logoutUser();
                close();
            } catch (DatabaseException ex) {
                Logger.getLogger(KontoDeleteWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }
}

