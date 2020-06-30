package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;

import java.util.List;

public class SucheEinfach implements SucheEinfachInt {
    private static SucheEinfach sucheEinfach = null;

    private SucheEinfach() {
    }

    public static SucheEinfach getInstance() {
        if (sucheEinfach == null) {
            sucheEinfach = SucheEinfach.getInstance();
        }
        return sucheEinfach;
    }

    @Override
    public List<StellenanzeigeDTO> getStellenanzeigeByLocationAndJobTitelOrUnternehment(String titel, String ort) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByLocationAndJobTitelOrUnternehmen(titel, ort);
    }

    @Override
    public List<StellenanzeigeDTO> getStellenanzeigeByLocation(String ort) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByLocation(ort);
    }

    @Override
    public List<StellenanzeigeDTO> getStellenanzeigeByJob(String job) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByJobTitelOrUnternehmen(job);
    }
}