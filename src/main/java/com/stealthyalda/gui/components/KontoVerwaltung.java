package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.KontoControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.windows.ConfirmationWindow;
import com.stealthyalda.gui.windows.KontoDeleteWindow;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

public class KontoVerwaltung extends VerticalLayout {
    private final String w = "300px";
    public KontoVerwaltung(Benutzer user){
        KontoControl kc = new KontoControl();

        VerticalLayout top = new VerticalLayout();
        top.setWidth("1000px");
        top.setHeight("150px");
        Label label = new Label("<b> Kontoverwaltung </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        top.addComponent(label);

        Label label2 = new Label("Sie können hier Ihr Konto löschen oder Ihr Passwort ändern.");
        top.addComponent(label2);

        this.addComponent(top);
        this.setComponentAlignment(top, Alignment.TOP_CENTER);

        HorizontalLayout main = new HorizontalLayout();
        main.setWidth("1000px");
        Label loschen = new Label("Konto löschen");

        VerticalLayout deletekonto = new VerticalLayout();

        deletekonto.addComponent(loschen);

        TextField mail = new TextField();
        mail.setPlaceholder("E-Mail Adresse");
        mail.setValue(user.getEmail());
        mail.setWidth(w);
        deletekonto.addComponent(mail);

        PasswordField passwort = new PasswordField();
        passwort.setPlaceholder("Passwort");
        passwort.setWidth(w);
        deletekonto.addComponent(passwort);


        Button delete = new Button("Löschen");
        delete.addClickListener(clickEvent -> {
            KontoDeleteWindow deleteWindow = new KontoDeleteWindow(mail.getValue(), passwort.getValue());
            UI.getCurrent().addWindow(deleteWindow);
        });
        deletekonto.addComponent(delete);
        
        VerticalLayout changepassword = new VerticalLayout();
        Label change = new Label("Passwort ändern");

        changepassword.addComponent(change);

        final TextField mail2 = new TextField();
        mail2.setPlaceholder("E-Mail Adresse");
        mail2.setValue(user.getEmail());
        mail2.setWidth(w);
        changepassword.addComponent(mail2);

        final PasswordField alt = new PasswordField();
        alt.setPlaceholder("Altes Passwort");
        alt.setWidth(w);
        changepassword.addComponent(alt);

        final PasswordField neu = new PasswordField();
        neu.setPlaceholder("Neues Passwort");
        neu.setWidth(w);
        changepassword.addComponent(neu);

        final Button andern = new Button("Ändern");
        changepassword.addComponent(andern);
        andern.addClickListener(clickEvent -> {
            String email = mail2.getValue();
            String altpasswort = alt.getValue();
            String neupasswort = neu.getValue();
            Boolean check = kc.changekonto(email, altpasswort, neupasswort);
            if(check){
                ConfirmationWindow confirm = new ConfirmationWindow("Erfolgreich");
                UI.getCurrent().addWindow(confirm);
            }
                });

        main.addComponent(deletekonto);
        main.setComponentAlignment(deletekonto, Alignment.TOP_LEFT);
        main.addComponent(changepassword);
        main.setComponentAlignment(changepassword, Alignment.TOP_RIGHT);
        this.addComponent(main);
        this.setComponentAlignment(main, Alignment.TOP_CENTER);


    }
}
