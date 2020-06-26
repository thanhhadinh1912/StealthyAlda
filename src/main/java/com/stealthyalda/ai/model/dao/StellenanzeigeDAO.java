package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.Anforderung;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.Roles;
import com.vaadin.ui.UI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StellenanzeigeDAO extends AbstractDAO {

    private static StellenanzeigeDAO dao = null;


    public static StellenanzeigeDAO getInstance() {
        if (dao == null) dao = new StellenanzeigeDAO();
        return dao;

    }

    public Stellenanzeige getStellenanzeige(String jobtitel,  String beschreibung, String ort, String status){
        String sql = "select * from stealthyalda.stellenanzeige where titel = ?" +
                "and beschreibung = ?" +
                "and ort = ?" +
                "and status = ?;";
        ResultSet rs = null;
        StellenanzeigeDTO stellenanzeige = new StellenanzeigeDTO();
        try {
            // use prepared stmt
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
            statement.setString(1, jobtitel);
            statement.setString(2, beschreibung);
            statement.setString(3, ort);
            statement.setString(4, status);
            rs = statement.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                Arbeitgeber a = new Arbeitgeber();
                stellenanzeige.setStellenanzeigeID(rs.getInt(1));
                stellenanzeige.setTitel(rs.getString(2));
                stellenanzeige.setBeschreibung(rs.getString(3));
                stellenanzeige.setStatus(rs.getString(4));
                stellenanzeige.setDatum(rs.getDate(5).toLocalDate());
                stellenanzeige.setOrt(rs.getString(7));
                a = ArbeitgeberDAO.getInstance().getArbeitgeberFromArbeitgeberid(rs.getInt(6));
                stellenanzeige.setUnternehmen(a);
            }
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, rs);
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(rs);
        }
        return stellenanzeige;

    }

    public boolean createStellenanzeige(Stellenanzeige s) {

        String sql = "insert into stealthyalda.stellenanzeige values(default,?,?,?,?,?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);
        Benutzer user = (Benutzer) UI.getCurrent().getSession().getAttribute(Roles.CURRENTUSER);
        String email = user.getEmail();
        Arbeitgeber a = ArbeitgeberDAO.getInstance().getArbeitgeber(email);

        //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
        try {
            statement.setString(1, s.getTitel());
            statement.setString(2, s.getBeschreibung());
            statement.setString(3, s.getStatus());
            statement.setDate(4, Date.valueOf(s.getDatum()));
            statement.setInt(5, a.getArbeitgeberId());
            statement.setString(6, s.getOrt());
            statement.executeUpdate();

            //Nachtragliches Setzen der BuchungsID
            setStellenanzeigesID(s);
            List<Anforderung> list = s.getAnforderungs();
            for (int i=0; i<list.size();i++){
                AnforderungDAO.getInstance().createAnforderung(s.getStellenanzeigeID(),list.get(i).getAnforderung());
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StellenanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private void setStellenanzeigesID(Stellenanzeige s) throws SQLException {
        Statement statement = this.getStatement();
        ResultSet rs = null;

        int currentValue = 0;
        try {
            rs = statement.executeQuery("SELECT max(stealthyalda.stellenanzeige.stellenanzeige_id) FROM stealthyalda.stellenanzeige");

            assert rs != null;
            rs.next();
            currentValue = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(StellenanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(rs);
        }

        s.setStellenanzeigeID(currentValue);
    }
    public List<StellenanzeigeDTO> getStellenanzeigeByArbeitgeber(String arbeitgeber){
        ResultSet set = null;
        List<StellenanzeigeDTO> liste = new ArrayList<>();
        String sql = "select *\n" +
                "from stealthyalda.stellenanzeige s\n" +
                "join stealthyalda.arbeitgeber a\n" +
                "on s.arbeitgeber_id = a.arbeitgeber_id\n" +
                "where a.unternehmen = ? order by s.stellenanzeige_id";
        try{
            PreparedStatement statement = this.getPreparedStatement(sql);
            statement.setString(1, arbeitgeber);
            set = statement.executeQuery();
            assert (set != null);
            while (set.next()) {
                StellenanzeigeDTO s = new StellenanzeigeDTO();
                s.setTitel(set.getString(2));
                s.setBeschreibung(set.getString(3));
                s.setStatus(set.getString(4));
                s.setDatum(set.getDate(5).toLocalDate());
                s.setOrt(set.getString(7));
                liste.add(s);
            }

        }
        catch (SQLException ex) {
            Logger.getLogger(StellenanzeigeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(set);
        }
        return liste;
    }



    public List<StellenanzeigeDTO> getStellenanzeigeByLocation(String ort){
        ResultSet rs = null;

        StellenanzeigeDTO stellenanzeige = null;
        String getStellenanzeigen = "SELECT s.titel,s.beschreibung, s.status,s.datum,a.unternehmen, s.ort \n" +
                "FROM stealthyalda.stellenanzeige s\n" +
                "JOIN stealthyalda.arbeitgeber a ON s.arbeitgeber_id = a.arbeitgeber_id \n" +
                "WHERE s.ort LIKE '%"+ort+"%'\n";
        List<StellenanzeigeDTO> liste = hilfe(getStellenanzeigen);

        return liste;
    }

    private List<StellenanzeigeDTO> hilfe(String sql){
        ResultSet rs = null;
        List<StellenanzeigeDTO> liste = new ArrayList<>();
        StellenanzeigeDTO stellenanzeige = null;
        try {
            // use prepared stmt
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(sql);

            rs = preparedStatement.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                stellenanzeige = new StellenanzeigeDTO();
                stellenanzeige.setTitel(rs.getString(1));
                stellenanzeige.setBeschreibung(rs.getString(2));
                stellenanzeige.setStatus(rs.getString(3));
                stellenanzeige.setDatum(rs.getDate(4).toLocalDate());
                stellenanzeige.setArbeitgeber(rs.getString(5));
                stellenanzeige.setOrt(rs.getString(6));
                liste.add(stellenanzeige);
            }
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, rs);
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(rs);
        }

        return liste;
    }

    public List<StellenanzeigeDTO> getStellenanzeigeByJobTitelOrUnternehmen(String titelorunternehmen){
        ResultSet rs = null;

        StellenanzeigeDTO stellenanzeige = null;
        String getStellenanzeigen = "SELECT s.titel,s.beschreibung, s.status,s.datum,a.unternehmen, s.ort \n" +
                "FROM stealthyalda.stellenanzeige s\n" +
                "JOIN stealthyalda.arbeitgeber a ON s.arbeitgeber_id = a.arbeitgeber_id \n" +
                "WHERE a.unternehmen LIKE '%"+titelorunternehmen+"%' \n" +
                "OR s.titel LIKE '%"+titelorunternehmen+"%'";
        List<StellenanzeigeDTO> liste = hilfe(getStellenanzeigen);

        return liste;
    }

    public List<StellenanzeigeDTO> getStellenanzeigeByLocationAndJobTitelOrUnternehmen(String titelorunternehmen, String ort) {
        ResultSet rs = null;

        StellenanzeigeDTO stellenanzeige = null;
        String getStellenanzeigen = "SELECT s.titel,s.beschreibung, s.status,s.datum,a.unternehmen, s.ort \n" +
                "FROM stealthyalda.stellenanzeige s\n" +
                "JOIN stealthyalda.arbeitgeber a ON s.arbeitgeber_id = a.arbeitgeber_id \n" +
                "WHERE s.ort LIKE '%"+ort+"%'\n" +
                "OR a.unternehmen LIKE '%"+titelorunternehmen+"%' \n" +
                "OR s.titel LIKE '%"+titelorunternehmen+"%'";
        List<StellenanzeigeDTO> liste = hilfe(getStellenanzeigen);

        return liste;
    }

    public StellenanzeigeDTO getJobangebot(int stellenanzeige_id){
        String sql = "SELECT * \n" +
                "FROM stealthyalda.stellenanzeige s\n" +
                "JOIN stealthyalda.arbeitgeber a\n" +
                "ON s.arbeitgeber_id = a.arbeitgeber_id\n" +
                "WHERE s.stellenanzeige_id = '"+stellenanzeige_id+"';";
        ResultSet rs = null;
        StellenanzeigeDTO stellenanzeige = new StellenanzeigeDTO();
        try {
            // use prepared stmt
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(sql);
            rs = preparedStatement.executeQuery();
            assert (rs != null);
            while (rs.next()) {
                Arbeitgeber a = new Arbeitgeber();
                stellenanzeige.setStellenanzeigeID(rs.getInt(1));
                stellenanzeige.setTitel(rs.getString(2));
                stellenanzeige.setBeschreibung(rs.getString(3));
                stellenanzeige.setStatus(rs.getString(4));
                stellenanzeige.setDatum(rs.getDate(5).toLocalDate());
                stellenanzeige.setOrt(rs.getString(7));
                a = ArbeitgeberDAO.getInstance().getArbeitgeberFromArbeitgeberid(rs.getInt(8));
                stellenanzeige.setUnternehmen(a);
            }
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, rs);
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(rs);
        }
return stellenanzeige;
    }

}
