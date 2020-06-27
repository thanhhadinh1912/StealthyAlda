/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dtos;

import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Stellenanzeige;

import java.time.LocalDate;

/**
 * @author WINDOWS
 */
public class StellenanzeigeDTO extends Stellenanzeige {
    private String arbeitgeber;
    private String ort;
    private Arbeitgeber unternehmen;

    public Arbeitgeber getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(Arbeitgeber unternehmen) {
        this.unternehmen = unternehmen;
    }



    public String getArbeitgeber() {
        return arbeitgeber;
    }

    public void setArbeitgeber(String arbeitgeber) {
        this.arbeitgeber = arbeitgeber;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
