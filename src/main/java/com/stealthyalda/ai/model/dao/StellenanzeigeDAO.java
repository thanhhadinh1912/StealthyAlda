package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.db.JDBCConnection;
import com.vaadin.ui.UI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StellenanzeigeDAO extends AbstractDAO {

    private static StellenanzeigeDAO dao = null;


    public static StellenanzeigeDAO getInstance() {
        if (dao == null) dao = new StellenanzeigeDAO();
        return dao;

    }

    public boolean createStellenanzeige(Stellenanzeige s) throws DatabaseException {

        String sql = "insert into stealthyalda.stellenanzeige values(default,?,?,?,?,?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);
        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
        int userLogin = user.getId();
        Arbeitgeber a = ArbeitgeberDAO.getInstance().getArbeitgeber(userLogin);

        //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
        try {
            statement.setString(1, s.getTitel());
            statement.setString(2, s.getBeschreibung());
            statement.setString(3, s.getStatus());
            statement.setDate(4, (Date) s.getDatum());
            statement.setInt(5, a.getArbeitgeber_id());
            statement.setString(6, s.getOrt());
            statement.executeUpdate();

            //Nachtragliches Setzen der BuchungsID
            setStellenanzeigesID(s);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StellenanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private void setStellenanzeigesID(Stellenanzeige s) throws SQLException {
        Statement statement = this.getStatement();
        ResultSet rs = null;

        int currentValue = 0;
        try {
            rs = statement.executeQuery("SELECT max(stealthyalda.stellenanzeige.stellenanzeige_id) FROM stealthyalda.stellenanzeige");

            assert rs != null;
            rs.next();
            currentValue = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(StellenanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(rs);
        }

        s.setStellenanzeigeID(currentValue);
    }


    public List<StellenanzeigeDTO> getStellenanzeigeByLocationOrJobTitelOrUnternehmen(String titelorunternehmen, String ort) {
        ResultSet rs = null;

        List<StellenanzeigeDTO> liste = new ArrayList<>();
        StellenanzeigeDTO stellenanzeige = null;
        String getStellenanzeigen = "SELECT" +
                "  s.titel," +
                "  s.beschreibung," +
                "  s.status," +
                "  s.datum," +
                "  a.unternehmen," +
                "  s.ort " +
                "FROM" +
                " stealthyalda.stellenanzeige s, stealthyalda.arbeitgeber a " +
                "WHERE s.arbeitgeber_id = a.arbeitgeber_id " +
                "AND ort LIKE '%" + ort + "%' " +
                "OR unternehmen LIKE '%" + titelorunternehmen + "%' " +
                "OR titel LIKE '%" + titelorunternehmen + "%'";
        try {
            // use prepared stmt
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(getStellenanzeigen);

            rs = preparedStatement.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                stellenanzeige = new StellenanzeigeDTO();
                stellenanzeige.setTitel(rs.getString(1));
                stellenanzeige.setBeschreibung(rs.getString(2));
                stellenanzeige.setStatus(rs.getString(3));
                stellenanzeige.setDatum(rs.getDate(4));
                stellenanzeige.setArbeitgeber(rs.getString(5));
                stellenanzeige.setOrt(rs.getString(6));
                liste.add(stellenanzeige);
            }
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, rs);
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(rs);
        }

        return liste;
    }

}
