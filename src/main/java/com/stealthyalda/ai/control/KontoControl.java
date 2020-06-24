package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.BenutzerDAO;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KontoControl {

    public void deletekonto(String email, String password) throws DatabaseException {
        BenutzerDAO.getInstance().deleteUser(email, password);

    }

    public boolean changekonto(String email, String altpassword, String neupassword) {
        Boolean check = false;
        try {
            check = BenutzerDAO.getInstance().changepassword(email, altpassword, neupassword);
        } catch (DatabaseException e) {
            Logger.getLogger(KontoControl.class.getName()).log(Level.SEVERE, e.getReason(), e);
            return false;
        }
        return check;
    }
}
