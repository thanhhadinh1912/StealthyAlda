package com.stealthyalda.gui.views;

import com.stealthyalda.services.util.Roles;
import com.stealthyalda.services.util.Views;
import com.vaadin.ui.UI;

import java.util.Objects;

public class Unternehmen extends LoggedinSeiten {
    /**
     * Allow only Arbeitgeber to see these pages
     */
    public Unternehmen() {
        if (!Objects.equals(user.getRole(), Roles.ARBEITGEBER)) {
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        }
    }
}
