/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dtos;

/**
 *
 * @author WINDOWS
 */
public class Adresse {
    
private String ort;
    private String strasse;
    private int plz;
    private String hausnummer;
    private int adresseID;
    public  Adresse (){

    }

    public Adresse(String strasse, int plz, String hausnummer, String ort) {
        this.strasse = strasse;
        this.plz = plz;
        this.hausnummer = hausnummer;
        this.ort = ort;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public int getAdresseID() {
        return adresseID;
    }

    public void setAdresseID(int id) {
        this.adresseID = id;
    }

    public String toString(){

        return strasse + hausnummer + "\n" + Integer.toString(plz) + ort;
    }
}

