package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.dao.BenutzerDAO;
import com.stealthyalda.ai.model.dtos.UnternehmenDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.views.Unternehmen;

import com.vaadin.server.VaadinSession;
import org.cdisource.logging.SystemOutLogger;
import org.junit.Test;

import static com.stealthyalda.ai.model.dao.BenutzerDAO.getUser;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class RegisterControlTest {


    @Test
   public void checkUserExists() throws UserExistsException, DatabaseException, NoSuchUserOrPassword {

        RegisterControl rc = mock(RegisterControl.class);
        assertFalse(rc.checkUserExists("ingo"));

    }

    @Test
   public void registerUser() throws DatabaseException, NoSuchUserOrPassword {

        BenutzerDAO myDAO=BenutzerDAO.getInstance();
        myDAO.createBenutzer("one","one","Student");
        Benutzer ben= myDAO.getBenutzer("one","one");
        System.out.println(ben.getEmail());

    }

    @Test
    public void registerArbeitgeber() {







    }

    @Test
    public void registerStudent() {
    }
}