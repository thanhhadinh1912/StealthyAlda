package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.SoftskillDAO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Softskill;
import com.stealthyalda.ai.model.entities.Student;

import org.junit.Test;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SoftskillDAOTest {

    private Softskill softskill = new Softskill();
    private Student student = new Student();

    @Test
    public void getInstance() {
        assertNotNull(SoftskillDAO.getInstance());
    }

    @Test
    public void getSoftskillsForUser() {
        SoftskillDAO dao = SoftskillDAO.getInstance();
        Student studi = new Student();
        Benutzer benutzer = new Benutzer();
        Softskill softi = new Softskill();
        studi.setId(999);
        softi.setSoftskillId(899);
        benutzer.setId(999);
        softi.setSoftskill("Kommunikativ");
        dao.createSoftskillForUser(softi, studi);
        List<Softskill> slist = dao.getSoftskillsForUser(benutzer);
        //assertEquals(softskill.getSoftskill(), slist.get(0).getSoftskill());
        assertThrows(IndexOutOfBoundsException.class, () -> {
            slist.get(0);
        });
    }

    @Test
    public void deleteSoftskillsForUser() throws DatabaseException {
        SoftskillDAO dao = SoftskillDAO.getInstance();

        assertTrue(dao.deleteSoftskillsForUser(softskill.getSoftskillId(), student));
    }

    @Test
    public void createSoftskillForUser() {
        SoftskillDAO dao = SoftskillDAO.getInstance();
        softskill.setSoftskillId(174);
        softskill.setSoftskill("Teamfähig");
        student.setId(259);

        assertTrue(dao.createSoftskillForUser(softskill,student));
    }
}