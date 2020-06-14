package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.PasswordAuthentication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BenutzerDAO extends AbstractDAO {

    private static BenutzerDAO dao = null;

    private BenutzerDAO() {

    }

    public static BenutzerDAO getInstance() {
        if (dao == null) {
            dao = new BenutzerDAO();
        }
        return dao;
    }

    public static Benutzer getUser(String email) throws DatabaseException {

        ResultSet set = null;

        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT * "
                    + "FROM stealthyalda.benutzer "
                    + "WHERE stealthyalda.benutzer.email = '" + email + "'");
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }
        boolean exist;
        Benutzer benutzer = null;
        try {
            while (set.next()) {
                benutzer = new Benutzer();
                benutzer.setId(set.getInt(1));
                benutzer.setTelefonnummer(set.getString(2));
                benutzer.setAnrede(set.getString(3));
                benutzer.setEmail(set.getString(4));
                benutzer.setPasswort(set.getString(5));
                benutzer.setRole(set.getString(6));
                benutzer.setAdresse_id((set.getInt(7)));
            }

            return benutzer;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
        return null;
    }

    public static String getUserRole(String email) {
        ResultSet set;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT role "
                    + "FROM stealthyalda.benutzer "
                    + "WHERE stealthyalda.benutzer.email = '" + email + "'");

            if (set.next()) {
                return set.getString(1);
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Benutzer getBenutzer(String email, String password) throws DatabaseException, NoSuchUserOrPassword {
        ResultSet set;
        final String USER_LOGIN_STATEMENT = "SELECT passwort, role, anrede, telefonnummer FROM stealthyalda.benutzer WHERE email = ?";

        try {
            // use prepared statements to mitigate sql injection
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(USER_LOGIN_STATEMENT);
            // remember, the int references the index of the item, starting 1
            preparedStatement.setString(1, email);

            // query!
            set = preparedStatement.executeQuery();
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, set);
        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen");
        }
        String dbPasswordHash = null;
        try {
            if (set.next()) {
                dbPasswordHash = set.getString(1);
            } else {
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
            if (authenticator.authenticate(c, dbPasswordHash) == true) {
                benutzer = new Benutzer();
                benutzer.setEmail(email);
                benutzer.setRole(set.getString(2));
                benutzer.setAnrede(set.getString(3));
                benutzer.setTelefonnummer(set.getString(4));
                return benutzer;
            } else {
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
        return null;
    }

    public static void deleteUser(String email) throws DatabaseException {
        String sql;
        sql = "DELETE FROM stealthyalda.benutzer WHERE email = '" + email + "'";

        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        try {
            assert statement != null;
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

    }

    public boolean checkUserExists(String email) throws UserExistsException, DatabaseException {
        ResultSet set;
        boolean checksOkay = false;
        try {
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement("SELECT  COUNT(*) AS rowcount FROM stealthyalda.benutzer " +
                    " WHERE stealthyalda.benutzer.email = ?;");

            preparedStatement.setString(1, email);

            set = preparedStatement.executeQuery();
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, set);
        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen");
        }
        try {
            set.next();
            int count = set.getInt("rowcount");
            set.close();
            if (count != 0) {
                checksOkay = false;
                throw new UserExistsException("Sorry, Sie können diese Email Adresse nicht benutzen");
            }
            checksOkay = true;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

        return checksOkay;
    }

    public void createBenutzer(String email, String passwort, String role) throws DatabaseException {

        String sql;
        sql = "INSERT INTO stealthyalda.benutzer (email, passwort, role) VALUES (?,?,?)";


        PasswordAuthentication hasher = new PasswordAuthentication();

        // convert to char[] as the string method is deprecated
        char[] c = passwort.toCharArray();
        String passwordHash = hasher.hash(c); // password hash

        try {
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwordHash);
            preparedStatement.setString(3, role);
            int row = preparedStatement.executeUpdate();

            Logger.getLogger(JDBCConnection.class.getName()).log(Level.INFO, null, row);
        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen");
        }


    }


}
