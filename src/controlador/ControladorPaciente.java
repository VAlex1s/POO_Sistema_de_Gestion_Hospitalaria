package controlador;

import java.util.ArrayList;
import modelo.Paciente;

public class ControladorPaciente {

    private ArrayList<Paciente> pacientes;

    public ControladorPaciente() {
        pacientes = new ArrayList<Paciente>();
        pacientes.add(new Paciente("P001", "74185296", "Carlos", "Torres",
                "987654321", "carlos@mail.com", "Av. Peru 123", 25, "Masculino", "O+", "Ninguna"));
    }

    public void agregar(Paciente paciente) {
        pacientes.add(paciente);
    }

    public ArrayList<Paciente> listar() {
        return pacientes;
    }

    public Paciente buscarPorCodigo(String codigo) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCodigoPaciente().equals(codigo)) {
                return paciente;
            }
        }
        return null;
    }
}
