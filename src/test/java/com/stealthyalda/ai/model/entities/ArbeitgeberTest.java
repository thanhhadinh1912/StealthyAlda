package com.stealthyalda.ai.model.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArbeitgeberTest {
    private Arbeitgeber arbeitgeber;
    private String unternehmen = "=o2";
    private String beschreibung = "xxx";
    private int arbeitgeberId = 2;
    private byte logo;


    @Before
    public void setup(){
        arbeitgeber = new Arbeitgeber();
        arbeitgeber.setArbeitgeberId(arbeitgeberId);
        arbeitgeber.setUnternehmen(unternehmen);
        arbeitgeber.setBeschreibung(beschreibung);
        arbeitgeber.setLogo(logo);

    }
    @Test
    public void testget(){
        assertEquals(arbeitgeberId,arbeitgeber.getArbeitgeberId());
        assertNotNull(arbeitgeber.getArbeitgeberId());
        assertEquals(unternehmen, arbeitgeber.getUnternehmen());
        assertEquals(beschreibung, arbeitgeber.getBeschreibung());
        assertEquals(logo, arbeitgeber.getLogo());

    }
}
