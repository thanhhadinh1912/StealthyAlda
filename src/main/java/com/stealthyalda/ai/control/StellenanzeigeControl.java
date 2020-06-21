package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StellenanzeigeControl {
    public void erstellen(Stellenanzeige s){
        try {
            StellenanzeigeDAO.getInstance().createStellenanzeige(s);
        } catch (DatabaseException ex) {
            Logger.getLogger(StellenanzeigeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
