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

    /**
     * Constructor de la classe d'un Dossier Medical.
     * @param patient
     * @param numS
     * @param dateE
     * @param correspondance 
     */
    public DM(Patient patient, String numS, java.sql.Date dateE, String correspondance) {
        this.patient = patient;
        this.dateE = dateE;
        this.numS = numS;
        this.correspondance = correspondance;
        this.documents = new ArrayList<>();
    }

    /**
     * Renvoie la classe Patient.
     * @return 
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Define la classe Patient.
     * @param patient 
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Renvoie le numero de sejour.
     * @return 
     */
    public String getNumS(){
        return numS;
    }
    
    /**
     * Renvoie la date d'entrée.
     * @return 
     */
    public java.sql.Date getDateE(){
        return dateE;
    }
    
    /**
     * Ajoute la classe Document dans la liste de documents.
     * @param document 
     */
    public void addDocument(Document document){
        documents.add(document);
    }
    
    /**
     * Define le numero de sejour.
     * @param numS 
     */
    public void setNumS(String numS){
        this.numS = numS;
    }
    
    /**
     * Define la date de ouverture.
     * @param date 
     */
    public void setDateO(java.sql.Date date){
        this.dateE = date;
    }

}
