/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriouno.cotizaciondivisas;

import javax.swing.UIManager;

/**
 *
 * @author Jere
 */
public class EntornoGrafico {
    public void seleccionarWindows(){
    try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}
