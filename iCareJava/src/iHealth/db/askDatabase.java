/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cleme
 */
public class askDatabase {
     public static void lectureCompte(Connection conn) throws SQLException {
// Get a statement from the connection
        Statement stmt = conn.createStatement();
// Execute the query

        ResultSet rs = stmt.executeQuery("SELECT * FROM Comptes WHERE identifiant = '" + identifiant + "'");
        while (rs.next()) {
            System.out.print("numS : " + rs.getString(1) + " ----> ");
            System.out.print("nomS : " + rs.getString(2) + "\n");
        }

// Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
}
