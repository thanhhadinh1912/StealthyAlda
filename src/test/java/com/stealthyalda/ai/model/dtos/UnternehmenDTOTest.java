package com.stealthyalda.ai.model.dtos;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnternehmenDTOTest {
    private String beschreibung = "Beschreibung";
    private String unternehmen = "Test";
    private int arbeitgeberId = 1;
    private UnternehmenDTO u;
    @Before
    public void setup(){
        u = new UnternehmenDTO();
        u.setUnternehmen(unternehmen);
        u.setArbeitgeberId(arbeitgeberId);
        u.setBeschreibung(beschreibung);
    }
    @Test

    public void testget(){
        assertEquals(unternehmen,u.getUnternehmen());
        assertNotNull(arbeitgeberId);
        assertEquals(arbeitgeberId, u.getArbeitgeberId());
        assertEquals(beschreibung, u.getBeschreibung());
    }

}
