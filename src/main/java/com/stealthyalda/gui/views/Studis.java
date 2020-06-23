package com.stealthyalda.gui.views;

import com.stealthyalda.services.util.Roles;
import com.stealthyalda.services.util.Views;
import com.vaadin.ui.UI;

import java.util.Objects;

public class Studis extends LoggedinSeiten {
    /**
     * Check if user is a student
     */
    public Studis() {
        if (!Objects.equals(user.getRole(), Roles.STUDENT)) {
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        }
    }
}
