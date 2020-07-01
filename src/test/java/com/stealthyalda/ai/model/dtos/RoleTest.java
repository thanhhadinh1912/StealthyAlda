package com.stealthyalda.ai.model.dtos;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {

    @Test
    public void getBezeichnung() {
        Role r = new Role();
        r.setBezeichnung("bez");
        assertEquals("bez", r.getBezeichnung());

    }

    @Test
    public void setBezeichnung() {
        Role r = new Role();
        r.setBezeichnung("bez");
        assertEquals("bez", r.getBezeichnung());
    }
}