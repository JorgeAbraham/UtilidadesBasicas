/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

import conexion.conexionBD;
import conexion.datosBD;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abraham
 */
public class utilidadVinculoBD 
{
    
    
    private ArrayList listaComponentes;
    
    private ArrayList nombreColumnaEnBD;
    private ArrayList tipoDeObjetoAlmacenado;
    
    private String tablaBD;
    private String idBD;
    private static Connection Con;
    
   
    private ArrayList ArregloDeFolios;
    private int indiceDeFolios;
    
    private JTextField fieldID;
    
    utilidadJFrame UF;

   
   public utilidadVinculoBD(datosBD Datos,String NombreBD,String nombreTabla) 
   {
        
        conexionBD CON=new conexionBD();
        Con= CON.getConnection(Datos,NombreBD);
        
         listaComponentes=new ArrayList();
         
         tipoDeObjetoAlmacenado=new ArrayList();
         nombreColumnaEnBD=new ArrayList();
         
         this.tablaBD=nombreTabla;
         
         ArregloDeFolios=new ArrayList();
         indiceDeFolios=ArregloDeFolios.size()-1;
         UF=new utilidadJFrame();
        
    }
    
    
    
    
    public utilidadVinculoBD(datosBD Datos,String NombreBD) 
    {
        
        conexionBD CON=new conexionBD();
        Con= CON.getConnection(Datos,NombreBD);
        
    }
    
    public utilidadVinculoBD(datosBD Datos,String NombreBD,String tablaBD,String PK, JTextField fieldPK) 
    {
         
         conexionBD CON=new conexionBD();
         Con= CON.getConnection(Datos,NombreBD);
        
         listaComponentes=new ArrayList();
         
         tipoDeObjetoAlmacenado=new ArrayList();
         nombreColumnaEnBD=new ArrayList();
         
         this.tablaBD=tablaBD;
         idBD=PK;
         
         ArregloDeFolios=new ArrayList();
         ConsultaIDs();
         indiceDeFolios=ArregloDeFolios.size()-1;
         
        
         
         
         this.fieldID=fieldPK;
         
          UF=new utilidadJFrame();
          
          
        
    }
    
    
    public void setIDTextField(String PK, JTextField fieldPK)
    { 
        idBD=PK;
        this.fieldID=fieldPK;
    }
    
    public utilidadVinculoBD(String tablaBD,String PK, JTextField fieldPK) 
    {
       
        conexionBD CON=new conexionBD();
        Con = CON.getConnection("cis");
        
        
         listaComponentes=new ArrayList();
         
         tipoDeObjetoAlmacenado=new ArrayList();
         nombreColumnaEnBD=new ArrayList();
         
         this.tablaBD=tablaBD;
         idBD=PK;
         
         ArregloDeFolios=new ArrayList();
         ConsultaIDs();
         indiceDeFolios=ArregloDeFolios.size()-1;
         
        
         
         
         this.fieldID=fieldPK;
         
          UF=new utilidadJFrame();
          
          
         
    }
    
    
    public Connection getCon() {
        return Con;
    }
        
    
    public void consultaInicial()
    {
        
        consulta(    (String)ArregloDeFolios.get(indiceDeFolios)         );
        fieldID.setText((String)ArregloDeFolios.get(indiceDeFolios)  );
    }
    
    
    public void BorraPantalla()
    {
       UF.limpiaTodosLosCampos();     
    }
    
    
    
    public void BotonSiguiente(java.awt.event.ActionEvent evt)
    {
      
       if (indiceDeFolios!=ArregloDeFolios.size()-1  )
       {
            BorraPantalla();
            indiceDeFolios++;
            
            
            fieldID.setText((String)ArregloDeFolios.get(indiceDeFolios));
            consulta(fieldID.getText());
       }     
    }
    
    public void BotonAnterior(java.awt.event.ActionEvent evt)
    {
       if (indiceDeFolios>0  )
       {
            BorraPantalla();
            indiceDeFolios--;
         
            fieldID.setText((String)ArregloDeFolios.get(indiceDeFolios));
            consulta(fieldID.getText());
       }     
    }
    
