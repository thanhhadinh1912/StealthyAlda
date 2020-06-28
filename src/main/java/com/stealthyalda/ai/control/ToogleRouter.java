package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.ToogleFeatureDAO;

public class ToogleRouter {
    public static boolean isEnabled(String feature) throws DatabaseException {
        return (ToogleFeatureDAO.getInstance().checkFeature(feature));
    }
}
