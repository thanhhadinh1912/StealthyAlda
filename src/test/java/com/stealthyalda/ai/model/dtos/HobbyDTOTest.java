package com.stealthyalda.ai.model.dtos;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HobbyDTOTest {
    private int id = 1;
    private String description = "Hobby";
    private HobbyDTO hobby;
    @Before
    public void setup(){
        hobby = new HobbyDTO();
        hobby.setId(id);
        hobby.setDescription(description);
    }
    @Test
    public void testget(){
        assertEquals(id,hobby.getId());
        assertNotNull(hobby.getId());
        assertEquals(description, hobby.getDescription());
    }
}
