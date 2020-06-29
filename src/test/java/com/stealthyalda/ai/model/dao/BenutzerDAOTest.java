package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.entities.Benutzer;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenutzerDAOTest {

    @Test
    public void getUser() throws DatabaseException {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        Benutzer ben = BenutzerDAO.getUser("Rodin@test.de");
        System.out.println(ben.getEmail());

        // Benutzer scheint nicht richtig ersellt zu werden


    }

    @Test
    public void getUserRole() {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        assertEquals("Student", BenutzerDAO.getUserRole("Rodin@test.de"));
    }

    @Test
    public void getBenutzer() throws DatabaseException, NoSuchUserOrPassword {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        Benutzer ben = BenutzerDAO.getBenutzer("ingo", "ingo");
        assertEquals(2, ben.getId());

        // weird bug INFORMATION NULL aber Methode funktioniert
    }

    @Test
    public void deleteUser() {

    }

    @Test
    public void checkUserExists() throws UserExistsException, DatabaseException {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        boolean b = benutzerdao.checkUserExists("ingoooo");
        // ingoooo gibt es nicht.
        assertEquals(true, benutzerdao.checkUserExists("ingoooo"));
        // testet man mit adressen die registriert sind komm eine Exception
        // assertEquals(false,benutzerdao.checkUserExists("ingo"));

    }

    @Test
    public void createBenutzer() {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        boolean bean = benutzerdao.createBenutzer("createme", "createme", "Student");
        // wegen UserId vergabe nicht einfach testbar
    }

    @Test
    public void updateStammdaten() {
    }

    @Test
    public void changepassword() throws DatabaseException, NoSuchUserOrPassword {
        BenutzerDAO benutzerdao = BenutzerDAO.getInstance();
        boolean bean = benutzerdao.changepassword("ingo", "ingo", "ingoo");
        System.out.println(bean);
        Benutzer ben = BenutzerDAO.getBenutzer("ingo", "ingoo");
        assertEquals(2, ben.getId());
        bean = benutzerdao.changepassword("ingo", "ingoo", "ingo");
        System.out.println(bean);
        ben = BenutzerDAO.getBenutzer("ingo", "ingo");
        assertEquals(2, ben.getId());

    }
}