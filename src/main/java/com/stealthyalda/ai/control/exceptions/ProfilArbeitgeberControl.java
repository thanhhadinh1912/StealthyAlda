package com.stealthyalda.ai.control.exceptions;

import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;

import java.util.List;

public class ProfilArbeitgeberControl {
    public List<StellenanzeigeDTO> getStellenanzeige(int arbeitgeberid){
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByArbeitgeber(arbeitgeberid);
    }
}
