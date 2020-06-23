/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.entities;

/**
 * @author WINDOWS
 */
public class Arbeitgeber extends Benutzer {
    private byte logo;
    private String beschreibung;
    private String unternehmen;
    private int arbeitgeberId;


    public byte getLogo() {
        return logo;
    }

    public void setLogo(byte logo) {
        this.logo = logo;
    }

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
