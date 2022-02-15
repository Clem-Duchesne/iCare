/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.db;

import iHealth.nf.Patient;
import iHealth.nf.Sexe;
import iHealth.nf.toDate;
import iHealth.nf.toSexe;
import iHealth.ui.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cleme
 */
public class requetes {
    
    // requête d'authentification
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
    
    //requête création patient
    public void createPatient(Connection conn, Patient patient) throws SQLException{
        Statement stmt = conn.createStatement();
        String IPP = patient.getIPP();
        String nom = patient.getNom();
        String prenom = patient.getPrenom();
        String adresse = patient.getAdresse();
        LocalDate dateNaissance = patient.getDateNaissance();
        Sexe sexe = patient.getSexe();
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("INSERT INTO Patient VALUES ('" + IPP + "','"+ nom + "','" + dateNaissance + "','" + sexe + "','" + adresse + "'");
    
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
    
        //requête sélection nom, prénom personnel
    public String[] getPersonnel(Connection conn, String id) throws SQLException{
        
        String[] identite = new String[2];
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Comptes WHERE numP = '" + id + "'");
            String prenom = rs.getString("prenom");
            String nom = rs.getString("nom");
            identite[0] = nom;
            identite[1] = prenom;
        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
        
        return identite;
    }
    
    //récupérer la liste des patients de tout l'hôpital
    public List<Patient> getPatients(Connection conn) throws SQLException{
        
//Créer une liste de patient
        List<Patient> patients = new ArrayList<Patient>();
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Patient");
        
        while(rs.next()){
            String IPP = rs.getString("IPP");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String adresse = rs.getString("adresse");
            LocalDate dateNaissance = new toDate().convertToLocalDateViaInstant(rs.getDate("dateN"));
            Sexe sexe = new toSexe().stringToSexe(rs.getString("sexe"));
            
            Patient patient = new Patient(IPP,nom,prenom,dateNaissance, sexe, adresse);
            patients.add(patient);
            
            
        }
     
        
        
        
        String prenom = rs.getString("prenom");
        String nom = rs.getString("nom");
        String[] identite = {nom, prenom};
        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close(); 
        
        return patients;
    }
}
