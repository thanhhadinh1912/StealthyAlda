/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.HardskillDAO;
import com.stealthyalda.ai.model.dao.HobbyDAO;
import com.stealthyalda.ai.model.dao.SoftskillDAO;
import com.stealthyalda.ai.model.dao.StudentDAO;
import com.stealthyalda.ai.model.entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author WINDOWS
 */
public class ProfilStudentControl {
    public String printHardskill(Benutzer user){
        String print="";
        List<Hardskill> liste = HardskillDAO.getInstance().getHardskillsForUser(user);
        for(int i=0;i<liste.size();i++){
            print += liste.get(i).getHardskill() +"\n";
}
        return print;
    }
    public String printSoftskill(Benutzer user){
        StringBuilder print = new StringBuilder();
        List<Softskill> liste = SoftskillDAO.getInstance().getSoftskillsForUser(user);
        for(int i=0;i<liste.size();i++){
            print.append(liste.get(i).getSoftskill());
            print.append("\n");
}
        return print.toString();
    }
    
    public String printHobby(Benutzer user){
        String print="";
        List<Hobby> liste = HobbyDAO.getInstance().getHobbysForUser(user);
        for(int i=0;i<liste.size();i++){
            print += liste.get(i).getHobby() +"\n";
}
        return print;
    }
    
    public void hardskillchange(Benutzer user, String input) throws DatabaseException{
        List<String> listStr = new ArrayList<>();
        listStr.addAll(Arrays.asList(input.split("\n")));
        List<Hardskill> liste = HardskillDAO.getInstance().getHardskillsForUser(user);
        Student s = StudentDAO.getInstance().getStudent(user.getId());
         for(int i=0;i<liste.size();i++){
            HardskillDAO.getInstance().deleteHardskillForUser(liste.get(i).getHardskill_id(),s);
         }
        
        for(int i=1; i<listStr.size(); i++){
            Hardskill hardskill = new Hardskill(listStr.get(i));
            HardskillDAO.getInstance().createHardskillForUser(hardskill, s);
        }
        
}
    
    
    }

