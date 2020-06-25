package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.dao.BenutzerDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KontoControlTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void deletekonto() throws DatabaseException, UserExistsException {

        RegisterControl rc = new RegisterControl();
        String email = "deletekonto@test.de";
        String pw = "test123";
        rc.registerUser(email,pw,"student");
        assertEquals(false,rc.checkUserExists(email));

        BenutzerDAO benDAO = BenutzerDAO.getInstance();
        benDAO.deleteUser(email,pw);

        assertEquals(true,rc.checkUserExists(email));

    }

    @Test
    void changekonto() {

    }
}