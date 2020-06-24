package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.dtos.UnternehmenDTO;
import com.stealthyalda.gui.views.Unternehmen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterControlTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkUserExists() throws UserExistsException, DatabaseException {

        RegisterControl rc = new RegisterControl();
        assertEquals(false,rc.checkUserExists("Auto2@test.de"));

    }

    @Test
    void registerUser() throws DatabaseException {
        RegisterControl rc = new RegisterControl();

        String email = "hallo@test.de";
        String pw = "test123";
        String student="Student";



        assertEquals(true, rc.registerUser(email,pw,student));

    }

    @Test
    void registerArbeitgeber() {







    }

    @Test
    void registerStudent() {
    }
}