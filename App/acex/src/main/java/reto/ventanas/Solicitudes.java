package reto.ventanas;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DatePicker;
import raven.datetime.component.time.TimePicker;
import reto.components.Multiseleccion;
import reto.objects.*;
import reto.sql.CursosDAO;
import reto.sql.DepartamentoDAO;
import reto.sql.GruposDAO;
import reto.sql.ProfesorDAO;
import reto.sql.ProgramadasDAO;
import reto.sql.SolicitudDAO;
import reto.sql.TransporteDAO;
import reto.utilidades.*;
import reto.components.VentanaSingleton;

public class Solicitudes extends JPanel {

    private JLabel departamento;
    private JComboBox<String> departamento_desp;
    private JLabel titulo;
    private JTextField titulo_field;
    private JLabel prevista;
    private JToggleButton prevista_toggle;
    private JLabel transporte;
    private Multiseleccion<String> transporte_desp;
    private JLabel transporte_disclaimer;
    private JLabel hora;
    private JLabel hora_com;
    private JLabel hora_fin;
    private JFormattedTextField textoHoraIni;
    private JFormattedTextField textoHoraFin;
    private TimePicker selectHora1;
    private TimePicker selectHora2;
    private JLabel fecha;
    private JLabel fecha_com;
    private JLabel fecha_fin;
    private JFormattedTextField textoFechaIni;
    private JFormattedTextField textoFechaFin;
    private DatePicker selectFecha1;
    private DatePicker selectFecha2;
    private JLabel alumnos;
    private JRadioButton cursos_boton;
    private JRadioButton grupos_boton;
    private Multiseleccion<String> alumnos_desp;
    private JLabel alojamiento;
    private JRadioButton alojamiento_si;
    private JRadioButton alojamiento_no;
    private JLabel responsables;
    private Multiseleccion<String> responsables_desp;
    private JLabel participantes;
    private Multiseleccion<String> participantes_desp;
    private JRadioButton tipo1_boton;
    private JRadioButton tipo2_boton;
    private JLabel tipo;
    private JTextArea observaciones_trans;
    private JTextArea observaciones;
    private JTextArea observaciones_aloj;
    private int estado;
    private Solicitud solicitud;
    private Programadas programada;

    public Solicitudes(int estado, Solicitud solicitud) {
        this.estado = estado;
        this.solicitud = solicitud;
        init();
    }

    public Solicitudes(int estado, Programadas programada) {
        this.estado = estado;
        this.programada = programada;
        System.out.println(programada);
        init();
    }

    public Solicitudes(int estado) {
        this.estado = estado;
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "[fill, 350]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");

        JLabel titulo = new JLabel("Creacion de solicitudes");
        titulo.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");

