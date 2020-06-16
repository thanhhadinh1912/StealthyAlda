/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.HardskillDAO;
import com.stealthyalda.ai.model.dao.HobbyDAO;
import com.stealthyalda.ai.model.dao.SoftskillDAO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Hardskill;
import com.stealthyalda.ai.model.entities.Hobby;
import com.stealthyalda.ai.model.entities.Softskill;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WINDOWS
 */
public class ProfilStudentControl {
    public ProfilStudentControl(){
        
    }
    public String printHardskill(Benutzer user){
        String print="";
        List<Hardskill> liste = new ArrayList<Hardskill>();
        liste = HardskillDAO.getInstance().getHardskillsForUser(user);
        for(int i=0;i<liste.size();i++){
            print += liste.get(i).getHardskill() +"\n";
}
        return print;
    }
    public String printSoftskill(Benutzer user){
        String print="";
        List<Softskill> liste = new ArrayList<Softskill>();
        liste = SoftskillDAO.getInstance().getSoftskillsForUser(user);
        for(int i=0;i<liste.size();i++){
            print += liste.get(i).getSoftskill() +"\n";
}
        return print;
    }
    
    public String printHobby(Benutzer user){
        String print="";
        List<Hobby> liste = new ArrayList<Hobby>();
        liste = HobbyDAO.getInstance().getHobbysForUser(user);
        for(int i=0;i<liste.size();i++){
            print += liste.get(i).getHobby() +"\n";
}
        return print;
    }
}
