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
    public Patient(LocalDate annee_premiere_venue, String nom, String prenom, String dateNaissance, Sexe sexe, String adresse) throws ParseException{
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = new toDate().stringToDate(dateNaissance);
        this.sexe = sexe;
        this.adresse = adresse;
        DMA mon_dma = new DMA(this, annee_premiere_venue);

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

    
    
    

}