        JButton aceptar = new JButton("Enviar Solicitud");
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAceptarClicked();
            }
        });

        panel.add(titulo, "gapleft 140, align center, gapbottom 20");
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(introDepartamento());
        panel.add(introTitulo());
        panel.add(IntroPrevista());
        panel.add(introHoras());
        panel.add(introFechas());
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(introResponsables());
        panel.add(introParticipantes());
        panel.add(introAlumnos());
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(introTransporte());
        if (estado == 3) {
            panel.add(introProgramadas());
        }
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(introAlojamiento());
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(introObservaciones());
        panel.add(new JSeparator(), "gapy 5 5, wrap");
        panel.add(aceptar, "right, width 200!, height 50!");

        add(panel);
    }

    private Component introDepartamento() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        departamento = new JLabel("Departamento: ");
        departamento_desp = new JComboBox<>();
        DepartamentoDAO departamentosSQL = new DepartamentoDAO();
        ProfesorDAO profesoresSQL = new ProfesorDAO();
        List<Departamento> departamentos = departamentosSQL.listar();
        int index = -1;
        for (int i = 0; i < departamentos.size(); i++) {
            departamento_desp.addItem(departamentos.get(i).getCodigo());
            if (departamentos.get(i).getId() == profesoresSQL.buscar(Login.user.getId()).getDepartamento().getId()
                    && estado == 1) {
                index = i;
            } else if (solicitud != null && programada != null) {
                if (departamentos.get(i).getId() == solicitud.get_solicitante().getDepartamento().getId()
                        && estado == 2) {
                    index = i;
                }
            }
        }
        departamento_desp.setSelectedIndex(index);

        panel.add(departamento);
        panel.add(departamento_desp, "dock east, gapleft 100, gapright 10, align right, width 300!");

        return panel;
    }

    private Component introTitulo() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        titulo = new JLabel("Titulo: ");
        titulo_field = new JTextField();
        setDocumentfilter(titulo_field, 45);
        if (estado == 2) {
            titulo_field.setText(solicitud.getTitulo());
        } else if (estado == 3) {
            titulo_field.setText(programada.getTitulo());
        }
        panel.add(titulo);
        panel.add(titulo_field, "dock east, gapleft 150, gapright 10, align right, width 300!");
        return panel;
    }

    private Component IntroPrevista() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        prevista = new JLabel("Prevista en el curriculo?: ");
        prevista_toggle = new JToggleButton();
        prevista_toggle.setIcon(new ImageIcon(getClass().getResource("/icons/nop.png")));
        prevista_toggle.setSelectedIcon(new ImageIcon(getClass().getResource("/icons/yep.svg")));
        tipo = new JLabel("Tipo de actividad: ");
        tipo1_boton = new JRadioButton("Extraescolar");
        tipo2_boton = new JRadioButton("Complementaria");
        ButtonGroup group = new ButtonGroup();
        group.add(tipo1_boton);
        group.add(tipo2_boton);
        if (estado == 2) {
            if (solicitud.getTipo().equals(TipoActividad.Extraescolar)) {
                tipo1_boton.setSelected(true);
            } else {
                tipo2_boton.setSelected(true);
            }
            prevista_toggle.setSelected(solicitud.isPrevisto());
        } else if (estado == 3) {
            if (programada.getTipo().equals(TipoActividad.Extraescolar)) {
                tipo1_boton.setSelected(true);
            } else {
                tipo2_boton.setSelected(true);
            }
            prevista_toggle.setSelected(programada.isPrevisto());
        }
        panel.add(tipo);
        panel.add(tipo1_boton, "gapleft 40");
        panel.add(tipo2_boton, "gapleft 70, gapy 5, wrap");
        panel.add(prevista);
        panel.add(prevista_toggle, "gapleft 310, span, gapright 10, align right, wrap");
        return panel;
    }

    private Component introTransporte() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        transporte = new JLabel("Medios de Transporte: ");
        transporte_desp = new Multiseleccion<>();
        TransporteDAO transporteSQL = new TransporteDAO();
        List<MediosTransporte> transportes = transporteSQL.listar();
        for (MediosTransporte transporte : transportes) {
            transporte_desp.addItem(transporte.getNombre());
        }
        transporte_desp.clearSelectedItems();
        transporte_disclaimer = new JLabel(
                "<html>Los desplazamientos en autobús deberán ser solicitados con al menos DOS semanas de antelación.<br>"
                        + "La salida se efectuará siempre desde el aparcamiento de la Bolera Municipal al lado del instituto.</html>");
        transporte_disclaimer.setFont(new Font("Arial", Font.PLAIN, 10));

        observaciones_trans = new JTextArea();
        observaciones_trans.setLineWrap(true);
        observaciones_trans.setWrapStyleWord(true);
        setDocumentfilter(observaciones_trans, 75);
        JScrollPane scrollPane = new JScrollPane(observaciones_trans);

        if (estado == 2) {
            SolicitudDAO solicitudSQL = new SolicitudDAO();
            List<MediosTransporte> transps = solicitudSQL.buscaTransporte(solicitud.getId_solicitud());
            List<Object> transps2 = new ArrayList<>();
            for (MediosTransporte t : transps) {
                transps2.add(t.getNombre());
            }
            transporte_desp.setSelectedItems(transps2);
            observaciones_trans.setText(solicitud.getTransp_comentario());
        } else if (estado == 3) {
            ProgramadasDAO programadaSQL = new ProgramadasDAO();
            List<MediosTransporte> transps = programadaSQL.buscaTransporte(programada.getId_programada());
            List<Object> transps2 = new ArrayList<>();
            for (MediosTransporte t : transps) {
                transps2.add(t.getNombre());
            }
            transporte_desp.setSelectedItems(transps2);
            observaciones_trans.setText(programada.getTransp_comentario());
        }

        panel.add(transporte);
        panel.add(transporte_desp, "gapleft 60, gapright 10, align right, width 300!, wrap");
        panel.add(transporte_disclaimer, "span, align center, gapbottom 10, wrap");
        panel.add(scrollPane, "height 30!, width 100%, align center, span 3, wrap");

        return panel;
    }

    private Component introHoras() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        hora = new JLabel("Horario de la actividad: ");
        hora_com = new JLabel("Inicio ");
        hora_fin = new JLabel("Fin ");
        textoHoraIni = new JFormattedTextField();
        selectHora1 = new TimePicker();
        selectHora1.setEditor(textoHoraIni);
        selectHora1.set24HourView(true);
        textoHoraFin = new JFormattedTextField();
        selectHora2 = new TimePicker();
        selectHora2.setEditor(textoHoraFin);
        selectHora2.set24HourView(true);

        if (estado == 2) {
            textoHoraIni.setText(solicitud.getHini().toString());
            textoHoraFin.setText(solicitud.getHfin().toString());
        } else if (estado == 3) {
            textoHoraIni.setText(programada.getHini().toString());
            textoHoraFin.setText(programada.getHfin().toString());
        }

        panel.add(hora);
        panel.add(hora_com, "gapleft 20");
        panel.add(textoHoraIni, "width 130!");
        panel.add(hora_fin);
        panel.add(textoHoraFin, "width 130!");
        return panel;
    }

    private Component introFechas() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        fecha = new JLabel("Fechas de la actividad: ");
        fecha_com = new JLabel("Inicio ");
        fecha_fin = new JLabel("Fin ");
        textoFechaIni = new JFormattedTextField();
        selectFecha1 = new DatePicker();
        selectFecha1.setEditor(textoFechaIni);
        textoFechaFin = new JFormattedTextField();
        selectFecha2 = new DatePicker();
        selectFecha2.setEditor(textoFechaFin);

        if (estado == 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            textoFechaIni.setText(solicitud.getFini().format(formatter).toString());
            textoFechaFin.setText(solicitud.getFfin().format(formatter).toString());
        } else if (estado == 3) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            textoFechaIni.setText(programada.getFini().format(formatter).toString());
            textoFechaFin.setText(programada.getFfin().format(formatter).toString());
        }

        panel.add(fecha);
        panel.add(fecha_com, "gapleft 20");
        panel.add(textoFechaIni, "width 130!");
        panel.add(fecha_fin);
        panel.add(textoFechaFin, "width 130!");
        return panel;
    }

    private Component introAlumnos() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        alumnos = new JLabel("Grupos/Cursos participantes: ");
        cursos_boton = new JRadioButton("Cursos");
        grupos_boton = new JRadioButton("Grupos");

        alumnos_desp = new Multiseleccion<>();
        alumnos_desp.addItem("Seleccione cursos o grupos");

        cursos_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                alumnos_desp.removeAllItems();
                CursosDAO cursosSQL = new CursosDAO();
                List<Curso> cursos = cursosSQL.listar();
                for (Curso curso : cursos) {
                    alumnos_desp.addItem(curso.getCodigo());
                }
                alumnos_desp.clearSelectedItems();
            }
        });

        grupos_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alumnos_desp.removeAllItems();
                GruposDAO gruposSQL = new GruposDAO();
                List<Grupo> grupos = gruposSQL.listar();
                for (Grupo grupo : grupos) {
                    alumnos_desp.addItem(grupo.getCodigo());
                }
                alumnos_desp.clearSelectedItems();
            }
        });

        if (estado == 2) {
            SolicitudDAO solicitudSQL = new SolicitudDAO();
            List<Curso> cursos = new ArrayList<>();
            List<Grupo> grupos = new ArrayList<>();
            if (solicitud.getCursos().isEmpty()) {
                grupos_boton.doClick();
                grupos = solicitudSQL.buscaGrupos(solicitud.getId_solicitud());
            } else {
                cursos_boton.doClick();
                cursos = solicitudSQL.buscaCursos(solicitud.getId_solicitud());
            }
            List<Object> alumnos2 = new ArrayList<>();
            if (cursos_boton.isSelected()) {
                for (Curso curso : cursos) {
                    alumnos2.add(curso.getCodigo());
                }
            } else {
                for (Grupo grupo : grupos) {
                    alumnos2.add(grupo.getCodigo());
                }
            }
            alumnos_desp.setSelectedItems(alumnos2);
        } else if (estado == 3) {
            ProgramadasDAO programadasSQL = new ProgramadasDAO();
            List<Curso> cursos = new ArrayList<>();
            List<Grupo> grupos = new ArrayList<>();
            if (programada.getCursos().isEmpty()) {
                grupos_boton.doClick();
                grupos = programadasSQL.buscaGrupos(programada.getId_programada());
            } else {
                cursos_boton.doClick();
                cursos = programadasSQL.buscaCursos(programada.getId_programada());
            }
            List<Object> alumnos2 = new ArrayList<>();
            if (cursos_boton.isSelected()) {
                for (Curso curso : cursos) {
                    alumnos2.add(curso.getCodigo());
                }
            } else {
                for (Grupo grupo : grupos) {
                    alumnos2.add(grupo.getCodigo());
                }
            }
            alumnos_desp.setSelectedItems(alumnos2);
        }

        ButtonGroup group = new ButtonGroup();
        group.add(cursos_boton);
        group.add(grupos_boton);

        panel.add(alumnos);
        panel.add(grupos_boton, "gapleft 20");
        panel.add(cursos_boton, "wrap");
        panel.add(alumnos_desp, "width 100%, span 3, wrap");
        return panel;
    }

    private Component introAlojamiento() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        alojamiento = new JLabel("¿Se requiere alojamiento?");
        alojamiento_si = new JRadioButton("Si");
        alojamiento_no = new JRadioButton("No");
        observaciones_aloj = new JTextArea();
        observaciones_aloj.setLineWrap(true);
        observaciones_aloj.setWrapStyleWord(true);
        setDocumentfilter(observaciones_aloj, 75);
        JScrollPane scrollPane = new JScrollPane(observaciones_aloj);

        if (estado == 2) {
            if (solicitud.isAloj_requerido()) {
                alojamiento_si.setSelected(true);
            } else {
                alojamiento_no.setSelected(true);
            }
            observaciones_aloj.setText(solicitud.getAloj_comentario());
        } else if (estado == 3) {
            if (programada.isAloj_requerido()) {
                alojamiento_si.setSelected(true);
            } else {
                alojamiento_no.setSelected(true);
            }
            observaciones_aloj.setText(programada.getAloj_comentario());
        }
        ButtonGroup group = new ButtonGroup();
        group.add(alojamiento_si);
        group.add(alojamiento_no);
        panel.add(alojamiento);
        panel.add(alojamiento_si, "gapleft 20");
        panel.add(alojamiento_no, "wrap");
        panel.add(scrollPane, "height 30!, width 800, align center, span 3, wrap");
        return panel;
    }

    private Component introResponsables() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        responsables = new JLabel("Profesores Responsables: ");
        responsables_desp = new Multiseleccion<>();
        ProfesorDAO profesoresSQL = new ProfesorDAO();
        List<Profesor> profesores = profesoresSQL.listar();
        for (Profesor profesor : profesores) {
            responsables_desp.addItem(String.valueOf(profesor.getId()).concat("-").concat(profesor.getNombre())
                    .concat(" ").concat(profesor.getApellidos()));
        }

        if (estado == 2) {
            SolicitudDAO solicitudSQL = new SolicitudDAO();
            List<Profesor> responsables = solicitudSQL.buscaResponsable(solicitud.getId_solicitud());
            List<Object> responsables2 = new ArrayList<>();
            for (Profesor responsable : responsables) {
                responsables2.add(responsable.getId() + "-"
                        + responsable.getNombre() + " "
                        + responsable.getApellidos());
            }
            responsables_desp.setSelectedItems(responsables2);
        } else if (estado == 3) {
            ProgramadasDAO programadasSQL = new ProgramadasDAO();
            List<Profesor> responsables = programadasSQL.buscaResponsable(programada.getId_programada());
            List<Object> responsables2 = new ArrayList<>();
            for (Profesor responsable : responsables) {
                responsables2.add(responsable.getId() + "-"
                        + responsable.getNombre() + " "
                        + responsable.getApellidos());
            }
            responsables_desp.setSelectedItems(responsables2);
        }

        panel.add(responsables);
        panel.add(responsables_desp, "dock east, gapleft 60, gapright 10, align right, width 300!");
        return panel;
    }

    private Component introParticipantes() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        participantes = new JLabel("*Profesores Participantes: ");
        participantes_desp = new Multiseleccion<>();
        ProfesorDAO profesoresSQL = new ProfesorDAO();
        List<Profesor> profesores = profesoresSQL.listar();
        for (Profesor profesor : profesores) {
            participantes_desp.addItem(String.valueOf(profesor.getId()).concat("-").concat(profesor.getNombre())
                    .concat(" ").concat(profesor.getApellidos()));
        }

        if (estado == 2) {
            SolicitudDAO solicitudSQL = new SolicitudDAO();
            List<Profesor> participantes = solicitudSQL.buscaParticipantes(solicitud.getId_solicitud());
            List<Object> participantes2 = new ArrayList<>();
            for (Profesor participante : participantes) {
                participantes2.add(participante.getId() + "-"
                        + participante.getNombre() + " "
                        + participante.getApellidos());
            }
            participantes_desp.setSelectedItems(participantes2);
        } else if (estado == 3) {
            ProgramadasDAO programadasSQL = new ProgramadasDAO();
            List<Profesor> participantes = programadasSQL.buscaParticipantes(programada.getId_programada());
            List<Object> participantes2 = new ArrayList<>();
            for (Profesor participante : participantes) {
                participantes2.add(participante.getId() + "-"
                        + participante.getNombre() + " "
                        + participante.getApellidos());
            }
            participantes_desp.setSelectedItems(participantes2);
        }

        panel.add(participantes);
        panel.add(participantes_desp, "dock east, gapleft 60, gapright 10, align right, width 300!");
        return panel;
    }

    private Component introObservaciones() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        panel.add(new JLabel("*Observaciones: "), "wrap");
        observaciones = new JTextArea();
        observaciones.setLineWrap(true);
        observaciones.setWrapStyleWord(true);
        setDocumentfilter(observaciones, 100);

        if (estado == 2) {
            observaciones.setText(solicitud.getComentario());
        } else if (estado == 3) {
            observaciones.setText(programada.getComentario());
        }

        JScrollPane scrollPane = new JScrollPane(observaciones);
        panel.add(scrollPane, "height 60!, width 800, align center");
        return panel;
    }

    private Component introProgramadas() {
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 5 5 0 5", "[fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        panel.add(new JLabel("Empresa de transporte: "), "split 2");
        panel.add(new JLabel("Precio: "), "gapleft 290, wrap");
        JTextField precio = new JTextField();
        JTextField emp_transporte = new JTextField();
        setDocumentfilter(emp_transporte, 30);
        panel.add(emp_transporte, "split 2, width 850, height 30!, align center");
        panel.add(precio, "gapleft 20, height 30!, align center, wrap");
        return panel;
    }

    private void onAceptarClicked() {
        if (validaSolicitud()) {
            creaSolicitud();
        }
    }

    private boolean validaSolicitud() {
        if (departamento_desp.getSelectedIndex() < 0) {
            System.out.println("Debes seleccionar un departamento");
            return false;
        } else if (!titulo_field.getText().matches("^([A-Z0-9Ñ][a-záéíóúñºª]*)(\\s[A-Za-z0-9ñÑºªáéíóú]*)*$")) {
            System.out.println("Debes introducir un titulo correcto.");
            return false;
        } else if (!tipo1_boton.isSelected() && !tipo2_boton.isSelected()) {
            System.out.println("Debes seleccionar el tipo de actividad");
            return false;
        } else if (!selectHora1.isTimeSelected()) {
            System.out.println("Debes introducir una hora de comienzo");
            return false;
        } else if (!selectHora2.isTimeSelected()) {
            System.out.println("Debes introducir una hora de fin");
            return false;
        } else if (!selectFecha1.isDateSelected()) {
            System.out.println("Debes introducir una fecha de comienzo");
            return false;
        } else if (!selectFecha2.isDateSelected()) {
            System.out.println("Debes introducir una fecha de fin");
            return false;
        } else if (responsables_desp.getSelectedItems().isEmpty()) {
            System.out.println("Debes seleccionar al menos un profesor responsable");
            return false;
        } else if (alumnos_desp.getSelectedItems().contains("Seleccione cursos o grupos")) {
            System.out.println("Has seleccionado un item no válido.");
            return false;
        } else if (alumnos_desp.getSelectedItems().isEmpty()) {
            System.out.println("Debes seleccionar al menos un grupo o curso");
            return false;
        } else if (!observaciones_trans.getText().isEmpty()
                && !observaciones_trans.getText().matches("^([A-Z0-9Ña-záéíóñúºª]*)(\\s[A-Za-z0-9ñÑºªáéíóú]*)*$")) {
            System.out.println("Debes introducir observaciones de transporte correctas.");
            return false;
        } else if (!alojamiento_si.isSelected() && !alojamiento_no.isSelected()) {
            System.out.println("Debes seleccionar si se requiere alojamiento");
            return false;
        } else if (alojamiento_si.isSelected()
                && !observaciones_aloj.getText().matches("^([A-Z0-9Ña-záéíñóúºª]*)(\\s[A-Za-z0-9ñÑºªáéíóú]*)*$")) {
            System.out.println("Debes introducir observaciones de alojamiento correctas.");
            return false;
        } else if (!observaciones.getText().isEmpty()
                && !observaciones.getText().matches("^([A-Z0-9Ña-záéíóñúºª]*)(\\s[A-Za-z0-9ñÑºªáéíóú]*)*$")) {
            System.out.println("Debes introducir observaciones correctas.");
            return false;
        } else {
            return true;
        }
    }

    private void creaSolicitud() {
        SolicitudDAO solicitudSQL = new SolicitudDAO();
        boolean cambio = true;
        String titulo = titulo_field.getText();
        TipoActividad tipo = TipoActividad.valueOf(tipo1_boton.isSelected() ? "Extraescolar" : "Complementaria");
        boolean prevista = prevista_toggle.isSelected();
        LocalDate fini = selectFecha1.getSelectedDate();
        LocalDate ffin = selectFecha2.getSelectedDate();
        LocalTime hini = selectHora1.getSelectedTime();
        LocalTime hfin = selectHora2.getSelectedTime();
        boolean transporte_req = transporte_desp.getSelectedItems().isEmpty() ? false : true;
        String transporte_com = observaciones_trans.getText().isEmpty() ? null : observaciones_trans.getText();
        boolean alojam_req = alojamiento_si.isSelected();
        String alojam_com = observaciones_aloj.getText().isEmpty() ? null : observaciones_aloj.getText();
        String comentarios = observaciones.getText().isEmpty() ? null : observaciones.getText();
        TransporteDAO transporteSQL = new TransporteDAO();
        ProfesorDAO profesorSQL = new ProfesorDAO();
        CursosDAO cursosSQL = new CursosDAO();
        GruposDAO gruposSQL = new GruposDAO();

        // List<String> participantes = (List<String>)
        // participantes_desp.getSelectedItems().stream().map(Object::toString)
        // .collect(Collectors.toList());
        // List<String> responsables = (List<String>)
        // responsables_desp.getSelectedItems().stream().map(Object::toString)
        // .collect(Collectors.toList());
        // List<String> alumnos = (List<String>)
        // alumnos_desp.getSelectedItems().stream().map(Object::toString)
        // .collect(Collectors.toList());
        // List<String> transportes = (List<String>)
        // transporte_desp.getSelectedItems().stream().map(Object::toString)
        // .collect(Collectors.toList());
        // List<MediosTransporte> transportes =
        // transporte_desp.getSelectedItems().stream()
        // .map(obj -> (MediosTransporte) obj).collect(Collectors.toList());

        List<Profesor> participantes = participantes_desp.getSelectedItems().stream()
                .map(nombre -> (Profesor) profesorSQL.buscar(Integer.parseInt(String.valueOf(nombre).split("-")[0])))
                .collect(Collectors.toList());

        List<Profesor> responsables = responsables_desp.getSelectedItems().stream()
                .map(nombre -> (Profesor) profesorSQL.buscar(Integer.parseInt(String.valueOf(nombre).split("-")[0])))
                .collect(Collectors.toList());

        List<MediosTransporte> transportes = transporte_desp.getSelectedItems().stream()
                .map(nombre -> (MediosTransporte) transporteSQL
                        .buscar(String.valueOf(nombre)))
                .collect(Collectors.toList());

        List<Curso> cursos = new ArrayList<>();

        List<Grupo> grupos = new ArrayList<>();

        if (cursos_boton.isSelected()) {
            cursos = alumnos_desp.getSelectedItems().stream()
                    .map(nombre -> (Curso) cursosSQL.buscar(String.valueOf(nombre)))
                    .collect(Collectors.toList());
        } else {
            grupos = alumnos_desp.getSelectedItems().stream()
                    .map(nombre -> (Grupo) gruposSQL.buscar(String.valueOf(nombre)))
                    .collect(Collectors.toList());
        }

        int id = 0;
        if (estado == 2) {
            id = solicitud.getId_solicitud();
            Profesor solicitante = profesorSQL.buscar(Login.user.getId());

            Solicitud solicitud = new Solicitud(id, solicitante, titulo, tipo, fini, ffin, hini, hfin, prevista,
                    transporte_req, transporte_com, alojam_req, alojam_com, comentarios, EstadoActividad.Solicitada,
                    null, transportes, participantes, responsables, cursos, grupos);

            if (solicitud.equals(this.solicitud, participantes, responsables, transportes, cursos, grupos,
                    cursos_boton.isSelected() ? true : false)) {
                cambio = false;
                return;
            } else {
                solicitudSQL.borraCursos(id);
                solicitudSQL.borraGrupos(id);
                solicitudSQL.borraParticipantes(id);
                solicitudSQL.borraResponsables(id);
                solicitudSQL.borraTransportes(id);
                solicitudSQL.guardar(solicitud);
            }
        } else {
            Profesor solicitante = profesorSQL.buscar(Login.user.getId());

            Solicitud solicitud = new Solicitud(solicitante, titulo, tipo, fini, ffin, hini, hfin, prevista,
                    transporte_req, transporte_com, alojam_req, alojam_com, comentarios, EstadoActividad.Solicitada,
                    null, transportes, participantes, responsables, cursos, grupos);
            id = solicitudSQL.guardar(solicitud);
        }
        /*
         * if (cambio != true) {
         * JOptionPane.showMessageDialog(null,
         * "No ha realizado cambios en la solicitud.");
         * } else
         */if (cambio == true) {

            for (Profesor responsable : responsables) {
                int id_responsable = responsable.getId();
                solicitudSQL.guardarResponsable(id, id_responsable);
            }
            for (Profesor participante : participantes) {
                int id_participante = participante.getId();
                solicitudSQL.guardarParticipante(id, id_participante);
            }
            if (cursos_boton.isSelected()) {
                for (Curso curso : cursos) {
                    solicitudSQL.guardarCurso(id, curso.getId());
                }
            } else {
                for (Grupo grupo : grupos) {
                    solicitudSQL.guardarGrupo(id, grupo.getId());
                }
            }
            for (MediosTransporte transporte : transportes) {
                int id_transporte = transporte.getId();
                solicitudSQL.guardarTransporte(id, id_transporte);
            }
            JOptionPane.showMessageDialog(null, "Cambios realizados con éxito.");
            VentanaSingleton.getInstance().cerrarVentana("Crear Solicitud");
        }
    }

    private void setDocumentfilter(JTextField campo, int caracteres) {
        DocumentFilter filter = Utils.limitaCaracteres(caracteres);
        ((PlainDocument) campo.getDocument()).setDocumentFilter(filter);
    }

    private void setDocumentfilter(JTextArea campo, int caracteres) {
        DocumentFilter filter = Utils.limitaCaracteres(caracteres);
        ((PlainDocument) campo.getDocument()).setDocumentFilter(filter);
    }
}
