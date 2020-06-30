package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.dao.HardskillDAO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Hardskill;
import com.stealthyalda.ai.model.entities.Student;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class HardskillDAOTest {

    private Hardskill hskill = new Hardskill();
    private Student stest = new Student();
    private Benutzer benutzertest = new Benutzer();

    @Test
    public void getInstance() {

        assertNotNull(HardskillDAO.getInstance());

    }

    @Test
    public void getHardskillsForUser() {
        hskill.setHardskillId(123);
        hskill.setHardskill("Baumarkt");
        stest.setId(253);
        HardskillDAO dao = HardskillDAO.getInstance();
        dao.createHardskillForUser(hskill, stest);
        List<Hardskill> skillref = dao.getHardskillsForUser(stest);
        String testskill = skillref.get(skillref.size()-1).getHardskill();
        assertEquals(hskill.getHardskill(), testskill);

    }

    @Test
    public void deleteHardskillForUser() {
    }

    @Test 
    public void createHardskillForUser() {
        HardskillDAO dao = HardskillDAO.getInstance();
        hskill.setHardskillId(14);
        hskill.setHardskill("Webentwickler");
        stest.setId(25);
        assertTrue(dao.createHardskillForUser(hskill,stest));
    }
}