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
public class Arbeitgeber extends Benutzer{
    private byte logo;
    private String beschreibung;
    private String unternehmen;
    private int arbeitgeber_id;
    

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

    public int getArbeitgeber_id() {
        return arbeitgeber_id;
    }

    public void setArbeitgeber_id(int arbeitgeber_id) {
        this.arbeitgeber_id = arbeitgeber_id;
    }
}
