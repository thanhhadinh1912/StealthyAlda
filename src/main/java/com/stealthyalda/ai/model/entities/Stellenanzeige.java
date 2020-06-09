package com.stealthyalda.ai.model.entities;

import java.util.Date;

public class Stellenanzeige {
    private int stellenanzeigeID;
    private String titel;
    private String beschreibung;
    private String status;
    private Date datum;
    private String fachbereich;
    private int arbeitgeber_id;
    private String ort;
    private String unternehmen;

    public int getArbeitgeber_id() {
        return arbeitgeber_id;
    }

    public void setArbeitgeber_id(int arbeitgeber_id) {
        this.arbeitgeber_id = arbeitgeber_id;
    }

    public int getStellenanzeige_id() {
        return stellenanzeigeID;
    }

    public void setStellenanzeige_id(int stellenanzeigeID) {
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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getFachbereich() {
        return fachbereich;
    }

    public void setFachbereich(String fachbereich) {
        this.fachbereich = fachbereich;
    }
    public String getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(String unternehmen) {
        this.unternehmen = unternehmen;
    }


}
