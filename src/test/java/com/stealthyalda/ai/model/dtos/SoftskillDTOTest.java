package com.stealthyalda.ai.model.dtos;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoftskillDTOTest {

    @Test
    public void getSetSoftSkillName() {
        SoftskillDTO s = new SoftskillDTO();
        s.setSetSoftSkillName("soft");
        assertEquals("soft",s.getSetSoftSkillName());
    }

    @Test
    public void setSetSoftSkillName() {
        SoftskillDTO s = new SoftskillDTO();
        s.setSetSoftSkillName("soft");
        assertEquals("soft",s.getSetSoftSkillName());
    }

    @Test
    public void getSoftSkillId() {
        SoftskillDTO s = new SoftskillDTO();
        s.setSoftSkillId(1);
        assertEquals(1,s.getSoftSkillId());
    }

    @Test
    public void setSoftSkillId() {
        SoftskillDTO s = new SoftskillDTO();
        s.setSoftSkillId(1);
        assertEquals(1,s.getSoftSkillId());
    }
}