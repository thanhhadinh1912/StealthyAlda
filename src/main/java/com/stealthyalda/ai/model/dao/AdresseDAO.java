/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.dtos.Adresse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    /**
     * singleton instance
     *
     * @return a static AdresseDAO instance
     */
    public static AdresseDAO getInstance() {
        if (dao == null) {
            dao = new AdresseDAO();
        }
        return dao;
    }

    /**
     * Create a new address in the database
     *
     * @param adr    - Adresse object
     * @param userId - userID of current user
     */
    public void addAdresse(Adresse adr, int userId) {
        String sql = "INSERT INTO stealthyalda.adresse (plz, strasse, ort, haus_nr, benutzer_id) values(?,?,?,?,?);";

        try (PreparedStatement statement = this.getPreparedStatement(sql)) {
            statement.setInt(1, adr.getPlz());
            statement.setString(2, adr.getStrasse());
            statement.setString(3, adr.getOrt());
            statement.setString(4, adr.getHausnummer());
            statement.setInt(5, userId);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Get adress using benuterId
     *
     * @param benutzerId user-id of user
     * @return Adresse object
     */
    public Adresse getAdresse(int benutzerId) {
        String sql = "SELECT * "
                + "FROM stealthyalda.adresse "
                + "WHERE stealthyalda.adresse.benutzer_id = ?";
        ResultSet set = null;
        try (PreparedStatement statement = this.getPreparedStatement(sql)) {
            statement.setInt(1, benutzerId);
            set = statement.executeQuery();
            if (set.next()) {
                Adresse a = new Adresse();
                a.setAdresseID(set.getInt(1));
                a.setPlz(set.getInt(2));
                a.setStrasse(set.getString(3));
                a.setOrt(set.getString(4));
                a.setHausnummer(set.getString(5));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return null;
    }
}
