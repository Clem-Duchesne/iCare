package iHealth.nf;

/**
 *
 * @author cleme
 */
import java.util.ArrayList;
import java.util.List;

public class Document {

    private java.sql.Date date_ajout;
    private String numS;
    private String numP;
    private String IPP;
    private String idDoc;
    private DocumentType type;
    private String titre;
    private String description;

    /**
     * Constructor de la classe Document.
     * @param dateEcriture
     * @param numS
     * @param numP
     * @param IPP
     * @param type
     * @param titre
     * @param description 
     */
    public Document(java.sql.Date dateEcriture, String numS, String numP, String IPP,  DocumentType type, String titre, String description) {
        this.date_ajout = dateEcriture;
        this.numS = numS;
        this.numP = numP;
        this.IPP = IPP;
        int min = 0;
        int max = 99999;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        String random_string = String.valueOf(random_int); 
        this.idDoc = random_string;
        this.type = type;
        this.titre = titre;
        this.description = description;
    }
    public Document(String idDoc, java.sql.Date dateEcriture, String numS, String numP, String IPP,  DocumentType type, String titre, String description) {
        this.date_ajout = dateEcriture;
        this.numS = numS;
        this.numP = numP;
        this.IPP = IPP;
        this.idDoc = idDoc;
        this.type = type;
        this.titre = titre;
        this.description = description;
    }
    
    /**
     * Renvoie la date de ecriture du document.
     * @return 
     */
    public java.sql.Date getDateEcriture() {
        return date_ajout;
    }

    /**
     * Renvoie le numero de sejour du document actuel.
     * @return 
     */
    public String getNumS() {
        return numS;
    }

    /**
     * Renvoie le numero du profissionel.
     * @return 
     */
    public String getNumP() {
        return numP;
    }

    /**
     * Renvoie le identifiant du patient.
     * @return 
     */
    public String getIPP() {
        return IPP;
    }

    /**
     * Renvoie l'identifiant du document.
     * @return 
     */
    public String getIdDoc() {
        return idDoc;
    }

    /**
     * Renvoie le type du document en format DocumentType.
     * @return 
     */
    public DocumentType getType() {
        return type;
    }

    /**
     * Renvoie le titre du document.
     * @return 
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Renvoie la description du document.
     * @return 
     */
    public String getDescription() {
        return description;
    }

    
    

}
