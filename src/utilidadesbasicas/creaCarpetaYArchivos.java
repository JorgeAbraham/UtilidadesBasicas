/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Abraham
 */
public class creaCarpetaYArchivos 
{
  
    
    public static void creaArchivo(String URL)
    {
        File archivo = new File(URL);
        try {
            archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(creaCarpetaYArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void creaDirectorio(String URL)
    {
        File folder = new File(URL);
        if (!folder.exists())
        {
            folder.mkdirs();
        }
    }
    
    public static void main(String adfjsk[])
    {
        JFileChooser JF=new JFileChooser();
        JF.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        JF.showOpenDialog(null);
        
        String Carpeta=JF.getSelectedFile().getPath();
        
        
        System.out.println(Carpeta);
        creaCarpetaYArchivos.creaArchivo(Carpeta+"\\archivo.abr");
        
     
        
        
    }
   
}
