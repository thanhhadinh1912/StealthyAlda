package com.stealthyalda.ai.model.entities;

import com.stealthyalda.ai.model.dtos.Anforderung;

import java.time.LocalDate;
import java.util.List;

public class Stellenanzeige {
    private int stellenanzeigeID;
    private String titel;
    private String beschreibung;
    private String status;
    private LocalDate datum;
    private int arbeitgeberID;
    private String ort;
    private List<Anforderung> anforderungs;


    public int getArbeitgeberID() {
        return arbeitgeberID;
    }

    public List<Anforderung> getAnforderungs(){return anforderungs;}

    public void setAnforderungs(List<Anforderung>anforderungs){this.anforderungs=anforderungs;}

    public void setArbeitgeberID(int arbeitgeberID) {
        this.arbeitgeberID = arbeitgeberID;
    }

    public int getStellenanzeigeID() {
        return stellenanzeigeID;
    }

    public void setStellenanzeigeID(int stellenanzeigeID) {
        this.stellenanzeigeID = stellenanzeigeID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }


    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
