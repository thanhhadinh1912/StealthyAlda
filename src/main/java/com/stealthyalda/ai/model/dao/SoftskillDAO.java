/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Hardskill;
import com.stealthyalda.ai.model.entities.Softskill;

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

    public static SoftskillDAO getInstance() {
        if (dao == null) {
            dao = new SoftskillDAO();
        }
        return dao;
    }

    public List<Softskill> getSoftskillsForUser(Benutzer user) {
        List<Softskill> liste = new ArrayList<>();
        Softskill softskill = null;
        ResultSet rs = null;
        String sql = "SELECT h.softskill_id, h.softskill\n" +
                "FROM stealthyalda.softskill h\n" +
                "INNER JOIN  stealthyalda.student_hat_softskill sh on sh.softskill_id = h.softskill_id\n" +
                "INNER JOIN stealthyalda.student s on s.student_id = sh.student_id\n" +
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
            Logger.getLogger(Hardskill.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            closeResultset(rs);
        }
        return liste;
    }


}