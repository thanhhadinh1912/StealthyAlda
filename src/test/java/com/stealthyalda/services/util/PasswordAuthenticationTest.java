package com.stealthyalda.services.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordAuthenticationTest {
    private final String strongP = "123456";
    private PasswordAuthentication hashComputer;

    @Before
    public void setUp() {
        hashComputer = new PasswordAuthentication();
    }

    @Test
    public void testHash() {
        String hashedStrongPassword = hashComputer.hash(strongP.toCharArray());
        String sameHashedPassword = hashComputer.hash("123456".toCharArray());
        assertNotEquals(hashedStrongPassword, sameHashedPassword);
        assertNotNull(hashedStrongPassword);
        assertNotNull(sameHashedPassword);
    }

    @Test
    public void testAuthenticate() {
        String somePasswordHash = hashComputer.hash(strongP.toCharArray());
        assertNotNull(hashComputer);
        // pos
        assertTrue(hashComputer.authenticate("123456".toCharArray(), somePasswordHash));
        // neg
        assertFalse(hashComputer.authenticate("123456 ".toCharArray(), somePasswordHash));
    }
}