package modelo;

public class HistorialClinico {

    private String codigoHistorial;
    private Paciente paciente;
    private Medico medico;
    private String fechaRegistro;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;

    public HistorialClinico() {
    }

    public HistorialClinico(String codigoHistorial, Paciente paciente, Medico medico,
            String fechaRegistro, String diagnostico, String tratamiento,
            String observaciones) {
        this.codigoHistorial = codigoHistorial;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaRegistro = fechaRegistro;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
    }

    public String getCodigoHistorial() { return codigoHistorial; }
    public void setCodigoHistorial(String codigoHistorial) { this.codigoHistorial = codigoHistorial; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public String getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
