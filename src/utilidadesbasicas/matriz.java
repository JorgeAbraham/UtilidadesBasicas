/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesbasicas;

/**
 *
 * @author Soxtec Desarrollo
 */
public class matriz {
    public static String[][] transponerMatriz(String matriz[][] ){
        
        
        String transpuesta[][]=new String[matriz[0].length][matriz.length];
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                transpuesta[j][i] = matriz[i][j];
            }
        }
        
        return transpuesta;
        
    }
 
}
