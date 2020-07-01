package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import org.junit.Test;

public class FeatureToogleTest {

    @Test
    public void testFeatureToggle() throws DatabaseException {
        ToogleRouter.isEnabled("bewerbung");
    }
}
