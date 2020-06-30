package com.stealthyalda.ai.model.dtos;

import com.stealthyalda.ai.model.entities.Arbeitgeber;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StellenanzeigeDTOTest {

    private int stellenanzeigeID;
    private String titel;
    private String beschreibung;
    private String status;
    private LocalDate datum;
    private int arbeitgeberID;
    private String ort;
    private List<Anforderung> anforderungs ;
    private Arbeitgeber unternehmen;
    StellenanzeigeDTO stellenanzeigeDTO;

    @Before
    public void setup(){
        anforderungs = new ArrayList<>();
        stellenanzeigeDTO = new StellenanzeigeDTO();
        stellenanzeigeDTO.setOrt("Bonn");
        stellenanzeigeDTO.setTitel("Test");
        stellenanzeigeDTO.setBeschreibung("Beschreibung");
        stellenanzeigeDTO.setStellenanzeigeID(100);
        stellenanzeigeDTO.setStatus("Offen");
        Anforderung a1 = new Anforderung();
        a1.setAnforderung("Anforderung 1");
        Anforderung a2 = new Anforderung();
        a2.setAnforderung("Anforderung 2");
        anforderungs.add(a1);
        anforderungs.add(a2);

        unternehmen = new Arbeitgeber();

        stellenanzeigeDTO.setAnforderungs(anforderungs);
        unternehmen.setArbeitgeberId(50);
        unternehmen.setUnternehmen("Test");

        stellenanzeigeDTO.setUnternehmen(unternehmen);
    }

    @Test
    public void getStellenanzeige(){
        assertTrue(anforderungs.size()==2);
        assertEquals(stellenanzeigeDTO.getStellenanzeigeID(),100);
        assertNotNull(stellenanzeigeDTO.getUnternehmen().getArbeitgeberId());
        assertEquals(stellenanzeigeDTO.getUnternehmen().getUnternehmen(), "Test");
        assertEquals(stellenanzeigeDTO.getOrt(),"Bonn");
        assertEquals("Test", stellenanzeigeDTO.getTitel());
        assertEquals("Offen",stellenanzeigeDTO.getStatus());
        assertEquals("Beschreibung", stellenanzeigeDTO.getBeschreibung());
    }
}
