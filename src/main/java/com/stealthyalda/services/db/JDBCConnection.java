package com.stealthyalda.services.db;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.services.util.Password;
import org.postgresql.Driver;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {
    private static JDBCConnection connection = null;
    private String url = "jdbc:postgresql://dumbo.inf.h-brs.de:5432/wrast2s";
    private Connection conn;
    private String login = "wrast2s";
    private Password password;
    public static JDBCConnection getInstance() throws DatabaseException {
        if(connection == null){
            connection = new JDBCConnection();
        }
        return connection;
    }
    private JDBCConnection() throws DatabaseException{
        this.initConnection();
    }
    public void initConnection() throws DatabaseException {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.openConnection();
    }

    public void openConnection() throws DatabaseException {
        try {
            Properties props = new Properties();
            props.setProperty("user", "wrast2s");
            props.setProperty("password", Password.PASSWORD);

            this.conn = DriverManager.getConnection(this.url, props);

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler bei Zugriff auf die DB! Sichere Verbindung vorhanden!?");
        }
    }
    public Statement getStatement() throws DatabaseException{
        try {
            if(this.conn.isClosed()){
                this.openConnection();
            }
            return this.conn.createStatement();
        }
        catch (SQLException ex){
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public void closeConnenction(){
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public PreparedStatement getPreparedStatement( String sql) throws DatabaseException {
        try {
            if(this.conn.isClosed()) {
                this.openConnection();
            }
            return this.conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler beim Zugriff auf die Datenbank! Sichere Verbindung vorhanden?");
        }
    }
}
