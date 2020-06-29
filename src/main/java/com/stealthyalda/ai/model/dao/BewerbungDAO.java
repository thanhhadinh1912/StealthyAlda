package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.services.db.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BewerbungDAO extends AbstractDAO {
    private static BewerbungDAO dao;

    private BewerbungDAO() {

    }

    public static BewerbungDAO getInstance() {
        if (dao == null) {
            dao = new BewerbungDAO();
        }
        return dao;

    }

    public boolean createBewerbung(Stellenanzeige a, BewerbungCollAtHBRSDTO b, Student s) {
        String sql = "INSERT INTO stealthyalda.bewerbung(bewerbung_id, student_id, stellenanzeige_id, status, anschreiben, erfahrung, zeugnisse, datum) VALUES (default,?,?,?,?,?,?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);


        try {
            statement.setInt(1, s.getStudentId());
            statement.setInt(2, a.getStellenanzeigeID());
            statement.setString(3, "gesendet");
            statement.setString(4, b.getAnschreiben());
            statement.setString(5, b.getErfahrung());
            statement.setString(6, b.getZertifikat());
            statement.setDate(7, Date.valueOf(b.getDatum()));

            int rowsChanged = statement.executeUpdate();
            setBewerbungsID(b);
            if (rowsChanged == 0) {
                throw new SQLException("Creating bewerbung failed, no rows affected.");
            }
            return true;
        } catch (SQLException ex) {
            logEntry(this.getClass().getName(), Level.SEVERE, ex.getMessage());
            return false;
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                logEntry(this.getClass().getName(), Level.SEVERE, e.getMessage());
            }
        }
    }

    private void setBewerbungsID(BewerbungCollAtHBRSDTO b) throws SQLException {
        Statement statement = this.getStatement();
        ResultSet rs = null;

        int currentValue = 0;
        try {
            rs = statement.executeQuery("SELECT max(stealthyalda.bewerbung.bewerbung_id) FROM stealthyalda.bewerbung");

            assert rs != null;
            rs.next();
            currentValue = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(BewerbungDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(rs);
        }
        b.setId(currentValue);
    }

    public List<BewerbungCollAtHBRSDTO> getBewerbungFromStudent(Student s) {
        String sql = "SELECT a.titel, u.unternehmen, b.status\n" +
                "FROM stealthyalda.bewerbung b\n" +
                "JOIN stealthyalda.stellenanzeige a ON b.stellenanzeige_id = a.stellenanzeige_id\n" +
                "JOIN stealthyalda.student s ON b.student_id = s.student_id\n" +
                "JOIN stealthyalda.arbeitgeber u ON u.arbeitgeber_id = a.arbeitgeber_id\n" +
                "WHERE s.student_id = ? " +
                "ORDER BY b.bewerbung_id DESC\n";


        ResultSet rs = null;
        List<BewerbungCollAtHBRSDTO> liste = new ArrayList<>();
        try {
            // use prepared stmt
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
            statement.setInt(1, s.getStudentId());
            rs = statement.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                BewerbungCollAtHBRSDTO bewerbung = new BewerbungCollAtHBRSDTO();
                Arbeitgeber a = new Arbeitgeber();
                StellenanzeigeDTO st = new StellenanzeigeDTO();
                st.setTitel(rs.getString(1));
                a.setUnternehmen(rs.getString(2));
                bewerbung.setArbeitgeber(a);
                bewerbung.setStatus(rs.getString(3));
                bewerbung.setStellenanzeige(st);
                liste.add(bewerbung);
            }
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, rs);
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(rs);
        }
        return liste;

    }

    public List<BewerbungCollAtHBRSDTO> getBewerbungFromArbeitgeber(Arbeitgeber a) {
        String sql = "SELECT s.nachname, s.vorname, a.titel, b.datum, s.student_id, s.benutzer_id\n" +
                "FROM stealthyalda.bewerbung b\n" +
                "JOIN stealthyalda.stellenanzeige a ON b.stellenanzeige_id = a.stellenanzeige_id\n" +
                "JOIN stealthyalda.student s ON b.student_id = s.student_id\n" +
                "JOIN stealthyalda.arbeitgeber u ON u.arbeitgeber_id = a.arbeitgeber_id\n" +
                "WHERE u.arbeitgeber_id = ?\n" +
                "AND b.status = 'gesendet'\n" +
                "ORDER BY b.bewerbung_id";
        ResultSet rs = null;
        List<BewerbungCollAtHBRSDTO> liste = new ArrayList<>();
        try {
            // use prepared stmt
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
            statement.setInt(1, a.getArbeitgeberId());
            rs = statement.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                BewerbungCollAtHBRSDTO bewerbung = new BewerbungCollAtHBRSDTO();
                Student s = new Student();
                StellenanzeigeDTO st = new StellenanzeigeDTO();
                s.setNachname(rs.getString(1));
                s.setVorname(rs.getString(2));
                st.setTitel(rs.getString(3));
                s.setStudentId(rs.getInt(5));
                s.setId(rs.getInt(6));
                bewerbung.setDatum(rs.getDate(4).toLocalDate());
                bewerbung.setStudent(s);
                bewerbung.setStellenanzeige(st);
                liste.add(bewerbung);
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
