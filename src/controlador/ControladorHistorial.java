package controlador;

import java.util.ArrayList;
import modelo.HistorialClinico;

public class ControladorHistorial {

    private ArrayList<HistorialClinico> historiales;

    public ControladorHistorial() {
        historiales = new ArrayList<HistorialClinico>();
    }

    public void agregar(HistorialClinico historial) {
        historiales.add(historial);
    }

    public ArrayList<HistorialClinico> listar() {
        return historiales;
    }
}
