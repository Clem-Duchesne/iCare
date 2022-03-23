/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.nf;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author cleme
 */
public class Authentification {
    
    /**
     * Ça fonction permet de differencier un utilisateur entre Secretaire Administrative
     * Secretaire Medical et Medicin.
     * @param identifiant
     * @return 
     */
     public Poste definirPoste(String identifiant){
         
        Poste poste = null;
        String posteStr = identifiant.substring(0,2);

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
                    default:
                        poste = null;
                        break;
                }
        return poste;
    }
     
     /**
      * Encryptage du mot de passe d'un utilisateur.
      * @param password
      * @param key
      * @return 
      */
    public String encrypt(String password,String key){
        try{
            Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE,clef);
            return new String(cipher.doFinal(password.getBytes()));
        }
        catch (Exception e){
            return null;
        }
    }
    
    /**
     * Decryptage du mot de passe d'un utilisateur.
     * @param password
     * @param key
     * @return 
     */
    public String decrypt(String password,String key){
        try
        {
            Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE,clef);
            return new String(cipher.doFinal(password.getBytes()));
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
}

    
}
