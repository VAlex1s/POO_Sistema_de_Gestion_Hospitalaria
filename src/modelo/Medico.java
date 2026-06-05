package modelo;

public class Medico extends Persona {

    private String codigoMedico;
    private String especialidad;
    private String cmp;
    private String horario;

    public Medico() {
    }

    public Medico(String codigoMedico, String dni, String nombres, String apellidos,
            String telefono, String correo, String direccion, String especialidad,
            String cmp, String horario) {
        super(dni, nombres, apellidos, telefono, correo, direccion);
        this.codigoMedico = codigoMedico;
        this.especialidad = especialidad;
        this.cmp = cmp;
        this.horario = horario;
    }

    public String getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String mostrarInformacion() {
        return codigoMedico + " - Dr(a). " + getNombreCompleto() + " - " + especialidad;
    }
}
