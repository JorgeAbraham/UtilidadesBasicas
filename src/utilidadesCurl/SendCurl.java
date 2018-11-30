/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadesCurl;

import java.io.BufferedReader;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;



/**
 *
 * @author Soxtec Desarrollo
 */
public class SendCurl {
    
    String result;
    StatusLine status;

    public HttpResponse  send(String URL,JSONObject jsonObject) {

        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

        HttpResponse response=null;

        
        try {

            HttpPost request = new HttpPost(URL);
            
            StringEntity params = new StringEntity(jsonObject.toString());
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            
            //handle response here...
            response = httpClient.execute(request);
            
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                result = convertStreamToString(instream);  
                // now you have the string representation of the HTML request
                
                instream.close();
               

            }
            
            
            
            status=response.getStatusLine();
        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {
            //Deprecated
            //httpClient.getConnectionManager().shutdown(); 
        }
        
        return response;
        
            
    }  
   
    
    public static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public StatusLine getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

     public JSONObject getResultJSON() {
        
        JSONObject myObject=null;
        try {
            
            myObject = new JSONObject(result);
        } catch (JSONException ex) {
            Logger.getLogger(SendCurl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myObject;
    }
    
    
    
    
    public static void main(String[] args) {
        SendCurl S = new SendCurl();
        JSONObject myObject=null;
        JSONObject detalles;
        try {
            
            
           
            detalles = new JSONObject();
            detalles.put("Estoy", "Sentado");
            detalles.put("Sentado en Un crater", "Desierto");
            detalles.put("Sigo Aguantando", "El Temblor");
            
           // myObject = new JSONObject(S.getResult());
            myObject = new JSONObject();
            myObject.put("details1", detalles);
            myObject.put("details2", detalles);
            myObject.put("details3", detalles);
             
            
        } catch (JSONException ex) {
            Logger.getLogger(SendCurl.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        S.send( "http://b73cf44d.ngrok.io/SoxtecWeb/EnvioHorarios?",myObject);
        
        
        System.out.println(S.getStatus());
        System.out.println(S.getResult());
       // System.out.println(S.getResultJSON());
        
    }

}
