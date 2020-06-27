package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Stellenanzeige;

public class StellenanzeigeControl {
    public void erstellen(Stellenanzeige s) {
        StellenanzeigeDAO.getInstance().createStellenanzeige(s);
    }

    public StellenanzeigeDTO get(int stellenanzeigeid) {
        return StellenanzeigeDAO.getInstance().getJobangebot(stellenanzeigeid);
    }
}
