package iHealth.nf;

/**
 *
 * @author cleme
 */
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;


public class Patient {
    private String IPP;
    private String nom;
    private String prenom;
    private java.sql.Date dateNaissance;
    private Sexe sexe;
    private java.sql.Date annee_premiere_venue;
    private String adresse;
    //private DPI dPI;
    /**
     * Constructor de la classe Patient
     * @param IPP
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @param sexe
     * @param adresse
     * @throws ParseException 
     */
    public Patient(String IPP, String nom, String prenom, String dateNaissance, Sexe sexe, String adresse) throws ParseException{
        this.IPP = IPP;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = new toDate().stringToDate(dateNaissance);
        this.sexe = sexe;
        this.adresse = adresse;

    }
    
     public Patient(String IPP, String nom, String prenom, java.sql.Date dateNaissance, Sexe sexe, String adresse) throws ParseException{
        this.IPP = IPP;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        

    }
    public Patient(java.sql.Date annee_premiere_venue, String nom, String prenom, java.sql.Date dateNaissance, Sexe sexe, String adresse) throws ParseException{
        
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        this.annee_premiere_venue = annee_premiere_venue;
        /*
        System.out.println("patient");
        System.out.println(nom);
        System.out.println(prenom);
        System.out.println(dateNaissance);
        System.out.println(sexe);
        System.out.println(adresse);
        System.out.println(annee_premiere_venue);
*/
        
        
        //DMA mon_dma = new DMA(this, annee_premiere_venue);

    }
    
    /**
     * Renvoie l'indentifiant du Patient
     * @return 
     */
    public String getIPP(){
        return IPP;
    }
    
    /**
     * Renvoie le nom du Patient
     * @return 
     */
    public String getNom(){
        return nom;
    }
    
    /**
     * Renvoie le prenom du Patient
     * @return 
     */
    public String getPrenom(){
        return prenom;
    }
    
    /**
     * Renvoie la date de naissance du Patient
     * @return 
     */
    public java.sql.Date getDateNaissance(){
        return dateNaissance;
    }
    
    /**
     * Renvoie le sexe du Patient
     * @return 
     */
    public Sexe getSexe(){
        return sexe;
    }
    
    /**
     * Renvoie l'adresse du Patient
     * @return 
     */
    public String getAdresse(){
        return adresse;
    }
    
    /**
     * Renvoie la date de premiere venue du Patient
     * @return 
     */
    public java.sql.Date getPremiereVenue(){
        return annee_premiere_venue;
    }

    /**
     * Define l'identifiant du Patient
     * @param IPP 
     */
    public void setIPP(String IPP){
        this.IPP = IPP;
    }
    
    /**
     * Define l'année d'entrée du Patient
     * @param annee 
     */
    public void setAnneeEntree(java.sql.Date annee){
        this.annee_premiere_venue = annee;
    }
    
    
    

}
