package iHealth.nf;

/**
 *
 * @author cleme
 */
public class Consultation {

    private java.sql.Date dateDebutSejour;
    private java.sql.Date dateFinSejour;
    private String IPP;
    private String numP;
    private String naturePrestation;
    private LettreDeSortie lettreDeSortie;
    private NumeroSejour numeroSejour;
    private Lit lit;
    
    /**
     * Constructor simple de la classe Consultation.
     * @param numeroSejour
     * @param IPP
     * @param dateDebutSejour
     * @param numP
     * @param naturePrestation
     * @param lit 
     */
    public Consultation(NumeroSejour numeroSejour, String IPP, java.sql.Date dateDebutSejour, String numP, String naturePrestation, Lit lit){
        
        this.dateDebutSejour = dateDebutSejour;
        this.naturePrestation = naturePrestation;
        this.IPP = IPP;
        this.numP = numP;
        this.numeroSejour = numeroSejour;
        this.lit = lit;
        
    }
    
    /**
     * Constructor complet de la classe Consultation.
     * @param numeroSejour
     * @param IPP
     * @param dateEntree
     * @param dateSortie
     * @param numP
     * @param nature
     * @param lit
     * @param lettreSortie 
     */
    public Consultation(NumeroSejour numeroSejour, String IPP, java.sql.Date dateEntree, java.sql.Date dateSortie, String numP, String nature, Lit lit,String lettreSortie){
        this.numeroSejour = numeroSejour;
        this.IPP = IPP;
        this.dateDebutSejour = dateEntree;
        this.dateFinSejour = dateSortie;
        this.numP =numP;
        this.naturePrestation = nature;
        this.lit = lit;
        this.lettreDeSortie = new LettreDeSortie(dateSortie,lettreSortie);
 
    }
    
    /**
     * Renvoie le numero de sejour d'une Consultation.
     * @return 
     */
    public NumeroSejour getNumeroSejour(){
        return numeroSejour;
    }
    
     /**
     * Renvoie le numero du professionel d'une Consultation.
     * @return 
     */
    public String getNumP(){
        return numP;
    }
        
     /**
     * Renvoie la date debut de sejour d'une Consultation.
     * @return 
     */
    public java.sql.Date getDateDebutSejour(){
        return dateDebutSejour;
    }
        
     /**
     * Renvoie la date fin de sejour d'une Consultation.
     * @return 
     */
    public java.sql.Date getDateFinSejour(){
        return dateFinSejour;
    }
        
     /**
     * Renvoie la prestation nature d'une Consultation.
     * @return 
     */
    public String getNaturePrestation(){
        return naturePrestation;
    }
        
     /**
     * Renvoie la classe lit d'une Consultation.
     * @return 
     */
    public Lit getLit(){
        return lit;
    }
      
     /**
     * Renvoie la classe lettre de sortie d'une Consultation.
     * @return 
     */
    public LettreDeSortie getLettre(){
        return lettreDeSortie;
    }
        
     /**
     * Define la classe lettre de sortie d'une Consultation.
     * @return 
     */
    public void setLettreDeSortie(LettreDeSortie lettreDeSortie){
        this.lettreDeSortie = lettreDeSortie;
    }
        
     /**
     * Renvoie le identifient du patient d'une Consultation.
     * @return 
     */
    public String getIPP(){
        return IPP;
    }
        
     /**
     * Define la date fin de sejour d'une Consultation.
     * @return 
     */
    public void setDateS(java.sql.Date date){
        this.dateFinSejour = date;
    }
    
    

}
