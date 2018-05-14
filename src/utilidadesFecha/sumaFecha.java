/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesFecha;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class sumaFecha {
    
    
    
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
    
}
