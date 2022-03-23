package iHealth.nf;

/**
 *
 * @author cleme
 */
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public class DM {

    private String dateCreation;

    private String dateDerniereModification;
    private List<Document> document = new ArrayList<Document>();
    public Chambre chambre;

    private Patient patient;
    private String numS;
    private java.sql.Date dateE;
    private List<Document> documents;
    private String correspondance;

    public DM() {

    }

    public DM(Patient patient, String numS, java.sql.Date dateE, String correspondance) {
        this.patient = patient;
        this.dateE = dateE;
        this.numS = numS;
        this.correspondance = correspondance;
        this.documents = new ArrayList<>();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

 
    
    public String getNumS(){
        return numS;
    }
    
    public java.sql.Date getDateE(){
        return dateE;
    }
    
    public void addDocument(Document document){
        documents.add(document);
    }
    
    public void setNumS(String numS){
        this.numS = numS;
    }
    public void setDateO(java.sql.Date date){
        this.dateE = date;
    }

}
