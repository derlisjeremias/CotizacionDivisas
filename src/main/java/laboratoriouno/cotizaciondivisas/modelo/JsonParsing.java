/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas.modelo;

import java.io.InputStream;
import java.util.Iterator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Jere
 */
public class JsonParsing {

    public static void main(String[] args) throws Exception {
        InputStream jsonStream = JsonParsing.class.getResourceAsStream("iso4217.txt");
        String jsonTxt = IOUtils.toString(jsonStream);
        JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);

        for (Iterator<String> i = json.keys(); i.hasNext();) {
            String key = i.next();
            //list.add(new DatosMoneda(key, json.getString(key)));
           // JSONObject a = json.getJSONObject(key);
            //System.out.println(key + " aca " + a.getString("sign"));
            System.out.println(json.getString(key));
        }
    }
}
