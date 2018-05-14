/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abraham
 */
public class utilidadJFrame 
{
    
    private ArrayList listaJComboBoxs;
    private ArrayList listaJTextFields;
    private ArrayList listaGruposDeRadio;
    private ArrayList listaJTextAreas;
    private ArrayList listaJCheckBoxs;
    private ArrayList listaGruposDeCheck;
    
    
    public utilidadJFrame()
    {
        listaJComboBoxs=new ArrayList();
        listaJTextFields=new ArrayList();
        listaGruposDeRadio=new ArrayList();
        listaJTextAreas=new ArrayList();
        listaJCheckBoxs=new ArrayList();
        listaGruposDeCheck=new ArrayList();
       
    }
    
    
    
    public void add(JComboBox combo)
    {
        listaJComboBoxs.add(combo);
    }
    
    public void add(JTextField field)
    {
        listaJTextFields.add(field);
    }
    
    public void add(grupoRadioButtonExtendido grupoDeRadios)
    {
        listaGruposDeRadio.add(grupoDeRadios);
    }
    
    public void add(JTextArea area)
    {
        listaJTextAreas.add(area);
    }
    
    public void add(JCheckBox check)
    {
        listaJCheckBoxs.add(check);
    }
    
    public void add(grupoRadioCheckButtonExtendido checks)
    {
        listaGruposDeCheck.add(checks);
    }
    
    
    public void limpiaTodosLosCampos()
    {
        
        for (int i=0;i<listaJComboBoxs.size();i++)
        {
            (  (JComboBox)  (  listaJComboBoxs.get(i)  )  ).setSelectedItem("");
        }
        
        for (int i=0;i<listaJTextFields.size();i++)
        {
            (  (JTextField)  (  listaJTextFields.get(i)  )  ).setText("");
        }
        
        for (int i=0;i<listaGruposDeRadio.size();i++)
        {
            (  (grupoRadioButtonExtendido)  (  listaGruposDeRadio.get(i)  )  ).clearSelection();
        }
        
        for (int i=0;i<listaJTextAreas.size();i++)
        {
            (  (JTextArea)  (  listaJTextAreas.get(i)  )  ).setText("");
        }
        
        for (int i=0;i<listaJCheckBoxs.size();i++)
        {
            (  (JCheckBox)  (  listaJCheckBoxs.get(i)  )  ).setSelected(false);
        }
        
        for (int i=0;i<listaGruposDeCheck.size();i++)
        {
            (  (grupoRadioCheckButtonExtendido)  (  listaGruposDeCheck.get(i)  )  ).clearSelection();
        }
        
        
    }
    
    
    
    public static void main(String ags[])
    {
         final grupoRadioButtonExtendido GrupoE=new grupoRadioButtonExtendido();
        //****************************
        JRadioButton r1=new JRadioButton( "Boton 1" ); 
        JRadioButton r2=new JRadioButton( "Boton 2" ); 
        JRadioButton r3=new JRadioButton( "Boton 3" ); 
        JRadioButton r4=new JRadioButton( "Boton 4" );
        
        
        GrupoE.add(r1,"102");
        GrupoE.add(r2,"2234");
        GrupoE.add(r3,"ab");
        GrupoE.add(r4,"yeah!");
        //****************************
        
        
        JTextField jb=new JTextField("Etiqueta");
        JTextField jb2=new JTextField("Valor");
        JComboBox jb3=new JComboBox(new String[]{"","A","B","C" } );
        JButton jb4=new JButton("borra");
        
      
        
        
        
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
        JF.add(jb4);
        
        jb.setBounds(0, 120, 100, 20);
        jb2.setBounds(0, 150, 100, 20);
        jb3.setBounds(0, 180, 100, 20);
        jb4.setBounds(0, 210, 100, 20);
        
        
        
        final utilidadJFrame UF=new utilidadJFrame();
        UF.add(GrupoE);
        UF.add(jb);
        UF.add(jb2);
        UF.add(jb3);
        
        
        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UF.limpiaTodosLosCampos();
            } 
        });
        
        

        JF.setSize(300, 300);
        JF.setVisible(true);
        
      
        
    }
    
    
    public static void clearJTable(DefaultTableModel tabla)
    {
       
        for (int i=0;i<tabla.getRowCount();i++)
        {
            tabla.removeRow(0);
        }
        
     
        
    }
    
}
