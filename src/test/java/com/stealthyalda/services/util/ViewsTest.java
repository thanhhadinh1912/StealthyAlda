package com.stealthyalda.services.util;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ViewsTest {
    private final String MAIN = "suche";
    private final String LOGIN = "login";

    @Test
    public void get() {
        assertEquals(MAIN, Views.MAIN);
        assertEquals(LOGIN, Views.LOGIN);
        assertNotNull(Views.DASHBOARDA);
    }

}
