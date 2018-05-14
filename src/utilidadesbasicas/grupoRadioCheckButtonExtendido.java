/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;


public class grupoRadioCheckButtonExtendido
{
    
    private ButtonGroup grupo; 
    private ArrayList botonesPila;
    private ArrayList valoresBotonesPila;
    
    
    public grupoRadioCheckButtonExtendido()
    {
        grupo=new ButtonGroup();
        botonesPila=new ArrayList();
        valoresBotonesPila=new ArrayList();
    }

    //Mete un objeto a la pila
    public void add(JCheckBox BotonCheck, String Valor)
    {
        grupo.add(BotonCheck);
        botonesPila.add(BotonCheck);
        valoresBotonesPila.add(Valor);
    }

   

    //Obtiene el objeto de hasta arriba de la pila (sin quitarlo)
    public String getEtiquetaSeleccionado()
    {
        int numeroDeObjetos=botonesPila.size();
        String valorDeRadio="";
        
        
        for (int i=0;i<numeroDeObjetos;i++)
        {
            if  (   (   (JCheckBox)(botonesPila.get(i))   ).isSelected()   )
            {
                valorDeRadio=  (   (JCheckBox)(botonesPila.get(i))   ).getText();
            }
        }
        
        return valorDeRadio;

    }

    
    public String getValorSeleccionado()
    {
        int numeroDeObjetos=botonesPila.size();
        String valorDeRadio="";
        
        
        for (int i=0;i<numeroDeObjetos;i++)
        {
            if  (   (   (JCheckBox)(botonesPila.get(i))   ).isSelected()   )
            {
                valorDeRadio=  (String)(  valoresBotonesPila.get(i) ) ;
            }
        }
        
        return valorDeRadio;

    }
    
    public String activaValor(String valor)
    {
        int numeroDeObjetos=botonesPila.size();
        String valorDeRadio="";
        
        
        for (int i=0;i<numeroDeObjetos;i++)
        {
            if  (   (   (String)(valoresBotonesPila.get(i))   ).equals(valor)   )
            {
                ((JCheckBox)(  botonesPila.get(i) )).setSelected(true) ;
            }
            else
            {
                ((JCheckBox)(  botonesPila.get(i) )).setSelected(false) ;
            }
        }
        
        return valorDeRadio;

    }
    
    public String activaEtiqueta(String etiqueta)
    {
        int numeroDeObjetos=botonesPila.size();
        String valorDeRadio="";
        
        
        for (int i=0;i<numeroDeObjetos;i++)
        {
            if  (   (   (JCheckBox)(botonesPila.get(i))   ).getText().equals(etiqueta)   )
            {
                ((JCheckBox)(  botonesPila.get(i) )).setSelected(true) ;
            }
            else
            {
                ((JCheckBox)(  botonesPila.get(i) )).setSelected(false) ;
            }
        }
        
        return valorDeRadio;

    }
    
    
    public void clearSelection()
    {
        grupo.clearSelection();
        
    }
    
    
    
    
    
    public static void main(String args[])
    {
        
                
        
        final grupoRadioCheckButtonExtendido GrupoE=new grupoRadioCheckButtonExtendido();
        //****************************
        JCheckBox r1=new JCheckBox( "Boton 1" ); 
        JCheckBox r2=new JCheckBox( "Boton 2" ); 
        JCheckBox r3=new JCheckBox( "Boton 3" ); 
        JCheckBox r4=new JCheckBox( "Boton 4" );
        
        
        GrupoE.add(r1,"102");
        GrupoE.add(r2,"2234");
        GrupoE.add(r3,"ab");
        GrupoE.add(r4,"yeah!");
        //****************************
        
        GrupoE.activaEtiqueta("Boton 3");
        
        JButton jb=new JButton("Etiqueta");
        JButton jb2=new JButton("Valor");
        JButton jb3=new JButton("Clear");
    
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //*********************
                javax.swing.JOptionPane.showMessageDialog(null, GrupoE.getEtiquetaSeleccionado() );
                //*********************
            } 
        });
        
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //*********************
                javax.swing.JOptionPane.showMessageDialog(null, GrupoE.getValorSeleccionado() );
                //*********************
                
            } 
        });
        
         jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrupoE.clearSelection() ;
            } 
        });
        
        JFrame  JF=new JFrame();
        JF.setLayout(null);
        
        
        
        
       
        
        
        
        
        r1.setBounds(0, 0, 100, 20);
        r2.setBounds(0, 30, 100, 20);
        r3.setBounds(0, 60, 100, 20);
        r4.setBounds(0, 90, 100, 20);
        
        JF.add(r1);
        JF.add(r2);
        JF.add(r3);
        JF.add(r4);
        JF.add(jb);
        JF.add(jb2);
        JF.add(jb3);
        
        jb.setBounds(0, 120, 100, 20);
        jb2.setBounds(0, 150, 100, 20);
        jb3.setBounds(0, 180, 100, 20);
        
        JF.setSize(300, 300);
        JF.setVisible(true);
        
      
        
        
    }
    
    
}
