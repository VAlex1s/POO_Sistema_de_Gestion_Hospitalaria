package vista;

import controlador.SistemaHospitalario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
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
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo aplicar el tema Nimbus.");
        }

        this.sistema = sistema;

        if (!mostrarLogin()) {
            System.exit(0);
        }

        setTitle("Sistema de Gestión Hospitalaria");
        setSize(980, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crearMenu();
    }

    private boolean mostrarLogin() {
        JPanel panelLogin = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField txtUsuario = new JTextField();
        JPasswordField txtClave = new JPasswordField();

        panelLogin.add(new JLabel("Usuario:"));
        panelLogin.add(txtUsuario);
        panelLogin.add(new JLabel("Contraseña:"));
        panelLogin.add(txtClave);

        while (true) {
            int opcion = JOptionPane.showConfirmDialog(null, panelLogin, 
                    "Acceso al Sistema", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (opcion == JOptionPane.OK_OPTION) {
                String usuario = txtUsuario.getText();
                String clave = new String(txtClave.getPassword());

                if (usuario.equals("admin") && clave.equals("1234")) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", 
                            "Error de Acceso", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                return false;
            }
        }
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
        formulario.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JTextField txtCodigo = new JTextField();
        JTextField txtDni = new JTextField();
        JTextField txtNombres = new JTextField();
        JTextField txtApellidos = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtCorreo = new JTextField();
        JTextField txtDireccion = new JTextField();
        JTextField txtEdad = new JTextField();
        JComboBox<String> cboGenero = new JComboBox<>(new String[]{"Seleccione", "Masculino", "Femenino", "Otro"});
        JComboBox<String> cboSangre = new JComboBox<>(new String[]{"Seleccione", "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"});
        JTextField txtAlergias = new JTextField();
        JButton btnAgregar = new JButton("Agregar paciente");

        txtDni.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || txtDni.getText().length() >= 8) e.consume();
            }
        });

        txtTelefono.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || txtTelefono.getText().length() >= 9) e.consume();
            }
        });

        txtEdad.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || txtEdad.getText().length() >= 3) e.consume();
            }
        });

        agregarCampo(formulario, "Codigo:", txtCodigo);
        agregarCampo(formulario, "DNI:", txtDni);
        agregarCampo(formulario, "Nombres:", txtNombres);
        agregarCampo(formulario, "Apellidos:", txtApellidos);
        agregarCampo(formulario, "Telefono:", txtTelefono);
        agregarCampo(formulario, "Correo:", txtCorreo);
        agregarCampo(formulario, "Direccion:", txtDireccion);
        agregarCampo(formulario, "Edad:", txtEdad);
        agregarCampo(formulario, "Genero:", cboGenero);
        agregarCampo(formulario, "Tipo sangre:", cboSangre);
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
            String cod = txtCodigo.getText().trim();
            String dni = txtDni.getText().trim();
            String nom = txtNombres.getText().trim();
            String ape = txtApellidos.getText().trim();
            String tel = txtTelefono.getText().trim();
            String cor = txtCorreo.getText().trim();
            String dir = txtDireccion.getText().trim();
            String ed = txtEdad.getText().trim();
            String gen = cboGenero.getSelectedItem().toString();
            String san = cboSangre.getSelectedItem().toString();
            String ale = txtAlergias.getText().trim();

            if (gen.equals("Seleccione") || san.equals("Seleccione")) {
                JOptionPane.showMessageDialog(this, "Error: Debe seleccionar un género y un tipo de sangre.");
                return;
            }

            if (!camposEstanLlenos(cod, dni, nom, ape, tel, cor, dir, ed, ale)) {
                JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios.");
                return;
            }

            if (sistema.getControladorPaciente().buscarPorCodigo(cod) != null) {
                JOptionPane.showMessageDialog(this, "Error: Ya existe un paciente con el código " + cod);
                return;
            }

            if (!tel.matches("9\\d{8}")) {
                JOptionPane.showMessageDialog(this, "Error: El teléfono debe empezar con 9.");
                return;
            }

            if (!cor.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(this, "Error: Formato de correo inválido.");
                return;
            }

            try {
                int edadAComprobar = Integer.parseInt(ed);
                Paciente paciente = new Paciente(cod, dni, nom, ape, tel, cor, dir, edadAComprobar, gen, san, ale);
                sistema.getControladorPaciente().agregar(paciente);
                refrescarPacientes();
                limpiar(txtCodigo, txtDni, txtNombres, txtApellidos, txtTelefono, txtCorreo, txtDireccion, txtEdad, txtAlergias);
                cboGenero.setSelectedIndex(0);
                cboSangre.setSelectedIndex(0);
                JOptionPane.showMessageDialog(this, "Paciente registrado exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar el paciente.");
            }
        });

        refrescarPacientes();
        return panel;
    }

    private JPanel crearPanelMedicos() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formulario = new JPanel(new GridLayout(11, 1, 5, 5));
        formulario.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

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

        txtDni.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || txtDni.getText().length() >= 8) e.consume();
            }
        });

        txtTelefono.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || txtTelefono.getText().length() >= 9) e.consume();
            }
        });

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
            String cod = txtCodigo.getText().trim();
            String dni = txtDni.getText().trim();
            String nom = txtNombres.getText().trim();
            String ape = txtApellidos.getText().trim();
            String tel = txtTelefono.getText().trim();
            String cor = txtCorreo.getText().trim();
            String dir = txtDireccion.getText().trim();
            String esp = txtEspecialidad.getText().trim();
            String cmp = txtCmp.getText().trim();
            String hor = txtHorario.getText().trim();

            if (!camposEstanLlenos(cod, dni, nom, ape, tel, cor, dir, esp, cmp, hor)) {
                JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios.");
                return;
            }

            if (sistema.getControladorMedico().buscarPorCodigo(cod) != null) {
                JOptionPane.showMessageDialog(this, "Error: Ya existe un médico con el código " + cod);
                return;
            }

            if (!tel.matches("9\\d{8}")) {
                JOptionPane.showMessageDialog(this, "Error: El teléfono debe empezar con 9.");
                return;
            }

            if (!cor.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(this, "Error: Formato de correo inválido.");
                return;
            }

            Medico medico = new Medico(cod, dni, nom, ape, tel, cor, dir, esp, cmp, hor);
            sistema.getControladorMedico().agregar(medico);
            refrescarMedicos();
            limpiar(txtCodigo, txtDni, txtNombres, txtApellidos, txtTelefono, txtCorreo, txtDireccion, txtEspecialidad, txtCmp, txtHorario);
            JOptionPane.showMessageDialog(this, "Médico registrado exitosamente.");
        });

        refrescarMedicos();
        return panel;
    }

    private JPanel crearPanelCitas() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formulario = new JPanel(new GridLayout(8, 1, 5, 5));
        formulario.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JTextField txtCodigo = new JTextField();
        JTextField txtPaciente = new JTextField();
        JTextField txtMedico = new JTextField();
        JTextField txtFecha = new JTextField();
        JTextField txtHora = new JTextField();
        JTextField txtMotivo = new JTextField();
        JComboBox<String> cboEstado = new JComboBox<>(new String[]{"Pendiente", "Atendida", "Cancelada"});
        JButton btnAgregar = new JButton("Registrar cita");

        agregarCampo(formulario, "Codigo:", txtCodigo);
        agregarCampo(formulario, "Cod. paciente:", txtPaciente);
        agregarCampo(formulario, "Cod. medico:", txtMedico);
        agregarCampo(formulario, "Fecha (DD/MM/AAAA):", txtFecha);
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
            if (!camposEstanLlenos(txtCodigo.getText(), txtPaciente.getText(), txtMedico.getText(), txtFecha.getText(), txtHora.getText(), txtMotivo.getText())) {
                JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios.");
                return;
            }

            Paciente paciente = sistema.getControladorPaciente().buscarPorCodigo(txtPaciente.getText().trim());
            Medico medico = sistema.getControladorMedico().buscarPorCodigo(txtMedico.getText().trim());

            if (paciente == null || medico == null) {
                JOptionPane.showMessageDialog(this, "Paciente o medico no encontrado.");
                return;
            }

            Cita cita = new Cita(txtCodigo.getText().trim(), paciente, medico, txtFecha.getText().trim(),
                    txtHora.getText().trim(), txtMotivo.getText().trim(), cboEstado.getSelectedItem().toString());
            sistema.getControladorCita().agregar(cita);
            refrescarCitas();
            limpiar(txtCodigo, txtPaciente, txtMedico, txtFecha, txtHora, txtMotivo);
            cboEstado.setSelectedIndex(0);
            JOptionPane.showMessageDialog(this, "Cita registrada exitosamente.");
        });

        refrescarCitas();
        return panel;
    }

    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formulario = new JPanel(new GridLayout(8, 1, 5, 5));
        formulario.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

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
            if (!camposEstanLlenos(txtCodigo.getText(), txtPaciente.getText(), txtMedico.getText(), txtFecha.getText(), txtDiagnostico.getText(), txtTratamiento.getText(), txtObservaciones.getText())) {
                JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios.");
                return;
            }

            Paciente paciente = sistema.getControladorPaciente().buscarPorCodigo(txtPaciente.getText().trim());
            Medico medico = sistema.getControladorMedico().buscarPorCodigo(txtMedico.getText().trim());

            if (paciente == null || medico == null) {
                JOptionPane.showMessageDialog(this, "Paciente o medico no encontrado.");
                return;
            }

            HistorialClinico historial = new HistorialClinico(txtCodigo.getText().trim(),
                    paciente, medico, txtFecha.getText().trim(), txtDiagnostico.getText().trim(),
                    txtTratamiento.getText().trim(), txtObservaciones.getText().trim());
            sistema.getControladorHistorial().agregar(historial);
            refrescarHistoriales();
            limpiar(txtCodigo, txtPaciente, txtMedico, txtFecha, txtDiagnostico, txtTratamiento, txtObservaciones);
            JOptionPane.showMessageDialog(this, "Historial registrado exitosamente.");
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
        etiqueta.setPreferredSize(new Dimension(140, 24));
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
        if (modeloPacientes == null) return;
        modeloPacientes.setRowCount(0);
        for (Paciente paciente : sistema.getControladorPaciente().listar()) {
            modeloPacientes.addRow(new Object[]{
                paciente.getCodigoPaciente(),
                paciente.getNombres() + " " + paciente.getApellidos(),
                paciente.getEdad(),
                paciente.getGenero(),
                paciente.getTipoSangre()
            });
        }
    }

    private void refrescarMedicos() {
        if (modeloMedicos == null) return;
        modeloMedicos.setRowCount(0);
        for (Medico medico : sistema.getControladorMedico().listar()) {
            modeloMedicos.addRow(new Object[]{
                medico.getCodigoMedico(),
                medico.getNombres() + " " + medico.getApellidos(),
                medico.getEspecialidad(),
                medico.getCmp(),
                medico.getHorario()
            });
        }
    }

    private void refrescarCitas() {
        if (modeloCitas == null) return;
        modeloCitas.setRowCount(0);
        for (Cita cita : sistema.getControladorCita().listar()) {
            modeloCitas.addRow(new Object[]{
                cita.getCodigoCita(),
                cita.getPaciente().getNombres() + " " + cita.getPaciente().getApellidos(),
                cita.getMedico().getNombres() + " " + cita.getMedico().getApellidos(),
                cita.getFecha(),
                cita.getHora(),
                cita.getEstado()
            });
        }
    }

    private void refrescarHistoriales() {
        if (modeloHistoriales == null) return;
        modeloHistoriales.setRowCount(0);
        for (HistorialClinico historial : sistema.getControladorHistorial().listar()) {
            modeloHistoriales.addRow(new Object[]{
                historial.getCodigoHistorial(),
                historial.getPaciente().getNombres() + " " + historial.getPaciente().getApellidos(),
                historial.getMedico().getNombres() + " " + historial.getMedico().getApellidos(),
                historial.getFechaRegistro(),
                historial.getDiagnostico()
            });
        }
    }

    private boolean camposEstanLlenos(String... valores) {
        for (String valor : valores) {
            if (valor == null || valor.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
