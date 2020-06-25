package com.stealthyalda.gui.components;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.views.LoggedinSeiten;

public class ProfilVerwalten extends LoggedinSeiten {
    boolean isAdmin;

    public ProfilVerwalten(Benutzer user) {
        isAdmin = user.getRole().equals("admin");

    }
}
