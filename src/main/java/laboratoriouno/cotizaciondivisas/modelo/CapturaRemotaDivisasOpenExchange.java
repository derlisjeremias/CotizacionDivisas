/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
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

public class CapturaRemotaDivisasOpenExchange extends CapturaRemotaDivisas {

    private JSONObject descargarInformacion(String url, String tipo) {
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
            if (tipo.equals("cotizacion")) {
                JSONObject rates = json.getJSONObject("rates");
                return rates;
            }
            if (tipo.equals("datos")) {
                return json;
            }
        } catch (java.io.IOException ex) {
            Logger.getLogger(ServidorJSon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new JSONObject();
    }

    @Override
    protected JSONObject obtenerDatos() {
        JSONObject datosMonedas = new JSONObject();
        String url = "http://openexchangerates.org/api/currencies.json";
        JSONObject datos = descargarInformacion(url, "datos");
        for (Iterator<String> i = datos.keys(); i.hasNext();) {
            try {
                String clave = i.next();
                JSONObject unaMoneda = new JSONObject();
                String d = datos.getString(clave);
                String descripcion = new String(d.getBytes("ISO-8859-1"));
                datosMonedas.put(clave, descripcion);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CapturaRemotaDivisasOpenExchange.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //            
        return datosMonedas;
    }

    @Override
    protected JSONObject obtenerCotizaciones() {
        JSONObject datosMonedas = new JSONObject();
        String url = "http://openexchangerates.org/api/latest.json?app_id=4272aa1329564d879f5fc1e54d666f1c";
        JSONObject cotizaciones = descargarInformacion(url, "cotizacion");
        JSONObject datos = obtenerDatos();
        for (Iterator<String> i = cotizaciones.keys(); i.hasNext();) {
            String clave = i.next();
            JSONObject unaCotizacion = new JSONObject();
            unaCotizacion.put("descripcion", datos.getString(clave));
            unaCotizacion.put("cotizacion", cotizaciones.getString(clave));
            datosMonedas.put(clave, unaCotizacion);
        }
        return datosMonedas;
    }
}
