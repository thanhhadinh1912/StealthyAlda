package com.stealthyalda.ai.model.entities;

import com.stealthyalda.ai.model.dtos.Anforderung;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StellenanzeigeTest {

    private Stellenanzeige stellenanzeige;
    private final int stellenanzeigeID = 5;
    private final String titel = "IT-Support";
    private final String beschreibung = "XXXX";
    private final String status = "Werkstudent";
    private LocalDate datum;
    private final int arbeitgeberID = 10;
    private final String ort = "Bonn";
    private List<Anforderung> anforderungs;


    @Before
    public void setup() {
        stellenanzeige = new Stellenanzeige();
        stellenanzeige.setStellenanzeigeID(stellenanzeigeID);
        stellenanzeige.setTitel(titel);

        stellenanzeige.setBeschreibung(beschreibung);
        stellenanzeige.setStatus(status);
        stellenanzeige.setArbeitgeberID(arbeitgeberID);

        stellenanzeige.setOrt(ort);
        stellenanzeige.setAnforderungs(anforderungs);
        stellenanzeige.setDatum(datum);

    }

    @Test
    public void testget() {
        assertEquals(stellenanzeigeID, stellenanzeige.getStellenanzeigeID());
        assertNotNull(stellenanzeige.getStellenanzeigeID());
        assertEquals(titel, stellenanzeige.getTitel());
        assertEquals(beschreibung, stellenanzeige.getBeschreibung());
        assertEquals(status, stellenanzeige.getStatus());
        assertEquals(arbeitgeberID, stellenanzeige.getArbeitgeberID());
        assertNotNull(stellenanzeige.getArbeitgeberID());
        assertEquals(ort, stellenanzeige.getOrt());
        assertEquals(anforderungs, stellenanzeige.getAnforderungs());
        assertEquals(datum, stellenanzeige.getDatum());


    }
}
