/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.vaadin.ui.UI;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author WINDOWS
 */
public class AdresseDAO extends AbstractDAO {
    private static AdresseDAO dao = null;

    private AdresseDAO() {

    }

    public static AdresseDAO getInstance() {
        if (dao == null) {
            dao = new AdresseDAO();
        }
        return dao;
    }

    public void createAdresse(String strasse, int plz, String hausnummer, String ort) throws DatabaseException {
        String sql = "INSERT INTO stealthyalda.adresse (plz, strasse, ort, haus_nr, benutzer_id) values(?,?,?,?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);
        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
        int userid = user.getId();

        //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
        try {
            statement.setInt(1, plz);
            statement.setString(2, strasse);
            statement.setString(3, ort);
            statement.setString(4, hausnummer);
            statement.setInt(5, userid);
            statement.executeUpdate();

            //Nachtragliches Setzen der BuchungsID


        } catch (SQLException ex) {
            Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }


}
