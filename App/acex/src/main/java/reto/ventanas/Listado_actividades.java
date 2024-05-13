package reto.ventanas;

import java.awt.event.MouseAdapter;
import java.util.Comparator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import reto.components.PerfilTabla;
import reto.objects.Profesor;
import reto.objects.Programadas;
import reto.objects.Solicitud;
import reto.sql.*;
import reto.components.VentanaSingleton;
import java.awt.*;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

/**
 * Esta clase representa una ventana que muestra un listado de actividades.
 * Extiende de JPanel y contiene una tabla con información de las actividades.
 */
public class Listado_actividades extends JPanel {
    private JTable table;
    private int tipo;
    private MouseAdapter evento2click = null;
    private SolicitudDAO solicitudSQL = new SolicitudDAO();
    private ProgramadasDAO programadasSQL = new ProgramadasDAO();

    /**
     * Constructor de la clase Listado_actividades.
     * 
     * @param tipo El tipo de actividades a mostrar en la tabla.
     */
    public Listado_actividades(int tipo) {
        this.tipo = tipo;
        init();
    }

    /**
     * Inicializa la interfaz de la ventana y sus componentes.
     */
    public void init() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "[fill, 850]", "[fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");

        JLabel titulo = new JLabel("Actividades");
        titulo.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");

        panel.add(titulo, "span, align 50% 50%, wrap");
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(crearTabla(), "span, align 50% 50%, wrap");
        add(panel);
    }

    /**
     * Crea y configura la tabla que muestra las actividades.
     * 
     * @return El componente de la tabla.
     */
    private Component crearTabla() {
        JPanel panel = new JPanel(new MigLayout("fill, insets 20", "[center]", "[center]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");

        table = new JTable();
        table.setDefaultRenderer(Object.class, new PerfilTabla());

        actualizarTabla();
        anchoTablaSolicitadas();

        panel.add(new JScrollPane(table), "width 100%, cell 0 0 1 3");

        return panel;
    }

    /**
     * Actualiza los datos de la tabla de actividades.
     */
    private void actualizarTabla() {
        DefaultTableModel model;
        model = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Titulo", "Tipo",
                "Fechas", "Transporte", "Alojamiento" }) {
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

        if (evento2click != null) {
            table.removeMouseListener(evento2click);
            evento2click = null;
        }

        if (tipo == 1) {
            evento2click = new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow();
                        int id = (int) table.getValueAt(row, 0);
                        ProgramadasDAO programadasSQL = new ProgramadasDAO();
                        Programadas programada = programadasSQL.buscar(id);
                        VentanaSingleton.getInstance().mostrarVentana("Actividades Programadas",
                                new Solicitudes(3, programada),
                                new Dimension(700, 700));
                    }
                }
            };
        } else if (tipo == 2) {
            evento2click = new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow();
                        int id = (int) table.getValueAt(row, 0);
                        Solicitud solicitud = solicitudSQL.buscar(id);
                        VentanaSingleton.getInstance().mostrarVentana("Solicitudes Aprobadas",
                                new Solicitudes(2, solicitud),
                                new Dimension(700, 700));
                    }

                }
            };
        }

        table.addMouseListener(evento2click);

        if (tipo == 1) {
            datosAprobadas();
        } else if (tipo == 2) {
            datosSolicitadas();
        }
    }

    /**
     * Carga los datos de las solicitudes en la tabla.
     */
    public void datosSolicitadas() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Solicitud> solicitudes = solicitudSQL.listar();
        ProfesorDAO profesorSQL = new ProfesorDAO();
        for (Solicitud solicitud : solicitudes) {
            String fini = solicitud.getFini().toString().concat(" ").concat(solicitud.getHini().toString());
            String ffin = solicitud.getFfin().toString().concat(" ").concat(solicitud.getHfin().toString());
            String fecha = fini.concat(" / ").concat(ffin);
            List<Profesor> responsables = solicitud.getResponsables();
            if (String.valueOf(solicitud.getEstado()).equalsIgnoreCase("solicitada")) {
                if (Login.user.getNivel().equalsIgnoreCase("Profesor")) {
                    if (solicitud.get_solicitante() == profesorSQL.buscar(Login.user.getId())
                            || responsables.contains(profesorSQL.buscar(Login.user.getId()))) {
                        model.addRow(new Object[] {
                                solicitud.getId_solicitud(),
                                solicitud.getTitulo(),
                                solicitud.getTipo().toString(),
                                fecha,
                                solicitud.isTransp_requerido() ? "Transporte requerido" : "Transporte no requerido",
                                solicitud.isAloj_requerido() ? "Alojamiento requerido" : "Alojamiento no requerido",
                        });
                    }
                } else {
                    model.addRow(new Object[] {
                            solicitud.getId_solicitud(),
                            solicitud.getTitulo(),
                            solicitud.getTipo().toString(),
                            fecha,
                            solicitud.isTransp_requerido() ? "Transporte requerido" : "Transporte no requerido",
                            solicitud.isAloj_requerido() ? "Alojamiento requerido" : "Alojamiento no requerido",
                    });
                }
            }
        }
    }

    /**
     * Carga los datos de las actividades programadas en la tabla.
     */
    public void datosAprobadas() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Programadas> programadas = programadasSQL.listar();
        ProfesorDAO profesorSQL = new ProfesorDAO();
        for (Programadas programada : programadas) {
            String fini = programada.getFini().toString().concat(" ").concat(programada.getHini().toString());
            String ffin = programada.getFfin().toString().concat(" ").concat(programada.getHfin().toString());
            String fecha = fini.concat(" / ").concat(ffin);
            List<Profesor> responsables = programada.getResponsables();
            if (String.valueOf(programada.getEstado()).equalsIgnoreCase("aprobada")) {
                if (Login.user.getNivel().equalsIgnoreCase("Profesor")) {
                    if (programada.get_solicitante() == profesorSQL.buscar(Login.user.getId())
                            || responsables.contains(profesorSQL.buscar(Login.user.getId()))) {
                        model.addRow(new Object[] {
                                programada.getId_programada(),
                                programada.getTitulo(),
                                programada.getTipo().toString(),
                                fecha,
                                programada.isTransp_requerido() ? "Transporte requerido" : "Transporte no requerido",
                                programada.isAloj_requerido() ? "Alojamiento requerido" : "Alojamiento no requerido",
                        });
                    }
                } else {
                    model.addRow(new Object[] {
                            programada.getId_programada(),
                            programada.getTitulo(),
                            programada.getTipo().toString(),
                            fecha,
                            programada.isTransp_requerido() ? "Transporte requerido" : "Transporte no requerido",
                            programada.isAloj_requerido() ? "Alojamiento requerido" : "Alojamiento no requerido",
                    });
                }
            }
        }
    }

    /**
     * Configura el ancho de las columnas de la tabla de solicitudes.
     */
    private void anchoTablaSolicitadas() {
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(15);
        columnModel.getColumn(1).setPreferredWidth(60);
        columnModel.getColumn(2).setPreferredWidth(35);
    }
}
