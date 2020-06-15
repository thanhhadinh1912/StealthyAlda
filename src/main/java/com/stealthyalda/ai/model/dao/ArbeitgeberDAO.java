/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.Adresse;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.db.JDBCConnection;
import com.vaadin.ui.UI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WINDOWS
 */
public class ArbeitgeberDAO extends AbstractDAO{
    public static ArbeitgeberDAO dao = null;

    private ArbeitgeberDAO(){

    }

    public static ArbeitgeberDAO getInstance(){
        if( dao == null){
            dao = new ArbeitgeberDAO();
        }
        return dao;
    }
    
    public boolean createArbeitgeber(String anrede, String unternehmen, String strasse, int plz, String ort, String hausnummer, String telefonnumer) throws DatabaseException{
        String sql = "insert into stealthyalda.benutzer(benutzer_id, email, passwort, role, anrede, telefonnummer) values(?,?,?,?,?,?);"+ "insert into stealthyalda.arbeigeber(arbeitgeber_id,unternehmen,benutzer_id) values(?,?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);
        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
        int userid = user.getId();

        //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
        try{
            statement.setInt(1,userid);
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getPasswort());
            statement.setString(4,user.getRole());
            statement.setString(5,anrede);
            statement.setString(6,telefonnumer);

            statement.setInt(7, arbeitgeberID());
            statement.setString(8,unternehmen);
            statement.setInt(9,userid);
            statement.executeUpdate();
            AdresseDAO.getInstance().createAdresse(strasse, plz, hausnummer, ort);

            //Nachtragliches Setzen der BuchungsID
            return true;
        }  catch (SQLException ex){
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    private int arbeitgeberID() throws SQLException {
        Statement statement = this.getStatement();
        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT max(stealthyalda.arbeitgeber.arbeitgeber_id) FROM stealthyalda.arbeitgeber");
            }
        catch (SQLException ex){
                Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int currentValue = 0;
        try{
            rs.next();
            currentValue=rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return currentValue;
    }
    
    public Arbeitgeber getArbeitgeber(int benutzerid){
         ResultSet set;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT * "
                    + "FROM stealthyalda.arbeitgeber "
                    + "WHERE stealthyalda.arbeitgeber.benutzer_id = '" + benutzerid + "'");

            if (set.next()) {
                Arbeitgeber a = new Arbeitgeber();
                a.setArbeitgeber_id(set.getInt(1));
                a.setUnternehmen(set.getString(2));
                a.setId(benutzerid);
                a.setLogo(set.getByte(4));
                a.setBeschreibung(set.getString(5));
                return a;
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    }

    



