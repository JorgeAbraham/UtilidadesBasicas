/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author Abraham
 */
public class filtroCuadrosTexto 
{
    public static void SoloNumeros(KeyEvent evt)
    {
         JTextField F=(JTextField)evt.getSource();
        if ( (evt.getKeyCode()<48 ||  evt.getKeyCode()>57) &&  evt.getKeyCode()!=8  &&   evt.getKeyCode()!=127 &&   evt.getKeyCode()!=10  && evt.getKeyCode()!=37 && evt.getKeyCode()!=38 && evt.getKeyCode()!=39 && evt.getKeyCode()!=40 )
        {
          F.setText(F.getText().substring(0,F.getText().length()-1));
        }
    }
    
    public static void SoloLetras(KeyEvent evt)
    {
         JTextField F=(JTextField)evt.getSource();
        if ( (evt.getKeyCode()<65 ||  evt.getKeyCode()>112) &&  evt.getKeyCode()!=8  &&   evt.getKeyCode()!=127 &&   evt.getKeyCode()!=10  && evt.getKeyCode()!=37 && evt.getKeyCode()!=38 && evt.getKeyCode()!=39 && evt.getKeyCode()!=40 )
        {
          F.setText(F.getText().substring(0,F.getText().length()-1));
        }
    }
    
    
}
