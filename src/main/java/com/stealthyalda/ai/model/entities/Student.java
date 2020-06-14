/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.entities;

/**
 *
 * @author WINDOWS
 */
public class Student extends Benutzer{
    private String nachname;
    private String vorname;
    private int student_id;
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

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public byte getProfilbild() {
        return profilbild;
    }

    public void setProfilbild(byte profilbild) {
        this.profilbild = profilbild;
    }
}
