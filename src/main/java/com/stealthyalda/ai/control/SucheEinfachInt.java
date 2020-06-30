package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;

import java.util.List;

public interface SucheEinfachInt {
    List<StellenanzeigeDTO> getStellenanzeigeByLocationAndJobTitelOrUnternehment(String titel, String ort);

    List<StellenanzeigeDTO> getStellenanzeigeByLocation(String ort);

    List<StellenanzeigeDTO> getStellenanzeigeByJob(String job);
}
