package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.dao.BenutzerDAO;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class KontoControlTest {

    @Test
    public void deletekonto() throws DatabaseException, UserExistsException {

        RegisterControl rc = mock ( RegisterControl.class);
        String email = "deletekonto@test.de";
        String pw = "test123";
        rc.registerUser(email,pw,"student");
        assertTrue(rc.checkUserExists(email));

        BenutzerDAO benDAO = BenutzerDAO.getInstance();
        benDAO.deleteUser(email,pw);

        assertFalse(rc.checkUserExists(email));

    }

    @Test
   public  void changekonto() {

    }
}