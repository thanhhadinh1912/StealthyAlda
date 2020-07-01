package com.stealthyalda.ai.model.dtos;

import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Student;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BewerbungCollAtHBRSDTOTest {

    @Test
    public void testToString() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testClone() {
    }

    @Test
    public void getId() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        b.setId(2);
        assertEquals(2, b.getId());
    }

    @Test
    public void setId() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        b.setId(2);
        assertEquals(2, b.getId());
    }

    @Test
    public void getStatus() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        b.setStatus("Status");
        assertEquals("Status", b.getStatus());
    }

    @Test
    public void setStatus() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        b.setStatus("Status");
        assertEquals("Status", b.getStatus());
    }

    @Test
    public void getAnschreiben() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        b.setAnschreiben("Anschreiben");
        assertEquals("Anschreiben", b.getAnschreiben());
    }

    @Test
    public void setAnschreiben() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        b.setAnschreiben("Anschreiben");
        assertEquals("Anschreiben", b.getAnschreiben());
    }

    @Test
    public void getErfahrung() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        b.setErfahrung("Erfahrung");
        assertEquals("Erfahrung", b.getErfahrung());
    }

    @Test
    public void setErfahrung() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        b.setErfahrung("Erfahrung");
        assertEquals("Erfahrung", b.getErfahrung());
    }

    @Test
    public void getZertifikat() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        b.setZertifikat("Zert");
        assertEquals("Zert", b.getZertifikat());
    }

    @Test
    public void setZertifikat() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        b.setZertifikat("Zert");
        assertEquals("Zert", b.getZertifikat());
    }

    @Test
    public void getStudent() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        Student s = new StudentDTO();
        s.setVorname("peter");
        b.setStudent(s);
        assertEquals("peter", b.getStudent().getVorname());

    }

    @Test
    public void setStudent() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        Student s = new StudentDTO();
        s.setVorname("peter");
        b.setStudent(s);
        assertEquals("peter", b.getStudent().getVorname());
    }

    @Test
    public void getStellenanzeige() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        StellenanzeigeDTO s = new StellenanzeigeDTO();
        s.setOrt("koeln");
        b.setStellenanzeige(s);
        assertEquals("koeln", b.getStellenanzeige().getOrt());
    }

    @Test
    public void setStellenanzeige() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        StellenanzeigeDTO s = new StellenanzeigeDTO();
        s.setOrt("koeln");
        b.setStellenanzeige(s);
        assertEquals("koeln", b.getStellenanzeige().getOrt());
    }

    @Test
    public void getArbeitgeber() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        Arbeitgeber a = new Arbeitgeber();
        a.setUnternehmen("Microsoft");
        b.setArbeitgeber(a);
        assertEquals("Microsoft", b.getArbeitgeber().getUnternehmen());
    }

    @Test
    public void setArbeitgeber() {
        BewerbungCollAtHBRSDTO b = new BewerbungCollAtHBRSDTO();
        Arbeitgeber a = new Arbeitgeber();
        a.setUnternehmen("Microsoft");
        b.setArbeitgeber(a);
        assertEquals("Microsoft", b.getArbeitgeber().getUnternehmen());
    }

    @Test
    public void getDatum() {


    }

    @Test
    public void setDatum() {
    }
}