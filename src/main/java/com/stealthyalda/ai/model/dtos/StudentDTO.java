package com.stealthyalda.ai.model.dtos;

public class StudentDTO extends DTOs {
    private String nachname;
    private String vorname;
    private String anrede;
    private int studentId;
    private byte profilbild;

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public byte getProfilbild() {
        return profilbild;
    }

    public void setProfilbild(byte profilbild) {
        this.profilbild = profilbild;
    }


}
