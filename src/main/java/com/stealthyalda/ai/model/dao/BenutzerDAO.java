package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.PasswordAuthentication;
import com.vaadin.server.VaadinSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BenutzerDAO extends AbstractDAO {

    private static final String EXCEPTION = "Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen";
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
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement("SELECT * "
                    + "FROM stealthyalda.benutzer "
                    + "WHERE stealthyalda.benutzer.email = ?");
            statement.setString(1, email);
            set = statement.executeQuery();
            while (set.next()) {
                benutzer = new Benutzer();
                benutzer.setId(set.getInt(1));
                benutzer.setTelefonnummer(set.getString(2));
                benutzer.setAnrede(set.getString(3));
                benutzer.setEmail(set.getString(4));
                benutzer.setPasswort(set.getString(5));
                benutzer.setRole(set.getString(6));
            }
        } catch (SQLException | DatabaseException e) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, e.getMessage());

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
            PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement("SELECT role "
                    + "FROM stealthyalda.benutzer "
                    + "WHERE stealthyalda.benutzer.email = ?");
            statement.setString(1, email);
            set = statement.executeQuery();

            if (set.next()) {
                return set.getString(1);
            }
        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultset(set);
        }

        return null;
    }

    public static Benutzer getBenutzer(String email, String password) throws DatabaseException, NoSuchUserOrPassword {
        ResultSet set;
        final String USER_LOGIN_STATEMENT = "SELECT email, passwort, role, anrede, telefonnummer, benutzer_id FROM stealthyalda.benutzer WHERE email = ?";

        try {
            // use prepared statements to mitigate sql injection
            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement(USER_LOGIN_STATEMENT);
            // remember, the int references the index of the item, starting 1
            preparedStatement.setString(1, email);

            // query!
            set = preparedStatement.executeQuery();
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.INFO, null, set);
        } catch (SQLException e) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(EXCEPTION);
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
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(EXCEPTION);
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
            if (authenticator.authenticate(c, dbPasswordHash)) {
                benutzer = new Benutzer();
                benutzer.setEmail(email);
                benutzer.setPasswort(dbPasswordHash);
                benutzer.setRole(set.getString(3));
                benutzer.setAnrede(set.getString(4));
                benutzer.setTelefonnummer(set.getString(5));
                benutzer.setId(set.getInt(6));
                return benutzer;
            } else {
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AbstractDAO.closeResultset(set);
        }
        return new Benutzer();
    }

    public void deleteUser(String email, String passwort) throws DatabaseException {
        PasswordAuthentication hasher = new PasswordAuthentication();
        char[] c = passwort.toCharArray();
        String passwordHash = hasher.hash(c);
        try (PreparedStatement statement = getPreparedStatement("DELETE FROM stealthyalda.benutzer WHERE email = ? AND passwort = ?;")) {
            statement.setString(1, email);
            statement.setString(2, passwordHash);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, throwables.getMessage(), throwables);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

    }

    public boolean checkUserExists(String email) throws DatabaseException {
        ResultSet set = null;
        try {

            PreparedStatement preparedStatement = JDBCConnection.getInstance().getPreparedStatement("SELECT  COUNT(*) AS rowcount FROM stealthyalda.benutzer " +
                    " WHERE stealthyalda.benutzer.email = ?;");

            preparedStatement.setString(1, email);

            set = preparedStatement.executeQuery();
            set.next();
            int count = set.getInt("rowcount");
            set.close();
            return count == 0;
        } catch (SQLException e) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new DatabaseException(EXCEPTION);
        } finally {
            closeResultset(set);
            JDBCConnection.getInstance().closeConnection();
        }

    }

    public boolean createBenutzer(String email, String passwort, String role) {

        String sql = "INSERT INTO stealthyalda.benutzer(email, passwort, role) VALUES (?,?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);
        PasswordAuthentication hasher = new PasswordAuthentication();

        // convert to char[] as the string method is deprecated
        char[] c = passwort.toCharArray();
        String passwordHash = hasher.hash(c); // password hash
        ResultSet genKeys = null;

        try {
            statement.setString(1, email);
            statement.setString(2, passwordHash);
            statement.setString(3, role);
            int rowsChanged = statement.executeUpdate();
            if (rowsChanged == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            genKeys = statement.getGeneratedKeys();
            if (genKeys.next()) {
                Long userId = genKeys.getLong(1);
                VaadinSession.getCurrent().setAttribute("userId", userId);
                logEntry("BenutzerDAO", Level.INFO, "Found userid: " + userId);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            return true;
        } catch (SQLException ex) {
            logEntry(this.getClass().getName(), Level.SEVERE, ex.getMessage());
            return false;
        } finally {
            closeResultset(genKeys);
        }
    }

    public boolean updateStammdaten(Benutzer u, String anrede, Benutzer user) {
        String updateBenutzerTbl = "UPDATE stealthyalda.benutzer " +
                "SET " +
                "anrede = ?, " +
                "telefonnummer =  ? " +
                "WHERE " +
                "benutzer_id = ?;";
        PreparedStatement statement = this.getPreparedStatement(updateBenutzerTbl);

        try {
            statement.setString(1, anrede);
            statement.setString(2, u.getTelefonnummer());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ArbeitgeberDAO.class.getName()).log(Level.SEVERE, ex.getMessage());
            return false;

        }
    }


    public boolean changepassword(String email, String altpasswort, String neupasswort) throws DatabaseException {
        String sql = "UPDATE stealthyalda.benutzer SET passwort = ? WHERE stealthyalda.benutzer.email = ? AND stealthyalda.benutzer.passwort = ?;";


        PasswordAuthentication hasher = new PasswordAuthentication();
        char[] alt = altpasswort.toCharArray();
        char[] neu = neupasswort.toCharArray();


        try (PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql)) {
            String currentHash = getBenutzer(email, altpasswort).getPasswort();
            if (!hasher.authenticate(alt, currentHash)) {
                throw new NoSuchUserOrPassword();
            }
            statement.setString(1, hasher.hash(neu));
            statement.setString(2, email);
            statement.setString(3, currentHash);
            statement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, throwables.getMessage());
            return false;
        } catch (NoSuchUserOrPassword noSuchUserOrPassword) {
            Logger.getLogger(BenutzerDAO.class.getName()).log(Level.SEVERE, noSuchUserOrPassword.getMessage(), noSuchUserOrPassword);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
        return false;
    }


}
