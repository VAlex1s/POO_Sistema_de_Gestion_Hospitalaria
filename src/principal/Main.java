package principal;

import controlador.SistemaHospitalario;
import javax.swing.SwingUtilities;
import vista.VistaMenuPrincipal;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SistemaHospitalario sistema = new SistemaHospitalario();
                VistaMenuPrincipal menu = new VistaMenuPrincipal(sistema);
                menu.setVisible(true);
            }
        });
    }
}
