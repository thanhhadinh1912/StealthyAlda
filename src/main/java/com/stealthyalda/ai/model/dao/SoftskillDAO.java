/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Softskill;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author WINDOWS
 */
public class SoftskillDAO extends AbstractDAO {
    private static SoftskillDAO dao = null;

    private SoftskillDAO() {
    }

    /**
     * Singleton
     *
     * @return SoftskillDAO object
     */
    public static SoftskillDAO getInstance() {
        if (dao == null) {
            dao = new SoftskillDAO();
        }
        return dao;
    }

    /**
     * Get a List of the user's soft skills
     *
     * @param user Benutzer object
     * @return List<Softskill> a list object containing the stored soft skills
     */
    public List<Softskill> getSoftskillsForUser(Benutzer user) {
        List<Softskill> liste = new ArrayList<>();
        Softskill softskill = null;
        ResultSet rs = null;
        String sql = "SELECT h.softskill_id, h.softskill\n" +
                "FROM stealthyalda.softskill h\n" +
                "INNER JOIN  stealthyalda.student_hat_softskill sh ON sh.softskill_id = h.softskill_id\n" +
                "INNER JOIN stealthyalda.student s ON s.student_id = sh.student_id\n" +
                "WHERE s.benutzer_id = ?;";
        try (PreparedStatement stmt = this.getPreparedStatement(sql)) {
            stmt.setInt(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                softskill = new Softskill();
                softskill.setSoftskillId(rs.getInt(1));
                softskill.setSoftskill(rs.getString(2));
                liste.add(softskill);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Softskill.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(rs);
        }
        return liste;
    }

    /**
     * Delete stored user's soft skills
     *
     * @param h id of soft skill
     * @param s Student object
     * @throws DatabaseException
     */
    public void deleteSoftskillsForUser(int h, Student s) throws DatabaseException {
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(
                "DELETE FROM stealthyalda.student_hat_softskill WHERE softskill_id = ? AND student_id = ?;")) {
            statement.setInt(1, h);
            statement.setInt(1, s.getStudentId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, throwables.getMessage(), throwables);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
    }

    /**
     * Insert a user inputted soft skill
     *
     * @param softskill A Softskill object
     * @param s         A student object
     */
    public void createSoftskillForUser(Softskill softskill, Student s) {
        try (PreparedStatement statement = this.getPreparedStatement(
                "INSERT INTO stealthyalda.softskill VALUES(default,?);")) {
            statement.setString(1, softskill.getSoftskill());
            // instead of manually setting the id, let sql decide which ID to use

            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Failed to insert skill");
            }
            ResultSet idKeys = statement.getGeneratedKeys();
            if (idKeys.next()) {
                int sskillId = idKeys.getInt(1);
                insertStudentHatSoftskill(s.getStudentId(), sskillId);
            } else {
                throw new SQLException("Funny, we didn't get a soft skill ID back :(");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SoftskillDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Insert into table student_hat_softskill
     *
     * @param studId   ID of the student
     * @param sskillId soft skill id
     */
    private void insertStudentHatSoftskill(int studId, int sskillId) {
        String sql2 = "INSERT INTO stealthyalda.student_hat_softskill(student_id, softskill_id) VALUES(?,?);";
        try (PreparedStatement statement2 = this.getPreparedStatement(sql2)) {
            statement2.setInt(1, studId);
            statement2.setInt(2, sskillId);
            statement2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SoftskillDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new IllegalStateException();
        }
    }
}