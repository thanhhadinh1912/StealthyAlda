package com.stealthyalda.ai.model.factories;

import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.AnwendungDTOs;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.ai.model.entities.Student;

public class BewerbungCollAtHBRSFactory implements AnwendungFactory {


    private static BewerbungCollAtHBRSFactory instance = null;

    public BewerbungCollAtHBRSFactory() {
    }

    public static BewerbungCollAtHBRSFactory getInstance() {
        if (instance == null) {
            instance = new BewerbungCollAtHBRSFactory();
        }
        return instance;
    }


    @Override
    public AnwendungDTOs create() {
        BewerbungCollAtHBRSDTO bewerbung = new BewerbungCollAtHBRSDTO();
        return bewerbung;
    }

}
