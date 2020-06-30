package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StellenanzeigeDAOTest {
    StellenanzeigeDAO dao;
    Arbeitgeber a;
    String arbeitgeber;
    StellenanzeigeDTO s;
    @Before
    public void setup(){
        dao = new StellenanzeigeDAO();
        a = new Arbeitgeber();
        arbeitgeber = "Test test";
        s = new StellenanzeigeDTO();
        s.setStellenanzeigeID(19);
        s.setStatus("Offen");
    }
    @Test
    public void getStellenanzeigeByArbeitgeber(){
        List<StellenanzeigeDTO> list = dao.getStellenanzeigeByArbeitgeber("Test test");
        assertEquals(list.get(0).getStellenanzeigeID(), 19);
        assertEquals(list.get(0).getArbeitgeberID(),17);
    }
    @Test
    public void updateStatus(){
        assertTrue(dao.updateStatusStellenanzeige(s));
    }

}
