package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class StellenanzeigeDAO extends AbstractDAO{

    public static StellenanzeigeDAO dao = null;


    public static StellenanzeigeDAO getInstance(){
        if(dao == null) dao = new StellenanzeigeDAO();
        return dao;

    }

    public List<Stellenanzeige> getStellenanzeigeByLocation(String ort)  {
        Statement statement = this.getStatement();

        ResultSet rs = null;
        try{
            rs = statement.executeQuery("SELECT * FROM stealthyalda.stellenanzeige WHERE stealthyalda.stellenanzeige.ort =  \'" + ort +"\' " );
        }catch(SQLException ex){
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rs == null){
            System.out.println("h2");
            return null;
        }
        List<Stellenanzeige> liste = new ArrayList<Stellenanzeige>();
        Stellenanzeige stellenanzeige = null;

        try{
            while(rs.next()){
                stellenanzeige = new Stellenanzeige();
                stellenanzeige.setStellenanzeige_id(rs.getInt(1));
                stellenanzeige.setTitel(rs.getString(2));
                stellenanzeige.setBeschreibung(rs.getString(3));
                stellenanzeige.setStatus(rs.getString(4));
                stellenanzeige.setDatum(rs.getDate(5));
                stellenanzeige.setFachbereich(rs.getString(6));
                stellenanzeige.setUnternehmen_id(rs.getInt(7));
                stellenanzeige.setOrt(rs.getString(8));
                liste.add(stellenanzeige);
            }
        }catch(SQLException ex){

        }
        return liste;
    }

}
