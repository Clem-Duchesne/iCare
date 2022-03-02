/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.db;

import iHealth.nf.DMA;
import iHealth.nf.Patient;
import iHealth.nf.Sexe;
import iHealth.nf.toDate;
import iHealth.nf.toSexe;
import iHealth.ui.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRO WHERE numP = '" + id + "'");
        
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
                                if (rs.getString("numP").equals(id) && rs.getString("mdp").equals(password)) {
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
        String IPP = patient.getPremiereVenue().toString().substring(8,10) + patient.getPremiereVenue().toString().substring(2, 4) + (10000 + (int)(Math.random() * ((99999 - 10000) + 1)));;
        
        String nom = patient.getNom();
        String prenom = patient.getPrenom();
        String adresse = patient.getAdresse();
        java.sql.Date dateNaissance = patient.getDateNaissance();
        
        Sexe sexe = patient.getSexe();
        String sexeString = new toSexe().sexeToString(sexe);
        System.out.println(IPP + nom +  prenom +  dateNaissance +  sexeString + adresse);
      //  System.out.println(dateNaissance.getTime());
        
        // Execute the query

        
        ResultSet rs = stmt.executeQuery("INSERT INTO Patient VALUES ('" + IPP + "','"+ nom + "','" + prenom + "', to_date('" + dateNaissance + "', 'YYYY-MM-DD'),'" + sexeString + "','" + adresse + "')");
    
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRO WHERE numP = '" + id + "'");
        String prenom = null;
        String nom = null;
        while (rs.next()) {
            prenom = rs.getString(3);
            nom = rs.getString(4);
        }
            identite[0] = nom;
            identite[1] = prenom;
        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
        
        return identite;
    }
    
    //requête sélection nom, prénom personnel
    /*
    public DMA getDMA_IPP(Connection conn, Patient patient) throws SQLException{
        
        
         Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM DMA Where IPP = '" + patient.getIPP() + "'");
        
        String IPP = null;
        java.sql.Date dateS = null;
        String ph = null;
        
        String natureP = null;
        String service = null;
        while(rs.next()){
            IPP = rs.getString("IPP");
            dateS = rs.getDate("dateS");
            ph = rs.getString("nomPH");
            
            natureP = rs.getString("nature");
            service = rs.getString("service");
            
            DMA dma = new DMA(IPP,dateS,ph,natureP,service);
            
            if(rs.getString("lettreS") != null ){
                String lettreS = rs.getString("lettreS");
                dma.setLettreS(lettreS);
            }

        }
        
        
        
        
        
        
        return dma;
    }
*/
    
    //récupérer la liste des patients de tout l'hôpital
    public List<Patient> getPatients(Connection conn) throws SQLException, ParseException{
        
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
            java.sql.Date dateNaissance = rs.getDate("dateN");
            Sexe sexe = new toSexe().stringToSexe(rs.getString("sexe"));
            
            Patient patient = new Patient(IPP,nom,prenom,dateNaissance, sexe, adresse);
            patients.add(patient);
            
            
        }

        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close(); 
        
        return patients;
    }
    
    //Recherche de patient
    public List<Patient> getPatient(Connection conn, String nom) throws SQLException, ParseException{
        
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Patient WHERE nom = '" + nom + "'");
        List<Patient> patients = new ArrayList<>();
        
        String prenom = null;
        String nomPatient = null;
        String IPP = null;
        java.sql.Date dateNaissance = null;
        String sexe = null;
        String adresse = null;
        Sexe format = null;
        while (rs.next()) {
            IPP = rs.getString("IPP");
            nomPatient = rs.getString("nom");
            prenom = rs.getString("prenom");
            dateNaissance = rs.getDate("dateN");
            sexe = rs.getString("sexe");
            format = new toSexe().stringToSexe("sexe");
            adresse = rs.getString("adresse");  
            
            Patient patient = new Patient(IPP, nomPatient, prenom, dateNaissance, format, adresse);
            patients.add(patient);
        }
        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
        
        return patients;
    }
}
