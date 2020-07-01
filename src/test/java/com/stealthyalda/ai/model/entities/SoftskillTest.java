package com.stealthyalda.ai.model.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SoftskillTest {

    private Softskill softskill;
    private int softskillId = 3;
    private String name = "softskill";


    @Before
    public void setup(){
        softskill = new Softskill();
        softskill.setSoftskillId(softskillId);
        softskill.setSoftskill(name);
    }
    @Test
    public void testget(){
        assertEquals(softskillId,softskill.getSoftskillId());
        assertNotNull(softskill.getSoftskillId());
        assertEquals(name, softskill.getSoftskill());
    }
}

