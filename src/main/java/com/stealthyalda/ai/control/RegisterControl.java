package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.Email;
import com.stealthyalda.services.util.PasswordAuthentication;
import org.apache.commons.mail.*;


import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterControl {
    // prepared statement for insertion
    private static final String userInsertStatement = "INSERT INTO stealthyalda.benutzer (email, passwort) VALUES (?,?)";

    public static boolean checkUserExists( String email) throws UserExistsException, DatabaseException {
        ResultSet set;
        try {
            //DB - Zugriffxyss
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT  COUNT(*) AS rowcount FROM stealthyalda.benutzer " +
                    " WHERE stealthyalda.benutzer.email = '" + email + "' ;");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen");
        }


        try {
            set.next();
            int count = set.getInt("rowcount");
            set.close();
            if(count != 0 ){
                throw new UserExistsException("Sorry, Sie können diese Email Adresse nicht benutzen");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            JDBCConnection.getInstance().closeConnenction();
        }
        return true;
    }

    /**
     *
     * @param email - String
     * @param password - String of the password entered in the form
     * @return boolean
     * @throws DatabaseException When murphy is around
     */
    public static boolean registerUser(String email, String password) throws DatabaseException {
        // store hashed password!!
        ResultSet set = null;

        // new password hasher
        PasswordAuthentication hasher = new PasswordAuthentication();

        // convert to char[] as the string method is deprecated
        char[] c = password.toCharArray();
        String passwordHash = hasher.hash(c); // password hash

        try{
            // use prepared statements to mitigate sql injection
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(userInsertStatement);
            // remember, the int references the index of the item, starting 1
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwordHash);
            // insert!
            int row = preparedStatement.executeUpdate();
            // delete for now
            // System.out.println(row);
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, row);
        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen");
        }
        return true;
    }
    private void sendConfirmationEmail(String email) {
        HtmlEmail mail = new HtmlEmail();

        // mail.setHostName("smtp.mailtrap.io");
        mail.setSmtpPort(587);
        mail.setSSLOnConnect(false);
        mail.setAuthentication("fc96262875e64b", "556759cf419b54");

        try {
            mail.setFrom("stealthy.alda@test.com");
            mail.addTo(email, email);
            mail.setSubject("The subject");
            mail.setHtmlMsg("This is the message.");
            mail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}

