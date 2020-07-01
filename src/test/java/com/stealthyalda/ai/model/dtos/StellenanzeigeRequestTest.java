package com.stealthyalda.ai.model.dtos;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StellenanzeigeRequestTest {

    @Test
    public void getStatus() {
        StellenanzeigeRequest s = new StellenanzeigeRequest();
        s.setStatus("status");
        assertEquals("status", s.getStatus());
    }

    @Test
    public void setStatus() {
        StellenanzeigeRequest s = new StellenanzeigeRequest();
        s.setStatus("status");
        assertEquals("status", s.getStatus());
    }

    @Test
    public void getDatum() {
    }

    @Test
    public void setDatum() {
    }

    @Test
    public void getTitel() {
        StellenanzeigeRequest s = new StellenanzeigeRequest();
        s.setTitel("titel");
        assertEquals("titel", s.getTitel());

    }

    @Test
    public void setTitel() {
        StellenanzeigeRequest s = new StellenanzeigeRequest();
        s.setTitel("titel");
        assertEquals("titel", s.getTitel());
    }

    @Test
    public void getBeschreibung() {
        StellenanzeigeRequest s = new StellenanzeigeRequest();
        s.setBeschreibung("beschreibung");
        assertEquals("beschreibung", s.getBeschreibung());
    }

    @Test
    public void setBeschreibung() {
        StellenanzeigeRequest s = new StellenanzeigeRequest();
        s.setBeschreibung("beschreibung");
        assertEquals("beschreibung", s.getBeschreibung());
    }

    @Test
    public void getOrt() {
        StellenanzeigeRequest s = new StellenanzeigeRequest();
        s.setOrt("koeln");
        assertEquals("koeln", s.getOrt());
    }

    @Test
    public void setOrt() {
        StellenanzeigeRequest s = new StellenanzeigeRequest();
        s.setOrt("koeln");
        assertEquals("koeln", s.getOrt());
    }

    @Test
    public void getAnforderung() {
        StellenanzeigeRequest s = new StellenanzeigeRequest();
        Anforderung anf = new Anforderung();
        anf.setAnforderung("anf");
        s.setAnforderung(anf);
        assertEquals("anf", anf.getAnforderung());
    }

    @Test
    public void setAnforderung() {
        StellenanzeigeRequest s = new StellenanzeigeRequest();
        Anforderung anf = new Anforderung();
        anf.setAnforderung("anf");
        s.setAnforderung(anf);
        assertEquals("anf", anf.getAnforderung());
    }
}