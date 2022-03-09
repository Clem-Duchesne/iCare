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
    
    
    public Consultation(NumeroSejour numeroSejour, String IPP, java.sql.Date dateDebutSejour, String numP, String naturePrestation, Lit lit){
        
        this.dateDebutSejour = dateDebutSejour;
        this.naturePrestation = naturePrestation;
        this.IPP = IPP;
        this.numP = numP;
        this.numeroSejour = numeroSejour;
        this.lit = lit;
        
    }
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
    public NumeroSejour getNumeroSejour(){
        return numeroSejour;
    }
    public String getNumP(){
        return numP;
    }
    public java.sql.Date getDateDebutSejour(){
        return dateDebutSejour;
    }
    public java.sql.Date getDateFinSejour(){
        return dateFinSejour;
    }
    public String getNaturePrestation(){
        return naturePrestation;
    }
    public Lit getLit(){
        return lit;
    }
    public LettreDeSortie getLettre(){
        return lettreDeSortie;
    }
    public void setLettreDeSortie(LettreDeSortie lettreDeSortie){
        this.lettreDeSortie = lettreDeSortie;
    }
    public String getIPP(){
        return IPP;
    }
    
    

}
