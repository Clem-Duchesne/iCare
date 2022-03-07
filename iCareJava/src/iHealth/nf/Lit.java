package iHealth.nf;

/**
 *
 * @author cleme
 */
public class Lit {
    private Localisation localisation;
    private Chambre chambre;
    
    public Lit(Localisation localisation, Chambre chambre){
        this.localisation = localisation;
        this.chambre = chambre;
    }
    
    public Chambre getChambre(){
        return chambre;
    }
    public Localisation getLoc(){
        return localisation;
    }
}
