package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.dao.BenutzerDAO;
import com.stealthyalda.ai.model.entities.Benutzer;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
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
}