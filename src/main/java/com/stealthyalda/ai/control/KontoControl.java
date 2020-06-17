package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.BenutzerDAO;

public class KontoControl {
    public boolean deletekonto(String email, String password) throws DatabaseException {
        BenutzerDAO.getInstance().deleteUser(email,password);
        return true;
    }

    public boolean changekonto(String email, String altpassword, String neupassword){
        Boolean check = false;
        try {
            check = BenutzerDAO.getInstance().changepassword(email, altpassword, neupassword);
        } catch (DatabaseException e) {
            e.printStackTrace();
            return false;
        }
        return check;
    }
}
