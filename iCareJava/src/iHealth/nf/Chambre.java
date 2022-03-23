package iHealth.nf;

/**
 *
 * @author cleme
 */
public class Chambre {
    private Service serviceGeographique;
    private Service serviceResponsable;
    private String numeroChambre;
    
    /**
     * Constructor de la classe Chambre.
     * @param serviceGeographique
     * @param serviceResponsable
     * @param numeroChambre 
     */
    public Chambre(Service serviceGeographique,Service serviceResponsable, String numeroChambre){
        this.serviceResponsable = serviceResponsable;
        this.serviceGeographique = serviceGeographique;
        this.numeroChambre = numeroChambre;
    }
    
    /**
     * Renvoie l'information d'un service responsable d'un Chambre.
     * @return 
     */
    public Service getServiceResponsable(){
        return serviceResponsable;
    }
    
    /**
     * Renvoie l'information d'un service geographique d'un Chambre.
     * @return 
     */
  
    public Service getServiceGeographique(){
        return serviceGeographique;
    }
    /**
     * Renvoie l'information d'un número d'un Chambre.
     * @return 
     */
    public String getNumeroChambre(){
        return numeroChambre;
    }
}
