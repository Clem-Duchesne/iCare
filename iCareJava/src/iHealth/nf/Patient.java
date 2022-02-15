package iHealth.nf;

/**
 *
 * @author cleme
 */
import java.time.LocalDate;
import java.util.Date;


public class Patient {
    private String IPP;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Sexe sexe;
    private String adresse;
    //private DPI dPI;
    public Patient(String IPP, String nom, String prenom, LocalDate dateNaissance, Sexe sexe, String adresse){
        this.IPP = IPP;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        DMA mon_dma = new DMA(this);

    }
    public Patient(LocalDate annee_premiere_venue, String nom, String prenom, LocalDate dateNaissance, Sexe sexe, String adresse){
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
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
    
    public LocalDate getDateNaissance(){
        return dateNaissance;
    }
    
    public Sexe getSexe(){
        return sexe;
    }
    
    public String getAdresse(){
        return adresse;
    }
    
    
    
    

}
