package com.stealthyalda.ai.model.dtos;

public class StudentDTO extends BenutzerDTO {
    private String nachname;
    private String vorname;
    private byte profilbild;
    private String strasse;
    private String wohnort;
    private String plz;
    private String telefonNr;

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getWohnort() {
        return wohnort;
    }

    public void setWohnort(String wohnort) {
        this.wohnort = wohnort;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(String telefonNr) {
        this.telefonNr = telefonNr;
    }

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

    public byte getProfilbild() {
        return profilbild;
    }

    public void setProfilbild(byte profilbild) {
        this.profilbild = profilbild;
    }
}
