package controlador;

public class SistemaHospitalario {

    private ControladorPaciente controladorPaciente;
    private ControladorMedico controladorMedico;
    private ControladorCita controladorCita;
    private ControladorHistorial controladorHistorial;

    public SistemaHospitalario() {
        controladorPaciente = new ControladorPaciente();
        controladorMedico = new ControladorMedico();
        controladorCita = new ControladorCita();
        controladorHistorial = new ControladorHistorial();
    }

    public ControladorPaciente getControladorPaciente() {
        return controladorPaciente;
    }

    public ControladorMedico getControladorMedico() {
        return controladorMedico;
    }

    public ControladorCita getControladorCita() {
        return controladorCita;
    }

    public ControladorHistorial getControladorHistorial() {
        return controladorHistorial;
    }
}
