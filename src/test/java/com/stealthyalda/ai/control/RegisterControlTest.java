package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.dao.BenutzerDAO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.gui.views.Registerseite;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class RegisterControlTest {


    @Test
    public void checkUserExists() throws UserExistsException, DatabaseException, NoSuchUserOrPassword {

        RegisterControl rc = mock(RegisterControl.class);
        assertFalse(rc.checkUserExists("ingo"));

    }

    @Test
    public void registerUser() throws DatabaseException, NoSuchUserOrPassword {

        BenutzerDAO myDAO = BenutzerDAO.getInstance();
        myDAO.createBenutzer("one", "one", "Student");
        Benutzer ben = BenutzerDAO.getBenutzer("one", "one");
        System.out.println(ben.getEmail());

    }

    @Test
    public void registerArbeitgeber() {


    }

    @Test
    public void registerStudent() {
    }

    @Test
    public void testRegistration() throws Exception {
        Student student = Mockito.mock(Student.class);
        Mockito.when(student.getVorname()).thenReturn("Jan");
        Mockito.when(student.getNachname()).thenReturn("Müller");
        Mockito.when(student.getEmail()).thenReturn("jan@mueller.de");
        Mockito.when(student.getPasswort()).thenReturn("12345678");


        assertTrue(student.getVorname() == "Jan");
        assertTrue(student.getNachname() == "Müller");
        assertTrue(student.getEmail() == "jan@mueller.de");
        assertTrue(student.getPasswort() == "12345678");
        assertEquals(student, student);
        Registerseite test1 = Mockito.mock(Registerseite.class);

    }
}