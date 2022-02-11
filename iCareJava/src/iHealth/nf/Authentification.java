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
public class Authentification {
    
     public Poste definirPoste(String identifiant){
        Poste poste = null;
        String posteStr = identifiant.substring(0,2);
        System.out.print(posteStr);

                switch(posteStr){
                    case "00": 
                        poste = Poste.SECRETAIREA;
                        break;

                    case "01":
                        poste = Poste.SECRETAIREM;
                        break;

                    case "02":
                        poste = Poste.MEDECIN;
                        break;

                    case "03":
                        poste = Poste.KINESITHERAPEUTE;
                        break;
                    case "04":
                        poste = Poste.ANESTHESISTE;
                        break;
                    case "05":
                        poste = Poste.RADIOLOGUE;
                        break;
                    case "06":
                        poste = Poste.INFIRMIERE;
                        break;
                    default:
                        poste = null;
                        break;
                }
        return poste;
    }

    
}
