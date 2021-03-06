package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.dtos.StudentDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.gui.ui.MyUI;
import com.vaadin.ui.UI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO extends AbstractDAO {
    private static StudentDAO dao = null;
    Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

    private StudentDAO() {

    }

    public static StudentDAO getInstance() {
        if (dao == null) {
            dao = new StudentDAO();
        }
        return dao;
    }

    public void newStudent(StudentDTO studi) {
        ResultSet nextKey = null;
        String sql = "INSERT INTO stealthyalda.student(vorname, nachname ,benutzer_id) VALUES(?,?,?);";
        try (PreparedStatement stmt = this.getPreparedStatement(sql)) {
            stmt.setString(1, studi.getVorname());
            stmt.setString(2, studi.getNachname());
            stmt.setInt(3, user.getId());

            int rowsChanged = stmt.executeUpdate();
            if (rowsChanged == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            nextKey = stmt.getGeneratedKeys();

            if (nextKey.next()) {
                int studentId = nextKey.getInt(1);
                studi.setStudentId(studentId);
                logEntry("BenutzerDAO", Level.INFO, "Found studentid: " + studentId);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(nextKey);
        }
    }

    public Student getStudent(int benutzerid) {
        ResultSet set = null;
        try (PreparedStatement stmt = this.getPreparedStatement("SELECT * "
                + "FROM stealthyalda.student "
                + "WHERE stealthyalda.student.benutzer_id = ?")) {
            stmt.setInt(1, benutzerid);
            set = stmt.executeQuery();

            if (set.next()) {
                Student s = new Student();
                s.setStudentId(set.getInt(1));
                s.setNachname(set.getString(2));
                s.setId(benutzerid);
                s.setVorname(set.getString(4));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(set);
        }
        return null;
    }

    public Student getStudent(String email) {
        ResultSet set = null;
        try {
            PreparedStatement stmt = this.getPreparedStatement("SELECT * \n" +
                    "FROM stealthyalda.student s\n" +
                    "JOIN stealthyalda.benutzer b ON  s.benutzer_id = b.benutzer_id\n" +
                    "WHERE b.email = ?;");
            stmt.setString(1, email);
            set = stmt.executeQuery();

            if (set.next()) {
                Student s = new Student();
                s.setStudentId(set.getInt(1));
                s.setNachname(set.getString(2));
                s.setId(set.getInt(3));
                s.setVorname(set.getString(4));
                s.setEmail(email);
                s.setRole(set.getString(11));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(set);
        }
        return null;
    }

}
