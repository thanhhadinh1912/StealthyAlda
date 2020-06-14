/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.Anforderung;
import com.stealthyalda.ai.model.dtos.Role;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.services.db.JDBCConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WINDOWS
 */
public class AnforderungDAO extends AbstractDAO{
    public static AnforderungDAO dao;

    private AnforderungDAO() {

    }

    public static AnforderungDAO getInstance() {
        if (dao == null) {
            dao = new AnforderungDAO();
        }
        return dao;
        
    }
    
        
    public List<Anforderung> getAnforderungForStellenanzeige(Stellenanzeige s) throws DatabaseException{
       Statement statement=this.getStatement();

        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT anforderung FROM stealthyalda.anforderung WHERE stealthyalda.anforderung.stellenanzeige_id = '" + s.getStellenanzeigeID() + "'");
                   }
       catch (SQLException throwables){
            throwables.printStackTrace();
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }
        if(rs==null) return null;
        List<Anforderung> liste = new ArrayList<Anforderung>();
        Anforderung anforderung = null;
        try {
            while (rs.next()){
                anforderung = new Anforderung();
                anforderung.setAnforderung(rs.getString(1));
                liste.add(anforderung);
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }
        return liste;
    }
    
}