/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.db;

import iHealth.nf.Authentification;
import iHealth.nf.Chambre;
import iHealth.nf.Consultation;
import iHealth.nf.DMA;
import iHealth.nf.DM;
import iHealth.nf.Document;
import iHealth.nf.DocumentType;
import iHealth.nf.Lit;
import iHealth.nf.Localisation;
import iHealth.nf.NumeroSejour;
import iHealth.nf.PH;
import iHealth.nf.Patient;
import iHealth.nf.Poste;
import iHealth.nf.Professionnel;
import iHealth.nf.Service;
import iHealth.nf.Sexe;
import iHealth.nf.toDate;
import iHealth.nf.toDocument;
import iHealth.nf.toLocalisation;
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
        
    
        System.out.println(sexeString);
        
        // Execute the query

        
        ResultSet rs = stmt.executeQuery("INSERT INTO Patient VALUES ('" + IPP + "','"+ nom + "','" + prenom + "', to_date('" + dateNaissance + "', 'YYYY-MM-DD'),'" + sexeString + "','" + adresse + "')");
    
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
    
    //requête création séjour
    public void addSejour(Connection conn, String IPP, Consultation consultation) throws SQLException{
        Statement stmt = conn.createStatement();
        
        String numS = consultation.getNumeroSejour().getNumero();
        String numP = consultation.getNumP();
        java.sql.Date date_entree = consultation.getDateDebutSejour();
        String date_sql_e = date_entree.toString();
        //java.sql.Date date_sortie = null;
        String date_sql_s = "0000-00-00";
        String nature = consultation.getNaturePrestation();
        String lettreS = null;
        String service_resp = consultation.getLit().getChambre().getServiceResponsable().toString();
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("INSERT INTO Sejour VALUES ('" + numS + "','" + IPP + "','" + numP + "', to_date('" + date_sql_e + "', 'YYYY-MM-DD'), NULL ,'"+ nature + "','" + service_resp + "','" + lettreS + "')");
    
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
    //requête création localisation
    public void addLocalisation(Connection conn, String IPP, Lit lit, Consultation consultation) throws SQLException{
        Statement stmt = conn.createStatement();
        
        String loc = new toLocalisation().LocalisationtoString(lit.getLoc());
        String chambre = lit.getChambre().getNumeroChambre();
        String service_resp = lit.getChambre().getServiceResponsable().toString();
        String service_geo = lit.getChambre().getServiceGeographique().toString();
        String num_sejour = consultation.getNumeroSejour().getNumero();
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("INSERT INTO LOC VALUES ('" + num_sejour + "','" + IPP + "','" + loc + "','" + chambre + "','" + service_resp + "','" + service_geo +"')");
    
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
    
     //requête création DMA
    public void createDMA(Connection conn, DMA dma) throws SQLException{
        Statement stmt = conn.createStatement();
        
        java.sql.Date date_creation = dma.getDateCreation();
        String date_sql = date_creation.toString();
        System.out.println(date_creation);
        String IPP = dma.getIPP();
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("INSERT INTO DMA VALUES ('" + IPP + "', to_date('" + date_sql + "','YYYY-MM-DD'))");
    
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
    
    
    
    
    //requête sélection nom, prénom personnel
    public String getPersonnel(Connection conn, String id) throws SQLException{
        
        String identite = null;
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
            identite = nom + " " + prenom;

        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
        
        return identite;
    }
    
    //requête récup listes practiciens
    public List<PH> getPHs(Connection conn) throws SQLException{
        
        List<PH> PHs = new ArrayList<>();
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRO");
        
        while(rs.next()){
            
            String numP = rs.getString("numP");
            if(new Authentification().definirPoste(numP) == Poste.MEDECIN){
                String nom = rs.getString("nomP");
                String prenom = rs.getString("prenomP");
                String service_str = rs.getString("serviceP");
                
                Service service = Service.valueOf(Service.class, service_str);
                

                PH ph = new PH(numP,nom,prenom,service);
                PHs.add(ph);
            }
        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close(); 
        
        return PHs;
    }
    //by numP
     public PH getPH(Connection conn, String numP) throws SQLException{
        
        PH ph = null;
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRO WHERE numP = '" + numP + "'");
        
        while(rs.next()){
                String nom = rs.getString("nomP");
                String prenom = rs.getString("prenomP");
                String service_str = rs.getString("serviceP");    
                Service service = Service.valueOf(Service.class, service_str);
                ph = new PH(numP,nom,prenom,service);
        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close(); 
        
        return ph;
    }
     //by name and surname
    public String getPHnumP(Connection conn, String nom, String prenom) throws SQLException{
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        
        String numP = null;
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRO WHERE nomP = '" + nom + "' AND prenomP = '" + prenom + "'");

        while(rs.next()){
            numP = rs.getString("numP");

        }
//        System.out.println(numP);
        // Close the result set, statement and the connection
        rs.close();
        stmt.close(); 
        
        return numP;
    }
    public String getPHService(Connection conn, String nom, String prenom) throws SQLException{
         // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRO WHERE nomP = '" + nom + "' AND prenomP = '" + prenom + "'");
        String service_P = null;
        while(rs.next()){
            service_P = rs.getString("serviceP");

        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close(); 
        
        return service_P;
    }
    public List<Patient> getPatientService(Connection conn, String service_resp)throws SQLException, ParseException{
        //Créer une liste de patient
        List<Patient> patients = new ArrayList<Patient>();
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Patient natural join Sejour WHERE service ='" + service_resp + "'");
        
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
     public Lit getLoc(Connection conn, String IPP, String num_sejour) throws SQLException{
        
        Lit lit = null;
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LOC WHERE numS = '" + num_sejour + "' AND IPP = '" + IPP + "'");
        
        while(rs.next()){
                String loc = rs.getString("lit");
                String num_c = rs.getString("chambre");
                String service_geo = rs.getString("service_geographique");    
                String service_resp = rs.getString("service_responsable"); 
                Service service_geo_s = Service.valueOf(Service.class, service_geo);
                Service service_resp_s = Service.valueOf(Service.class, service_resp);
                Localisation loc_l = new toLocalisation().StringToLocalisation(loc);
                lit = new Lit(loc_l, new Chambre(service_geo_s, service_resp_s,num_c));
        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close(); 
        
        return lit;
    }
     public Consultation getConsultationByNumS(Connection conn, String numS, String IPP) throws SQLException{
          // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Sejour WHERE numS = '" + numS + "' AND IPP = '" + IPP + "'");
        String service_P = null;
        String numP =null;
        String nature = null;
        Lit lit = null;
        Consultation sejour = null;
        while(rs.next()){
            service_P = rs.getString("service");
            nature = rs.getString("nature");
            numS = rs.getString("numS");
            numP = rs.getString("numP");
            NumeroSejour numS2 = new NumeroSejour(numS);
            lit = this.getLoc(conn, IPP, numS);
            java.sql.Date date_entree2 = rs.getDate("date_entree");
            sejour = new Consultation(numS2, IPP, date_entree2, numP,nature,lit);

        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close(); 
        
        
        return sejour;
     }
     public Consultation getSejour(Connection conn, String date_entree, String nature,String IPP) throws SQLException{
         // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Sejour WHERE date_entree = to_date('" + date_entree + "','YYYY-MM-DD') AND nature = '" + nature + "' AND IPP = '" + IPP + "'");
        String service_P = null;
        String numP =null;
        String numS = null;
        Lit lit = null;
        Consultation sejour = null;
        while(rs.next()){
            service_P = rs.getString("service");
            numS = rs.getString("numS");
            numP = rs.getString("numP");
            NumeroSejour numS2 = new NumeroSejour(numS);
            lit = this.getLoc(conn, IPP, numS);
            java.sql.Date date_entree2 = rs.getDate("date_entree");
            sejour = new Consultation(numS2, IPP, date_entree2, numP,nature,lit);

        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close(); 
        
        
        return sejour;
    }
    //requête sélection nom, prénom personnel
    
    public DMA getDMA_IPP(Connection conn, String IPP) throws SQLException{
        
        
         Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM DMA Where IPP = '" + IPP + "'");
        String IPP_found = null;
        java.sql.Date dateCreation = null;
        DMA dma = null;
        
        while(rs.next()){
            IPP_found = rs.getString("IPP");
            dateCreation = rs.getDate("date_creation");
            dma = new DMA(IPP, dateCreation);
        }

        return dma;
    }

    
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
    //Recherche de patient pour un ph
    public List<Patient> getPatientPH(Connection conn, String numP) throws SQLException, ParseException{
        
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Patient NATURAL JOIN Sejour WHERE numP = '" + numP + "'");
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
        
//        for (int i=0; i<patients.size();i++){
//            System.out.println(patients.get(i).getNom() + patients.get(i).getPrenom() + "\n");
//        }
        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
        
        return patients;
    }
      //Recherche de patient
    public Patient getPatientIPP(Connection conn, String IPP) throws SQLException, ParseException{
        
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Patient WHERE IPP = '" + IPP + "'");
        
        String prenom = null;
        String nomPatient = null;
        java.sql.Date dateNaissance = null;
        String sexe = null;
        String adresse = null;
        Sexe format = null;
        Patient patient = null;
        while (rs.next()) {
            nomPatient = rs.getString("nom");
            prenom = rs.getString("prenom");
            dateNaissance = rs.getDate("dateN");
            sexe = rs.getString("sexe");
            format = new toSexe().stringToSexe(sexe);
            adresse = rs.getString("adresse");  
            
            patient = new Patient(IPP,nomPatient, prenom, dateNaissance, format, adresse);

        }
        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
        
        return patient;
    }
    
    
    
    //Retourner séjour
    public  List<Consultation> getSejours(Connection conn, String IPP) throws SQLException, ParseException{
        
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM Sejour WHERE IPP = '" + IPP + "'");
        List<Consultation> consultations = new ArrayList<>();
        
        String numero_sejour = null;
        String numP = null;
        java.sql.Date dateEntree = null;
        java.sql.Date dateSortie = null;
        String nature = null;
        String service_r = null;
        String lettreSortie = null;
        Lit lit = null;
        while (rs.next()) {
            numero_sejour = rs.getString("numS");
            numP = rs.getString("numP");
            dateEntree = rs.getDate("date_entree");
            dateSortie = rs.getDate("date_sortie");
            nature = rs.getString("nature"); 
            service_r = rs.getString("service");
            lettreSortie = rs.getString("lettreS");
            lit = this.getLoc(conn, IPP, numero_sejour);
            NumeroSejour numeroSejour = new NumeroSejour(numero_sejour);
            Consultation consultation = new Consultation(numeroSejour, IPP, dateEntree, dateSortie, numP, nature, lit, lettreSortie);
            consultations.add(consultation);
        }

        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
        
        return consultations;
    }
    
    
    
    // Créer un Dossier Médical
    public void createDM(Connection conn, DM dm) throws SQLException, ParseException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();

        String IPP = dm.getPatient().getIPP();
        String numS = dm.getNumS();
        java.sql.Date date_ouverture = dm.getDateE();
      

        // Execute the query
        ResultSet rs = stmt.executeQuery("INSERT INTO DM VALUES ('" + IPP + "','" + numS + "'" + date_ouverture + "'" + null + "'')");

        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }

// Rechercher un Dossier Médical
    public DM getDM(Connection conn, Patient patient) throws SQLException, ParseException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM DM WHERE IPP = '" + patient.getIPP() + "'");
        DM dm = new DM();

        String IPP = null;
        String numS = null;
        java.sql.Date date_E =null;
        String correspondance = null;

        while (rs.next()) {
            IPP = rs.getString("IPP");
            numS = rs.getString("numS");
            date_E = rs.getDate("date_ouverture");
            correspondance = rs.getString("corres");

            dm = new DM(patient, numS, date_E, correspondance);
        }

        // Close the result set, statement and the connection
        rs.close();
        stmt.close();

        return dm;
    }

// Modifier un Dossier Médical
    public void setDMCorrespondance(Connection conn, Patient patient, String correspondance) throws SQLException, ParseException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("UPDATE DM SET corres='" + correspondance + "' WHERE IPP = '" + patient.getIPP() + "'");

        stmt.executeUpdate("commit");

        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
    
    //Modifier séjour
    public void setLettreS(Connection conn, Consultation sejour, String lettre) throws SQLException, ParseException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        java.sql.Date dateS = new java.sql.Date(System.currentTimeMillis());
        String date_sql = dateS.toString();
        
        // Execute the query
        System.out.print("UPDATE SEJOUR SET date_sortie = to_date('" + date_sql + "','YYYY-MM-DD') ,lettreS = '" + lettre + "' WHERE numS = '" + sejour.getNumeroSejour().getNumero() + "'");
        ResultSet rs = stmt.executeQuery("UPDATE SEJOUR SET date_sortie = to_date('" + date_sql + "','YYYY-MM-DD'), lettreS = '" + lettre + "' WHERE numS = '" + sejour.getNumeroSejour().getNumero() + "'");

        stmt.executeUpdate("commit");

        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
    
// Obtenir la date de séjour la plus récente dans le DM
    public Date get_date_entree(Connection conn, String IPP) throws SQLException, ParseException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT MAX(date_entree) FROM Sejour WHERE IPP = '" + IPP + "'");
        Date date = null;
        while (rs.next()) {
            date = rs.getDate(1);
        }

        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();

        return date;
    }
    
    public Date get_date_sortie(Connection conn, String IPP,String date_entree) throws SQLException, ParseException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT date_sortie FROM Sejour WHERE IPP = '" + IPP + "' AND date_entree= to_date('" + date_entree + "','YYYY-MM-DD')");
        Date date = null;
        while (rs.next()) {
            date = rs.getDate("date_sortie");
        }

        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();

        return date;
    }
    
// Obtenir le PH du séjour le plus récent
    public String get_PH_sejour(Connection conn, String IPP) throws SQLException, ParseException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT nomP, prenomP FROM PRO NATURAL JOIN Sejour WHERE date_entree=(SELECT max(date_entree) FROM Sejour WHERE IPP='" + IPP + "')");
        String nom = null;
        String prenom = null;
        while (rs.next()) {
            nom = rs.getString(1);
            prenom = rs.getString(2);
        }
//        System.out.println("PH de ce séjour : Dr. " + nom + " " + prenom);
        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();

        return "Dr. " + nom + " " + prenom;
    }
    
// Obtenir le service du dernier séjour
    public String get_service_sejour(Connection conn, String IPP) throws SQLException, ParseException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT service FROM Sejour WHERE IPP='" + IPP + "' AND date_entree=(SELECT MAX(date_entree) FROM Sejour WHERE IPP='" + IPP + "')");
        String service = null;
        while (rs.next()) {
            service = rs.getString(1);
        }
//        System.out.println("Service de ce séjour : " + service);
        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();

        return service;
    }
    
// Obtenir la nature du dernier séjour
    public String get_nature_sejour(Connection conn, String IPP) throws SQLException, ParseException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT nature FROM Sejour WHERE IPP='" + IPP + "' AND date_entree=(SELECT MAX(date_entree) FROM Sejour WHERE IPP='" + IPP + "')");
        String nature = null;
        while (rs.next()) {
            nature = rs.getString(1);
        }
//        System.out.println("Nature de ce séjour : " + nature);
        
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();

        return nature;
    }
    
    
    public List<Document> getDocuments(Connection conn, String IPP, String numS) throws SQLException{
        
        List<Document> documents = new ArrayList<>();
        Statement stmt = conn.createStatement();
        
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM DOCUMENT WHERE IPP='" + IPP + "' AND numS = '" + numS + "'");
        String idDoc = null;
        java.sql.Date date_ajout =null;
        DocumentType type = null;
        String numP =null;
        String titre =null;
        String description =null;
        
        
        while (rs.next()) {
            idDoc = rs.getString("idDoc");
            date_ajout = rs.getDate("date_ajout");
            type = new toDocument().toDocument(rs.getString("type"));
            numP = rs.getString("numP");
            titre = rs.getString("titre");
            description = rs.getString("description");
            
            Document document = new Document(idDoc, date_ajout, numS, numP,IPP, type, titre, description);
            documents.add(document);
        }
        
        return documents;
        
    }
    
       //requête ajout document
    public void addDocument(Connection conn, Document doc) throws SQLException{
        Statement stmt = conn.createStatement();
        
        java.sql.Date date_ajout = doc.getDateEcriture();
        String date_sql = date_ajout.toString();
   
        
        // Execute the query
       
	//System.out.print("INSERT INTO DOCUMENT VALUES ('" + doc.getIdDoc() + "','" + doc.getIPP() + "','" + doc.getNumS() + "', to_date('" + date_sql + "','YYYY-MM-DD'),'" + new toDocument().DocToStringUpperCase(doc.getType()) + "','" + doc.getNumP() + "','" + doc.getTitre() + "','" + doc.getDescription() + "')'");
        ResultSet rs = stmt.executeQuery("INSERT INTO DOCUMENT VALUES ('" + doc.getIdDoc() + "','" + doc.getIPP() + "','" + doc.getNumS() + "', to_date('" + date_sql + "','YYYY-MM-DD'),'" + new toDocument().DocToStringUpperCase(doc.getType()) + "','" + doc.getNumP() + "','" + doc.getTitre() + "','" + doc.getDescription() + "')" );
    
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }
    
    
}
