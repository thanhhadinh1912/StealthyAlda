package com.stealthyalda.gui.views;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.services.util.Roles;
import com.stealthyalda.services.util.Views;
import com.vaadin.navigator.View;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoggedinSeiten extends VerticalLayout implements View {

    protected transient Benutzer user = (Benutzer) UI.getCurrent().getSession().getAttribute(Roles.CURRENTUSER);

    /**
     * Check if we have a logged in user
     *
     * @return boolean
     */
    public boolean isLoggedIn() {
        if (user == null) {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        }
        return true;
    }
}
