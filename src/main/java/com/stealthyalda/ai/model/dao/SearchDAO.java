package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.services.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchDAO extends AbstractDAO {
    private static SearchDAO dao;

    private SearchDAO() {

    }

    public static SearchDAO getInstance() {
        if (dao == null) {
            dao = new SearchDAO();
        }
        return dao;
    }

    public List<String> getJobtitelOrArbeitgeber() {
        ResultSet set = null;
        ResultSet set2 = null;
        List<String> liste = new ArrayList<>();
        try {
            Statement statement = this.getStatement();
            set = statement.executeQuery("SELECT titel FROM stealthyalda.stellenanzeige ");
            while (true) {
                assert set != null;
                if (!set.next()) break;
                liste.add(set.getString(1));
            }
            set2 = statement.executeQuery("select distinct a.unternehmen\n" +
                    "from stealthyalda.arbeitgeber a\n" +
                    "join stealthyalda.stellenanzeige s\n" +
                    "on s.arbeitgeber_id = a.arbeitgeber_id");
            while (true) {
                assert set2 != null;
                if (!set2.next()) break;
                liste.add(set2.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                Logger.getLogger(SearchDAO.class.getName()).log(Level.SEVERE, null, e);

            }
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(set);
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(set2);
        }


        return liste;

    }

    public List<String> getOrt() {

        ResultSet set = null;
        List<String> liste = new ArrayList<>();

        try {
            Statement statement = this.getStatement();
            set = statement.executeQuery("SELECT ort FROM stealthyalda.stellenanzeige order by ort");
            while (true) {
                assert set != null;
                if (!set.next()) break;
                liste.add(set.getString(1));
            }
        } catch (SQLException e) {
            Logger.getLogger(SearchDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                Logger.getLogger(SearchDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(set);
        }


        return liste;
    }

    public List<String> getStellenanzeigeFürArbeitgeber(Arbeitgeber a){
        ResultSet set = null;
        List<String> liste = new ArrayList<>();

        try {
            Statement statement = this.getStatement();
            set = statement.executeQuery("select distinct a.titel\n" +
                    "from stealthyalda.arbeitgeber a\n" +
                    "JOIN stealthyalda.stellenanzeige s ON s.stellenanzeige_id = a.stellenanzeige_id\n" +
                    "where a.arbeitgeber_id = '"+a.getArbeitgeberId()+"'\n");
            while (true) {
                assert set != null;
                if (!set.next()) break;
                liste.add(set.getString(1));
            }
        } catch (SQLException e) {
            Logger.getLogger(SearchDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                Logger.getLogger(SearchDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(set);
        }


        return liste;
    }

    public List<String> getBewerberOrStellenanzeige(Arbeitgeber a) {
        ResultSet set = null;
        ResultSet set2 = null;
        List<String> liste = new ArrayList<>();
        try {
            Statement statement = this.getStatement();
            set = statement.executeQuery("select distinct s.nachname\n" +
                    "from stealthyalda.bewerbung b\n" +
                    "JOIN stealthyalda.stellenanzeige a ON b.stellenanzeige_id = a.stellenanzeige_id\n" +
                    "JOIN stealthyalda.arbeitgeber u ON u.arbeitgeber_id = a.arbeitgeber_id\n" +
                    "join stealthyalda.student s ON b.student_id = s.student_id\n" +
                    "where a.arbeitgeber_id = '"+ a.getArbeitgeberId()+"'");
            while (true) {
                assert set != null;
                if (!set.next()) break;
                liste.add(set.getString(1));
            }
            set2 = statement.executeQuery("select distinct a.titel\n" +
                    "from stealthyalda.arbeitgeber a\n" +
                    "JOIN stealthyalda.stellenanzeige s ON s.stellenanzeige_id = a.stellenanzeige_id\n" +
                    "where a.arbeitgeber_id = '"+a.getArbeitgeberId()+"'\n");
            while (true) {
                assert set2 != null;
                if (!set2.next()) break;
                liste.add(set2.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                Logger.getLogger(SearchDAO.class.getName()).log(Level.SEVERE, null, e);

            }
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(set);
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(set2);
        }


        return liste;
    }

}
