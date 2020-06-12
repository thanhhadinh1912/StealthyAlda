package com.stealthyalda.tests;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.services.db.JDBCConnection;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JDBCConnectionTest {

    private JDBCConnection n;

    @Test
    void getInstance() {
        try {
            n = JDBCConnection.getInstance();
        } catch (DatabaseException e) {
            Logger.getLogger(JDBCConnectionTest.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        assertNotNull(n);
    }

    @Test
    void initConnection() {
    }

    @Test
    void openConnection() {
    }

    @Test
    void getStatement() {
    }

    @Test
    void closeConnenction() {
    }

    @Test
    void getPreparedStatement() {
    }
}