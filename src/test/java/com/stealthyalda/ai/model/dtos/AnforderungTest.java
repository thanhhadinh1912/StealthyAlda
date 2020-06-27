package com.stealthyalda.ai.model.dtos;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnforderungTest {
    Anforderung an;
    String anfor;

    @Before
    public void setUp() throws Exception {
        an = new Anforderung();
        anfor = "Sie müssen java Kenntnisse vorweisen";
    }

    @Test
    public void getAnforderung() {
        an.setAnforderung(anfor);
        assertEquals(anfor, an.getAnforderung());
    }

    @Test
    public void setAnforderung() {
        an.setAnforderung(anfor);
        an.setAnforderung(anfor + anfor);
        assertEquals(anfor + anfor, an.getAnforderung());
        an.setAnforderung(null);
        assertNull(an.getAnforderung());
    }
}