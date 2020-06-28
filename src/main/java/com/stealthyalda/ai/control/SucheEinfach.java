package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;

import java.util.List;

public class SucheEinfach {
    private static SucheEinfach search = null;

    private SucheEinfach() {

    }

    public static SucheEinfach getInstance() {
        if (search == null) {
            search = new SucheEinfach();
        }
        return search;
    }

    public List<StellenanzeigeDTO> getStellenanzeigeByLocationAndJobTitelOrUnternehment(String titel, String ort) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByLocationAndJobTitelOrUnternehmen(titel, ort);
    }

    public List<StellenanzeigeDTO> getStellenanzeigeByLocation(String ort) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByLocation(ort);
    }

    public List<StellenanzeigeDTO> getStellenanzeigeByJob(String job) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByJobTitelOrUnternehmen(job);
    }
}