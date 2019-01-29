/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Soxtec Desarrollo
 */
public class archivoProperties {
    
    public String nombreArchivo;
    
    Properties prop = new Properties();
    InputStream input = null;

    public archivoProperties(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        
        try {

            input = new FileInputStream(nombreArchivo);

            // load a properties file
            prop.load(input);

            

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        
        
    }
    
    
    
    public String getProperties(String variable){
        return  prop.getProperty(variable);
    }
    
    
    
}
