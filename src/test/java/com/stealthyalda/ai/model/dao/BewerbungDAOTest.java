package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.dao.BewerbungDAO;
import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.ai.control.BewerbungControl;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import java.time.LocalDate;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BewerbungDAOTest {

    @Test
    public void getInstance() {
        assertNotNull(BewerbungDAO.getInstance());
    }

    @Test
    public void createBewerbung() {
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
        //asserTrue(dao.createBewerbung(an,b,s);
    }

    /*
    @Test
    public void getBewerbungFromStudent() {
    }

    @Test
    public void getBewerbungFromArbeitgeber() {
    }
    */

    @Test
    public void updateStatusBewerbung() {
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