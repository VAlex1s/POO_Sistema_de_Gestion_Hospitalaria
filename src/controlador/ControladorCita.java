package controlador;

import java.util.ArrayList;
import modelo.Cita;

public class ControladorCita {

    private ArrayList<Cita> citas;

    public ControladorCita() {
        citas = new ArrayList<Cita>();
    }

    public void agregar(Cita cita) {
        citas.add(cita);
    }

    public ArrayList<Cita> listar() {
        return citas;
    }
}
