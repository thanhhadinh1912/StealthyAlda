package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;

import java.util.List;

public class SucheEinfachProxy implements SucheEinfachInt {
    private static SucheEinfach search = null;

    private SucheEinfachProxy() {
    }

    public static SucheEinfach getInstance() {
        if (search == null) {
            search = SucheEinfach.getInstance();
        }
        return search;
    }

    @Override
    public List<StellenanzeigeDTO> getStellenanzeigeByLocationAndJobTitelOrUnternehment(String titel, String ort) {
        return search.getStellenanzeigeByLocationAndJobTitelOrUnternehment(titel, ort);
    }

    @Override
    public List<StellenanzeigeDTO> getStellenanzeigeByLocation(String ort) {
        return search.getStellenanzeigeByLocation(ort);
    }

    @Override
    public List<StellenanzeigeDTO> getStellenanzeigeByJob(String job) {

        return search.getStellenanzeigeByJob(job);
    }
}