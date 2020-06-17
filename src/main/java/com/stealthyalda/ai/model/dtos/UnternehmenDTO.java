package com.stealthyalda.ai.model.dtos;

public class UnternehmenDTO extends DTOs {
    private String beschreibung;
    private String unternehmen;
    private int arbeitgeberId;

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(String unternehmen) {
        this.unternehmen = unternehmen;
    }

    public int getArbeitgeberId() {
        return arbeitgeberId;
    }

    public void setArbeitgeberId(int arbeitgeberId) {
        this.arbeitgeberId = arbeitgeberId;
    }


}
