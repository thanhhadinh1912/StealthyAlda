package com.stealthyalda.ai.model.dtos;

import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

public class BewerbungDTOTest {

    BewerbungCollAtHBRSDTO bewerb;

    @Before
    public void setUp() {
        bewerb = new BewerbungCollAtHBRSDTO();
        LocalDate datum = null;
        bewerb.setStatus("gesendet");
        bewerb.setId(1);
        bewerb.setZertifikat("zertifikat");
        bewerb.setErfahrung("erfahrung");
        bewerb.setAnschreiben("motivation");
        Student s = new Student();
        s.setEmail("ingo");
        s.setPasswort("ingo");
        bewerb.setStudent(s);
        Arbeitgeber a = new Arbeitgeber();
        a.setUnternehmen("StealthyAlda");
        bewerb.setArbeitgeber(a);
        StellenanzeigeDTO st = new StellenanzeigeDTO();
        st.setTitel("Test");
        bewerb.setStellenanzeige(st);
        bewerb.setDatum(datum);
    }

    @Test
    public void getTest() {
        Assertions.assertEquals("gesendet", bewerb.getStatus());
        Assertions.assertEquals("zertifikat", bewerb.getZertifikat());
        Assertions.assertEquals("motivation", bewerb.getAnschreiben());
        Assertions.assertEquals("erfahrung", bewerb.getErfahrung());
        Assertions.assertEquals("ingo", bewerb.getStudent().getEmail());
        Assertions.assertEquals("ingo", bewerb.getStudent().getPasswort());
        Assertions.assertEquals("StealthyAlda", bewerb.getArbeitgeber().getUnternehmen());
        Assertions.assertEquals("Test", bewerb.getStellenanzeige().getTitel());
        Assertions.assertEquals(1, bewerb.getId());
        Assertions.assertSame(null, bewerb.getDatum());
    }

}
