/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.nf;

import java.util.List;

/**
 *
 * @author cleme
 */
public class Compte {
    
    private String identifiant;
    private String motDePasse;
    private Poste poste;
    
    public Compte (String identifiant, String motDePasse, Poste poste){
        //poste à spécifier dans les numéros d'identifiant
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.poste = poste;
    }
    
 
}
