/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import laboratoriouno.cotizaciondivisas.ServidorJSon;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author Jere
 */
public class CapturaRemotaDivisasGoogleFinance extends CapturaRemotaDivisas {

    @Override
    JSONObject obtenerDatos() {

        try {
            String request = "http://openexchangerates.org/api/currencies.json";
            HttpClient client = new DefaultHttpClient();
            HttpGet method = new HttpGet(request);
            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            InputStream rstream = entity.getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(rstream, writer);
            String theString = writer.toString();
            JSONObject json = (JSONObject) JSONSerializer.toJSON(theString);

            return json;

        } catch (java.io.IOException ex) {
            Logger.getLogger(ServidorJSon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new JSONObject();
    }
}
