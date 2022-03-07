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
public class toSexe {
    
    public toSexe(){
        
    }
    
    public Sexe stringToSexe(String sexe){
        Sexe sexe_S = null;
            
        switch(sexe){
            case "Masculin":
                sexe_S = Sexe.HOMME;
                break;
            
            case "Feminin":
                sexe_S = Sexe.FEMME;
                break;
                
            case "Autre":
                sexe_S = Sexe.AUTRE;
                break;

        }
        
        return sexe_S;
    }
    
    public String sexeToString(Sexe sexe){
        String sexe_S = null;
            
        switch(sexe){
            case HOMME:
                sexe_S = "Masculin";
                break;
            
            case FEMME:
                sexe_S = "Feminin";
                break;
                
            case AUTRE:
                sexe_S = "Autre";
                break;
                
            default:
                sexe_S = "Autre";
                break;
        }
        
        return sexe_S;
    }
    
}
