package com.stealthyalda.ai.model.factories;

import com.stealthyalda.ai.model.dtos.StepstoneDTO;
import com.stealthyalda.ai.model.dtos.AnwendungDTOs;

public class StepstoneFactory implements AnwendungFactory{
    public AnwendungDTOs create() {
        return new StepstoneDTO();
    }


}
