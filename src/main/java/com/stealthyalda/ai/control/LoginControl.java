package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.model.dao.BenutzerDAO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Views;
import com.vaadin.ui.UI;

public class LoginControl {


    private LoginControl() {
    }

    public static void checkAuthentification(String email, String password) throws NoSuchUserOrPassword, DatabaseException {

        Benutzer benutzer = BenutzerDAO.getBenutzer(email, password);


        ((MyUI) UI.getCurrent()).setBenutzer(benutzer);

        // Der Benutzer ist vorhanden

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);


    }

    public static void logoutUser() {
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/");
    }
}

