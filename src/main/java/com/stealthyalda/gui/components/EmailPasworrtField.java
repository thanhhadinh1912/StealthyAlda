package com.stealthyalda.gui.components;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class EmailPasworrtField extends HorizontalLayout {
    public EmailPasworrtField(Benutzer user) {
        this.setWidth("500px");
        final TextField email = new TextField();
        email.setWidth("250px");
        email.setValue(user.getEmail());
        email.setReadOnly(true);
        this.addComponent(email);

        final PasswordField passwort = new PasswordField();
        passwort.setValue(user.getPasswort());
        passwort.setWidth("230px");
        passwort.setReadOnly(true);
        this.addComponent(passwort);
        this.setComponentAlignment(passwort, Alignment.MIDDLE_RIGHT);
    }
}
