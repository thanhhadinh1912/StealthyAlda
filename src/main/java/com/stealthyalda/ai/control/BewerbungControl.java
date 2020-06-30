package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.BewerbungDAO;
import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Student;

public class BewerbungControl {
    /**
     * create a new application
     *
     * @param a StellenanzeigeDTO object
     * @param b BewerbugCollAtHBRSDTO object
     * @param s Student object
     */
    public void createbewerbung(StellenanzeigeDTO a, BewerbungCollAtHBRSDTO b, Student s) {
        BewerbungDAO.getInstance().createBewerbung(a, b, s);
    }

    /**
     * Update the status of a job application
     *
     * @param bewerbung which job application
     */
    public void updatestatus(BewerbungCollAtHBRSDTO bewerbung) {
        BewerbungDAO.getInstance().updateStatusBewerbung(bewerbung);
    }
}
