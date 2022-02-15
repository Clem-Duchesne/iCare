package iHealth.nf;

/**
 *
 * @author cleme
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DMA {
    private LocalDate dateCreation;
    private LocalDate dateModification;
    private Patient patient;
    private List<Consultation> consultation = new ArrayList<Consultation> ();
    
    public DMA(Patient patient, LocalDate dateCreation){
        this.patient =patient;
        this.dateCreation = dateCreation;
        DPI mydpi = new DPI(this);
        
    }

    DMA(Patient aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addConsultation(Consultation consultation){
        this.consultation.add(consultation);
        
    }
    
    public LocalDate getDateCreation(){
        return dateCreation;
    }
    
    public Patient getPatient(){
        return patient;
    }
    /*
    public Consultation getConsultation(LocalDate dateConsultation){
        
    }
*/

}
