/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ServidorJSon {

    public JSONObject getJSon(String url) {
        try {
            String request = url;
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

    public List<DatosMoneda> getArrayDatosMoneda() {
        String url = "http://openexchangerates.org/api/currencies.json";
        ServidorJSon sj = new ServidorJSon();
        JSONObject json = sj.getJSon(url);
        List<DatosMoneda> list = new ArrayList<DatosMoneda>();

        for (Iterator<String> i = json.keys(); i.hasNext();) {
            String key = i.next();
            list.add(new DatosMoneda(key, json.getString(key)));
        }
        return list;
    }

    public List<CotizacionMoneda> getArrayMonedas() {
        String url = "http://openexchangerates.org/api/latest.json?app_id=4272aa1329564d879f5fc1e54d666f1c";
        ServidorJSon sj = new ServidorJSon();
        JSONObject json = sj.getJSon(url);
        List<CotizacionMoneda> list = new ArrayList<CotizacionMoneda>();
        JSONObject rates = json.getJSONObject("rates");
        
        for (Iterator<String> i = rates.keys(); i.hasNext();) {
            String key = i.next();
            list.add(new CotizacionMoneda(key, rates.getString(key)));
        }
        return list;
    }
}
