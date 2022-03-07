package iHealth.nf;

/**
 *
 * @author cleme
 */
public class Chambre {
    private Service serviceGeographique;
    private Service serviceResponsable;
    private String numeroChambre;
    
    public Chambre(Service serviceGeographique,Service serviceResponsable, String numeroChambre){
        this.serviceResponsable = serviceResponsable;
        this.serviceGeographique = serviceGeographique;
        this.numeroChambre = numeroChambre;
    }
    
    public Service getServiceResponsable(){
        return serviceResponsable;
    }
    public Service getServiceGeographique(){
        return serviceGeographique;
    }
    public String getNumeroChambre(){
        return numeroChambre;
    }
}
