/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Abraham
 */
public class utilidadValidacionNulo 
{
    private ArrayList listaJComboBoxs;
    private ArrayList listaJTextFields;
    private ArrayList listaJTextAreas;
    
    private ArrayList listaJRadio;
    private ArrayList listaJCheck;
    
    public utilidadValidacionNulo() 
    {
        listaJComboBoxs=new ArrayList();
        listaJTextFields=new ArrayList();
        listaJTextAreas=new ArrayList();
        listaJRadio=new ArrayList();
        listaJCheck=new ArrayList();
       
    }
    
    
    public void add(grupoRadioButtonExtendido radio)
    {
        listaJRadio.add(radio);
    }
    
    public void add(grupoRadioCheckButtonExtendido check)
    {
        listaJCheck.add(check);
    }
    
    
    
    public void add(JComboBox combo)
    {
        listaJComboBoxs.add(combo);
    }
    
    public void add(JTextField field)
    {
        listaJTextFields.add(field);
    }
    
    public void add(JTextArea area)
    {
        listaJTextAreas.add(area);
    }
    
    
    public boolean existenNulos()
    {
        boolean B=false;
        for (int i=0;i<listaJComboBoxs.size();i++)
        {
            if (  ((JComboBox)listaJComboBoxs.get(i)).getSelectedItem().equals("")   )
            {
                B=true;
            }
        }
        for (int i=0;i<listaJTextAreas.size();i++)
        {
            if (  ((JTextArea)listaJTextAreas.get(i)).getText().equals("")   )
            {
                B=true;
            }
        }
        for (int i=0;i<listaJTextFields.size();i++)
        {
            if (  ((JTextField)listaJTextFields.get(i)).getText().equals("")   )
            {
                B=true;
            }
        }
        
        for (int i=0;i<listaJRadio.size();i++)
        {
            if (  ((grupoRadioButtonExtendido)listaJRadio.get(i)).getValorSeleccionado().equals("")   )
            {
                B=true;
            }
        }
        
        for (int i=0;i<listaJCheck.size();i++)
        {
            if (  ((grupoRadioCheckButtonExtendido)listaJCheck.get(i)).getValorSeleccionado().equals("")   )
            {
                B=true;
            }
        }
        
        
        return B;
    }
    
    public static void main(String args[])
    {
        utilidadValidacionNulo NONULOS=new utilidadValidacionNulo();
        
        
        
    }
    
}
