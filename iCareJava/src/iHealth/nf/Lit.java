package iHealth.nf;

/**
 *
 * @author cleme
 */
public class Lit {
    private Localisation localisation;
    private Chambre chambre;
    
    /**
     * Constructor de la classe Lit.
     * @param localisation
     * @param chambre 
     */
    public Lit(Localisation localisation, Chambre chambre){
        this.localisation = localisation;
        this.chambre = chambre;
    }
    
    /**
     * Renvoie la chambre du lit.
     * @return 
     */
    public Chambre getChambre(){
        return chambre;
    }
    
    /**
     * Renvoie la localisation du lit.
     * @return 
     */
    public Localisation getLoc(){
        return localisation;
    }
}
