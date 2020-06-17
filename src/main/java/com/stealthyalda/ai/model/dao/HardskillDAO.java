/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.model.dao;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Hardskill;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.services.db.JDBCConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WINDOWS
 */
public class HardskillDAO extends AbstractDAO {
    private static HardskillDAO dao = null;
    private HardskillDAO(){

    }
    public static HardskillDAO getInstance(){
        if(dao==null){
            dao = new HardskillDAO();
        }
        return dao;
    }
    public List<Hardskill> getHardskillsForUser(Benutzer user) throws DatabaseException {
        Statement statement=this.getStatement();
        List<Hardskill> liste = new ArrayList <>();
        ResultSet rs = null;
        try {
            String sql = "select h.hardskill_id, h.hardskill\n" +
                    "from stealthyalda.hardskill h\n" +
                    "inner join  stealthyalda.student_hat_hardskill sh on sh.hardskill_id = h.hardskill_id\n" +
                    "inner join stealthyalda.student s on s.student_id = sh.student_id\n" +
                    "where s.benutzer_id = '" + user.getId() + "';";
            rs = statement.executeQuery(sql);
            Hardskill hardskill = null;
            while (rs.next()){
                hardskill= new Hardskill();
                hardskill.setHardskill_id(rs.getInt(1));
                hardskill.setHardskill(rs.getString(2));
                liste.add(hardskill);
            }

        }catch (SQLException ex){
            Logger.getLogger(Hardskill.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            closeResultset(rs);
        }
        return liste;
    }
    
    public void deleteHardskillForUser(int h,Student s) throws DatabaseException {
        String sql;
        sql = "DELETE FROM stealthyalda.student_hat_hardskill WHERE hardskill_id = '" + h + "' AND student_id = '"+s.getStudent_id()+"';";

        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        try {
            assert statement != null;
            statement.executeUpdate();

        } catch (SQLException throwables) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, throwables.getMessage());
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

    }
    
    public void createHardskillForUser(Hardskill hardskill, Student s) throws DatabaseException {
         String sql = "insert into stealthyalda.hardskill values(default,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);
        
         try{
            statement.setString(1,hardskill.getHardskill());
            setHardskillsID(hardskill);
            statement.executeUpdate();
            String sql2 =  "insert into stealthyalda.student_hat_hardskill values(" + s.getStudent_id() +", " + hardskill.getHardskill_id() +");";
            PreparedStatement statement2 = this.getPreparedStatement(sql2);
            statement2.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(HardskillDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    private void setHardskillsID(Hardskill h) throws SQLException, DatabaseException {
        Statement statement = this.getStatement();
        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT max(stealthyalda.hardskill.hardskill_id) FROM stealthyalda.hardskill");
            int currentValue = 0;
            rs.next();
            currentValue=rs.getInt(1);
            h.setHardskill_id(currentValue);
        } catch (SQLException ex) {
            Logger.getLogger(HardskillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            closeResultset(rs);
        }

    }
    
    
}


