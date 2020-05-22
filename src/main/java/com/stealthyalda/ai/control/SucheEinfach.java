package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.entities.Stellenanzeige;

import java.util.ArrayList;
import java.util.List;

public class SucheEinfach {
    private SucheEinfach(){

    }
    public  static SucheEinfach search = null;
    public static SucheEinfach getInstance(){
        if(search == null){
            search = new SucheEinfach();
        }
        return search;
    }
    public List<Stellenanzeige> getStellenanzeigeByOrt(String ort){
        //Datenbank-Zugriff
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByLocation(ort);
    }
}