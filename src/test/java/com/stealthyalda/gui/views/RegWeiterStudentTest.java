package com.stealthyalda.gui.views;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class RegWeiterStudentTest {

    @Test
    public void setUp() {
        RegWeiterStudent rs = mock(RegWeiterStudent.class);
        assertDoesNotThrow(rs::setUp);
    }

    @Test
    public void enter() {
    }
}