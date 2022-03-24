/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.nf;

/**
 *
 * @author cleme
 */
public class toLocalisation {
    public toLocalisation(){
        
    }
    
    /**
     * Renvoie la localisation enum en format string.
     * @param loc
     * @return 
     */
    public String LocalisationtoString(Localisation loc){
        String loc_s = null;
            
        switch(loc){
            case FENETRE:
                loc_s = "F";
                break;
            
            case PORTE:
                loc_s = "P";
                break;
        }
        
        return loc_s;
    }
    
    /**
     * Renvoie la string en format enum
     * @param loc
     * @return 
     */
    public Localisation StringToLocalisation(String loc){
        Localisation loc_s = null;
            
        switch(loc){
            case "F":
                loc_s = Localisation.FENETRE;
                break;
            
            case "P":
                loc_s = Localisation.PORTE;
                break;
        }
        
        return loc_s;
    }
    
}
