package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
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
    void registerUser() {
        RegisterControl rc = new RegisterControl();

    }

    @Test
    void registerArbeitgeber() {
    }

    @Test
    void registerStudent() {
    }
}