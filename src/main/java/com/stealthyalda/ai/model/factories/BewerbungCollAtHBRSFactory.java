package com.stealthyalda.ai.model.factories;

import com.stealthyalda.ai.model.dao.BewerbungDAO;
import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.AnwendungDTOs;
import com.stealthyalda.ai.model.dtos.StepstoneDTO;
import com.stealthyalda.ai.model.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class BewerbungCollAtHBRSFactory implements AnwendungFactory {


    private static BewerbungCollAtHBRSFactory instance = null;
    private List<BewerbungCollAtHBRSDTO> list = new ArrayList<>();

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
    public List<BewerbungCollAtHBRSDTO> getListBewerbungStudent(Student s){
        list = BewerbungDAO.getInstance().getBewerbungFromStudent(s);
        return list;
    }
}
