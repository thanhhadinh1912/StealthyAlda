package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ToogleFeatureDAOTest {
    ToogleFeatureDAO dao;
    String feature;

    @Before
    public void setup() {
        dao = ToogleFeatureDAO.getInstance();
        feature = "bewerbung";
    }

    @Test
    public void checkFeature() throws DatabaseException {
        assertTrue(dao.checkFeature(feature));
    }
}
