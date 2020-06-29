/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Hobby;
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
public class HobbyDAO extends AbstractDAO {
    private static HobbyDAO dao = null;

    private HobbyDAO() {

    }

    public static HobbyDAO getInstance() {
        if (dao == null) {
            dao = new HobbyDAO();
        }
        return dao;
    }

    /**
     * Fetch user's hobbies
     *
     * @param user Benutzer object
     * @return List object containing all hobbies
     */
    public List<Hobby> getHobbysForUser(Benutzer user) {
        List<Hobby> liste = new ArrayList<>();

        ResultSet resultSet = null;
        String sql = "SELECT h.hobby_id, h.hobby\n" +
                "FROM stealthyalda.hobby h\n" +
                "INNER join stealthyalda.student_hat_hobby sh ON sh.hobby_id = h.hobby_id\n" +
                "INNER join stealthyalda.student s ON s.student_id = sh.student_id\n" +
                "WHERE s.benutzer_id = ?;";
        try (PreparedStatement stmt = this.getPreparedStatement(sql)) {
            stmt.setInt(1, user.getId());
            resultSet = stmt.executeQuery();
            Hobby hobby = null;
            while (resultSet.next()) {
                hobby = new Hobby();
                hobby.setHobbyId(resultSet.getInt(1));
                hobby.setHobby(resultSet.getString(2));
                liste.add(hobby);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HobbyDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(resultSet);
        }

        return liste;
    }


    /**
     * Delete stored user's hobbies
     *
     * @param h id of hobby
     * @param s Student object
     * @throws DatabaseException
     */
    public void deleteHobbysForUser(int h, Student s) throws DatabaseException {
        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(
                "DELETE FROM stealthyalda.student_hat_hobby WHERE hobby_id = ? AND student_id = ?;")) {
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
     * Insert a user inputted hobby
     *
     * @param hobby A Hobby object
     * @param s     A student object
     */
    public void createHobbyForUser(Hobby hobby, Student s) {
        try (PreparedStatement statement = this.getPreparedStatement(
                "INSERT INTO stealthyalda.hobby(hobby) VALUES(?);")) {
            statement.setString(1, hobby.getHobby());
            // instead of manually setting the id, let sql decide which ID to use

            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Failed to insert hobby");
            }
            ResultSet idKeys = statement.getGeneratedKeys();
            if (idKeys.next()) {
                int hobbyId = idKeys.getInt(1);
                insertStudentHatHobby(s.getStudentId(), hobbyId);
            } else {
                throw new SQLException("Funny, we didn't get a hobby ID back :O");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HobbyDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Insert into table student_hat_hobby
     *
     * @param studId  ID of the student
     * @param hobbyId hobby id
     */
    private void insertStudentHatHobby(int studId, int hobbyId) {
        String sql2 = "INSERT INTO stealthyalda.student_hat_hobby(student_id, hobby_id) VALUES(?,?);";
        try (PreparedStatement statement2 = this.getPreparedStatement(sql2)) {
            statement2.setInt(studId, hobbyId);
            statement2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HobbyDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new IllegalStateException(); // flip table
        }
    }


}


