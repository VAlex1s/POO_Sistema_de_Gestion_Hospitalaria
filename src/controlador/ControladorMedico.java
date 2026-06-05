package controlador;

import java.util.ArrayList;
import modelo.Medico;

public class ControladorMedico {

    private ArrayList<Medico> medicos;

    public ControladorMedico() {
        medicos = new ArrayList<Medico>();
        medicos.add(new Medico("M001", "12345678", "Maria", "Salazar",
                "999888777", "maria@hospital.com", "Jr. Salud 456",
                "Medicina General", "CMP12345", "Maniana"));
    }

    public void agregar(Medico medico) {
        medicos.add(medico);
    }

    public ArrayList<Medico> listar() {
        return medicos;
    }

    public Medico buscarPorCodigo(String codigo) {
        for (Medico medico : medicos) {
            if (medico.getCodigoMedico().equals(codigo)) {
                return medico;
            }
        }
        return null;
    }
}
