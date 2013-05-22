package laboratoriouno.cotizaciondivisas;

import javax.swing.UIManager;
import laboratoriouno.cotizaciondivisas.modelo.AdministracionDivisasUsuarios;
import laboratoriouno.cotizaciondivisas.modelo.Moneda;
import laboratoriouno.cotizaciondivisas.modelo.Usuario;


/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        EntornoGrafico visualizar = new EntornoGrafico();
        visualizar.seleccionarWindows();
        Usuario usuario = new Usuario("A");
        usuario.setClaveAcceso("a");
        Moneda mA = new Moneda("CUP", "Cuban Peso");
        Moneda mB = new Moneda("LBP", "Lebanese Pound");
        Moneda mC = new Moneda("ARS", "Argentine Peso");
        Moneda mD = new Moneda("NOK", "Norwegian Krone");
        usuario.agregarMoneda(mA);
        usuario.agregarMoneda(mB);
        usuario.agregarMoneda(mC);
        usuario.agregarMoneda(mD);
        usuario.agregarMoneda(mA);

        AdministracionDivisasUsuarios modelo = new AdministracionDivisasUsuarios();
        modelo.agregarUsuario(usuario);
        FrameCotizacionDivisas aplicacion = new FrameCotizacionDivisas();
        aplicacion.asignarAplicacion(modelo);
        aplicacion.asignarVisualizador(visualizar);

        aplicacion.setVisible(true);

    }
}
