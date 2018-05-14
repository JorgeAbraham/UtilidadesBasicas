/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Abraham
 */

public class fileStringIO 
{
	
	File nombreArchivo;



    public fileStringIO(String file) 
    {
    	nombreArchivo = new File( file );
    }
    
    public String getStringFile()
    {
    	String R="";
    	
    	try
    	{
    		BufferedReader lectura = new BufferedReader( new FileReader( nombreArchivo ) );
    		String cad=lectura.readLine();
    		while ( cad != null )
    		{
    			
    			R=R+cad+"\n";
    			cad=lectura.readLine();
    		}
    	}
    	catch (Exception ex)
    	{
            JOptionPane.showMessageDialog(null, ex.getMessage());
       	}
    	 
    	
    	
    	return R;
    }
    
    public void setStringFile(String text)
    {
    	    	
    	try
    	{
            
             
                
    		BufferedWriter bw;
                bw = new BufferedWriter(new FileWriter(nombreArchivo,false));
    		   
    		StringTokenizer tok=new StringTokenizer(text,"\n",true);
    		
    		String cad;
    		while (   tok.hasMoreTokens()  )
    		{
    			cad=tok.nextToken();
    			
    			if (cad.equals("\n"))
    			{
    				bw.newLine();
    			}
    			else
    			{
    				bw.write(cad);
    			}
    			
       		}
    		
    		
    		bw.close();
    	}
    	catch (Exception ex)
    	{
            JOptionPane.showMessageDialog(null, ex.getMessage());
       	}
    	 
    	
    }
    
    
    public void addStringFile(String text)
    {
    	    	
    	try
    	{
    		BufferedWriter bw;
                bw = new BufferedWriter(new FileWriter(nombreArchivo,true));
    		
    		StringTokenizer tok=new StringTokenizer(text,"\n",true);
    		
    		String cad;
    		while (   tok.hasMoreTokens()  )
    		{
    			cad=tok.nextToken();
    			
    			if (cad.equals("\n"))
    			{
    				bw.newLine();
    			}
    			else
    			{
    				bw.write(cad);
    			}
    			
       		}
    		
    		
    		bw.close();
    	}
    	catch (Exception ex)
    	{
            JOptionPane.showMessageDialog(null, ex.getMessage());
       	}
    	 
    	
    }
    
    public static void main (String[] args) 
    {
    	
    	fileStringIO objeto=new fileStringIO("tomala barbon.txt");
    	//objeto.setStringFile("Hola janis");
    	objeto.addStringFile("Adios Janis");
        
        //objeto.setStringFile("Siempre no");
    	javax.swing.JOptionPane.showMessageDialog(null,objeto.getStringFile());
    	
    	
    	
    	
 
    	
    }
    
     


    
}