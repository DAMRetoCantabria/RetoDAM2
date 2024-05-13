package reto.ventanas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import reto.utilidades.*;
import reto.sql.*;
import reto.objects.*;
import reto.components.*;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

/**
 * Esta clase representa la ventana de gestión de datos.
 * Proporciona una interfaz gráfica para cargar y visualizar datos de
 * profesores.
 */
public class Gestionar extends JPanel {
    private JTable table;
    private SolicitudDAO solicitudSQL = new SolicitudDAO();
    private JTextField id_text;
    private JTextField departamento_text;
    private JRadioButton tipo1;
    private JRadioButton tipo2;
    private JTextField solicitante_text;
    private JToggleButton prevista_toggle;
    private JTextField titulo_text;
    private JTextField textoFechaIni;
    private JTextArea transporte_medios;
    private JTextArea transporte_coment;
    private JTextField textoFechaFin;
    private JRadioButton alojamiento_si;
    private JRadioButton alojamiento_no;
    private JTextArea alojamiento_coment;
    private JTextArea responsables_text;
    private JTextArea participantes_text;
    private JTextArea comentario_text;
    private JRadioButton cursos_boton;
    private JRadioButton grupos_boton;
    private JTextArea alumnos_text;
    private JTextField num_alumnos;
    private JButton aceptar = new JButton("Aceptar");
    private JButton cancelar = new JButton("Cancelar");
    private JTextArea comentario_estado_text;

    /**
     * Constructor de la clase Gestionar.
     * Inicializa la ventana y llama al método init() para configurar los
     * componentes.
     */
    public Gestionar() {
        init();
    }

