package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.dtos.StudentDTO;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class StudentDAOTest {

    @Test
    public void getInstance() {
        StudentDAO dao = mock(StudentDAO.class);
        assertNotNull(dao);
    }

    @Test
    public void newStudent() {
        StudentDAO dao = mock(StudentDAO.class);
        StudentDTO s = new StudentDTO();
        s.setId(35);
        s.setNachname("Karl");
        s.setVorname("Heinz");
        assertDoesNotThrow(() -> dao.newStudent(s));
    }

    @Test
    public void getStudent() {
        StudentDAO dao = mock(StudentDAO.class);
        assertDoesNotThrow(() -> dao.getStudent(35));
    }

    @Test
    public void testGetStudent() {
        StudentDAO dao = mock(StudentDAO.class);
        StudentDTO s = new StudentDTO();
        s.setId(37);
        s.setNachname("Coral");
        s.setVorname("Dina");
        s.setEmail("coraldina@gmail.de");
        assertDoesNotThrow(() -> dao.getStudent(s.getEmail()));
    }
}
