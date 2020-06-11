package com.stealthyalda.services.util;

public class Adresse {
    private String ort;
    private String strasse;
    private int plz;
    private String hausnummer;

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

    private int adresseID;

    public int getAdresseID() {
        return adresseID;
    }

    public void setAdresseID(int id) {
        this.adresseID = id;
    }
}
