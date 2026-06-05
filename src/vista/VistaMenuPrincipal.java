package vista;

import controlador.SistemaHospitalario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Cita;
import modelo.HistorialClinico;
import modelo.Medico;
import modelo.Paciente;

public class VistaMenuPrincipal extends JFrame {

    private SistemaHospitalario sistema;
    private DefaultTableModel modeloPacientes;
    private DefaultTableModel modeloMedicos;
    private DefaultTableModel modeloCitas;
    private DefaultTableModel modeloHistoriales;

    public VistaMenuPrincipal(SistemaHospitalario sistema) {
        this.sistema = sistema;

        setTitle("Sistema Hospitalario");
        setSize(980, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crearMenu();
    }

    private void crearMenu() {
        JTabbedPane pestanias = new JTabbedPane();
        pestanias.addTab("Pacientes", crearPanelPacientes());
        pestanias.addTab("Medicos", crearPanelMedicos());
        pestanias.addTab("Citas", crearPanelCitas());
        pestanias.addTab("Historial", crearPanelHistorial());

        JPanel superior = new JPanel(new BorderLayout());
        superior.add(new JLabel(" Sistema de Gestion Hospitalaria"), BorderLayout.WEST);

        add(superior, BorderLayout.NORTH);
        add(pestanias, BorderLayout.CENTER);
    }

    private JPanel crearPanelPacientes() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formulario = new JPanel(new GridLayout(12, 1, 5, 5));

        JTextField txtCodigo = new JTextField();
        JTextField txtDni = new JTextField();
        JTextField txtNombres = new JTextField();
        JTextField txtApellidos = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtCorreo = new JTextField();
        JTextField txtDireccion = new JTextField();
        JTextField txtEdad = new JTextField();
        JTextField txtGenero = new JTextField();
        JTextField txtSangre = new JTextField();
        JTextField txtAlergias = new JTextField();
        JButton btnAgregar = new JButton("Agregar paciente");

        agregarCampo(formulario, "Codigo:", txtCodigo);
        agregarCampo(formulario, "DNI:", txtDni);
        agregarCampo(formulario, "Nombres:", txtNombres);
        agregarCampo(formulario, "Apellidos:", txtApellidos);
        agregarCampo(formulario, "Telefono:", txtTelefono);
        agregarCampo(formulario, "Correo:", txtCorreo);
        agregarCampo(formulario, "Direccion:", txtDireccion);
        agregarCampo(formulario, "Edad:", txtEdad);
        agregarCampo(formulario, "Genero:", txtGenero);
        agregarCampo(formulario, "Tipo sangre:", txtSangre);
        agregarCampo(formulario, "Alergias:", txtAlergias);
        formulario.add(btnAgregar);

        modeloPacientes = new DefaultTableModel();
        modeloPacientes.addColumn("Codigo");
        modeloPacientes.addColumn("Paciente");
        modeloPacientes.addColumn("Edad");
        modeloPacientes.addColumn("Genero");
        modeloPacientes.addColumn("Sangre");

        JTable tabla = new JTable(modeloPacientes);
        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> {
            try {
                Paciente paciente = new Paciente(txtCodigo.getText(), txtDni.getText(),
                        txtNombres.getText(), txtApellidos.getText(), txtTelefono.getText(),
                        txtCorreo.getText(), txtDireccion.getText(),
                        Integer.parseInt(txtEdad.getText()), txtGenero.getText(),
                        txtSangre.getText(), txtAlergias.getText());
                sistema.getControladorPaciente().agregar(paciente);
                refrescarPacientes();
                limpiar(txtCodigo, txtDni, txtNombres, txtApellidos, txtTelefono,
                        txtCorreo, txtDireccion, txtEdad, txtGenero, txtSangre, txtAlergias);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Revise los datos del paciente.");
            }
        });

