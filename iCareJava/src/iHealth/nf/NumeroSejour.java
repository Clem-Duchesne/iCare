package iHealth.nf;

/**
 *
 * @author cleme
 */
public class NumeroSejour {
    private String annee;
    private String mois;
    private String randomValue;
    private String numero;
    
    public NumeroSejour(java.sql.Date current_date){
        this.annee = current_date.toString().substring(2,4);
        this.mois = current_date.toString().substring(5,7);
        int min = 0;
        int max = 99999;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        String random_string = String.valueOf(random_int); 

      switch(random_string.length()){
          case 1:
              random_string = random_string + "0000";
          break;
          case 2:
              random_string = random_string + "000";
          break;
          case 3:
              random_string = random_string + "00";
          break;
          case 4:
              random_string = random_string + "0";
          break;
          default:
          break;
      }
      randomValue = random_string;
      numero = annee + mois + randomValue;
 
    }
    public NumeroSejour(String numero_s){
        this.numero = numero_s;
    }
    
    public String getNumero(){
        return numero;
    }

}
