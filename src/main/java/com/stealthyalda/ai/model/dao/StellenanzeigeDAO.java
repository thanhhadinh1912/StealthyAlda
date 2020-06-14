package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
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
    
    public void createStellenanzeige(String titel, String beschreibung, String ort)throws DatabaseException{
        
    }

    public List<StellenanzeigeDTO> getStellenanzeigeByLocationOrJobTitelOrUnternehmen(String titelorunternehmen, String ort) {
        ResultSet rs = null;

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
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, rs);
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        }


        if (rs == null) {
            return Collections.emptyList();
        }
        List<StellenanzeigeDTO> liste = new ArrayList<>();
        StellenanzeigeDTO stellenanzeige = null;

        try {
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
        } catch (SQLException ex) {
            Logger.getLogger(StellenanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }

}
