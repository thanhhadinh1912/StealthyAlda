/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Hobby;
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
public class HobbyDAO extends AbstractDAO{
    private static HobbyDAO dao = null;
    private HobbyDAO(){

    }
    public static HobbyDAO getInstance(){
        if(dao==null){
            dao = new HobbyDAO();
        }
        return dao;
    }
    public List<Hobby> getHobbysForUser(Benutzer user){
        Statement statement=this.getStatement();
        List<Hobby> liste = new ArrayList<Hobby>();
        Hobby hobby = null;
        ResultSet rs = null;
        try{
            String sql ="select h.hobby_id, h.hobby\n" +
"from stealthyalda.hobby h\n" +
"inner join stealthyalda.student_hat_hobby sh on sh.hobby_id = h.hobby_id\n" +
"inner join stealthyalda.student s on s.student_id = sh.student_id\n" +
"where s.benutzer_id = '"+user.getId()+"';";
            rs = statement.executeQuery(sql);


            while (rs.next()){
                hobby= new Hobby();
                hobby.setHobby_id(rs.getInt(1));
                hobby.setHobby(rs.getString(2));
                liste.add(hobby);
            }

        }catch (SQLException ex){
            Logger.getLogger(Hobby.class.getName()).log(Level.SEVERE, null, ex);

        }
        finally {
            closeResultset(rs);
        }
        return liste;
    }
}


