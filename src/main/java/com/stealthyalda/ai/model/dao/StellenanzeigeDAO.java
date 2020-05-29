package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StellenanzeigeDAO extends AbstractDAO {

    private static StellenanzeigeDAO dao = null;


    public static StellenanzeigeDAO getInstance() {
        if (dao == null) dao = new StellenanzeigeDAO();
        return dao;

    }

    public List<Stellenanzeige> getStellenanzeigeByLocationOrJobTitelOrUnternehmen(String titelorunternehmen, String ort) {
        ResultSet rs = null;


    /*
        Statement statement = this.getStatement();
        try {
            rs = statement.executeQuery("select s.stellenanzeige_id, s.titel, s.beschreibung, s.status, s.datum, s.fachbereich, a.unternehmen,s. ort\n" +
                    "from stealthyalda.stellenanzeige s, stealthyalda.arbeitgeber a\n" +
                    "where s.arbeitgeber_id = a.arbeitgeber_id\n" +
                    "and ort LIKE '%" + ort + "%'\n" +
                    "or unternehmen LIKE '%" + titelorunternehmen + "%'\n" +
                    "or titel LIKE '%" + titelorunternehmen + "%'");
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
        String getStellenanzeigen = "SELECT" +
                "  s.stellenanzeige_id," +
                "  s.titel," +
                "  s.beschreibung," +
                "  s.status," +
                "  s.datum," +
                "  s.fachbereich," +
                "  a.unternehmen," +
                "  s.ort " +
                "FROM" +
                " stealthyalda.stellenanzeige s, stealthyalda.arbeitgeber a " +
                "WHERE s.arbeitgeber_id = a.arbeitgeber_id " +
                "AND ort LIKE '%?%' " +
                "OR  unternehmen LIKE '%?%' " +
                "OR titel LIKE '%?%'";
        try {
            // use prepared stmt
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(getStellenanzeigen);

            preparedStatement.setString(1, titelorunternehmen);
            preparedStatement.setString(2, ort);

            rs = preparedStatement.executeQuery();
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, rs);
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        }


        if (rs == null) {
            return Collections.emptyList();
        }
        List<Stellenanzeige> liste = new ArrayList<>();
        Stellenanzeige stellenanzeige = null;

        try {
            while (rs.next()) {
                stellenanzeige = new Stellenanzeige();
                stellenanzeige.setStellenanzeige_id(rs.getInt(1));
                stellenanzeige.setTitel(rs.getString(2));
                stellenanzeige.setBeschreibung(rs.getString(3));
                stellenanzeige.setStatus(rs.getString(4));
                stellenanzeige.setDatum(rs.getDate(5));
                stellenanzeige.setFachbereich(rs.getString(6));
                stellenanzeige.setUnternehmen(rs.getString(7));
                stellenanzeige.setOrt(rs.getString(8));
                liste.add(stellenanzeige);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StellenanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

}
