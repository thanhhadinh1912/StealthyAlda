package com.stealthyalda.ai.model.dtos;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdresseTest {

    @Test
    public void getOrt() {
        Adresse adr = new Adresse();
        adr.setOrt("koeln");
        assertEquals("koeln", adr.getOrt());
    }

    @Test
    public void setOrt() {
        Adresse adr = new Adresse();
        adr.setOrt("koeln");
        assertEquals("koeln", adr.getOrt());
    }

    @Test
    public void getStrasse() {
        Adresse adr = new Adresse();
        adr.setStrasse("HauptStr");
        assertEquals("HauptStr", adr.getStrasse());
    }

    @Test
    public void setStrasse() {
        Adresse adr = new Adresse();
        adr.setStrasse("HauptStr");
        assertEquals("HauptStr", adr.getStrasse());
    }

    @Test
    public void getPlz() {
        Adresse adr = new Adresse();
        adr.setPlz(51147);
        assertEquals(51147, adr.getPlz());
    }

    @Test
    public void setPlz() {
        Adresse adr = new Adresse();
        adr.setPlz(51147);
        assertEquals(51147, adr.getPlz());
    }

    @Test
    public void getHausnummer() {
        Adresse adr = new Adresse();
        adr.setHausnummer("51");
        assertEquals("51", adr.getHausnummer());
    }

    @Test
    public void setHausnummer() {
        Adresse adr = new Adresse();
        adr.setHausnummer("51");
        assertEquals("51", adr.getHausnummer());
    }

    @Test
    public void getAdresseID() {
        Adresse adr = new Adresse();
        adr.setAdresseID(2);
        assertEquals(2, adr.getAdresseID());
    }

    @Test
    public void setAdresseID() {
        Adresse adr = new Adresse();
        adr.setAdresseID(2);
        assertEquals(2, adr.getAdresseID());
    }

    @Test
    public void testToString() {
        Adresse adr = new Adresse();
        adr.setOrt("koeln");
        adr.setStrasse("HauptStr");
        adr.setPlz(51147);
        adr.setHausnummer("51");
        adr.setAdresseID(2);

        assertEquals("HauptStr 51\n51147koeln", adr.toString());

    }
}