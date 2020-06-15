package com.stealthyalda.ai.model.dao;


import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dtos.Role;
import com.stealthyalda.ai.model.entities.Benutzer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDAO extends AbstractDAO {

    private static RoleDAO dao;

    private RoleDAO() {

    }

    public static RoleDAO getInstance() {
        if (dao == null) {
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<Role> getRolesForUser(Benutzer user) throws SQLException, DatabaseException {
        ResultSet rs = null;
        // labs

        try {
            PreparedStatement preparedStatement = this.getPreparedStatement("SELECT role from stealthyalda.benutzer where stealthyalda.benuter.benutzer_id =  = ?;");

            preparedStatement.setInt(1, user.getId());

            rs = preparedStatement.executeQuery();
            Logger.getLogger(RoleDAO.class.getName()).log(Level.INFO, "all good");
        } catch (SQLException throwables) {
            logEntry(this.getClass().getName(),Level.SEVERE, throwables.getMessage());
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");
        }
        // labs


        List<Role> liste = new ArrayList<>();
        Role role;

        try {
            while (rs.next()) {
                role = new Role();
                role.setBezeichnung(rs.getString(1));
                liste.add(role);
            }
        } catch (SQLException throwables) {
            logEntry(this.getClass().getName(),Level.SEVERE, throwables.getMessage());
            throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen.");

        } finally {
            rs.close();
        }

        return liste;
    }
}