        refrescarPacientes();
        return panel;
    }

    private JPanel crearPanelMedicos() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formulario = new JPanel(new GridLayout(11, 1, 5, 5));

        JTextField txtCodigo = new JTextField();
        JTextField txtDni = new JTextField();
        JTextField txtNombres = new JTextField();
        JTextField txtApellidos = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtCorreo = new JTextField();
        JTextField txtDireccion = new JTextField();
        JTextField txtEspecialidad = new JTextField();
        JTextField txtCmp = new JTextField();
        JTextField txtHorario = new JTextField();
        JButton btnAgregar = new JButton("Agregar medico");

        agregarCampo(formulario, "Codigo:", txtCodigo);
        agregarCampo(formulario, "DNI:", txtDni);
        agregarCampo(formulario, "Nombres:", txtNombres);
        agregarCampo(formulario, "Apellidos:", txtApellidos);
        agregarCampo(formulario, "Telefono:", txtTelefono);
        agregarCampo(formulario, "Correo:", txtCorreo);
        agregarCampo(formulario, "Direccion:", txtDireccion);
        agregarCampo(formulario, "Especialidad:", txtEspecialidad);
        agregarCampo(formulario, "CMP:", txtCmp);
        agregarCampo(formulario, "Horario:", txtHorario);
        formulario.add(btnAgregar);

        modeloMedicos = new DefaultTableModel();
        modeloMedicos.addColumn("Codigo");
        modeloMedicos.addColumn("Medico");
        modeloMedicos.addColumn("Especialidad");
        modeloMedicos.addColumn("CMP");
        modeloMedicos.addColumn("Horario");

        JTable tabla = new JTable(modeloMedicos);
        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> {
            Medico medico = new Medico(txtCodigo.getText(), txtDni.getText(),
                    txtNombres.getText(), txtApellidos.getText(), txtTelefono.getText(),
                    txtCorreo.getText(), txtDireccion.getText(), txtEspecialidad.getText(),
                    txtCmp.getText(), txtHorario.getText());
            sistema.getControladorMedico().agregar(medico);
            refrescarMedicos();
            limpiar(txtCodigo, txtDni, txtNombres, txtApellidos, txtTelefono,
                    txtCorreo, txtDireccion, txtEspecialidad, txtCmp, txtHorario);
        });

        refrescarMedicos();
        return panel;
    }

    private JPanel crearPanelCitas() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formulario = new JPanel(new GridLayout(8, 1, 5, 5));

        JTextField txtCodigo = new JTextField();
        JTextField txtPaciente = new JTextField();
        JTextField txtMedico = new JTextField();
        JTextField txtFecha = new JTextField();
        JTextField txtHora = new JTextField();
        JTextField txtMotivo = new JTextField();
        JComboBox<String> cboEstado = new JComboBox<String>();
        cboEstado.addItem("Pendiente");
        cboEstado.addItem("Atendida");
        cboEstado.addItem("Cancelada");
        JButton btnAgregar = new JButton("Registrar cita");

        agregarCampo(formulario, "Codigo:", txtCodigo);
        agregarCampo(formulario, "Cod. paciente:", txtPaciente);
        agregarCampo(formulario, "Cod. medico:", txtMedico);
        agregarCampo(formulario, "Fecha:", txtFecha);
        agregarCampo(formulario, "Hora:", txtHora);
        agregarCampo(formulario, "Motivo:", txtMotivo);
        agregarCampo(formulario, "Estado:", cboEstado);
        formulario.add(btnAgregar);

        modeloCitas = new DefaultTableModel();
        modeloCitas.addColumn("Codigo");
        modeloCitas.addColumn("Paciente");
        modeloCitas.addColumn("Medico");
        modeloCitas.addColumn("Fecha");
        modeloCitas.addColumn("Hora");
        modeloCitas.addColumn("Estado");

        JTable tabla = new JTable(modeloCitas);
        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> {
            Paciente paciente = sistema.getControladorPaciente().buscarPorCodigo(txtPaciente.getText());
            Medico medico = sistema.getControladorMedico().buscarPorCodigo(txtMedico.getText());

            if (paciente == null || medico == null) {
                JOptionPane.showMessageDialog(this, "Paciente o medico no encontrado.");
                return;
            }

            Cita cita = new Cita(txtCodigo.getText(), paciente, medico, txtFecha.getText(),
                    txtHora.getText(), txtMotivo.getText(), cboEstado.getSelectedItem().toString());
            sistema.getControladorCita().agregar(cita);
            refrescarCitas();
            limpiar(txtCodigo, txtPaciente, txtMedico, txtFecha, txtHora, txtMotivo);
        });

        refrescarCitas();
        return panel;
    }

    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formulario = new JPanel(new GridLayout(8, 1, 5, 5));

        JTextField txtCodigo = new JTextField();
        JTextField txtPaciente = new JTextField();
        JTextField txtMedico = new JTextField();
        JTextField txtFecha = new JTextField();
        JTextField txtDiagnostico = new JTextField();
        JTextField txtTratamiento = new JTextField();
        JTextField txtObservaciones = new JTextField();
        JButton btnAgregar = new JButton("Registrar historial");

        agregarCampo(formulario, "Codigo:", txtCodigo);
        agregarCampo(formulario, "Cod. paciente:", txtPaciente);
        agregarCampo(formulario, "Cod. medico:", txtMedico);
        agregarCampo(formulario, "Fecha:", txtFecha);
        agregarCampo(formulario, "Diagnostico:", txtDiagnostico);
        agregarCampo(formulario, "Tratamiento:", txtTratamiento);
        agregarCampo(formulario, "Observaciones:", txtObservaciones);
        formulario.add(btnAgregar);

        modeloHistoriales = new DefaultTableModel();
        modeloHistoriales.addColumn("Codigo");
        modeloHistoriales.addColumn("Paciente");
        modeloHistoriales.addColumn("Medico");
        modeloHistoriales.addColumn("Fecha");
        modeloHistoriales.addColumn("Diagnostico");

        JTable tabla = new JTable(modeloHistoriales);
        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> {
            Paciente paciente = sistema.getControladorPaciente().buscarPorCodigo(txtPaciente.getText());
            Medico medico = sistema.getControladorMedico().buscarPorCodigo(txtMedico.getText());

            if (paciente == null || medico == null) {
                JOptionPane.showMessageDialog(this, "Paciente o medico no encontrado.");
                return;
            }

            HistorialClinico historial = new HistorialClinico(txtCodigo.getText(),
                    paciente, medico, txtFecha.getText(), txtDiagnostico.getText(),
                    txtTratamiento.getText(), txtObservaciones.getText());
            sistema.getControladorHistorial().agregar(historial);
            refrescarHistoriales();
            limpiar(txtCodigo, txtPaciente, txtMedico, txtFecha,
                    txtDiagnostico, txtTratamiento, txtObservaciones);
        });

        refrescarHistoriales();
        return panel;
    }

    private void agregarCampo(JPanel panel, String texto, JTextField campo) {
        agregarCampo(panel, texto, (JComponent) campo);
    }

    private void agregarCampo(JPanel panel, String texto, JComponent campo) {
        JPanel fila = new JPanel(new BorderLayout(8, 0));
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setPreferredSize(new Dimension(120, 24));
        fila.add(etiqueta, BorderLayout.WEST);
        fila.add(campo, BorderLayout.CENTER);
        panel.add(fila);
    }

    private void limpiar(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    private void refrescarPacientes() {
        if (modeloPacientes == null) {
            return;
        }
        modeloPacientes.setRowCount(0);
        for (Paciente paciente : sistema.getControladorPaciente().listar()) {
            modeloPacientes.addRow(new Object[]{
                paciente.getCodigoPaciente(),
                paciente.getNombreCompleto(),
                paciente.getEdad(),
                paciente.getGenero(),
                paciente.getTipoSangre()
            });
        }
    }

    private void refrescarMedicos() {
        if (modeloMedicos == null) {
            return;
        }
        modeloMedicos.setRowCount(0);
        for (Medico medico : sistema.getControladorMedico().listar()) {
            modeloMedicos.addRow(new Object[]{
                medico.getCodigoMedico(),
                medico.getNombreCompleto(),
                medico.getEspecialidad(),
                medico.getCmp(),
                medico.getHorario()
            });
        }
    }

    private void refrescarCitas() {
        if (modeloCitas == null) {
            return;
        }
        modeloCitas.setRowCount(0);
        for (Cita cita : sistema.getControladorCita().listar()) {
            modeloCitas.addRow(new Object[]{
                cita.getCodigoCita(),
                cita.getPaciente().getNombreCompleto(),
                cita.getMedico().getNombreCompleto(),
                cita.getFecha(),
                cita.getHora(),
                cita.getEstado()
            });
        }
    }

    private void refrescarHistoriales() {
        if (modeloHistoriales == null) {
            return;
        }
        modeloHistoriales.setRowCount(0);
        for (HistorialClinico historial : sistema.getControladorHistorial().listar()) {
            modeloHistoriales.addRow(new Object[]{
                historial.getCodigoHistorial(),
                historial.getPaciente().getNombreCompleto(),
                historial.getMedico().getNombreCompleto(),
                historial.getFechaRegistro(),
                historial.getDiagnostico()
            });
        }
    }

}
