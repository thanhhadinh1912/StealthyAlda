package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class StellenanzeigeDAOTest {
    StellenanzeigeDAO dao;
    Arbeitgeber a;
    String arbeitgeber;
    StellenanzeigeDTO s;
    Stellenanzeige san = new Stellenanzeige();

    @Before
    public void setup(){
        dao = new StellenanzeigeDAO();
        a = new Arbeitgeber();
        arbeitgeber = "Test test";
        s = new StellenanzeigeDTO();
        s.setStellenanzeigeID(19);
        s.setStatus("Offen");
        LocalDate date = LocalDate.of(2020,7,1);
        a.setArbeitgeberId(44);
        san.setTitel("Webentwicklung");
        san.setBeschreibung("Lorem Ipsum");
        san.setStatus("");
        san.setDatum(date);
        san.setOrt("Bonn");
    }
    @Test
    public void getInstance() {
        assertNotNull(StellenanzeigeDAO.getInstance());
    }
    @Test
    public void getStellenanzeige() {
        StellenanzeigeDTO dtotest = new StellenanzeigeDTO();
        StellenanzeigeDAO sdao = StellenanzeigeDAO.getInstance();

        dtotest.setTitel(s.getTitel());
        dtotest.setBeschreibung(s.getBeschreibung());
        dtotest.setOrt(s.getOrt());
        dtotest.setStatus(s.getStatus());
        StellenanzeigeDTO sdto = sdao.getStellenanzeige(s.getTitel(),s.getBeschreibung(),s.getOrt(),s.getStatus());

        assertEquals(dtotest.getTitel(), sdto.getTitel());
        assertEquals(dtotest.getBeschreibung(), sdto.getBeschreibung());
        assertEquals(dtotest.getOrt(), sdto.getOrt());
    }

    @Test
    public void createStellenanzeige() {
        StellenanzeigeDAO sdao = mock(StellenanzeigeDAO.class);
        assertDoesNotThrow(() -> sdao.createStellenanzeige(san));
    }

    @Test
    public void getStellenanzeigeByArbeitgeber(){
        List<StellenanzeigeDTO> list = dao.getStellenanzeigeByArbeitgeber("Test test");
        assertEquals(list.get(0).getStellenanzeigeID(), 19);
        assertEquals(list.get(0).getArbeitgeberID(),0);
    }

    @Test
    public void getStellenanzeigeByLocation() {
    }

    @Test
    public void getStellenanzeigeByJobTitelOrUnternehmen() {
    }

    @Test
    public void getStellenanzeigeByLocationAndJobTitelOrUnternehmen() {
    }

    @Test
    public  void getJobangebot() {
        StellenanzeigeDAO dos = mock(StellenanzeigeDAO.class);
        assertDoesNotThrow(() -> dos.getJobangebot(s.getStellenanzeigeID()));
    }

    @Test
    public void updateStatusStellenanzeige() {
        assertTrue(dao.updateStatusStellenanzeige(s));
    }

    @Test
    public void deleteStellenanzeige() {
    }
}