/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.factories;

import com.stealthyalda.ai.model.dtos.StellenanzeigeRequest;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Stellenanzeige;

/**
 *
 * @author WINDOWS
 */
public class StellenanzeigeFactory {
    public static Stellenanzeige createStellenanzeige(StellenanzeigeRequest request, Arbeitgeber a){
        Stellenanzeige s = new Stellenanzeige();
        
        s.setArbeitgeberID(a.getArbeitgeber_id());
        s.setBeschreibung(request.getBeschreibung());
        s.setDatum(request.getDatum());
        s.setOrt(request.getOrt());
        s.setTitel(request.getTitel());
        s.setStatus(request.getStatus());
        return s;
    }
}
