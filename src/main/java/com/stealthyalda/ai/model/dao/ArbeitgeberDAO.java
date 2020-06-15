/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
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
public class ArbeitgeberDAO extends AbstractDAO {
    private static ArbeitgeberDAO dao = null;

    private ArbeitgeberDAO() {

    }

    public static ArbeitgeberDAO getInstance() {
        if (dao == null) {
            dao = new ArbeitgeberDAO();
        }
        return dao;
    }

    public boolean createArbeitgeber(String anrede, String unternehmen, String strasse, int plz, String ort, String hausnummer, String telefonnumer) throws DatabaseException {
        String sql = "insert into stealthyalda.benutzer(email, passwort, role, anrede, telefonnummer) values(?,?,?,?,?);" + "insert into stealthyalda.arbeitgeber(unternehmen,benutzer_id) values(?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);
        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
        int userid = user.getId();

        //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
        try {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPasswort());
            statement.setString(3, user.getRole());
            statement.setString(4, anrede);
            statement.setString(5, telefonnumer);

            statement.setString(6, unternehmen);
            statement.setInt(7, userid);
            statement.executeUpdate();
            AdresseDAO.getInstance().createAdresse(strasse, plz, hausnummer, ort);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, ex.getMessage());
            return false;
        }
    }

    private int arbeitgeberID() throws SQLException {
        Statement statement = this.getStatement();
        ResultSet rs = null;
        int currentValue = 0;

        try {
            rs = statement.executeQuery("SELECT MAX(stealthyalda.arbeitgeber.arbeitgeber_id) FROM stealthyalda.arbeitgeber");
        } catch (SQLException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs != null) {
            try {
                rs.next();
                currentValue = rs.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                rs.close();
            }
        }

        return currentValue;
    }

    public Arbeitgeber getArbeitgeber(int benutzerid) {
        ResultSet set = null;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT * "
                    + "FROM stealthyalda.arbeitgeber "
                    + "WHERE stealthyalda.arbeitgeber.benutzer_id = '" + benutzerid + "'");

            if (set.next()) {
                Arbeitgeber a = new Arbeitgeber();
                a.setArbeitgeber_id(set.getInt(1));
                a.setUnternehmen(set.getString(2));
                a.setId(benutzerid);
                a.setLogo(set.getByte(4));
                a.setBeschreibung(set.getString(5));
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

    



