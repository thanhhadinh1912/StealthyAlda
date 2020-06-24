package com.stealthyalda.ai.model.factories;

import com.stealthyalda.ai.model.dtos.CollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.AnwendungDTOs;

public class CollAtHBRSFactory implements AnwendungFactory {

    public AnwendungDTOs create() {
        return new CollAtHBRSDTO();
    }
}
