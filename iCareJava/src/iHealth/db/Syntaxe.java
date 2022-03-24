/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.db;

/**
 *
 * @author cleme
 */
public class Syntaxe {
    public String gestionApostrophe(String string) {
        String[] tmp = string.split("'");
        String finalString = tmp[0];
        if(tmp.length > 1) {
            for (int i = 1 ; i < tmp.length ; i++) {
                finalString += "''" + tmp[i];
            }
        }
        return finalString;
    }
}
