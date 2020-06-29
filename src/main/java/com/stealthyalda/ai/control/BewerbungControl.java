package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.BewerbungDAO;
import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Student;

public class BewerbungControl {
    public void createbewerbung(StellenanzeigeDTO a, BewerbungCollAtHBRSDTO b, Student s) {
        BewerbungDAO.getInstance().createBewerbung(a, b, s);
    }

    public void updatestatus(BewerbungCollAtHBRSDTO bewerbung) {
        BewerbungDAO.getInstance().updateStatusBewerbung(bewerbung);
    }
}
