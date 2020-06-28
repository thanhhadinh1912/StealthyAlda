package com.stealthyalda.ai.model.dtos;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class StudentDTOTest {
    StudentDTO testStudent;
    String nachname = "Test-Object";
    String vorname = "Student";
    byte profilePic = "f304d7e8c67bbbb91e9bfb999a95182e".getBytes()[0];
    int id = 101;
    String telefon = "0199999999";
    String anrede = "Sir";

    @Before
    public void setUp() {
        Adresse a = new Adresse();
        a.setAdresseID(201);
        a.setHausnummer("420");
        a.setOrt("Bonn");
        a.setPlz(53225);
        a.setStrasse("Königsstr.");

        testStudent = new StudentDTO();
        testStudent.setStudentId(id);
        testStudent.setNachname(nachname);
        testStudent.setVorname(vorname);
        testStudent.setProfilbild(profilePic);
        testStudent.setTelefonnummer(telefon);
        testStudent.setAnrede(anrede);
        testStudent.setAdresse(a);
    }

    @Test
    public void getNachname() {
        assertEquals(nachname, testStudent.getNachname());
    }

    @Test
    public void setNachname() {
        testStudent.setNachname(nachname + vorname);
        assertNotNull(testStudent.getNachname());
    }

    @Test
    public void getVorname() {
        assertEquals(vorname, testStudent.getVorname());
        testStudent.setVorname(null);
        try {
            assertNull(testStudent.getVorname());
        } catch (AssertionError e) {
            Logger.getLogger(StudentDTOTest.class.getName()).log(Level.SEVERE, e.getMessage());
        }

    }

    @Test
    public void setVorname() {
        assertNotNull(testStudent.getVorname());
        testStudent.setVorname(null);
        assertEquals(null, testStudent.getVorname());
        testStudent.setVorname("New Name!");
        assertNotNull(testStudent.getVorname());
    }

    @Test
    public void getAnrede() {
        assertNotNull(testStudent.getAnrede());
        testStudent.setAnrede(null);
        try {
            assertNull(testStudent.getAnrede());
        } catch (AssertionError e) {
            throw new AssertionError();
        }

        testStudent.setAnrede("King: ");
        assertNotNull(testStudent.getAnrede());
        assertEquals("King: ", testStudent.getAnrede());
    }

    @Test
    public void setAnrede() {

    }

    @Test
    public void getStudentId() {
    }

    @Test
    public void setStudentId() {
    }

    @Test
    public void getProfilbild() {
    }

    @Test
    public void setProfilbild() {
    }
}