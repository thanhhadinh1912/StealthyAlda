/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.Anforderung;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.vaadin.ui.UI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author WINDOWS
 */
public class AnforderungDAO extends AbstractDAO {
    private static AnforderungDAO dao;

    private AnforderungDAO() {

    }

    public static AnforderungDAO getInstance() {
        if (dao == null) {
            dao = new AnforderungDAO();
        }
        return dao;

    }

    /**
     * @param s Stellenanzeige
     * @return list of Stellenanzeigen
     * @throws DatabaseException if something goes horribly wrong
     */
    public List<Anforderung> getAnforderungForStellenanzeige(Stellenanzeige s) throws DatabaseException {
        Statement statement = this.getStatement();

        ResultSet rs = null;
        List<Anforderung> liste = new ArrayList<>();
        Anforderung anforderung = null;
        try {
            rs = statement.executeQuery("SELECT anforderung FROM stealthyalda.anforderung WHERE stealthyalda.anforderung.stellenanzeige_id = '" + s.getStellenanzeigeID() + "'");

            while (rs.next()) {
                anforderung = new Anforderung();
                anforderung.setAnforderung(rs.getString(1));
                liste.add(anforderung);
            }
        } catch (SQLException throwables) {
            logEntry(this.getClass().getName(), Level.SEVERE, throwables.getMessage());
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        } finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(rs);
        }

        return liste;
    }

    public boolean createAnforderung(int arbeitgeber_id, String anforderung){
        String sql = "insert into stealthyalda.anforderung values(?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);

        //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
        try{
            statement.setInt(1,arbeitgeber_id);
            statement.setString(2, anforderung);
            statement.executeUpdate();
            return true;
        }  catch (SQLException ex){
            Logger.getLogger(AnforderungDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}