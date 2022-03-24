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
public class toDocument {
     public DocumentType toDocument(String type){
        DocumentType type_doc = null;
            
        switch(type){
            case "PRESCRIPTION":
                type_doc = DocumentType.PRESCRIPTION;
                break;
            
            case "OBSERVATION":
                type_doc = DocumentType.OBSERVATION;
                break;
                
            case "COMMENTAIRE":
                type_doc = DocumentType.COMMENTAIRE;
                break;
            case "EXAMEN_ANESTHESISTE":
                type_doc = DocumentType.EXAMEN_ANESTHESISTE;
                break;
            case "EXAMEN_MEDICO_TECHNIQUE":
                type_doc = DocumentType.EXAMEN_MEDICO_TECHNIQUE;
                break;
            case "LETTRES":
                type_doc = DocumentType.LETTRES;
                break;
            case "OPERATION":
                type_doc = DocumentType.OPERATION;
                break;
            case "RESULTAT":
                type_doc = DocumentType.RESULTAT;
                break;
        }
        
        return type_doc;
    }
     
     /**
      * Renvoie l'enum en format de type_doc en string
      * @param type
      * @return 
      */
     public String DocToString(DocumentType type){
        String type_doc = null;
            
        switch(type){
            case PRESCRIPTION:
                type_doc = "Prescription";
                break;
            
            case OBSERVATION:
                type_doc = "Observation";
                break;
                
            case COMMENTAIRE:
                type_doc = "Commentaire";
                break;
            case EXAMEN_ANESTHESISTE:
                type_doc = "Examen anesthésiste";
                break;
            case EXAMEN_MEDICO_TECHNIQUE:
                type_doc = "Examen médicotechnique";
                break;
            case LETTRES:
                type_doc = "Lettre de sortie";
                break;
            case OPERATION:
                type_doc = "Opération";
                break;
            case RESULTAT:
                type_doc = "Résultat";
                break;
        }
        
        return type_doc;
     }
     
     /**
      * Renvoie l'enum en format de string en majuscule
      * @param type
      * @return 
      */
     public String DocToStringUpperCase(DocumentType type){
        String type_doc = null;
            
        switch(type){
            case PRESCRIPTION:
                type_doc = "PRESCRIPTION";
                break;
            
            case OBSERVATION:
                type_doc = "OBSERVATION";
                break;
                
            case COMMENTAIRE:
                type_doc = "COMMENTAIRE";
                break;
            case EXAMEN_ANESTHESISTE:
                type_doc = "EXAMEN_ANESTHESISTE";
                break;
            case EXAMEN_MEDICO_TECHNIQUE:
                type_doc = "EXAMEN_MEDICO_TECHNIQUE";
                break;
            case LETTRES:
                type_doc = "LETTRES";
                break;
            case OPERATION:
                type_doc = "OPERATION";
                break;
            case RESULTAT:
                type_doc = "RESULTAT";
                break;
        }
        
        return type_doc;
     }
     
     
}
