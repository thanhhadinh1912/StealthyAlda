/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.Anforderung;
import com.stealthyalda.ai.model.entities.Stellenanzeige;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
     *
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

}