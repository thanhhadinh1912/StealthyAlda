package com.stealthyalda.ai.model.dao;


import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.model.dtos.Role;
import com.stealthyalda.ai.model.entities.Benutzer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDAO extends  AbstractDAO{

    public static RoleDAO dao = null;

    private RoleDAO(){

    }

    public static RoleDAO getInstance(){
        if(dao == null){
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<Role> getRolesForUser(Benutzer user){
        Statement statement = this.getStatement();

        ResultSet rs = null;

        //rs = statement.executeQuery("SELECT role FROM  hoteluser.user_to_role WHERE hoteluser.user_to_role.login = \'"+user.getLogin()+"\' ");

        if(rs == null){
            return null;
        }

        List<Role> liste = new ArrayList<Role>();
        Role role = null;

        try {
            while(rs.next()){
               role = new Role();
               role.setBezeichnung(rs.getString(1));
               liste.add(role);
            }
        }catch(SQLException ex){
             Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }


        return liste;
    }
}
