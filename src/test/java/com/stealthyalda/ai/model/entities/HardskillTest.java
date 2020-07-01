package com.stealthyalda.ai.model.entities;

import com.stealthyalda.ai.model.dtos.Adresse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HardskillTest {

    private Hardskill hardskill;
    private int id = 1;
    private String name = "Hardskill";


    @Before
    public void setup(){
        hardskill = new Hardskill();
        hardskill.setHardskillId(id);
        hardskill.setHardskill(name);
    }
    @Test
    public void testget(){
        assertEquals(id,hardskill.getHardskillId());
        assertNotNull(hardskill.getHardskillId());
        assertEquals(name, hardskill.getHardskill());
    }


}
