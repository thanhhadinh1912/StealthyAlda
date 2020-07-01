package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToogleFeatureDAO extends AbstractDAO {
    private static ToogleFeatureDAO me;

    private ToogleFeatureDAO() {
    }

    public static ToogleFeatureDAO getInstance() {
        if (me == null) {
            me = new ToogleFeatureDAO();
        }
        return me;
    }

    public boolean checkFeature(String feature) throws DatabaseException {
        ResultSet set = null;
        boolean enabledOrNot = false;
        try (PreparedStatement ps = this.getPreparedStatement("SELECT status " +
                "FROM stealthyalda.toogle " +
                "WHERE stealthyalda.toogle.feature = ?")) {

            ps.setString(1, feature);
            set = ps.executeQuery();
            if (set.next()) {
                enabledOrNot = set.getBoolean(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ToogleFeatureDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new DatabaseException("Fehler beim Auslesen eines Toggle-Features aus der Datenbank! Bitte Programmierer informieren.");
        } finally {
            closeResultset(set);
            JDBCConnection.getInstance().closeConnection();
        }
        return enabledOrNot;
    }
}
