package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.JoberfahrungDTO;
import com.stealthyalda.ai.model.entities.Hardskill;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JoberfahrungDAO extends AbstractDAO {

    private static JoberfahrungDAO dao = null;

    private JoberfahrungDAO() {
    }

    public static JoberfahrungDAO getInstance() {
        if (dao == null) {
            dao = new JoberfahrungDAO();
        }
        return dao;
    }

    public List<JoberfahrungDTO> getJoberfahrungsForStudent(Student student) {
        List<JoberfahrungDTO> liste = new ArrayList<>();
        JoberfahrungDTO joberfahrung = null;
        ResultSet rs = null;
        String sql = "SELECT jo.joberfahrung\n" +
                "FROM stealthyalda.student s\n" +
                "JOIN stealthyalda.joberfahrung jo ON jo.student_id = s.student_id\n" +
                "WHERE s.student_id = ?\n";
        try (PreparedStatement stmt = this.getPreparedStatement(sql)) {
            stmt.setInt(1, student.getStudentId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                joberfahrung = new JoberfahrungDTO();
                joberfahrung.setJoberfahrung(rs.getString(1));
                liste.add(joberfahrung);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Hardskill.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(rs);
        }
        return liste;
    }

    public void deleteJoberfahrungForUser(int joberfahrungId, Student s) throws DatabaseException {
        try (PreparedStatement statement = getPreparedStatement(
                "DELETE FROM stealthyalda.joberfahrung WHERE joberfahrung_id = ? AND student_id = ?;")) {
            statement.setInt(1, joberfahrungId);
            statement.setInt(2, s.getStudentId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            Logger.getLogger(JoberfahrungDAO.class.getName()).log(Level.SEVERE, throwables.getMessage(), throwables);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
    }

    public void createJoberfahrungForUser(JoberfahrungDTO je, Student s) {
        ResultSet set = null;
        try (PreparedStatement statement = this.getPreparedStatement(
                "INSERT INTO stealthyalda.joberfahrung(joberfahrung,student_id) VALUES(?,?);")) {
            statement.setString(1, je.getJoberfahrung());
            statement.setInt(2, s.getStudentId());
            int numRows = statement.executeUpdate();
            if (numRows == 0) {
                throw new SQLException("Failed to insert Joberfahrung");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HobbyDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(set);
        }
    }
}
