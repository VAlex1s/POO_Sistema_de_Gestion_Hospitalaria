package modelo;

public class Paciente extends Persona {

    private String codigoPaciente;
    private int edad;
    private String genero;
    private String tipoSangre;
    private String alergias;

    public Paciente() {
    }

    public Paciente(String codigoPaciente, String dni, String nombres, String apellidos,
            String telefono, String correo, String direccion, int edad,
            String genero, String tipoSangre, String alergias) {
        super(dni, nombres, apellidos, telefono, correo, direccion);
        this.codigoPaciente = codigoPaciente;
        this.edad = edad;
        this.genero = genero;
        this.tipoSangre = tipoSangre;
        this.alergias = alergias;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String mostrarInformacion() {
        return codigoPaciente + " - " + getNombreCompleto() + " - " + edad + " anios";
    }
}
