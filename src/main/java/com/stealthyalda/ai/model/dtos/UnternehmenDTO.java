package com.stealthyalda.ai.model.dtos;

import com.stealthyalda.ai.model.entities.Benutzer;

public class UnternehmenDTO extends Benutzer {
    private String beschreibung;
    private String unternehmen;
    private int arbeitgeberId;

    /**
     * Get Beschreibung of the company
     *
     * @return String Beschreibung des Unternehmens
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Set Beschreibung of the company
     *
     * @param beschreibung Beschreibungstext
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * Get Unternehmen's name
     *
     * @return String name of Unternehmen
     */
    public String getUnternehmen() {
        return unternehmen;
    }

    /**
     * Set Unternehmen's name
     *
     * @param unternehmenName Name of Unternehmen
     */
    public void setUnternehmen(String unternehmenName) {
        this.unternehmen = unternehmenName;
    }

    /**
     * get id of Unternehmen
     *
     * @return int id of Unternehmen for this instance
     */
    public int getArbeitgeberId() {
        return arbeitgeberId;
    }

    /**
     * set id of Unternehmen for this instance
     *
     * @param arbeitgeberId int id of Unternehmen from db
     */
    public void setArbeitgeberId(int arbeitgeberId) {
        this.arbeitgeberId = arbeitgeberId;
    }


}
