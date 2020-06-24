package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.RegisterControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.RegisterseiteMainComponent;
import com.stealthyalda.gui.components.TopPanelStartSeite;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.gui.windows.ConfirmReg;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.Roles;
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
        this.addComponent(new TopPanelStartSeite());
        RadioButtonGroup<String> single = new RadioButtonGroup<>();
        single.setItems(ARBEITGEBER, STUDENT);
        single.setValue(STUDENT);
        single.setItemEnabledProvider(role -> !ARBEITGEBER.equals(role));
        single.setValue(STUDENT);
        single.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        Panel panel = new RegisterseiteMainComponent(single);
        setMargin(true);
        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        this.setUp();

    }
}
