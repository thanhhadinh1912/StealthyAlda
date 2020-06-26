package com.stealthyalda.ai.control.exceptions;

import com.stealthyalda.ai.model.dao.BewerbungDAO;
import com.stealthyalda.ai.model.entities.Bewerbung;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.ai.model.entities.Student;

public class BewerbungControl {
    public void createbewerbung(Stellenanzeige a, Bewerbung b, Student s){
        BewerbungDAO.getInstance().createBewerbung(a,b,s);
    }
}
