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

        Benutzer benutzer = null;
        try {
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT * "
                    + "FROM stealthyalda.benutzer "
                    + "WHERE stealthyalda.benutzer.email = '" + email + "'");

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
        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        } finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(set);
            JDBCConnection.getInstance().closeConnection();
        }

        return null;
    }

    public static String getUserRole(String email) {
        ResultSet set = null;
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
        } finally {
            com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(set);
        }

        return null;
    }

    public static Benutzer getBenutzer(String email, String password) throws DatabaseException, NoSuchUserOrPassword {
        ResultSet set;
        final String USER_LOGIN_STATEMENT = "SELECT email, passwort, role, anrede, telefonnummer FROM stealthyalda.benutzer WHERE email = ?";

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
                dbPasswordHash = set.getString(2);
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
                benutzer.setPasswort(password);
                benutzer.setRole(set.getString(3));
                benutzer.setAnrede(set.getString(4));
                benutzer.setTelefonnummer(set.getString(5));
                return benutzer;
            } else {
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AbstractDAO.closeResultset(set);
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

    public boolean createBenutzer(String email, String passwort, String role) throws DatabaseException {

        String sql;
        sql = "INSERT INTO stealthyalda.benutzer(benutzer_id,email, passwort, role) VALUES (?,?,?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);
        PasswordAuthentication hasher = new PasswordAuthentication();

        // convert to char[] as the string method is deprecated
        char[] c = passwort.toCharArray();
        String passwordHash = hasher.hash(c); // password hash


            try {
                statement.setInt(1, benutzerID());
                statement.setString(2, email);
                statement.setString(3, passwordHash);
                statement.setString(4, role);
                statement.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

    private int benutzerID() throws SQLException {
        Statement statement = this.getStatement();
        ResultSet rs = null;
        int currentValue = 0;

        try {
            rs = statement.executeQuery("SELECT max(stealthyalda.benutzer.benutzer_id) FROM stealthyalda.benutzer");
        } catch (SQLException ex) {
            Logger.getLogger(BenutzerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        if (rs != null) {
            try {
                rs.next();
                currentValue = rs.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                com.stealthyalda.ai.model.dao.AbstractDAO.closeResultset(rs);
            }
        }
        return currentValue;
    }


}
