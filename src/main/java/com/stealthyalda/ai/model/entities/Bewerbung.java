package com.stealthyalda.ai.model.entities;

import java.sql.Date;

public class Bewerbung {
    private int id;
    private int matrikelnr;
    private int stellenanzeigeid;
    private String status;
    private Date datum;
    private String anschreiben;
    private String erfahrung;
    private String zertifikat;

    public String getAnschreiben() {
        return anschreiben;
    }

    public void setAnschreiben(String anschreiben) {
        this.anschreiben = anschreiben;
    }

    public String getErfahrung() {
        return erfahrung;
    }

    public void setErfahrung(String erfahrung) {
        this.erfahrung = erfahrung;
    }

    public String getZertifikat() {
        return zertifikat;
    }

    public void setZertifikat(String zertifikat) {
        this.zertifikat = zertifikat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatrikelnr() {
        return matrikelnr;
    }

    public void setMatrikelnr(int matrikelnr) {
        this.matrikelnr = matrikelnr;
    }

    public int getStellenanzeigeid() {
        return stellenanzeigeid;
    }

    public void setStellenanzeigeid(int stellenanzeigeid) {
        this.stellenanzeigeid = stellenanzeigeid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

}
