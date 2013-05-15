/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import laboratoriouno.cotizaciondivisas.DatosMoneda;
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
public abstract class CapturaRemotaDivisas {
    abstract JSONObject obtenerDatos();

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

    public List<laboratoriouno.cotizaciondivisas.CotizacionMoneda> getArrayMonedas() {
        String url = "http://openexchangerates.org/api/latest.json?app_id=4272aa1329564d879f5fc1e54d666f1c";
        ServidorJSon sj = new ServidorJSon();
        JSONObject json = sj.getJSon(url);
        List<laboratoriouno.cotizaciondivisas.CotizacionMoneda> list = new ArrayList<laboratoriouno.cotizaciondivisas.CotizacionMoneda>();
        JSONObject rates = json.getJSONObject("rates");
        
        for (Iterator<String> i = rates.keys(); i.hasNext();) {
            String key = i.next();
            list.add(new laboratoriouno.cotizaciondivisas.CotizacionMoneda(key, rates.getString(key)));
        }
        return list;
    }
}
