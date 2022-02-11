/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.db;

import iHealth.ui.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cleme
 */
public class requetes {
    
    public boolean connection(Connection conn, String id, String password) throws SQLException {
// Get a statement from the connection
        Statement stmt = conn.createStatement();
// Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Comptes WHERE identifiant = '" + id + "'");
        boolean result = false;
        if (rs != null) {
                        try {
                            if (!rs.isBeforeFirst()) {
                                result =false;
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            while (rs.next()) {
                                if (rs.getString("identifiant").equals(id) && rs.getString("motdepasse").equals(password)) {
                                    result = true;
                                } else {
                                    //errorMessage.setVisible(true);
                                    result = false;
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
        rs.close();
        stmt.close();
        
        return result;

    }
}
