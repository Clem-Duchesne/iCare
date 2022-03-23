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
    
    public DMA(String IPP, java.sql.Date dateCreation){
       this.IPP = IPP;
       this.dateCreation = dateCreation;
        
    }
    
    public DMA(Patient patient, java.sql.Date dateCreation){
        this.patient =patient;
        this.dateCreation = dateCreation;
        DPI mydpi = new DPI(this);
        
    }
    

    public java.sql.Date getDateCreation(){
        return dateCreation;
    }
    
    public Patient getPatient(){
        return patient;
    }
    public String getIPP(){
        return IPP;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }
    public void addConsultation(Consultation consultation){
        this.consultation.add(consultation);
    }
   


}
