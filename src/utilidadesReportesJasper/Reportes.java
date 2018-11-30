/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesReportesJasper;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Soxtec Desarrollo
 */
public class Reportes {
    
    Map parametros;

    public Reportes() {
       
        this.parametros = new HashMap();
    }
    
    
    public void addParametro(String nombreParametro, String valor){
        parametros.put( nombreParametro,   valor    );
    }
    
    
    public void verReporte(String archivoJasper,Connection Conexion){
        File F=new File(archivoJasper);
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(F);
            JasperPrint jasperPrint = JasperFillManager.fillReport(  reporte, parametros, Conexion);
            JasperViewer vista = new JasperViewer(jasperPrint, false);
            vista.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void verReporte(String archivoJasper){
        File F=new File(archivoJasper);
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObject(F);
            JasperPrint jasperPrint = JasperFillManager.fillReport(  reporte, parametros, new JREmptyDataSource()   );
            JasperViewer vista = new JasperViewer(jasperPrint, false);
            vista.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void creaArchivoPDF(String archivoJasper, String archivoSalidaJasper){
        
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(archivoJasper);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,new JREmptyDataSource());
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(archivoSalidaJasper));
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 
        
        
    }
    
    
     public void creaArchivoPDF(String archivoJasper, String archivoSalidaJasper,Connection Conexion){
        
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(archivoJasper);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros,Conexion);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(archivoSalidaJasper));
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
