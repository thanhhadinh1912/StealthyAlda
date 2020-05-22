package com.stealthyalda.ai.model.entities;
import com.stealthyalda.ai.model.dao.RoleDAO;
import com.stealthyalda.ai.model.dtos.Role;
import com.stealthyalda.services.util.Account;
import com.stealthyalda.services.util.Adresse;

import java.util.Date;

public class Benutzer {
    private String nachname;
    private String email;
    private String telefonnummer = null;
    private int benutzer_id ;
    private Date geburtsdatum = null;
    private String login;
    private Adresse adresse;
    private String roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login=login;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    private String vorname = null;

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public int getBenutzer_id() {
        return benutzer_id;
    }

    public void setBenutzer_id(int benutzer_id) {
        this.benutzer_id = benutzer_id;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
//    public boolean hasRole(String role) {
//        //Lazy Load
//        if(this.roles == null){
//            getRoles();
//        }
//        for( Role r : roles){
//            if(r.getBezeichnung().equals(role)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void getRoles() {
//
//        this.roles = RoleDAO.getInstance().getRolesForUser(this);
//    }

}
