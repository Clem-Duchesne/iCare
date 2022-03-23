package iHealth.nf;

import java.util.Calendar;

/**
 *
 * @author cleme
 */
public class LettreDeSortie {
    private java.sql.Date dateSortie;
    private String lettre;
    
    public LettreDeSortie(String lettre){
        this.dateSortie = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        this.lettre = lettre;
    }
    public LettreDeSortie(java.sql.Date dateSortie, String lettre){
        this.dateSortie = dateSortie;
        this.lettre = lettre;
    }
    public java.sql.Date getDate(){
        return dateSortie;
    }
    
    public String getLettre_text(){
        return lettre;
    }
    
}
