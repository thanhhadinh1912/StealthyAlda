package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.PasswordAuthentication;
import org.apache.commons.mail.*;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterControl {
    // prepared statement for insertion
    private static final String userInsertStatement = "INSERT INTO stealthyalda.benutzer (email, passwort) VALUES (?,?)";

    public static boolean checkUserExists( String email) throws UserExistsException, DatabaseException {
        ResultSet set = null;
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
     * @throws DatabaseException
     */
    public static boolean registerUser(String email, String password) throws DatabaseException {
        HtmlEmail mail = new HtmlEmail();
        mail.setHostName("smtp.gmail.com");
        mail.setSmtpPort(465);
        mail.setSSLOnConnect(true);
        mail.setAuthentication("your-account-name@gmail.com", "your-password");

        try {
            mail.setFrom("sender@test.com");
            mail.addTo("recipient1@test.com", "recipient2@test.com");
            mail.setSubject("The subject");
            mail.setHtmlMsg("This is the message.");
            mail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
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
}

