/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Benutzer;
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

/**
 * @author WINDOWS
 */
public class HardskillDAO extends AbstractDAO {
    private static HardskillDAO dao = null;

    private HardskillDAO() {
    }

    /**
     * Singleton
     *
     * @return HardskillDAO object
     */
    public static HardskillDAO getInstance() {
        if (dao == null) {
            dao = new HardskillDAO();
        }
        return dao;
    }

    /**
     * Get a List of the user's hard skills
     *
     * @param user Benutzer object
     * @return List<Hardskill> a list object containing the stored hard skills
     */
    public List<Hardskill> getHardskillsForUser(Benutzer user) {
        List<Hardskill> liste = new ArrayList<>();
        Hardskill hardskill = null;
        ResultSet rs = null;
        String sql = "SELECT h.hardskill_id, h.hardskill\n" +
                "FROM stealthyalda.hardskill h\n" +
                "INNER JOIN  stealthyalda.student_hat_hardskill sh ON sh.hardskill_id = h.hardskill_id\n" +
                "INNER JOIN stealthyalda.student s ON s.student_id = sh.student_id\n" +
                "WHERE s.benutzer_id = ?;";
        try (PreparedStatement stmt = this.getPreparedStatement(sql)) {
            stmt.setInt(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                hardskill = new Hardskill();
                hardskill.setHardskillId(rs.getInt(1));
                hardskill.setHardskill(rs.getString(2));
                liste.add(hardskill);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Hardskill.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(rs);
        }
        return liste;
    }


    /**
     * Delete stored user's hard skills
     *
     * @param h id of hard skill
     * @param s Student object
     * @return boolean true if all is ok
     * @throws DatabaseException
     */
    public boolean deleteHardskillForUser(int h, Student s) throws DatabaseException {
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(
                "DELETE FROM stealthyalda.student_hat_hardskill WHERE hardskill_id = ? AND student_id = ?;")) {
            statement.setInt(1, h);
            statement.setInt(2, s.getStudentId());
            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, throwables.getMessage(), throwables);
            return false;
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
    }

    /**
     * Insert a user inputted hard skill
     *
     * @param hardskill A Hardskill object
     * @param s         A student object
     * @return boolean boolean
     */
    public boolean createHardskillForUser(Hardskill hardskill, Student s) {
        ResultSet idKeys = null;
        try (PreparedStatement statement = this.getPreparedStatement(
                "INSERT INTO stealthyalda.hardskill VALUES(default,?);")) {
            statement.setString(1, hardskill.getHardskill());
            // instead of manually setting the id, let sql decide which ID to use

            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Failed to insert skill");
            }
            idKeys = statement.getGeneratedKeys();
            if (idKeys.next()) {
                int rskillId = idKeys.getInt(1);
                insertStudentHatHardskill(s.getStudentId(), rskillId);
                return true;
            } else {
                throw new SQLException("Funny, we didn't get a hard skill ID back :(");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HardskillDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        } finally {
            closeResultset(idKeys);
        }

    }

    /**
     * Insert into table student_hat_hardskill
     *
     * @param studId   ID of the student
     * @param hskillId hard skill id
     * @return boolean true if all goes well
     */
    private boolean insertStudentHatHardskill(int studId, int hskillId) {
        String sql2 = "INSERT INTO stealthyalda.student_hat_hardskill(student_id, hardskill_id) VALUES(?,?);";
        try (PreparedStatement statement2 = this.getPreparedStatement(sql2)) {
            statement2.setInt(1, studId);
            statement2.setInt(2, hskillId);
            statement2.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HardskillDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }
}