package laboratoriouno.cotizaciondivisas;

import java.util.Iterator;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        FramePrincipal app = new FramePrincipal();
         app.setVisible(true);
       /*
        * ServidorJSon sj = new ServidorJSon();
        List<Moneda> list = sj.getArrayMonedas();
        
        for (int i = 0; i < list.size(); i++) {
            Moneda aux = (Moneda) (list.get(i));
            System.out.println("Moneda: " + aux.getSiglas() + "   Cambio: " + aux.getCambio());
        }
        */
    }
}
