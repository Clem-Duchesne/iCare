/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.db;

import iHealth.db.DatabaseAccessProperties;
import iHealth.db.SQLWarningsExceptions;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;


/**
 *
 * @author cleme
 */
public class ConnectBD {
    private static final String configurationFile = "src/iHealth/db/database.properties";
     
   
    private Connection conn = null;
    private Statement statement = null;
    private ResultSet resultat = null;
    private String jdbcDriver, dbUrl, username, password;
     
     public ConnectBD() throws ClassNotFoundException, SQLException {
         DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
            jdbcDriver = dap.getJdbcDriver();
            dbUrl = dap.getDatabaseUrl();
            username = dap.getUsername();
            password = dap.getPassword();
         try{
            
            

       // Load the database driver
            Class.forName(jdbcDriver);
       // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            statement = conn.createStatement();
         }
         catch (SQLException se) {
   // Print information about SQL exceptions
               SQLWarningsExceptions.printExceptions(se);
               return;
           } catch (Exception e) {
               System.err.println("Exception: " + e.getMessage());
               e.printStackTrace();
               return;
            }
        
    }
     /*
    public boolean connexion(){
        System.out.print("cc");
         /* Chargement du drier JDBC par MySQL */
     /*
        try {
            Class.forName(jdbcDriver);
        } 
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            statement = conn.createStatement();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
*/
    public void closeConnexion() throws SQLException{
         // Print information about connection warnings
               SQLWarningsExceptions.printWarnings(conn);
               conn.close();
    }
    public Connection getConnexion(){
        return conn;
    }
    public Statement getStatement(){
        return statement;
    }
    public ResultSet exec(String requete) {
        try {
            resultat = statement.executeQuery(requete);
            return resultat;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void close() {
        if (resultat != null) {
            try {
                resultat.close();
            } catch (SQLException ignore) {
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignore) {
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignore) {
            }
        }
    }


}
