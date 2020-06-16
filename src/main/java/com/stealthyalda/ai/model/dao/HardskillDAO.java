/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Hardskill;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WINDOWS
 */
public class HardskillDAO extends AbstractDAO {
     public static HardskillDAO dao = null;
    private HardskillDAO(){

    }
    public static HardskillDAO getInstance(){
        if(dao==null){
            dao = new HardskillDAO();
        }
        return dao;
    }
    public List<Hardskill> getHardskillsForUser(Benutzer user){
        Statement statement=this.getStatement();

        ResultSet rs = null;
        try{
            String sql ="select h.hardskill_id, h.hardskill\n" +
"from stealthyalda.hardskill h\n" +
"inner join  stealthyalda.student_hat_hardskill sh on sh.hardskill_id = h.hardskill_id\n" +
"inner join stealthyalda.student s on s.student_id = sh.student_id\n" +
"where s.benutzer_id = '"+user.getId()+"';";
            rs = statement.executeQuery(sql);
            }
        catch (SQLException ex){
            Logger.getLogger(HardskillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rs==null) return null;
        List<Hardskill> liste = new ArrayList<Hardskill>();
        Hardskill hardskill = null;
        try {
            while (rs.next()){
                hardskill= new Hardskill();
                hardskill.setHardskill_id(rs.getInt(1));
                hardskill.setHardskill(rs.getString(2));
                liste.add(hardskill);
            }

        }catch (SQLException ex){
            Logger.getLogger(Hardskill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
}


