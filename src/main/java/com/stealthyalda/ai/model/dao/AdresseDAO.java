/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.Adresse;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.db.JDBCConnection;
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
    public Adresse getAdresse(int benutzerid) {
        ResultSet set = null;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT * "
                    + "FROM stealthyalda.adresse "
                    + "WHERE stealthyalda.adresse.benutzer_id = '" + benutzerid + "'");

            if (set.next()) {
                Adresse a = new Adresse();
                a.setAdresseID(set.getInt(1));
                a.setPlz(set.getInt(2));
                a.setStrasse(set.getString(3));
                a.setOrt(set.getString(4));
                a.setHausnummer(set.getString(5));
                return a;
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }
        return null;
    }


}
