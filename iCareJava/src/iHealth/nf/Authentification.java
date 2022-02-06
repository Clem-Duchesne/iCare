/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.nf;

import iHealth.ui.Connexion;
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
public class Authentification {
    private List<Compte> lesComptes = new ArrayList<Compte> ();
    private Compte compte;
    private String identifiant;

    public Authentification(){
    }

    public boolean seConnecter(/*Connection conn, */String identifiant, String motdepasse) throws SQLException{
        //Statement stmt = conn.createStatement();
        //ResultSet rs = stmt.executeQuery("SELECT * FROM Comptes WHERE identifiant = '" + identifiant + "'");
        boolean res=false;
        /*if (rs.next()) {
            if(rs.getString(2).equals(motdepasse)){
                res=true;
            }
        }
*/
        String id = "001234";
        String password = "oui";
        if(identifiant.equals(id) && motdepasse.equals(password)){
            res = true;
        }
        return res;
    }
    
    public Poste definirPoste(String identifiant){
        Poste poste = null;
        String posteStr = identifiant.substring(0,2);
        System.out.print(posteStr);
                
                switch(posteStr){
                    case "00": 
                        poste = Poste.SECRETAIREA;
                        break;
                    
                    case "01":
                        poste = Poste.SECRETAIREM;
                        break;
                   
                    case "02":
                        poste = Poste.MEDECIN;
                        break;

                    case "03":
                        poste = Poste.KINESITHERAPEUTE;
                        break;
                    case "04":
                        poste = Poste.ANESTHESISTE;
                        break;
                    case "05":
                        poste = Poste.RADIOLOGUE;
                        break;
                    case "06":
                        poste = Poste.INFIRMIERE;
                        break;
                    default:
                        poste = null;
                        break;
                }
        return poste;
    }
    
    
    
}
