package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Bewerbung;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.PasswordAuthentication;
import com.vaadin.server.VaadinSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BewerbungDAO extends AbstractDAO{
    private static BewerbungDAO dao;

    private BewerbungDAO() {

    }

    public static BewerbungDAO getInstance() {
        if (dao == null) {
            dao = new BewerbungDAO();
        }
        return dao;

    }

    public boolean createBewerbung(Stellenanzeige a, Bewerbung b, Student s){
        String sql = "INSERT INTO stealthyalda.bewerbung(bewerbung_id, student_id, stellenanzeige_id, status, anschreiben, erfahrung, zeugnisse) VALUES (default,?,?,?,?,?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);



        try {
            statement.setInt(1,s.getStudentId());
            statement.setInt(2,a.getStellenanzeigeID());
            statement.setString(3,"gesendet");
            statement.setString(4,b.getAnschreiben());
            statement.setString(5, b.getErfahrung());
            statement.setString(6, b.getZertifikat());

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
                e.printStackTrace();
            }
        }
    }

    private void setBewerbungsID(Bewerbung b) throws SQLException {
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
}
