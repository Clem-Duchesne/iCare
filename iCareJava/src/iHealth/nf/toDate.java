/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iHealth.nf;

import iHealth.ui.Creation_DMA;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cleme
 */
public class toDate {
    public toDate(){
    }
    
    public LocalDate stringToDate(String date){
        Date date_sql = null;
        String date_j = date.substring(0, 2);
        String date_m = date.substring(3, 5);
        String date_a = date.substring(6, 10);
        String date_java = date_j + "/" + date_m + "/" + date_a;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //date_sql = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH).parse(date_java);
        LocalDate date_sql_format = LocalDate.parse(date_java, formatter);
        return date_sql_format;
    }
}
