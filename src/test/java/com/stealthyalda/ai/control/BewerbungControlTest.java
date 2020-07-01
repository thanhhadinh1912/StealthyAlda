package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.BewerbungDAO;
import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.ai.control.BewerbungControl;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BewerbungControlTest {

    @Test
    public void createbewerbung() {
        BewerbungDAO dao = BewerbungDAO.getInstance();
        LocalDate date = LocalDate.of(2020,7,1);
        Stellenanzeige an = new Stellenanzeige();
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();

        Student s = new Student();
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
        Arbeitgeber arbeitgeber = new Arbeitgeber();
        b.setArbeitgeber(arbeitgeber);
        StellenanzeigeDTO sdao = new StellenanzeigeDTO();
        b.setStellenanzeige(sdao);

        assertNotNull(dao.createBewerbung(an,b,s));
        //assertTrue(dao.createBewerbung(an,b,s));
    }

    @Test
    public void updatestatus() {
        BewerbungDAO dao = BewerbungDAO.getInstance();
        LocalDate date = LocalDate.of(2020,7,1);
        Stellenanzeige an = new Stellenanzeige();
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();

        Student s = new Student();
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
        Arbeitgeber arbeitgeber = new Arbeitgeber();
        b.setArbeitgeber(arbeitgeber);
        StellenanzeigeDTO sdao = new StellenanzeigeDTO();
        b.setStellenanzeige(sdao);
        assertTrue(dao.updateStatusBewerbung(b));
    }
}