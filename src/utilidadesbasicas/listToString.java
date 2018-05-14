/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class listToString {
    
    public static String listaEntreComas(ArrayList<String>  lista ){
        String listaString="";
          if (lista.size() > 1){
            for (int i=0;i<lista.size();i++){
                
                if (i==lista.size()-1){
                    listaString = listaString + lista.get(i) ;
                }
                else{
                    listaString = listaString + lista.get(i) +",";
                }
                
            }
        }
        else{
            listaString=lista.get(0);
        }
        return listaString;
    }
    
    
    public static void main(String args[]){
        ArrayList<String> estadosOperacion=new ArrayList();
            estadosOperacion.add("3");
            estadosOperacion.add("4");
        System.out.println(listToString.listaEntreComas(estadosOperacion));   ;
    }
    
    
}
