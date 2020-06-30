package com.stealthyalda.ai.model.dtos;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JoberfahrungDTOTest {

    @Test
    public void getJoberfahrungId() {
        JoberfahrungDTO j = new JoberfahrungDTO();
        j.setJoberfahrungId(1);
        assertEquals(1,j.getJoberfahrungId());

    }

    @Test
    public void setJoberfahrungId() {
        JoberfahrungDTO j = new JoberfahrungDTO();
        j.setJoberfahrungId(1);
        assertEquals(1,j.getJoberfahrungId());
    }

    @Test
    public void getJoberfahrung() {
        JoberfahrungDTO j = new JoberfahrungDTO();
        j.setJoberfahrung("1 Jahr");
        assertEquals("1 Jahr",j.getJoberfahrung());
    }

    @Test
    public void setJoberfahrung() {
        JoberfahrungDTO j = new JoberfahrungDTO();
        j.setJoberfahrung("1 Jahr");
        assertEquals("1 Jahr",j.getJoberfahrung());
    }
}