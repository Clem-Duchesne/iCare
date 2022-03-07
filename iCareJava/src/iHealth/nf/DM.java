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
    private String operation;
    private String observation;
    private String prescription;
    private String resultat;
    private String lettreS;
    private String correspondance;

    public DM() {

    }

    public DM(Patient patient, String operation, String observation, String prescription, String resultat, String lettreS, String correspondance) {
        this.patient = patient;
        this.operation = operation;
        this.observation = observation;
        this.prescription = prescription;
        this.resultat = resultat;
        this.lettreS = lettreS;
        this.correspondance = correspondance;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getLettreS() {
        return lettreS;
    }

    public void setLettreS(String lettreS) {
        this.lettreS = lettreS;
    }

    public String getCorrespondance() {
        return correspondance;
    }

    public void setCorrespondance(String correspondance) {
        this.correspondance = correspondance;
    }

}
