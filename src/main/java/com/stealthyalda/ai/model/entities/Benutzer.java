package com.stealthyalda.ai.model.entities;
import com.stealthyalda.ai.model.dao.RoleDAO;
import com.stealthyalda.ai.model.dtos.Role;
import com.stealthyalda.services.util.Account;
import com.stealthyalda.services.util.Adresse;

import java.util.Date;

public class Benutzer {
    private String nachname;
    private String vorname;
    private String email;
    private String passwort;
    private String telefonnummer = null;
    private int id ;
    private String anrede;

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

}
