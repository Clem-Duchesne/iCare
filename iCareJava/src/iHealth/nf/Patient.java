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
    
    public String getIPP(){
        return IPP;
    }
    
    public String getNom(){
        return nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public java.sql.Date getDateNaissance(){
        return dateNaissance;
    }
    
    public Sexe getSexe(){
        return sexe;
    }
    
    public String getAdresse(){
        return adresse;
    }
    
    public java.sql.Date getPremiereVenue(){
        return annee_premiere_venue;
    }

    
    
    

}
