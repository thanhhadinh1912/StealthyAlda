package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.Views;
import com.stealthyalda.services.util.PasswordAuthentication;
import com.vaadin.ui.UI;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControl {
    private static final String userLoginStatement = "SELECT passwort, role, anrede, telefonnummer FROM stealthyalda.benutzer WHERE email = ?";

    public static void checkAuthentification( String email, String password) throws NoSuchUserOrPassword, DatabaseException {
        ResultSet set = null;

        try{
            // hash password for check in db
            PasswordAuthentication hasher = new PasswordAuthentication();

            // remember, convert to char[] as the string method is deprecated
            char[] c = password.toCharArray();

            String passwordHash = hasher.hash(c); // password hash

            // use prepared statements to mitigate sql injection

            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(userLoginStatement);
            // remember, the int references the index of the item, starting 1
            preparedStatement.setString(1, email);
            // preparedStatement.setString(2, passwordHash);
            // query!
            set = preparedStatement.executeQuery();
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, set);
        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen");
        }
        String dbPasswordHash = null;
        try {
            if(set.next()) {
                dbPasswordHash = set.getString(1);
            } else{
                //Error Handling
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen");
        }
        // user vorhanden. Jetzt Passwort hashes vergleichen
        /**
        *
        * */
        Benutzer benutzer = null;

        PasswordAuthentication authenticator = new PasswordAuthentication();
        // remember, convert to char[] as the string method is deprecated
        char[] c = password.toCharArray();
        // check if hashes match

        try {
            if(authenticator.authenticate(c, dbPasswordHash) == true) {
                benutzer = new Benutzer();
                benutzer.setEmail(email);
                benutzer.setRole(set.getString(2));
                benutzer.setAnrede(set.getString(3));
                benutzer.setTelefonnummer(set.getString(4));

            } else {
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            JDBCConnection.getInstance().closeConnenction();
        }


//        VaadinSession session = UI.getCurrent().getSession();//welche account gerade eingeloggt
//        session.setAttribute(Rolles.CURRENT_USER, user);

        ((MyUI) UI.getCurrent()).setBenutzer(benutzer);

        try {
            // Der Benutzer ist vorhanden
            if(set.getString(2).equals("Student")){
                UI.getCurrent().getNavigator().navigateTo(Views.MAINSTUDENT);
            }
            else{
                UI.getCurrent().getNavigator().navigateTo(Views.MAINARBEITGEBER);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        


    }
    public static void logoutUser() {
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/");
        //UI.getCurrent().getSession().close();
    }
}

