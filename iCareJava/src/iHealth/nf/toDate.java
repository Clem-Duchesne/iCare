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
import java.time.ZoneId;
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
    
    public java.sql.Date stringToDate(String date) throws ParseException{
        
        java.sql.Date date_sql_format = null;
        String date_j = date.substring(0, 2);
        String date_m = date.substring(3, 5);
        String date_a = date.substring(6, 10);
        
        int date_j_int = Integer.parseInt(date_j);
        int date_m_int = Integer.parseInt(date_m);
        int date_a_int = Integer.parseInt(date_a);
        
       // System.out.print(date_j_int + " " + date_m_int + " " + date_a_int);
        
        if((date_j_int > 0 & date_j_int <= 31) & (date_m_int>0 & date_m_int<13) & (date_a_int>1900 & date_a_int < 2023)){
            
            String date_java = date_a + date_m  + date_j;
            
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            java.util.Date parsed = format.parse(date_java);
            System.out.println("CE QU'on VEUT VOIR : " + date_java);
            date_sql_format = new java.sql.Date(parsed.getTime());
            
            System.out.println("CE QU'on VEUT VOIR : " + date_sql_format);
        }
        
        
        return date_sql_format;
    }
    
    public LocalDate sqlDateToLocalDate(java.sql.Date date){
        LocalDate localDate = java.sql.Date.valueOf(""+date+"").toLocalDate();
        
        return localDate;
    }
    
     public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
        }
   
}
