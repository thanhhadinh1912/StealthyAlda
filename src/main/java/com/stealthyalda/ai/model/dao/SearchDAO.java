package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.services.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO extends AbstractDAO{
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
        List<String> liste = new ArrayList<String>();
        try {
            Statement statement = this.getStatement();
            set = statement.executeQuery("SELECT titel FROM stealthyalda.stellenanzeige ");
            while (true) {
                assert set != null;
                if (!set.next()) break;
                liste.add(set.getString(1));
            }
             set2 = statement.executeQuery("SELECT unternehmen FROM stealthyalda.arbeitgeber ");
            while (true){
                assert set2 != null;
                if(!set2.next()) break;
                liste.add(set2.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        }
        finally {
            try {
                JDBCConnection.getInstance().closeConnection();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(set);
        }


        return liste;
    }


}
