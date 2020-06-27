package com.stealthyalda.ai.model.factories;

import com.stealthyalda.ai.model.dtos.AnwendungDTOs;
import com.stealthyalda.ai.model.dtos.StepstoneDTO;

public class StepstoneFactory implements AnwendungFactory {
    public AnwendungDTOs create() {
        return new StepstoneDTO();
    }


}
