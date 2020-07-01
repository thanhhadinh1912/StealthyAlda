package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.BewerbungDAO;
import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.ai.model.entities.Student;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BewerbungControlTest {
    BewerbungDAO dao;
    LocalDate date;
    Stellenanzeige an;
    BewerbungCollAtHBRSDTO b;
    Student s;
    Arbeitgeber arbeitgeber;
    StellenanzeigeDTO sdao;

    @Before
    public void setUp() {
        dao = BewerbungDAO.getInstance();
        date = LocalDate.of(2020, 7, 1);
        an = new Stellenanzeige();
        b = new BewerbungCollAtHBRSDTO();
        s = new Student();
        s.setId(78);
        s.setVorname("Hans");
        s.setNachname("Mueller");

        an.setTitel("");
        an.setStellenanzeigeID(99);
        an.setOrt("");

        b.setAnschreiben("Anschreiben");
        b.setErfahrung("Erfahrung");
        b.setZertifikat("Zertifikat");
        b.setDatum(date);
        b.setId(an.getStellenanzeigeID());
        b.setStudent(s);
        b.setStatus("");

        arbeitgeber = new Arbeitgeber();
        b.setArbeitgeber(arbeitgeber);
        sdao = new StellenanzeigeDTO();
        b.setStellenanzeige(sdao);
    }

    @Test
    public void createbewerbung() {
        assertNotNull(dao.createBewerbung(an, b, s));
    }

    @Test
    public void updatestatus() {
        assertTrue(dao.updateStatusBewerbung(b));
    }
}