    public void BotonNuevo(java.awt.event.ActionEvent evt)
    {
            BorraPantalla();
            fieldID.setText("(nuevo)");
    }
    
    
    

    
    
    public final void ConsultaIDs()
    {
    
        ArregloDeFolios=new ArrayList();
        String SQL="SELECT "+idBD+" FROM "+tablaBD+" ;";
        
        if (Con != null) 
        {
            try 
            {
                Statement s ;
             
                s = Con.createStatement();
                
                try 
                {
                    try 
                    (ResultSet rs = s.executeQuery(SQL)) {
                        while (rs.next()) 
                        {
                           ArregloDeFolios.add(rs.getString(1));
                        }	
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null,"5:"+ e.getMessage());	
                    }
                }
                catch (Exception e)
                { 
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"6:"+ e.getMessage());	
                } finally { s.close(); 	} 
                
            }
            catch (SQLException e) 
            {  
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"7:"+ e.getMessage());  } finally {    }  
            }
            
                
    
    }
    
    
    
    public void add(JComboBox combo, String columnaBD)
    {
        listaComponentes.add(combo);
        nombreColumnaEnBD.add(columnaBD);
        tipoDeObjetoAlmacenado.add("combo");
        UF.add(combo);
        
    }
    
    public void add(JTextField field, String columnaBD)
    {
        listaComponentes.add(field);
        nombreColumnaEnBD.add(columnaBD);
        tipoDeObjetoAlmacenado.add("field");
        UF.add(field);
        
    }
    
    public void add(grupoRadioButtonExtendido radioGrupo, String columnaBD)
    {
        listaComponentes.add(radioGrupo);
        nombreColumnaEnBD.add(columnaBD);
        tipoDeObjetoAlmacenado.add("grupo");
        UF.add(radioGrupo);
        
    }
    
    public void add(JTextArea Areas, String columnaBD)
    {
        listaComponentes.add(Areas);
        nombreColumnaEnBD.add(columnaBD);
        tipoDeObjetoAlmacenado.add("area");
        UF.add(Areas);
        
    }
    
    public void add(JCheckBox checkBox, String columnaBD)
    {
        listaComponentes.add(checkBox);
        nombreColumnaEnBD.add(columnaBD);
        tipoDeObjetoAlmacenado.add("checkBox");
        UF.add(checkBox);
        
    }
    
    public void add(grupoRadioCheckButtonExtendido check, String columnaBD)
    {
        listaComponentes.add(check);
        nombreColumnaEnBD.add(columnaBD);
        tipoDeObjetoAlmacenado.add("checkBoxGrupo");
        UF.add(check);
        
    }
    
    
        
    
    public final void consulta(String ID)
    {
        
        
      
        String SQL="SELECT  ";
        for (int i=0;i<nombreColumnaEnBD.size();i++)
        {
             if (i  ==  nombreColumnaEnBD.size()-1  )  //ULTIMO ELEMENTO
            {
                SQL=SQL+nombreColumnaEnBD.get(i)+" " ;  
            }
            else
            {
                SQL=SQL+nombreColumnaEnBD.get(i)+" , " ;
            }
         }
          
        SQL=SQL+" FROM "+tablaBD+" WHERE "+idBD+"="+ID+"  ;";
       
        
     
        String consulta[]=ResultadoDeConsulta(SQL);
        
       
        
        
        for (int i=0;i<tipoDeObjetoAlmacenado.size();i++)
        {
            if (tipoDeObjetoAlmacenado.get(i).equals("combo")    )
            {
                if (consulta[i]!=null)
                {
                    (   (JComboBox)(  listaComponentes.get(i)  )   ).setSelectedItem(consulta[i]);
                }
                else
                {
                    (   (JComboBox)(  listaComponentes.get(i)  )   ).setSelectedItem("");
                }
                
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("field")    )
            {
                if (consulta[i]!=null)
                {
                    (   (JTextField)(  listaComponentes.get(i)  )   ).setText(consulta[i]);
                }
                else
                {
                    (   (JTextField)(  listaComponentes.get(i)  )   ).setText("");
                }
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("grupo")    )
            {
                if (consulta[i]!=null)
                {
                    (   (grupoRadioButtonExtendido)(  listaComponentes.get(i)  )   ).activaValor(consulta[i]);
                }
                else
                {
                    (   (grupoRadioButtonExtendido)(  listaComponentes.get(i)  )   ).clearSelection();
                }
            }
            
            if (tipoDeObjetoAlmacenado.get(i).equals("checkBoxGrupo")    )
            {
                if (consulta[i]!=null)
                {
                    (   (grupoRadioCheckButtonExtendido)(  listaComponentes.get(i)  )   ).activaValor(consulta[i]);
                }
                else
                {
                    (   (grupoRadioCheckButtonExtendido)(  listaComponentes.get(i)  )   ).clearSelection();
                }
            }
            
            
            if (tipoDeObjetoAlmacenado.get(i).equals("area")    )
            {
                if (consulta[i]!=null)
                {
                    (   (JTextArea)(  listaComponentes.get(i)  )   ).setText(consulta[i]);
                }
                else
                {
                    (   (JTextArea)(  listaComponentes.get(i)  )   ).setText("");
                }
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("checkBox")    )
            {
                if (consulta[i]!=null)
                {
                    if (  consulta[i].equals("1")  )
                    {
                        (   (JCheckBox)(  listaComponentes.get(i)  )   ).setSelected(true);    
                    }
                    else
                    {
                        (   (JCheckBox)(  listaComponentes.get(i)  )   ).setSelected(false);    
                    }
                    
                }
                else
                {
                    (   (JCheckBox)(  listaComponentes.get(i)  )   ).setSelected(false);
                }
            }

        }        
        
        
        
    }
    
    
    
    public void Inserta()
    {
        String SQL="INSERT "+tablaBD +" ( ";
        for (int i=0;i<nombreColumnaEnBD.size();i++)
        {
             if (i  ==  nombreColumnaEnBD.size()-1  )  //ULTIMO ELEMENTO
            {
                SQL=SQL+nombreColumnaEnBD.get(i)+" " ;  
            }
            else
            {
                SQL=SQL+nombreColumnaEnBD.get(i)+" , " ;
            }
         }
          
        SQL=SQL+" ) VALUES ( ";
       
        
        String ARREGLODATOSINTERFACE[]=new String[tipoDeObjetoAlmacenado.size()] ;
        
        for (int i=0;i<tipoDeObjetoAlmacenado.size();i++)
        {
            if (tipoDeObjetoAlmacenado.get(i).equals("combo")    )
            { 
                ARREGLODATOSINTERFACE[i]=(String) (   (JComboBox)(  listaComponentes.get(i)  )   ).getSelectedItem();   
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("field")    )
            {
                ARREGLODATOSINTERFACE[i]=(String)(   (JTextField)(  listaComponentes.get(i)  )   ).getText();
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("grupo")    )
            {
                ARREGLODATOSINTERFACE[i]=(   (grupoRadioButtonExtendido)(  listaComponentes.get(i)  )   ).getValorSeleccionado();
            
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("checkBoxGrupo")    )
            {
                ARREGLODATOSINTERFACE[i]=(   (grupoRadioCheckButtonExtendido)(  listaComponentes.get(i)  )   ).getValorSeleccionado();
            
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("area")    )
            {
                ARREGLODATOSINTERFACE[i]=   (   (JTextArea)(  listaComponentes.get(i)  )   ).getText();
              
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("checkBox")    )
            {
                if (  (   (JCheckBox)(  listaComponentes.get(i)  )   ).isSelected()  )
                {
                    ARREGLODATOSINTERFACE[i]  =  "1"  ;    
                }
                else
                {
                    ARREGLODATOSINTERFACE[i]  =  "0"  ;
                }
            }

        }     
        
        for (int i=0;i<ARREGLODATOSINTERFACE.length;i++)
        {
            if (i  ==  nombreColumnaEnBD.size()-1  )  //ULTIMO ELEMENTO
            {
                
                if (tipoDeObjetoAlmacenado.get(i).equals("checkBox")    )
                {
                    SQL=SQL+" "+ARREGLODATOSINTERFACE[i]+" " ;  
                }
                else
                {
                    SQL=SQL+" '"+ARREGLODATOSINTERFACE[i]+"' " ;  
                }
                
            }
            
            else
            {
                if (tipoDeObjetoAlmacenado.get(i).equals("checkBox")    )
                {
                    SQL=SQL+" "+ARREGLODATOSINTERFACE[i]+"  , " ;
                }
                else
                {
                    SQL=SQL+" '"+ARREGLODATOSINTERFACE[i]+"'  , " ;
                }       
                    
            }
         }
        
        SQL=SQL+"  ); ";
        operacionSQL(SQL);
        ConsultaIDs();
        
    }
    
    
    
    public void Actualiza(String ID)
    {
        String SQL="UPDATE  "+tablaBD +"   SET   ";
        
        
        for (int i=0;i<nombreColumnaEnBD.size();i++)
        {
            SQL=SQL+nombreColumnaEnBD.get(i) +" = '";
            if (tipoDeObjetoAlmacenado.get(i).equals("combo")    )
            { 
                SQL=SQL+(String) (   (JComboBox)(  listaComponentes.get(i)  )   ).getSelectedItem()  +"' " ;   
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("field")    )
            {
                SQL=SQL+(String)(   (JTextField)(  listaComponentes.get(i)  )   ).getText()+"' ";
            }
            if(tipoDeObjetoAlmacenado.get(i).equals("checkBoxGrupo")    )
            {
                SQL=SQL+(   (grupoRadioCheckButtonExtendido)(  listaComponentes.get(i)  )   ).getValorSeleccionado()+"' ";
            
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("grupo")    )
            {
                SQL=SQL+(   (grupoRadioButtonExtendido)(  listaComponentes.get(i)  )   ).getValorSeleccionado()+"' ";
            
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("area")    )
            {
                SQL=SQL+  (   (JTextArea)(  listaComponentes.get(i)  )   ).getText()+"' ";
              
            }
            if (tipoDeObjetoAlmacenado.get(i).equals("checkBox")    )
            {
                if (  (   (JCheckBox)(  listaComponentes.get(i)  )   ).isSelected()  )
                {
                   SQL=SQL+  "true"  +"' ";    
                }
                else
                {
                    SQL=SQL+  "false"  +"' ";
                }
            }
          
            
            if (i  ==  nombreColumnaEnBD.size()-1  )  //ULTIMO ELEMENTO
            {
                SQL=SQL+" " ;  
            }
            else
            {
                SQL=SQL+" , " ;
            }
            
         }
        
        
            
            
            SQL=SQL+"   WHERE `"+idBD+"`="+ ID +"  ; ";
            
          
        
       
           
        operacionSQL(SQL);
        ConsultaIDs();
        
    }
    
    public void Borra(String ID)
    {
        
        
        String SQL="DELETE FROM  "+tablaBD +"   WHERE `"+idBD+"`="+ ID +"  ; ";
        
        
       
           
        operacionSQL(SQL);
        ConsultaIDs();
        
    }
    
    
    
    
    public static void operacionSQL(String sql)
    {
        
        if (Con != null) 
        {

            try 
            {
                
                Con.setAutoCommit(false);
                Statement stmt;
                
                stmt = Con.createStatement();

                
                System.out.println(sql);
                stmt.executeUpdate(sql);
                
                
               Con.commit();
                stmt.close();
                
               
                
            }
            catch (SQLException e) 
            {
                e.printStackTrace() ;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
          
         }    
    }
    
    
    /*
    Funcion para inste
    
    */
    public static void operacionSQLupdateBlob(String Tabla,String idColumna, int id, String blobColumn,archivoSerializableParaBD objetoArchivo,String nombreArchivo,boolean insertar   )
    {
        PreparedStatement ps = null;
        String sql="";
           
        if (Con != null) 
        {
            try 
            {
                Con.setAutoCommit(false);
                
         
                if (insertar){
                    sql = "INSERT INTO "+Tabla+" ("+blobColumn+","+nombreArchivo+") VALUES (?,?) ";
                    ps = Con.prepareStatement(sql);
                    ps.setBinaryStream(1,objetoArchivo.getFis(),(int)objetoArchivo.getFile().length());
                    ps.setString(2, objetoArchivo.getNombreArchivo());
                    
                }else{
                    sql = "UPDATE "+Tabla+" SET "+blobColumn+" = ?,  "+nombreArchivo+"=?  WHERE "+idColumna+" = "+id+" ;"  ;
                    ps = Con.prepareStatement(sql);
                    
                    
                    ps.setBinaryStream(1,objetoArchivo.getFis(),(int)objetoArchivo.getFile().length());
                    ps.setString(2, objetoArchivo.getNombreArchivo());
                }
                System.out.println(sql);              
                ps.executeUpdate();
                Con.commit();
                
                
              
            }
            catch (SQLException e) 
            {
                e.printStackTrace() ;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            finally{
                try {
                    ps.close();
                    try {
                        (objetoArchivo.getFis()).close();
                    } catch (IOException ex) {
                        Logger.getLogger(utilidadVinculoBD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(utilidadVinculoBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            System.out.println("Conexion Nula");
        }
    }
    
    
    /*
    Funcion para inste
    
    */
    public static archivoSerializableParaBD operacionSQSelectBlob(String Tabla,String idColumna, int id, String blobColumn, String nombreColumnaArchivo )
    {
        
        archivoSerializableParaBD objeto=new archivoSerializableParaBD();
        PreparedStatement ps = null;
        String sql;
           
        if (Con != null) 
        {
            try 
            {
                Con.setAutoCommit(false);
                
                
                sql = "SELECT "+blobColumn+","+nombreColumnaArchivo+" FROM "+Tabla+" WHERE "+idColumna+"='"+id+"'; ";
                  System.out.println("SELECT BLOB: "+sql);
                
                ps = Con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    
                    Blob blob  = rs.getBlob(blobColumn);
                    String nombreArchivo  = rs.getString(nombreColumnaArchivo);
                    
                    byte data[] = blob.getBytes(1, (int)blob.length());
                    
                    objeto.setContenido(data);
                    objeto.setNombreArchivo(nombreArchivo);
                    
                   
                }
              
                System.out.println(sql);
                
            }
            catch (SQLException e) 
            {
                e.printStackTrace() ;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
           
        }
        else
        {
            System.out.println("Conexion Nula");
        }
        
        return objeto;
        
    }
    
    
    
    
    
    public  String[] ResultadoDeConsulta(String sql)
    {
        
        String R[]=new String[nombreColumnaEnBD.size()];

        
        if (Con != null) 
        {
            try 
            {
                Statement s ;
             
                s = Con.createStatement();
                
                try 
                {
                    try 
                    (ResultSet rs = s.executeQuery(sql)) {
                        int i=0;
                        while (rs.next()) 
                        {
                           for (int j=0;j<R.length;j++)
                           {
                               R[j]=(rs.getString(j+1));
                           }
                           i++;
                            
                        }	
                    }
                    catch (Exception e)
                    {  
                        e.printStackTrace() ;
                        JOptionPane.showMessageDialog(null, e.getMessage());	
                    }
                }
                catch (Exception e)
                { 
                    e.printStackTrace() ;
                    JOptionPane.showMessageDialog(null, e.getMessage());	
                } finally { s.close(); 	} 
                
            }
            catch (SQLException e) 
            {  
                 e.printStackTrace() ;
                 JOptionPane.showMessageDialog(null, e.getMessage());  
            } finally {    }  
            }

        
        
        return R;
    }
    
    
    public  String[] Consulta(String sql)
    {
        
        String R[]=null;

        
        if (Con != null) 
        {
            try 
            {
                Statement s ;
             
                s = Con.createStatement();
                
                try 
                {
                    try 
                    (ResultSet rs = s.executeQuery(sql)) {
                        int i=0;
                        while (rs.next()) 
                        {
                           i++;
                        }	
                        R=new String[i];
                    }
                    catch (Exception e)
                    {  
                        e.printStackTrace() ;
                        JOptionPane.showMessageDialog(null, e.getMessage());	
                    }
                    
                    
                    
                    
                    try 
                    (ResultSet rs = s.executeQuery(sql)) 
                    {
                        int i=0;
                        while (rs.next()) 
                        {
                            R[i]=(rs.getString(1));
                            i++;
                        }
                        
                        	
                    }
                    catch (Exception e)
                        
                    { 
                        e.printStackTrace() ;
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                    
                    
                }
                catch (Exception e)
                { 
                    e.printStackTrace() ;
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally { s.close(); 	} 
                
            }
            catch (SQLException e) 
            {  
                e.printStackTrace() ;
                JOptionPane.showMessageDialog(null, e.getMessage());
                
            } finally {    }  
        }

        
        
        return R;
    }
    
    
    
    
    
    public  String[][] ConsultaCuadro(String sql, int numeroDeColumnas)
    {
        System.out.println(sql);
        String R[][]=null;

        
        if (Con != null) 
        {
            try 
            {
                Statement s ;
               
                s = Con.createStatement();
                
                try 
                {
                    try 
                    {
                        ResultSet rs = s.executeQuery(sql);
                        
                       
                        int i=0;
                        while (rs.next()) 
                        {
                           i++;
                        }	
                        
                        
                        R=new String[i][numeroDeColumnas];
                    }
                    catch (Exception e)
                    { 
                        
                        e.printStackTrace() ;
                        JOptionPane.showMessageDialog(null, e.getMessage());	
                    }
                    
                    
                    
                    
                    try 
                    (ResultSet rs = s.executeQuery(sql)) 
                    {
                        int i=0;
                        
                        while (rs.next()) 
                        {
                            for (int j=1;j<=numeroDeColumnas;j++)
                            {
                              
                               
                                R[i][j-1]=(rs.getString(j));
                            }
                            
                            
                            i++;
                        }
                        
                        	
                    }
                    catch (Exception e)
                    { 
                        e.printStackTrace() ;
                        JOptionPane.showMessageDialog(null, e.getMessage());	
                    }
                    
                    
                }
                catch (Exception e)
                {
                    e.printStackTrace() ;
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } finally { s.close(); 	} 
                
            }
            catch (SQLException e) 
            {
                e.printStackTrace() ;
                JOptionPane.showMessageDialog(null, e.getMessage());
            } finally {    }  
        }
        else{
            System.err.println("Conection Null");
        }

        
        
        return R;
    }
    
    
    
    
    
    public DefaultTableModel consultaTabla(String SQL,String nombreColumaa[])
    {
        
        String A[][]=ConsultaCuadro(SQL,nombreColumaa.length);
        DefaultTableModel TABLA=new DefaultTableModel(A,nombreColumaa);
        return TABLA;
        
    }
    
   
    
    
    public DefaultComboBoxModel consultaCombo(String SQL)
    {
        
        String A[][]=ConsultaCuadro(SQL,1);
        String R[]=new String[A.length];
        for (int i=0;i<A.length;i++)
        {
            R[i]=A[i][0];
        }
        DefaultComboBoxModel TABLA=new DefaultComboBoxModel(R);
        return TABLA;
        
    }
    
    public static String dateToString(Date D)
    {
            SimpleDateFormat sdfDestination = new SimpleDateFormat(
            "yyyy/MM/dd");
            String str = sdfDestination.format( D );
            return str;
    }
    
    public static String dateToString_guion(Date D)
    {
            SimpleDateFormat sdfDestination = new SimpleDateFormat(
            "yyyy-MM-dd");
            String str = sdfDestination.format( D );
            return str;
    }
  
    
    
    public static Date stringToDate(String diaDeConsultaString) 
    {
        Date D=null;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy/MM/dd");
        try {
            D = formatoDelTexto.parse(diaDeConsultaString);
        } catch (ParseException ex) {
            System.err.print(ex);
        }
        return D;
    }
    
    public static Date stringToDate2(String diaDeConsultaString) 
    {
        Date D=null;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        try {
            D = formatoDelTexto.parse(diaDeConsultaString);
        } catch (ParseException ex) {
            System.err.print(ex);
        }
        return D;
    }
    
        //   El archivo debe contener la siguiente informacion en la misma carpeta de proyecto
    //   [password]&[URL]&[usuario]
    //   root&jdbc:mysql://192.168.1.2:3306/&investigacion
    
    public static datosBD datosDeBdDesdeArchivo(String Archivo)
    {
        datosBD D=new datosBD();
        fileStringIO FILE=new fileStringIO(Archivo);
        
        StringTokenizer TOK=new StringTokenizer(FILE.getStringFile(),"&\n",false);
        
        String A=TOK.nextToken();
        String B=TOK.nextToken();
        String C=TOK.nextToken();
        
        D.setDbPassword(A);
        D.setDbURL(B);
        D.setDbUserName(C);
        
        return D;
        
    }
    
    
    public static void main(String AGSA[])
    {
        /*
         final grupoRadioButtonExtendido GrupoE=new grupoRadioButtonExtendido();
        //****************************
        JRadioButton r1=new JRadioButton( "Boton 1" ); 
        JRadioButton r2=new JRadioButton( "Boton 2" ); 
        JRadioButton r3=new JRadioButton( "Boton 3" ); 
        JRadioButton r4=new JRadioButton( "Boton 4" );
        
        
        GrupoE.add(r1,"0");
        GrupoE.add(r2,"2234");
        GrupoE.add(r3,"ab");
        GrupoE.add(r4,"yeah!");
        //****************************
        
        
        JTextField jb=new JTextField("Etiqueta");
        JTextField jb2=new JTextField("Valor");
        JComboBox jb3=new JComboBox(new String[]{"","A","B","C" } );
        JButton jb4=new JButton("borra");
        
        JButton jb5=new JButton("inserta");
        JButton jb6=new JButton("Actualiza");
        
        
        
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
        JF.add(jb5);
        JF.add(jb6);
        
        jb.setBounds(0, 120, 100, 20);
        jb2.setBounds(0, 150, 100, 20);
        jb3.setBounds(0, 180, 100, 20);
        jb4.setBounds(0, 210, 100, 20);
        jb5.setBounds(100, 210, 100, 20);
        jb6.setBounds(0, 230, 100, 20);
        
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
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        */
      //  final utilidadVinculoBD VINCULOBD=new utilidadVinculoBD("TABLA","ID",null);
/*        VINCULOBD.add(jb, "C1" );
        VINCULOBD.add(jb2, "C2" );
        VINCULOBD.add(GrupoE, "C3" );
        
        VINCULOBD.consulta("2");
        
        
        jb5.addActionListener(new ActionListener() {
            
            
            utilidadVinculoBD VINCULO=VINCULOBD;
            @Override
            public void actionPerformed(ActionEvent e) {
                 VINCULOBD.Inserta();
            } 
        });
        
         jb6.addActionListener(new ActionListener() {
            
            
            utilidadVinculoBD VINCULO=VINCULOBD;
            @Override
            public void actionPerformed(ActionEvent e) {
                 VINCULOBD.Actualiza("4");
            } 
        });
        
  */      
    }
    
}
