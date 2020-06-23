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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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

    public static HardskillDAO getInstance() {
        if (dao == null) {
            dao = new HardskillDAO();
        }
        return dao;
    }

    public List<Hardskill> getHardskillsForUser(Benutzer user) {
        String sql = "SELECT h.hardskill_id, h.hardskill\n" +
                "FROM stealthyalda.hardskill h\n" +
                "INNER JOIN  stealthyalda.student_hat_hardskill sh " +
                "ON sh.hardskill_id = h.hardskill_id\n" +
                "INNER JOIN stealthyalda.student s " +
                "ON s.student_id = sh.student_id\n" +
                "WHERE s.benutzer_id = ?;";
        ResultSet resultSet = null;
        try (PreparedStatement stmt = this.getPreparedStatement(sql)) {
            stmt.setInt(1, user.getId());
            resultSet = stmt.executeQuery();
            List<Hardskill> liste = new ArrayList<>();
            Hardskill hardskill = null;

            while (resultSet.next()) {
                hardskill = new Hardskill();
                hardskill.setHardskillId(resultSet.getInt(1));
                hardskill.setHardskill(resultSet.getString(2));
                liste.add(hardskill);
            }

            return liste;
        } catch (SQLException ex) {
            Logger.getLogger(HardskillDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(resultSet);
        }
        return Collections.emptyList();
    }

    public void deleteHardskillForUser(int h, Student s) throws DatabaseException {
        String sql;
        sql = "DELETE FROM stealthyalda.student_hat_hardskill WHERE hardskill_id = '" + h + "' AND student_id = '" + s.getStudentId() + "';";

        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        try {
            assert statement != null;
            statement.executeUpdate();

        } catch (SQLException throwables) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, throwables.getMessage());
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

    }

    public void createHardskillForUser(Hardskill hardskill, Student s) {
        String sql = "insert into stealthyalda.hardskill values(default,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setString(1, hardskill.getHardskill());
            setHardskillsID(hardskill);
            statement.executeUpdate();
            String sql2 = "insert into stealthyalda.student_hat_hardskill values(" + s.getStudentId() + ", " + hardskill.getHardskillId() + ");";
            PreparedStatement statement2 = this.getPreparedStatement(sql2);
            statement2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HardskillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setHardskillsID(Hardskill h) throws SQLException {

        try (Statement statement = this.getStatement();
             ResultSet rs = statement.executeQuery("SELECT max(stealthyalda.hardskill.hardskill_id) FROM stealthyalda.hardskill")) {

            int currentValue = 0;
            rs.next();
            currentValue = rs.getInt(1);
            h.setHardskillId(currentValue);
        } catch (SQLException ex) {
            Logger.getLogger(Hardskill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}


