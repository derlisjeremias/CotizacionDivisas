/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
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

/**
 *
 * @author Jere
 */
public class CapturaRemotaDivisasGoogleFinance extends CapturaRemotaDivisas {

    private JSONObject resultadoFallido() {
        JSONObject fallido = new JSONObject();
        fallido.put("fallido", "fallido");
        return fallido;
    }

    @Override
    protected JSONObject obtenerDatos() {
        try {
            JSONObject datosMonedas = new JSONObject();
            InputStream jsonStream = CotizacionDivisas.class.getResourceAsStream("MonedasAceptadasPorGoogleFinance.json");
            String jsonTxt = IOUtils.toString(jsonStream);
            JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
            for (Iterator<String> i = json.keys(); i.hasNext();) {
                String clave = i.next();
                JSONObject datos = (JSONObject) json.getJSONObject(clave);
                String descripcion = datos.getString("pais") + " (" + datos.getString("nombre") + ")";
                datosMonedas.put(clave, descripcion);
            }
            return datosMonedas;
        } catch (Exception ex) {
            Logger.getLogger(ServidorJSon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoFallido();
    }

    @Override
    protected JSONObject obtenerCotizaciones() {
        try {
            JSONObject datosMonedas = obtenerDatos();
            JSONObject cotizacionMonedas = new JSONObject();
            HttpClient cliente = new DefaultHttpClient();
            HttpGet metodo = new HttpGet();
            for (Iterator<String> i = datosMonedas.keys(); i.hasNext();) {
                String clave = i.next();
                URL url = new URL("http://www.google.com/ig/calculator?hl=en&q=1USD=?" + clave);
                metodo.setURI(url.toURI());
                HttpResponse respuesta = cliente.execute(metodo);
                HttpEntity entidad = respuesta.getEntity();
                InputStream istream = entidad.getContent();
                StringWriter cadenaEscritura = new StringWriter();
                IOUtils.copy(istream, cadenaEscritura);
                String cadena = cadenaEscritura.toString();
                JSONObject js = (JSONObject) JSONSerializer.toJSON(cadena);
                if (js.getString("icc").equals("true")) {
                    String palabra = js.getString("rhs");
                    String cotizacion = palabra.replaceAll("[^\\d+$.]", "");
                    String descripcion = datosMonedas.getString(clave);
                    JSONObject unaCotizacion = new JSONObject();
                    unaCotizacion.put("descripcion", descripcion);
                    unaCotizacion.put("cotizacion", cotizacion);
                    cotizacionMonedas.put(clave, unaCotizacion);
                }
            }
            return cotizacionMonedas;
        } catch (Exception ex) {
            Logger.getLogger(ServidorJSon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoFallido();
    }
}
