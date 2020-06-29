package com.stealthyalda.ai.model.dtos;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HardskillDTOTest {

    HardskillDTO hardSk;

    @Before
    public void setUp() {
        hardSk = new HardskillDTO();
        hardSk.setHardSkillName("Head banging");
        hardSk.setHardSkillId(101);
    }

    @Test
    public void getHardSkillNameTest() {
        assertNotNull(hardSk);
        assertEquals("Head banging", hardSk.getHardSkillName());
        hardSk.setHardSkillName(null);
        assertNull(hardSk.getHardSkillName());
    }

    @Test
    public void setHardSkillNameTest() {
        HardskillDTO nhs = new HardskillDTO();
        nhs.setHardSkillName("Head banging");
        assertEquals(nhs.getHardSkillName(), hardSk.getHardSkillName());
    }

    @Test
    public void getHardSkillId() {
        assertTrue(hardSk.getHardSkillId() > 0 && hardSk.getHardSkillId() < Integer.MAX_VALUE);
    }

    @Test
    public void testEquals() {
        HardskillDTO neu = new HardskillDTO();
        neu.setHardSkillName("Head banging");
        neu.setHardSkillId(101);
        assertTrue(hardSk.getHardSkillId() == neu.getHardSkillId()
                && hardSk.getHardSkillName() == neu.getHardSkillName());
    }
}