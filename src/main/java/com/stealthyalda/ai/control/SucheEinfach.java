package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Stellenanzeige;

import java.util.List;

public class SucheEinfach {
    public static SucheEinfach search = null;

    private SucheEinfach() {

    }

    public static SucheEinfach getInstance() {
        if (search == null) {
            search = new SucheEinfach();
        }
        return search;
    }

    public List<StellenanzeigeDTO> getStellenanzeigeByLocationOrJobTitelOrUnternehment(String titel, String ort) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByLocationOrJobTitelOrUnternehmen(titel, ort);
    }
}