/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.UnternehmenDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.db.JDBCConnection;
import com.vaadin.ui.UI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author WINDOWS
 */
public class ArbeitgeberDAO extends AbstractDAO {
    private static ArbeitgeberDAO dao = null;

    private final Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

    private ArbeitgeberDAO() {

    }

    public static ArbeitgeberDAO getInstance() {
        if (dao == null) {
            dao = new ArbeitgeberDAO();
        }
        return dao;
    }

    public void insertArbeitgeber(UnternehmenDTO unternehmen) {
        int userid = user.getId();
        newArbeitgeber(unternehmen, userid);
    }

    private void newArbeitgeber(UnternehmenDTO u, int userId) {
        String newArbeitgeber = "INSERT INTO stealthyalda.arbeitgeber(unternehmen,benutzer_id) VALUES(?,?);";
        PreparedStatement stmt = this.getPreparedStatement(newArbeitgeber);
        try {
            stmt.setString(1, u.getUnternehmen());
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public Arbeitgeber getArbeitgeber(int benutzerid) {
        ResultSet set = null;
        String arbeitgeberQuery = "SELECT * "
                + "FROM stealthyalda.arbeitgeber "
                + "WHERE stealthyalda.arbeitgeber.benutzer_id = ?";
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(arbeitgeberQuery)) {
            statement.setInt(1, benutzerid);
            set = statement.executeQuery();

            if (set.next()) {
                Arbeitgeber a = new Arbeitgeber();
                a.setArbeitgeberId(set.getInt(1));
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

    public Arbeitgeber getArbeitgeber(String email) {
        ResultSet set = null;
        String arbeitgeberQuery = "SELECT * \n" +
                "FROM stealthyalda.arbeitgeber a\n" +
                "JOIN stealthyalda.benutzer b ON  a.benutzer_id = b.benutzer_id\n" +
                "WHERE b.email = ?;";
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(arbeitgeberQuery)) {
            statement.setString(1, email);
            set = statement.executeQuery();

            if (set.next()) {
                Arbeitgeber a = new Arbeitgeber();
                a.setArbeitgeberId(set.getInt(1));
                a.setUnternehmen(set.getString(2));
                a.setId(set.getInt(3));
                //a.setLogo(set.getByte(4));
                a.setBeschreibung(set.getString(5));
                a.setTelefonnummer(set.getString(7));
                a.setAnrede(set.getString(8));
                a.setEmail(email);
                a.setRole(set.getString(11));
                return a;
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return null;
    }

    public boolean updateArbeitgeber(UnternehmenDTO unternehmen) {
        String sqlArbeitgeber = "UPDATE stealthyalda.arbeitgeber " +
                "SET unternehmen = ? " +
                ", beschreibung = ? " +
                "WHERE arbeitgeber_id = ?";
        try (PreparedStatement stmt = this.getPreparedStatement(sqlArbeitgeber)) {
            stmt.setString(1, unternehmen.getUnternehmen());
            stmt.setString(2, unternehmen.getBeschreibung());
            stmt.setInt(3, unternehmen.getArbeitgeberId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

}

    



