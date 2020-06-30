package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.Anforderung;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnforderungDAOTest {

    @Test
 public   void getAnforderungForStellenanzeige() throws DatabaseException {
        AnforderungDAO anfdao = AnforderungDAO.getInstance();
        //anfdao.createAnforderung(17,"java kentnissse");
        List<Anforderung> anf= anfdao.getAnforderungForStellenanzeige(19);

        assertEquals("java kentnissse",anfdao.getAnforderungForStellenanzeige(19).get(0).getAnforderung() );
    }

    @Test
 public   void createAnforderung() throws DatabaseException {

        AnforderungDAO anfdao = AnforderungDAO.getInstance();
        anfdao.createAnforderung(18,"java kentnisse");
        List<Anforderung> anf= anfdao.getAnforderungForStellenanzeige(19);
        System.out.println(anf.get(0).getAnforderung());


    }
}