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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author WINDOWS
 */
public class AdresseDAO extends AbstractDAO {
    public static AdresseDAO dao = null;

    private AdresseDAO() {

    }

    public static AdresseDAO getInstance() {
        if (dao == null) {
            dao = new AdresseDAO();
        }
        return dao;
    }

    public boolean createAdresse(String strasse, int plz, String hausnummer, String ort) throws DatabaseException {
        String sql = "insert into stealthyalda.stellenanzeige values(?,?,?,?,?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);

        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
        int userid = user.getId();

        //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
        try {
            statement.setInt(1, adresseID());
            statement.setInt(2, plz);
            statement.setString(3, strasse);
            statement.setString(4, ort);
            statement.setString(5, hausnummer);
            statement.setInt(6, userid);
            statement.executeUpdate();

            //Nachtragliches Setzen der BuchungsID

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private int adresseID() throws SQLException {
        Statement statement = this.getStatement();
        ResultSet rs = null;
        int currentValue = 0;

        try {
            rs = statement.executeQuery("SELECT max(stealthyalda.adresse.adresse_id) FROM stealthyalda.adresse");
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        if (rs != null) {
            try {
                rs.next();
                currentValue = rs.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(rs);
            }
        }
        return currentValue;
    }


}
