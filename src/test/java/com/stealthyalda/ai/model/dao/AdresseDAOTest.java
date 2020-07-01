package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.model.dtos.Adresse;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdresseDAOTest {

    @Test
    public void addAdresse() {
        AdresseDAO adddao = AdresseDAO.getInstance();
        Adresse add = new Adresse("marktStr", 51147, "15", "koeln");
        adddao.addAdresse(add, 2);

        assertEquals("marktStr 15\n51147koeln", adddao.getAdresse(2).toString());
    }

    @Test
    public void getAdresse() {
        // selber Test da addnicht ohne get getestet werden kann

        AdresseDAO adddao = AdresseDAO.getInstance();
        Adresse add = new Adresse("marktStr", 51147, "15", "koeln");
        adddao.addAdresse(add, 2);

        assertEquals("marktStr 15\n51147koeln", adddao.getAdresse(2).toString());
    }

    @Test
    public void updateAdresse() {
        AdresseDAO adddao = AdresseDAO.getInstance();
        Adresse add = new Adresse("marktStr", 51147, "15", "koeln");
        adddao.addAdresse(add, 1);

        System.out.println(adddao.getAdresse(2).toString());

        Adresse addneu = new Adresse("hanseStr", 51137, "3", "traumstadt");

        adddao.updateAdresse(addneu);

        // hier muss die Methode angepasts werden damit eine ID übergeben werden kann
        // ohne ID kann man die Adresse nicht ändern


    }
}