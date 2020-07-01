package com.stealthyalda.gui.views;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

public class RegWeiterArbeitgeberTest {

    @Test
    public void setUp() {
        RegWeiterArbeitgeber ra = mock(RegWeiterArbeitgeber.class);
        assertDoesNotThrow(ra::setUp);
    }

    @Test
    public void enter() {
    }
}