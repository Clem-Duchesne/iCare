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
    
    
    public PH(String numP, String nom, String prenom, Service service){
        this.numP = numP;
        this.nom = nom;
        this.prenom = prenom;
        this.service = service;
    }
    
    public String getNom(){
        return nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public Service getService(){
        return service;
    }
    
    public String getNumP(){
        return numP;
    }
    

}
