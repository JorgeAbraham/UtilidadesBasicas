/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesFecha;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class sumaFecha {
    
    
    public static final int GUION=0;
    public static final int DIAGONAL=1;
    
    
    
    
     public static Date sumarDiasAFecha(Date fecha, int dias){
        if (dias==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); 
        calendar.add(Calendar.DAY_OF_YEAR, dias);  
        return calendar.getTime(); 
    }
    
    public static Date sumarMesAFecha(Date fecha, int mes){
        if (mes==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); 
        calendar.add(Calendar.MONTH, mes);  
        return calendar.getTime(); 
    }
    
    public static Date sumarSemanaAFecha(Date fecha, int semana){
        if (semana==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); 
        calendar.add(Calendar.WEEK_OF_YEAR, semana);  
        return calendar.getTime(); 
    }
    
    public static Date sumarAnioAFecha(Date fecha, int anio){
        if (anio==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); 
        calendar.add(Calendar.YEAR, anio);  
        return calendar.getTime(); 
    }
    
    public int diferenciaSemanas(Date fechaInicial, Date fechaFinal) {
        int diferenciaSemana;
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(fechaInicial); // Configuramos la fecha que se recibe
        int SemanaInicial = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(fechaFinal); // Configuramos la fecha que se recibe
        int SemanaFinal = calendar.get(Calendar.WEEK_OF_YEAR);

        int diferencia = SemanaFinal - SemanaInicial;

        if (diferencia >= 0) {
            diferenciaSemana = diferencia;
        } else {

            diferenciaSemana = (SemanaFinal + 53) - SemanaInicial;

        }

        return diferenciaSemana;
    }
    
    
    public static int numeroDiasEntreDosFechas(Date fecha1, Date fecha2){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        
        String strDate1 = sdf.format(fecha1);
        String strDate2 = sdf.format(fecha2);
 
        long diffDays=0;
        
        try {
            Date fechaParse1=new SimpleDateFormat("dd/MM/yyyy").parse(strDate1);
            Date fechaParse2=new SimpleDateFormat("dd/MM/yyyy").parse(strDate2);
            long startTime = fechaParse1.getTime();
            long endTime = fechaParse2.getTime();
            long diffTime = endTime - startTime;
            diffDays = diffTime / (1000 * 60 * 60 * 24);
        
        } catch (ParseException ex) {
            Logger.getLogger(sumaFecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
       
        
        
     
        return (int)diffDays;
    }
    
    
     public static String date_dd_mm_aaaa(Date fecha1){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha1);
       
    }
    
    public static String date_aaaa_mm_dd(Date fecha1){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(fecha1);
       
    }
    
    
    public static Date StringTOdate_aaaa_mm_dd(String StringDate,int simbolo){
        
        Date date1=null;  
         try {
             
            if (StringDate!=null && !StringDate.equals("")){
                if (simbolo==GUION){
                   date1 = new SimpleDateFormat("yyyy-MM-dd").parse(StringDate);
                }
                if (simbolo==DIAGONAL){
                   date1 = new SimpleDateFormat("yyyy/MM/dd").parse(StringDate);
                }
            }
             
             
         } catch (ParseException ex) {
             Logger.getLogger(sumaFecha.class.getName()).log(Level.SEVERE, null, ex);
         }
        return date1;
    }
    
    
}
