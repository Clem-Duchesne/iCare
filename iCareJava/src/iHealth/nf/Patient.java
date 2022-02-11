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
    private String sexe;
    private String adresse;
    //private DPI dPI;
    
    public Patient(LocalDate annee_premiere_venue, String nom, String prenom, LocalDate dateNaissance, String sexe, String adresse){
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        DMA mon_dma = new DMA(this);
    }
    

}
