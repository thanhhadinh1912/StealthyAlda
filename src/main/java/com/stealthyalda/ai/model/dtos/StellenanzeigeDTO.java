/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dtos;

import com.stealthyalda.ai.model.entities.Stellenanzeige;

import java.time.LocalDate;

/**
 * @author WINDOWS
 */
public class StellenanzeigeDTO extends Stellenanzeige {
    private String arbeitgeber;



    public String getArbeitgeber() {
        return arbeitgeber;
    }

    public void setArbeitgeber(String arbeitgeber) {
        this.arbeitgeber = arbeitgeber;
    }


}
