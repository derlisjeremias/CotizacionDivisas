/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONObject;

/**
 *
 * @author Jere
 */
public abstract class CapturaRemotaDivisas {

    abstract protected JSONObject obtenerDatos();

    abstract protected JSONObject obtenerCotizaciones();

    public List<Moneda> obtenerDatosMonedas() {
        JSONObject objetoJson = obtenerDatos();
        List<Moneda> lista = new ArrayList<Moneda>();
        for (Iterator<String> i = objetoJson.keys(); i.hasNext();) {
            String key = i.next();
            Moneda moneda = new Moneda(key, objetoJson.getString(key));
           // System.out.println("UNA MONEDA " + moneda);
            lista.add(moneda);
        }
        return lista;
    }

    public List<CotizacionMoneda> obtenerCotizacionesMonedas() {
        JSONObject cotizacionMonedas = obtenerCotizaciones();
        List<CotizacionMoneda> lista = new ArrayList<CotizacionMoneda>();
        for (Iterator<String> i = cotizacionMonedas.keys(); i.hasNext();) {
            String clave = i.next();
            JSONObject datos = (JSONObject) cotizacionMonedas.getJSONObject(clave);
            String descripcion = datos.getString("descripcion");
            String cotizacion = datos.getString("cotizacion");
            CotizacionMoneda cmoneda = new CotizacionMoneda(clave, descripcion, cotizacion);
           // System.out.println("UNA COTIZACIONMONEDA " + cmoneda);
            lista.add(cmoneda);
        }
        return lista;
    }
}
