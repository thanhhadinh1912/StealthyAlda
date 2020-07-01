package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.entities.Arbeitgeber;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchDAOTest {
    SearchDAO dao;
    Arbeitgeber a;

    @Before
    public void setup() {
        dao = SearchDAO.getInstance();
        a = new Arbeitgeber();
        a.setArbeitgeberId(17);
    }

    @Test
    public void getBewerber() {

        List<String> list = dao.getBewerber(a);
        assertEquals(list.get(0), "ingo");
    }

    @Test
    public void getStellenanzeigeFürArbeitgeber() {
        List<String> list = dao.getStellenanzeigeFuerArbeitgeber(a);
        assertEquals("Mitarbeiter in der Hochschulverwaltung (m/w/d)", list.get(0));
    }

    @Test
    public void getOrt() {
        List<String> list = dao.getOrt();
        assertTrue(list.contains("Bad Honnef"));
    }

    @Test
    public void getTitel() {
        List<String> list = dao.getJobtitelOrArbeitgeber();
        assertTrue(list.contains("Test abgabe"));
        assertTrue(list.contains("Test test"));

    }
}
