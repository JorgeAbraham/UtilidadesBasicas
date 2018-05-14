/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abraham
 */
public class archivoSerializableParaBD  {
    
    File file;
    FileInputStream fis;
    FileOutputStream fos;
    
    byte contenido[];
    String nombreArchivo;
     
     
    public archivoSerializableParaBD() {
      
        
    }
    
    
    public archivoSerializableParaBD(File file) {
        this.file = file;
        this.nombreArchivo = file.getName();
        try {
        
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
             Logger.getLogger(archivoSerializableParaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public archivoSerializableParaBD(String nombreArchivo) {
        this.file = new File(nombreArchivo);
        this.nombreArchivo = file.getName();
        
        try {
        
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
             Logger.getLogger(archivoSerializableParaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }

    public FileOutputStream getFos() {
        return fos;
    }

    public void setFos(FileOutputStream fos) {
        this.fos = fos;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

  
     
    
    
     
    
    
}
