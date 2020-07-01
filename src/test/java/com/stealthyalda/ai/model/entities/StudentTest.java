package com.stealthyalda.ai.model.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentTest {

    private Student student;
    private final String nachname = "Müller";
    private final String vorname = "Hans";
    private final int studentId = 2;
    private byte profilbild;


    @Before
    public void setup() {
        student = new Student();
        student.setVorname(vorname);
        student.setNachname(nachname);
        student.setStudentId(studentId);
        student.setProfilbild(profilbild);

    }

    @Test
    public void testget() {
        assertEquals(studentId, student.getStudentId());
        assertNotNull(student.getStudentId());
        assertEquals(vorname, student.getVorname());
        assertEquals(nachname, student.getNachname());
        assertEquals(profilbild, student.getProfilbild());

    }
}
