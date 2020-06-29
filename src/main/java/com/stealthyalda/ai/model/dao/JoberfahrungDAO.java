package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.dtos.JoberfahrungDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Hardskill;
import com.stealthyalda.ai.model.entities.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JoberfahrungDAO extends AbstractDAO{

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

}
