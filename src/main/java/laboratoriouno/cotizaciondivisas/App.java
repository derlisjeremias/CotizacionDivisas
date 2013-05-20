package laboratoriouno.cotizaciondivisas;

import laboratoriouno.cotizaciondivisas.modelo.*;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        AdministracionDivisasUsuarios app = new AdministracionDivisasUsuarios();
        FrameInicioSesion finicio = new FrameInicioSesion();
        finicio.asignarAplicacion(app);
        
        finicio.setVisible(true);
    }
}
