package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.NoSuchUserOrPassword;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.db.JDBCConnection;
import com.stealthyalda.services.util.Account;
import com.stealthyalda.services.util.Views;
import com.vaadin.ui.UI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControl {
    public static void checkAuthentification( String email, String password) throws NoSuchUserOrPassword, DatabaseException {
        ResultSet set = null;
        try {
            //DB - Zugriffxyss
            Statement statement = JDBCConnection.getInstance().getStatement();
            set = statement.executeQuery("SELECT * \n" +
                    "FROM stealthyalda.account\n" +
                    "WHERE stealthyalda.account.email = '" + email + "'\n" +
                    "AND stealthyalda.account.passwort = '"+password+"';");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmier benachrichtigen");
        }

        Benutzer benutzer = null;

        try {
            if(set.next()){
                benutzer = new Benutzer();
                benutzer.setLogin(set.getString(1));
                benutzer.setVorname(set.getString(4));
            } else{
                //Error Handling
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

        // Der Benutzer ist vorhanden
        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);


    }
    public static void logoutUser() {
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/");
        //UI.getCurrent().getSession().close();
    }
}

