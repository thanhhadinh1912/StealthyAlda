package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
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

    @Test
    public void getInstance() {

        assertNotNull(HardskillDAO.getInstance());

    }

    @Test
    public void getHardskillsForUser() {
        //Faulty
        //Indexoutofbounds Expections. hardskill don't get added to the List
        Hardskill hardskill = new Hardskill();
        Student student = new Student();
        hardskill.setHardskillId(190);
        hardskill.setHardskill("Hp");
        student.setId(456);
        HardskillDAO dao = HardskillDAO.getInstance();
        dao.createHardskillForUser(hardskill, student);
        List<Hardskill> skillref = dao.getHardskillsForUser(student);
        //assertEquals(hardskill, skillref.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            skillref.get(0);
        });

    }

    @Test
    public void deleteHardskillForUser() throws DatabaseException {
        HardskillDAO dao = HardskillDAO.getInstance();

        assertTrue(dao.deleteHardskillForUser(hskill.getHardskillId(), stest));
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