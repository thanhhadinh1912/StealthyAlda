package com.stealthyalda.ai.model.entities;

import com.stealthyalda.ai.model.dtos.Adresse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BenutzerTest {

    private Benutzer benutzer;

    private final int adresseId = 10;
    private final String email = "hanspeter@gmail.com";
    private final String passwort = "hallo12345";
    //private String telefonnummer = null;
    private final int id = 1;
    private final String anrede = "Herr";
    private final String role = "Projektmanager";
    private Adresse adresse;

    @Before
    public void setup() {
        benutzer = new Benutzer();
        benutzer.setAdresseId(adresseId);
        benutzer.setEmail(email);
        benutzer.setPasswort(passwort);
        //benutzer.setTelefonnummer(telefonnummer);
        benutzer.setId(id);
        benutzer.setAnrede(anrede);
        benutzer.setRole(role);
        benutzer.setAdresse(adresse);
    }

    @Test
    public void testget() {

        assertEquals(adresseId, benutzer.getAdresseId());
        assertNotNull(benutzer.getAdresseId());
        assertEquals(email, benutzer.getEmail());
        assertEquals(passwort, benutzer.getPasswort());
        assertEquals(id, benutzer.getId());
        assertNotNull(benutzer.getId());
        assertEquals(anrede, benutzer.getAnrede());
        assertEquals(role, benutzer.getRole());
        assertEquals(adresse, benutzer.getAdresse());


    }
}
