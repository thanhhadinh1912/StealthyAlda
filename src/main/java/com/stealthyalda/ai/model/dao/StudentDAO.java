package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.db.JDBCConnection;
import com.vaadin.ui.UI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO extends AbstractDAO {
    private static StudentDAO  dao = null;

    private StudentDAO () {

    }

    public static StudentDAO  getInstance() {
        if (dao == null) {
            dao = new StudentDAO ();
        }
        return dao;
    }

    public boolean createStudent(String anrede, String vorname, String nachname , String strasse, int plz, String ort, String hausnummer, String telefonnumer) throws DatabaseException {
            Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
            int userid = user.getId();

            String sql = "update stealthyalda.benutzer set anrede ='" + anrede + "', telefonnummer = '" + telefonnumer +"' where " +
                    "benutzer_id = '" + userid +"';"+ "insert into stealthyalda.student(vorname, nachname ,benutzer_id) values(?,?,?);";
            PreparedStatement statement = this.getPreparedStatement(sql);
            //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
            try {
                statement.setString(1, vorname);
                
                statement.setString(2, nachname);
                statement.setInt(3, userid);
                AdresseDAO.getInstance().createAdresse(strasse, plz, hausnummer, ort);
                statement.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, ex.getMessage());
                return false;
            }
        }


    public Student getStudent(int benutzerid) {
        ResultSet set = null;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT * "
                    + "FROM stealthyalda.student "
                    + "WHERE stealthyalda.student.benutzer_id = '" + benutzerid + "'");

            if (set.next()) {
                Student s = new Student();
                s.setStudent_id(set.getInt(1));
                s.setNachname(set.getString(2));
                s.setId(benutzerid);
                s.setVorname(set.getString(4));
                s.setProfilbild(set.getByte(5));
                return s;
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }
        return null;
    }

}
