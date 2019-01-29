/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesWeb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import utilidadesbasicas.archivoSerializableParaBD;

/**
 *
 * @author Abraham
 */
public class utilidadWeb {
    
    
    
    public static String printAjaxCodeAbrirUbicacion(String idElementoHTML,String accion,String parametros[]){
        
        
        String parametrosFormulario="";
        
        for (int i=0;i<parametros.length;i++){
            parametrosFormulario=parametrosFormulario+"           var "+parametros[i]+" = $('#"+parametros[i]+"').val();\n";
        }
        
        
        
        String parametrosFormulario2="";
        
        for (int i=0;i<parametros.length;i++){
            parametrosFormulario2=parametrosFormulario2+"               "+parametros[i]+": "+parametros[i]+"\n"+(i<parametros.length-1?",":"");
        }
        
        
        String codigoHTML=  "<script>\n" +
                            "   $(document).ready(function() {\n" +
                            "       $('#"+idElementoHTML+"').click(function(event) {\n" +
                            "           var "+accion+" = \""+accion+"\";\n" +
                                        parametrosFormulario+
                            "           // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get\n" +
                            "           $.post('Servlet', {\n" +
                            "               controlador : "+accion+",\n" +
                                            parametrosFormulario2 +
                            "           }, function(responseText) {\n" +
                            "               if (responseText!==\"\"){\n" +
                            "                   window.location = responseText;\n" +
                            "              }"+
                            "           });\n" +
                            "       });\n" +
                            "   });\n" +
                            "</script>";
        return codigoHTML;
            
        
    }
    
    
    
    
    public static void ajaxAbrirUbicacion( HttpServletResponse response ,String baseURL, String URL){
        
        PrintWriter salida;
        try {
            salida = response.getWriter();
            salida.print(baseURL+URL);
            salida.flush();
            salida.close();
        } catch (IOException ex) {
            Logger.getLogger(utilidadWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
            
    
     
    public static void downloadUbicacion( HttpServletResponse response, archivoSerializableParaBD archivo ){
        
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename="+archivo.getNombreArchivo()+"");
     //   response.setHeader("Cache-Control", "no-store");
     //   response.setHeader("Pragma", "no-cache");
     //   response.setDateHeader("Expires", 0);
        java.io.OutputStream os = null;
        try {
            os = response.getOutputStream();

            
            ServletOutputStream out = response.getOutputStream();
            out.write(archivo.getContenido());
           
            //  byte[] b = decodeBase64(reporte.getBytes());
            //  byte[] b = org.apache.tomcat.util.codec.binary.Base64.decodeBase64("//5mAG8AbwAgAGIAYQByAAoA");

          //  os.write(b);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(utilidadWeb.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    } 
    
            
    
    
    public static void htmlAbrirUbicacion( HttpServlet Servlet,HttpServletRequest request,HttpServletResponse response , String URL){
        
        RequestDispatcher requestDispatcher;
        requestDispatcher = Servlet.getServletContext().getRequestDispatcher(URL);  
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(utilidadWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(utilidadWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public static String printTableHTML(String columnas[],String datos[][]){
        
        String Html="<table class=\"ck\" data-sort=\"table\">";
        Html=Html+"<thead><tr>";
        
        for (int i=0;i<columnas.length;i++){
            Html=Html+"<th class=\"header\">"+columnas[i]+"</th>";
        }
        Html=Html+"</tr></thead>";
        
        
        Html=Html+"<tbody>";
        
        for (int i=0;i<datos.length;i++){
            Html=Html+"<tr>";
            
            for (int j=0;j<datos[0].length;j++){
                Html=Html+"<td>"+datos[i][j]+"</td>";
            }
            
           
            Html=Html+"</tr>";
        }
        
        Html=Html+"</tbody>" +
                "</table>";
        
        
      
        
        return Html;
        
        
    }
    
    
    
    public static void imprimeEnHtmlReporteJasper(String FileJasper,HttpServletResponse response, Connection conn){
        
        try {
           
            File reportFile = new File(FileJasper);//your report_name.jasper file
            Map parameters = new HashMap();
            
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
            
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream;
            try {
                outStream = response.getOutputStream();
                outStream.write(bytes, 0, bytes.length);
                outStream.flush();
                outStream.close();
            } catch (IOException ex) {
                Logger.getLogger(utilidadWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (JRException ex) {
            Logger.getLogger(utilidadWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void imprimeEnHtmlReporteJasper(String FileJasper,HttpServletResponse response, Connection conn, Map parameters){
        
        try {
           
            File reportFile = new File(FileJasper);//your report_name.jasper file
            
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
            
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream;
            try {
                outStream = response.getOutputStream();
                outStream.write(bytes, 0, bytes.length);
                outStream.flush();
                outStream.close();
            } catch (IOException ex) {
                Logger.getLogger(utilidadWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (JRException ex) {
            Logger.getLogger(utilidadWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /*
    
    Devuelve un arreglo bidimencional donde en el valor 0 se almacenan los archivos  y en 1 los parametros del form
    
    
    */
    public static ArrayList[] subirArchivo( HttpServlet Servlet,HttpServletRequest request,String directorio){
        
        
        ArrayList nombreDeArchivoSubido[]=new ArrayList[3];
        nombreDeArchivoSubido[0]=new ArrayList();
        nombreDeArchivoSubido[1]=new ArrayList();
        nombreDeArchivoSubido[2]=new ArrayList();
        
        File file;
        int maxFileSize = 5000 * 1024; //5 MB
        int maxMemSize = 5000 * 1024; //5 MB
        ServletContext context = Servlet.getServletContext();
        String filePath =  directorio ;   //"c:/Temp/";//context.getInitParameter("file-upload");

        // Verify the content type
        String contentType = request.getContentType();

        if (   contentType.indexOf("multipart/form-data") >= 0    ) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);

            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File(directorio));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // maximum file size to be uploaded.
            upload.setSizeMax(maxFileSize);

                // Parse the request to get file items.
            List fileItems;
            try {
                fileItems = upload.parseRequest( new ServletRequestContext(request)  );
            

                // Process the uploaded file items
                Iterator i = fileItems.iterator();
                
                                
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();

                        // Write the file
                        if (fileName.lastIndexOf("\\") >= 0) {
                            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                        } else {
                            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                        }
                        try {
                            
                            
                            if (  !  file.getAbsolutePath().replace("\\", "").equals(   directorio.replace("/", "")  )){  //esta vacia la peticion
                                 fi.write(file);
                                 nombreDeArchivoSubido[0].add(file.getAbsolutePath());
                                 nombreDeArchivoSubido[2].add(fieldName);
                            }
                            
                           
                            
                            
                        } catch (Exception ex   ) {
                            Logger.getLogger(utilidadWeb.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } //Si el Form trae variables aparte
                    else{
                        
                        
                        String parametro[]=new String[2];
                        parametro[0]=fi.getFieldName();
                        parametro[1]=fi.getString();
                        
                        nombreDeArchivoSubido[1].add(parametro);
                        
                    }
                }
            } catch (FileUploadException ex) {
                
                ex.printStackTrace();
                
                Logger.getLogger(utilidadWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            
            System.out.println("No hay archivos en la peticion");
             Logger.getLogger("No hay archivos en la peticion");
        }
            
        
        return nombreDeArchivoSubido;
        
        
    }
          
    
    
    
    public static String valorDeVariablaDePeticionMultipart(ArrayList peticion[],String nombrevariable){
        
        String valor=null;
        
        for (int i=0;i<peticion[1].size();i++){
            
            String valorFormulario[]=(String[])peticion[1].get(i);
            if (valorFormulario[0].equals(nombrevariable)   ){
                valor=valorFormulario[1];
            }
            
        }
        
        return valor;
        
        
    }
    
    
    public static ArrayList<String> ArrayDeVariablaDePeticionMultipart(ArrayList peticion[],String nombrevariable){
        
        ArrayList<String> valor=new ArrayList();

        for (int i=0;i<peticion[1].size();i++){
            
            String valorFormulario[]=(String[])peticion[1].get(i);
            if (valorFormulario[0].equals(nombrevariable+"[]")   ){
                valor.add(valorFormulario[1]);
            }
            
        }
        
        return valor;
        
        
    }
    
    
    
      
    public static ArrayList<String> listaDeArchivosSubidosPeticionMultipart(ArrayList peticion[]){
        return peticion[0];
    }
            
    public static ArrayList<String> listaDeParametrosDeArchivosSubidosPeticionMultipart(ArrayList peticion[]){
        return peticion[2];
    }
   
        
            
        
    
    
}
