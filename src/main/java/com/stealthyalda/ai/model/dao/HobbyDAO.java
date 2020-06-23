/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Hobby;

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

    public List<Hobby> getHobbysForUser(Benutzer user) {
        List<Hobby> liste = new ArrayList<>();

        ResultSet resultSet = null;
        String sql = "select h.hobby_id, h.hobby\n" +
                "from stealthyalda.hobby h\n" +
                "inner join stealthyalda.student_hat_hobby sh on sh.hobby_id = h.hobby_id\n" +
                "inner join stealthyalda.student s on s.student_id = sh.student_id\n" +
                "where s.benutzer_id = ?;";
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
            Logger.getLogger(HobbyDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(resultSet);
        }

        return liste;
    }
}


