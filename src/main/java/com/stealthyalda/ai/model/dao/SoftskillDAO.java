/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Hardskill;
import com.stealthyalda.ai.model.entities.Softskill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        Statement statement = this.getStatement();
        List<Softskill> liste = new ArrayList<>();
        Softskill softskill = null;
        ResultSet rs = null;
        try {
            String sql = "select h.softskill_id, h.softskill\n" +
                    "from stealthyalda.softskill h\n" +
                    "inner join  stealthyalda.student_hat_softskill sh on sh.softskill_id = h.softskill_id\n" +
                    "inner join stealthyalda.student s on s.student_id = sh.student_id\n" +
                    "where s.benutzer_id = '" + user.getId() + "';";
            rs = statement.executeQuery(sql);


            while (rs.next()) {
                softskill = new Softskill();
                softskill.setSoftskill_id(rs.getInt(1));
                softskill.setSoftskill(rs.getString(2));
                liste.add(softskill);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Hardskill.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            closeResultset(rs);
        }
        return liste;
    }


}