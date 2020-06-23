package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;

import java.util.List;

public class ProfilArbeitgeberControl {
    public List<StellenanzeigeDTO> getStellenanzeige(String arbeitgeber){
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByArbeitgeber(arbeitgeber);
    }
}
