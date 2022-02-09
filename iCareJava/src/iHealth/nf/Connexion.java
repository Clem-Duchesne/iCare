/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.nf;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author cleme
 */
public class Connexion {
    private List<Compte> lesComptes = new ArrayList<Compte> ();
    private Compte compte;
    private String identifiant;

    public Connexion() throws SQLException{
    }

    public boolean seConnecter(Connection conn, String identifiant, String motdepasse) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Comptes WHERE identifiant = '" + identifiant + "'");
        boolean res=false;
        if (rs.next()) {
            if(rs.getString(2).equals(motdepasse)){
                res=true;
            }
        }
        return res;
    }
    
}
