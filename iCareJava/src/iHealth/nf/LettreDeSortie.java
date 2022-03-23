package iHealth.nf;

import java.util.Calendar;

/**
 *
 * @author cleme
 */
public class LettreDeSortie {
    private java.sql.Date dateSortie;
    private String lettre;
    
    /**
     * Define la Lettre de Sortie et sa date de sortie.
     * @param lettre 
     */
    public LettreDeSortie(String lettre){
        this.dateSortie = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        this.lettre = lettre;
    } 
    
    /**
     * Define la Lettre de Sortie et sa date de sortie.
     * @param lettre 
     */
    public LettreDeSortie(java.sql.Date dateSortie, String lettre){
        this.dateSortie = dateSortie;
        this.lettre = lettre;
    }
     
    /**
     * Renvoie la lettre.
     * @param lettre 
     */
    public String getLettre_text(){
        return lettre;
    }
     
    /**
     * Renvoie la date sorte de la lettre.
     * @param lettre 
     */
    public java.sql.Date getDate(){
        return dateSortie;
    }
}
