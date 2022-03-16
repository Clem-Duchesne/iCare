package iHealth.nf;

/**
 *
 * @author cleme
 */
import java.util.ArrayList;
import java.util.List;

public class Document {
    private String dateEcriture;
    public java.sql.Date date_ajout;
    private String numS;
    private String numP;
    private String IPP;
    private String idDoc;
    private DocumentType type;
    private String titre;
    private String description;

    public Document(String dateEcriture, String numS, String numP, String IPP,  DocumentType type, String titre, String description) {
        this.dateEcriture = dateEcriture;
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
    

    public String getDateEcriture() {
        return dateEcriture;
    }

    public String getNumS() {
        return numS;
    }

    public String getNumP() {
        return numP;
    }

    public String getIPP() {
        return IPP;
    }

    public String getIdDoc() {
        return idDoc;
    }

    public DocumentType getType() {
        return type;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    
    

}
