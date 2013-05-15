/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import laboratoriouno.cotizaciondivisas.ServidorJSon;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author Jere
 */
public class CSVtoJSonApp {

    public void run() {
        try {
            String request = "http://data.okfn.org/data/currency-codes.csv";
            HttpClient client = new DefaultHttpClient();
            HttpGet method = new HttpGet(request);
            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            InputStream rstream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(rstream, "UTF-8"));

            String line = br.readLine();
            
            while (line != null) {
                String[] lista = line.split(",");
               if (lista.length >= 3){
                 System.out.println(lista[0]+" / "+lista[2]);
                }
                   
                   //System.out.println(lista[2]);
  
               /* if (!lista[0].equals("ANTARCTICA")){
                 System.out.println(lista[1]+" / "+lista[2]);
                }*/
                line = br.readLine();
            }


        } catch (java.io.IOException ex) {
            Logger.getLogger(ServidorJSon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        CSVtoJSonApp n = new CSVtoJSonApp();
        n.run();
    }
}
