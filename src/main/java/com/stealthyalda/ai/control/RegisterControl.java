package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.PasswordAuthentication;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterControl {
    // prepared statement for insertion
    private static final String USER_INSERT_STMT = "INSERT INTO stealthyalda.benutzer (" +
            "email, passwort, name,telefonnummer, anrede, role) " +
            "VALUES (?,?,?,?,?,?)";

    public boolean checkUserExists(String email) throws UserExistsException, DatabaseException {
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
            if (count != 0) {
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
     * @param email    - String
     * @param password - String of the password entered in the form
     * @return boolean
     * @throws DatabaseException When murphy is around
     */
    public boolean registerUser(String email, String password, String name, String telefonNummer, String anrede, String role) throws DatabaseException {
        // init password hasher
        PasswordAuthentication hasher = new PasswordAuthentication();

        // convert to char[] as the string method is deprecated
        char[] c = password.toCharArray();
        String passwordHash = hasher.hash(c); // password hash

        try {
            // use prepared statements to mitigate sql injection
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(USER_INSERT_STMT);
            // remember, the int references the index of the item, starting 1
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwordHash);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, telefonNummer);
            preparedStatement.setString(5, anrede);
            preparedStatement.setString(6, role);

            // insert!
            int row = preparedStatement.executeUpdate();
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, row);
        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen");
        }
        sendConfirmationEmail(email, name);

        return true;
    }

    private void sendConfirmationEmail(String email, String name) {
        HtmlEmail mail = new HtmlEmail();

        mail.setHostName("smtp.mailtrap.io");
        mail.setSmtpPort(587);
        mail.setSSLOnConnect(false);
        mail.setAuthentication("fc96262875e64b", "556759cf419b54");

        try {
            mail.setFrom("stealthy.alda@test.com");
            mail.addTo(email);
            mail.setSubject("Willkommen in Stealthy_Alda Portal");
            mail.setHtmlMsg("Hallo" + name + "Sie haben Ihr Konto erfolgreich erstellt!<br>Ab jetzt steht Ihnen das Portal zur Verfügung.");
            mail.send();
        } catch (EmailException e) {
            Logger.getLogger(RegisterControl.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

