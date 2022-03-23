package iHealth.nf;

/**
 *
 * @author cleme
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DMA {
    private java.sql.Date dateCreation;
    private java.sql.Date dateModification;
    private String IPP;
    private Patient patient;
    private List<Consultation> consultation = new ArrayList<Consultation> ();
    
    /**
     * Constructor de la classe DMA - Dossier Medical Administrative.
     * @param IPP
     * @param dateCreation 
     */
    public DMA(String IPP, java.sql.Date dateCreation){
       this.IPP = IPP;
       this.dateCreation = dateCreation;
        
    }
    
    
    public DMA(Patient patient, java.sql.Date dateCreation){
        this.patient =patient;
        this.dateCreation = dateCreation;
        DPI mydpi = new DPI(this);
        
    }
    
    /**
     * Ajoute une Consultation dans la liste de consultations.
     * @param consultation 
     */
    public void addConsultation(Consultation consultation){
        this.consultation.add(consultation);
        
    }
    
    /**
     * Renvoie la date de creation d'un DMA.
     * @return 
     */
    public java.sql.Date getDateCreation(){
        return dateCreation;
    }
    
    /**
     * Renvoie le Patient DMA actuel.
     * @return 
     */
    public Patient getPatient(){
        return patient;
    }
    /**
     * Renvoie l'identifiant patient.
     * @return 
     */
    public String getIPP(){
        return IPP;
    }
    /*
    public Consultation getConsultation(LocalDate dateConsultation){
        
    }
*/

}