    /**
     * Método privado que configura los componentes de la ventana.
     * Utiliza el administrador de diseño MigLayout para organizar los componentes
     * en la ventana.
     */
    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "[fill, 350]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");

        JLabel titulo = new JLabel("Carga de Datos de Profesores");
        titulo.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");

        panel.add(titulo, "span, align 50% 50%, wrap");
        panel.add(creaLista(), "split 2");
        panel.add(creaVisor(), "wrap");
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(introAceptada());

        add(panel);
    }

    /**
     * Crea el panel que contiene la lista de solicitudes.
     * 
     * @return El componente JPanel que contiene la lista de solicitudes.
     */
    private Component creaLista() {
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 5 5 0 5", "[fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        panel.add(crearTabla(), "dock north, wrap");

        return panel;
    }

    /**
     * Crea el panel que muestra los detalles de una solicitud seleccionada.
     * 
     * @return El componente JPanel que muestra los detalles de la solicitud.
     */
    private Component creaVisor() {
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 5 5 0 5", "[fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 10%)");
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        panel.add(cabecera());
        panel.add(cuerpo());
        return panel;
    }

    /**
     * Crea la tabla que muestra la lista de solicitudes.
     * 
     * @return El componente JPanel que contiene la tabla de solicitudes.
     */
    private Component crearTabla() {
        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");

        table = new JTable();
        table.setDefaultRenderer(Object.class, new PerfilTabla());

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int viewRow = table.getSelectedRow();
                    if (viewRow < 0) {

                    } else {
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        int id = Integer.parseInt(model.getValueAt(viewRow, 0).toString());
                        cargaSolicitud(id);
                    }
                }
            }
        });

        actualizarTabla();

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, "width 530!, height 580!");

        return panel;
    }

    /**
     * Actualiza la tabla de solicitudes con los datos de la base de datos.
     */
    private void actualizarTabla() {
        DefaultTableModel model;

        model = new DefaultTableModel(new Object[][] {},
                new String[] { "Id", "Solicitante", "Titulo", "Tipo", "Fecha", "Prevista", "Transporte",
                        "Alojamiento" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        table.setModel(model);

        TableRowSorter<TableModel> organizador = new TableRowSorter<>(table.getModel());
        table.setRowSorter(organizador);

        organizador.setComparator(0, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        datosSolicitadas();
    }

    /**
     * Obtiene los datos de las solicitudes de la base de datos y los muestra en la
     * tabla.
     */
    public void datosSolicitadas() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Solicitud> solicitudes = solicitudSQL.listar();
        for (Solicitud solicitud : solicitudes) {
            if (String.valueOf(solicitud.getEstado()).equalsIgnoreCase("solicitada")) {
                String fini = solicitud.getFini().toString().concat(" ").concat(solicitud.getHini().toString());
                String ffin = solicitud.getFfin().toString().concat(" ").concat(solicitud.getHfin().toString());
                String fecha = fini.concat(" / ").concat(ffin);
                model.addRow(new Object[] {
                        solicitud.getId_solicitud(),
                        solicitud.get_solicitante().getNombre().concat(" ")
                                .concat(solicitud.get_solicitante().getApellidos()),
                        solicitud.getTitulo(),
                        solicitud.getTipo().toString(),
                        fecha,
                        solicitud.isPrevisto() ? "Si" : "No",
                        solicitud.isTransp_requerido() ? "Si" : "No",
                        solicitud.isAloj_requerido() ? "Si" : "No",
                });
            }
        }
    }

    /**
     * Carga los datos de una solicitud seleccionada en los campos correspondientes.
     * 
     * @param id El ID de la solicitud seleccionada.
     */
    private void cargaSolicitud(int id) {
        responsables_text.setText("");
        participantes_text.setText("");
        alumnos_text.setText("");
        transporte_medios.setText("");
        id_text.setText("");
        departamento_text.setText("");
        tipo1.setSelected(false);
        tipo2.setSelected(false);
        solicitante_text.setText("");
        prevista_toggle.setSelected(false);
        titulo_text.setText("");
        textoFechaIni.setText("");
        textoFechaFin.setText("");
        transporte_coment.setText("");
        alojamiento_si.setSelected(false);
        alojamiento_no.setSelected(false);
        alojamiento_coment.setText("");
        num_alumnos.setText("");
        comentario_text.setText("");

        if (id != -1) {
            SolicitudDAO solicitudSQL = new SolicitudDAO();
            DepartamentoDAO departamentoSQL = new DepartamentoDAO();
            Solicitud solicitud = solicitudSQL.buscar(id);

            id_text.setText(String.valueOf(solicitud.getId_solicitud()));
            departamento_text.setText(departamentoSQL.buscar(solicitud.getDepartamento()).getCodigo());
            if (solicitud.getTipo().equals("Extraescolar")) {
                tipo1.setSelected(true);
            } else {
                tipo2.setSelected(true);
            }
            solicitante_text.setText(String.valueOf(solicitud.getSolicitante()));
            prevista_toggle.setSelected(solicitud.isPrevisto());
            titulo_text.setText(solicitud.getTitulo());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
            textoFechaIni
                    .setText(
                            solicitud.getHini().toString().concat(" / ").concat(solicitud.getFini().format(formatter)));
            textoFechaFin
                    .setText(
                            solicitud.getHfin().toString().concat(" / ").concat(solicitud.getFfin().format(formatter)));

            solicitudSQL.buscaTransporte(id).forEach(transporte -> {
                transporte_medios.append(transporte.getNombre() + ", ");
            });

            transporte_coment.setText(solicitud.getTransp_comentario() == null ? "" : solicitud.getTransp_comentario());

            if (solicitud.isAloj_requerido()) {
                alojamiento_si.setSelected(true);
            } else {
                alojamiento_no.setSelected(true);
            }
            alojamiento_coment.setText(solicitud.getAloj_comentario() == null ? "" : solicitud.getAloj_comentario());

            solicitudSQL.buscaResponsable(id).forEach(responsable -> {
                responsables_text.append(responsable.getNombre().concat(" ")
                        .concat(responsable.getApellidos()) + "\n");
            });

            solicitudSQL.buscaParticipantes(id).forEach(participante -> {
                participantes_text.append(participante.getNombre().concat(" ")
                        .concat(participante.getApellidos()) + "\n");
            });

            num_alumnos.setText(String.valueOf(solicitud.getNumAlumnos()));

            if (solicitudSQL.buscaCursos(id).size() > 0) {
                cursos_boton.setSelected(true);
                solicitudSQL.buscaCursos(id).forEach(curso -> {
                    alumnos_text.append(curso.getCodigo() + "\n");
                });
            } else {
                grupos_boton.setSelected(true);
                solicitudSQL.buscaGrupos(id).forEach(grupo -> {
                    alumnos_text.append(grupo.getCodigo() + "\n");
                });
            }

            comentario_text.setText(solicitud.getComentario() == null ? "" : solicitud.getComentario());
        }
    }

    /**
     * Este método crea un panel de cabecera con campos de información de la
     * solicitud.
     * Los campos incluyen Id, Departamento, Tipo, Solicitante y si está prevista en
     * el currículo.
     * Todos los campos son no editables.
     * 
     * @return Componente que representa el panel de cabecera.
     */
    private Component cabecera() {
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 5 5 0 5", "[fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");

        JLabel id = new JLabel("Id: ");
        JLabel departamento = new JLabel("Dept.: ");
        JLabel tipo = new JLabel("Tipo: ");
        JLabel solicitante = new JLabel("Solicitante: ");
        JLabel prevista = new JLabel("Prevista en el curriculo?: ");
        id_text = new JTextField();
        id_text.setEditable(false);
        departamento_text = new JTextField();
        departamento_text.setEditable(false);
        tipo1 = new JRadioButton("Extraescolar");
        tipo1.setEnabled(false);
        tipo2 = new JRadioButton("Complementaria");
        tipo2.setEnabled(false);
        ButtonGroup group = new ButtonGroup();
        group.add(tipo1);
        group.add(tipo2);
        solicitante_text = new JTextField();
        solicitante_text.setEditable(false);
        prevista_toggle = new JToggleButton();
        prevista_toggle.setEnabled(false);

        prevista_toggle.setIcon(new ImageIcon(getClass().getResource("/icons/nop.png")));
        prevista_toggle.setSelectedIcon(new ImageIcon(getClass().getResource("/icons/yep.svg")));

        panel.add(id, "split 7");
        panel.add(id_text, "gapleft 10, gapright 15, width 45!");
        panel.add(departamento);
        panel.add(departamento_text, "gapleft 10, gapright 15, width 45!");
        panel.add(tipo);
        panel.add(tipo1, "gapleft 25");
        panel.add(tipo2, "gapleft 25, wrap");

        panel.add(solicitante, "split 4");
        panel.add(solicitante_text, "width 260!");
        panel.add(prevista);
        panel.add(prevista_toggle, "width 30!");
        panel.add(new JSeparator(), "span, gapy 5 5, wrap");
        return panel;
    }

    /**
     * Este método crea un panel de cuerpo con campos de información detallada de la
     * solicitud.
     * Los campos incluyen Título, Fechas de inicio y fin, Medios de transporte,
     * Responsables, Participantes y Comentarios.
     * Todos los campos son no editables.
     * 
     * @return Componente que representa el panel de cuerpo.
     */
    private Component cuerpo() {
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 5 5 0 5", "[fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");

        JLabel titulo = new JLabel("Titulo: ");
        JLabel fecha_com = new JLabel("Inicio ");
        JLabel transporte = new JLabel("Medios de Transporte: ");
        JLabel fecha_fin = new JLabel("Fin ");
        JLabel responsables = new JLabel("Responsables: ");
        JLabel participantes = new JLabel("Participantes: ");
        JLabel comentario = new JLabel("Comentarios: ");
        titulo_text = new JTextField();
        titulo_text.setEditable(false);
        textoFechaIni = new JFormattedTextField();
        textoFechaIni.setEditable(false);
        transporte_medios = new JTextArea();
        transporte_medios.setEditable(false);
        transporte_coment = new JTextArea();
        transporte_coment.setEditable(false);
        textoFechaFin = new JFormattedTextField();
        textoFechaFin.setEditable(false);
        responsables_text = new JTextArea();
        responsables_text.setEditable(false);
        participantes_text = new JTextArea();
        participantes_text.setEditable(false);
        comentario_text = new JTextArea();
        comentario_text.setEditable(false);

        panel.add(titulo, "split 4");
        panel.add(titulo_text, "width 300!");
        panel.add(fecha_com);
        panel.add(textoFechaIni, "width 110!, wrap");
        panel.add(transporte, "split 4");
        panel.add(new JScrollPane(transporte_medios), "width 300!");
        panel.add(fecha_fin);
        panel.add(textoFechaFin, "width 110!, wrap");
        panel.add(new JScrollPane(transporte_coment), "height 60!");
        panel.add(introAlojamiento());
        panel.add(participantes, "split 2");
        panel.add(responsables, "wrap");
        panel.add(new JScrollPane(participantes_text), "width 300!, height 90!, split 2");
        panel.add(new JScrollPane(responsables_text), "width 300!, height 90!");
        panel.add(introAlumnos());
        panel.add(comentario, "split 2");
        panel.add(new JScrollPane(comentario_text), "gapleft 60, height 90!, width 100%");

        return panel;
    }

    /**
     * Este método crea un panel de alojamiento con campos de información sobre si
     * se requiere alojamiento y comentarios.
     * Todos los campos son no editables.
     * 
     * @return Componente que representa el panel de alojamiento.
     */
    private Component introAlojamiento() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel alojamiento = new JLabel("¿Se requiere alojamiento?");
        alojamiento_si = new JRadioButton("Si");
        alojamiento_si.setEnabled(false);
        alojamiento_no = new JRadioButton("No");
        alojamiento_no.setEnabled(false);
        alojamiento_coment = new JTextArea();
        alojamiento_coment.setEditable(false);
        ButtonGroup group = new ButtonGroup();
        group.add(alojamiento_si);
        group.add(alojamiento_no);
        panel.add(alojamiento, "span 1 2");
        panel.add(alojamiento_si, "gapleft 20");
        panel.add(alojamiento_coment, "span 1 2, width 100%, height 60!, wrap");
        panel.add(alojamiento_no, "gapleft 20, wrap");

        return panel;
    }

    /**
     * Este método crea un panel de alumnos con campos de información sobre los
     * grupos/cursos participantes y el número de alumnos.
     * Todos los campos son no editables.
     * 
     * @return Componente que representa el panel de alumnos.
     */
    private Component introAlumnos() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel alumnos = new JLabel("Grupos/Cursos participantes: ");
        cursos_boton = new JRadioButton("Cursos");
        cursos_boton.setEnabled(false);
        grupos_boton = new JRadioButton("Grupos");
        grupos_boton.setEnabled(false);
        alumnos_text = new JTextArea();
        alumnos_text.setEditable(false);
        num_alumnos = new JTextField();
        num_alumnos.setEditable(false);
        ButtonGroup group = new ButtonGroup();
        group.add(cursos_boton);
        group.add(grupos_boton);

        panel.add(alumnos);
        panel.add(grupos_boton, "gapleft 20, gapy 10 10");
        panel.add(new JScrollPane(alumnos_text), "span 1 2, width 100%, height 90!, wrap");
        panel.add(num_alumnos, "split 2");
        panel.add(new JLabel("alumnos"));
        panel.add(cursos_boton, "gapleft 20, gapy 10 10, wrap");

        return panel;
    }

    /**
     * Este método crea un panel de aceptación con campos de comentarios del
     * administrador y botones para aceptar o denegar la solicitud.
     * 
     * @return Componente que representa el panel de aceptación.
     */
    private Component introAceptada() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");

        JLabel comentario = new JLabel("Comentarios de administrador: ");
        comentario_estado_text = new JTextArea();
        setDocumentfilter(comentario_estado_text, 100);

        aceptar = new JButton("Aceptar");
        aceptar.setPreferredSize(new Dimension(50, 50));
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int aceptar = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que quieres aceptar esta solicitud?", "Aceptar solicitud",
                        JOptionPane.YES_NO_OPTION);
                if (aceptar == JOptionPane.YES_OPTION) {
                    ProgramadasDAO programadasSQL = new ProgramadasDAO();
                    Solicitud solicitud = solicitudSQL.buscar(Integer.parseInt(id_text.getText()));
                    Programadas programada = new Programadas(solicitudSQL.buscar(solicitud.getId_solicitud()),
                            solicitud.get_solicitante(),
                            solicitud.getTitulo(), solicitud.getTipo(), solicitud.getFini(), solicitud.getFfin(),
                            solicitud.getHini(), solicitud.getHfin(), solicitud.isPrevisto(),
                            solicitud.isTransp_requerido(),
                            solicitud.getTransp_comentario(), solicitud.isAloj_requerido(),
                            solicitud.getAloj_comentario(),
                            solicitud.getComentario(), EstadoActividad.Aprobada, comentario_estado_text.getText(),
                            "", 0, solicitud.getTransportes(), solicitud.getResponsables(),
                            solicitud.getParticipantes(), solicitud.getCursos(), solicitud.getGrupos());
                    int n = programadasSQL.guardar(programada);
                    for (Curso cursos : solicitudSQL.buscaCursos(solicitud.getId_solicitud())) {
                        programadasSQL.guardarCurso(n, cursos.getId());
                    }
                    for (Grupo grupos : solicitudSQL.buscaGrupos(solicitud.getId_solicitud())) {
                        programadasSQL.guardarGrupo(n, grupos.getId());
                    }
                    for (MediosTransporte transporte : solicitud.getTransportes()) {
                        programadasSQL.guardarTransporte(n, transporte.getId());
                    }
                    for (Profesor responsable : solicitud.getResponsables()) {
                        programadasSQL.guardarResponsable(n, responsable.getId());
                    }
                    for (Profesor participante : solicitud.getParticipantes()) {
                        programadasSQL.guardarParticipante(n, participante.getId());
                    }
                    solicitud.setEstado(EstadoActividad.Aprobada);
                    solicitud.setEstado_comentario(comentario_estado_text.getText());
                    solicitudSQL.guardar(solicitud);
                    JOptionPane.showMessageDialog(null, "Se ha aceptado la solicitud.");
                    table.clearSelection();
                    cargaSolicitud(-1);
                    actualizarTabla();
                }
            }
        });
        cancelar = new JButton("Denegar");
        cancelar.setPreferredSize(new Dimension(50, 50));
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int denegar = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que quieres denegar esta solicitud?", "Denegar solicitud",
                        JOptionPane.YES_NO_OPTION);
                if (denegar == JOptionPane.YES_OPTION) {
                    Solicitud solicitud = solicitudSQL.buscar(Integer.parseInt(id_text.getText()));
                    solicitud.setEstado(EstadoActividad.Denegada);
                    solicitudSQL.guardar(solicitud);
                    JOptionPane.showMessageDialog(null, "Se ha denegado la solicitud.");
                    table.clearSelection();
                    cargaSolicitud(-1);
                    actualizarTabla();
                }
            }
        });

        panel.add(comentario, "wrap");
        panel.add(new JScrollPane(comentario_estado_text), "height 90!, width 100%, wrap");

        panel.add(aceptar, "width 100%, split 2");
        panel.add(cancelar, "width 100%, wrap");

        return panel;
    }

    /**
     * Este método establece un filtro de documento en un campo de texto para
     * limitar el número de caracteres que se pueden introducir.
     * 
     * @param campo      Campo de texto al que se aplica el filtro.
     * @param caracteres Número máximo de caracteres permitidos.
     */
    private void setDocumentfilter(JTextArea campo, int caracteres) {
        DocumentFilter filter = Utils.limitaCaracteres(caracteres);
        ((PlainDocument) campo.getDocument()).setDocumentFilter(filter);
    }

}
