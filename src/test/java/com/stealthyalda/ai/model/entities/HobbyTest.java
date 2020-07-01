package com.stealthyalda.ai.model.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HobbyTest {
    private final int id = 1;
    private final String name = "Hobby";
    private Hobby hobby;

    @Before
    public void setup() {
        hobby = new Hobby();
        hobby.setHobbyId(id);
        hobby.setHobby(name);
    }

    @Test
    public void testget() {
        assertEquals(id, hobby.getHobbyId());
        assertNotNull(hobby.getHobbyId());
        assertEquals(name, hobby.getHobby());
    }
}


