package iHealth.nf;

/**
 *
 * @author cleme
 */
public class PH {
    private String typePH;
    private Patients patients;
    private String numP;
    private String nom;
    private String prenom;
    private Service service;
    
    /**
     * Constructor de la class PH - Praticien Hospitalier.
     * @param numP
     * @param nom
     * @param prenom
     * @param service 
     */
    public PH(String numP, String nom, String prenom, Service service){
        this.numP = numP;
        this.nom = nom;
        this.prenom = prenom;
        this.service = service;
    }
    
    /**
     * Renvoie le nom du Praticien
     * @return 
     */
    public String getNom(){
        return nom;
    }
    
    /**
     * Renvoie le prénom du Praticien
     * @return 
     */
    public String getPrenom(){
        return prenom;
    }
    
    /**
     * Renvoie le service du Praticien
     * @return 
     */
    public Service getService(){
        return service;
    }
    
    /**
     * Renvoie l'identifiant du Praticien
     * @return 
     */
    public String getNumP(){
        return numP;
    }
    

}
