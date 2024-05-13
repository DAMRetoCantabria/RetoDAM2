package reto.ventanas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.formdev.flatlaf.FlatClientProperties;
import com.mysql.cj.xdevapi.Table;

import net.miginfocom.swing.MigLayout;
import reto.components.*;
import reto.objects.*;
import reto.sql.*;

public class Listado_admin extends JPanel {
    private JComboBox<String> comboBox;
    private JTable table;
    private JButton editar;
    private JButton borrar;
    private JButton crear;
    private String tema;
    private ProfesorDAO profesorSQL = new ProfesorDAO();
    private Profesor profesor;
    private CursosDAO cursoSQL = new CursosDAO();
    private GruposDAO gruposSQL = new GruposDAO();
    private DepartamentoDAO departamentoSQL = new DepartamentoDAO();
    private MouseAdapter evento2click = null;

    public Listado_admin(int seleccionado) {
        init(seleccionado);
    }

    public void init(int seleccionado) {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "[fill, 850]", "[fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");

        JLabel titulo = new JLabel("Gestion de Datos");
        titulo.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
        comboBox = new JComboBox<>();
        comboBox.addItem("Profesores");
        comboBox.addItem("Cursos");
        comboBox.addItem("Grupos");
        comboBox.addItem("Departamentos");

        comboBox.setSelectedIndex(seleccionado);

        panel.add(titulo, "align center, gapleft 325, gapbottom 20");
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(comboBox, "align center");
        panel.add(new JSeparator(), "gapy 5 20");
        panel.add(crearTabla(), "width 100%, height 500");
        panel.add(new JSeparator(), "gapy 5 20");
        add(panel);
    }

    private Component crearTabla() {
        JPanel panel = new JPanel(new MigLayout("fill, insets 20", "[center]", "[center]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");

        table = new JTable();
        table.setDefaultRenderer(Object.class, new PerfilTabla());

        editar = new JButton("Editar");
        editar.setEnabled(Login.user.getNivel().equalsIgnoreCase("superusuario"));
        editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();

                if (selectedRows.length == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una o mas filas para editar.");
                } else {
                    for (int seleccionada : selectedRows) {
                        int id = (int) table.getValueAt(seleccionada, 0);
                        if (tema.equalsIgnoreCase("Profesores")) {
                            profesor = profesorSQL.buscar(id);
                            VentanaSingleton.getInstance().mostrarVentana("Modificar Profesor",
                                    new Mostrar(1, profesor),
                                    new Dimension(700, 700));
                        } else if (tema.equalsIgnoreCase("Cursos")) {
                            CursosDAO cursosSQL = new CursosDAO();
                            Curso curso = cursosSQL.buscar(id);
                            VentanaSingleton.getInstance().mostrarVentana("Modificar Cursos", new Mostrar(3, curso),
                                    new Dimension(700, 700));
                        } else if (tema.equalsIgnoreCase("Grupos")) {
                            GruposDAO gruposSQL = new GruposDAO();
                            Grupo grupo = gruposSQL.buscar(id);
                            VentanaSingleton.getInstance().mostrarVentana("Modificar Grupos", new Mostrar(2, grupo),
                                    new Dimension(700, 700));
                        } else if (tema.equalsIgnoreCase("Departamentos")) {
                            DepartamentoDAO departamentosSQL = new DepartamentoDAO();
                            Departamento departamento = departamentosSQL.buscar(id);
                            VentanaSingleton.getInstance().mostrarVentana("Modificar Departamentos",
                                    new Mostrar(4, departamento),
                                    new Dimension(700, 700));
                        }
                    }
                }
            }
        });

        borrar = new JButton("Borrar");
        borrar.setEnabled(Login.user.getNivel().equalsIgnoreCase("superusuario"));
        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();

                if (selectedRows.length == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una o mas filas para borrar.");
                } else {
                    for (int seleccionada : selectedRows) {
                        int id = (int) table.getValueAt(seleccionada, 0);
                        if (tema.equalsIgnoreCase("Profesores")) {
                            int confirmacion = JOptionPane.showConfirmDialog(null,
                                    "¿Estás seguro de que quieres eliminar al profesor?", "Confirmar eliminación",
                                    JOptionPane.YES_NO_OPTION);
                            if (confirmacion == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Profesor eliminado correctamente");
                                profesorSQL.borrar(profesorSQL.buscar(id));
                            }
                        } else if (tema.equalsIgnoreCase("Cursos")) {

                        } else if (tema.equalsIgnoreCase("Grupos")) {

                        } else if (tema.equalsIgnoreCase("Departamentos")) {

                        }
                    }
                }
            }
        });

        crear = new JButton("Crear");
        crear.setEnabled(Login.user.getNivel().equalsIgnoreCase("superusuario"));
        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tema.equalsIgnoreCase("Profesores")) {
                    VentanaSingleton.getInstance().mostrarVentana("Modificar Profesor",
                            new Mostrar(1),
                            new Dimension(700, 700));
                } else if (tema.equalsIgnoreCase("Cursos")) {
                    VentanaSingleton.getInstance().mostrarVentana("Modificar Cursos", new Mostrar(3),
                            new Dimension(700, 700));
                } else if (tema.equalsIgnoreCase("Grupos")) {
                    VentanaSingleton.getInstance().mostrarVentana("Modificar Grupos", new Mostrar(2),
                            new Dimension(700, 700));
                } else if (tema.equalsIgnoreCase("Departamentos")) {
                    VentanaSingleton.getInstance().mostrarVentana("Modificar Departamentos",
                            new Mostrar(4),
                            new Dimension(700, 700));
                }
            }
        });

        actualizarTabla((String) comboBox.getSelectedItem());

        comboBox.addActionListener(e -> {
            String temaSeleccionado = (String) comboBox.getSelectedItem();
            actualizarTabla(temaSeleccionado);
            anchoTabla();
        });

        anchoTabla();

        panel.add(new JScrollPane(table), "width 80%, west, cell 0 0 1 3");
        panel.add(borrar, "split 3, flowy, right, width 200!, height 50!, cell 1 0");
        panel.add(editar, "right, width 200!, height 50!, cell 1 1");
        panel.add(crear, "right, width 200!, height 50!, cell 1 2");

        return panel;
    }

    private void actualizarTabla(String tema) {
        DefaultTableModel model;
        this.tema = tema;

        switch (tema.toLowerCase()) {
            case "profesores":
                model = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Departamento", "DNI", "Nombre",
                        "Apellidos", "Correo", "Nivel", "Activo" }) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                break;
            case "cursos":
                model = new DefaultTableModel(new Object[][] {},
                        new String[] { "Id", "Codigo", "Descripcion", "Etapa", "Activo" }) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                break;
            case "grupos":
                model = new DefaultTableModel(new Object[][] {},
                        new String[] { "Id", "Curso", "Codigo", "Numero de Alumnos", "Activo" }) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                break;
            case "departamentos":
                model = new DefaultTableModel(new Object[][] {},
                        new String[] { "Id", "Codigo", "Nombre", "Jefe" }) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                break;
            default:
                model = new DefaultTableModel();
        }
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

        if (tema.toLowerCase().equals("grupos")) {
            organizador.setComparator(3, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o1, o2);
                }
            });
        }

        if (evento2click != null) {
            table.removeMouseListener(evento2click);
            evento2click = null;
        }

        evento2click = new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int id = (int) table.getValueAt(row, 0);
                    if (tema.equalsIgnoreCase("Profesores")) {
                        profesor = profesorSQL.buscar(id);
                        VentanaSingleton.getInstance().mostrarVentana("Modificar Profesor", new Mostrar(1, profesor),
                                new Dimension(700, 700));
                    } else if (tema.equalsIgnoreCase("Cursos")) {
                        CursosDAO cursosSQL = new CursosDAO();
                        Curso curso = cursosSQL.buscar(id);
                        VentanaSingleton.getInstance().mostrarVentana("Modificar Cursos", new Mostrar(3, curso),
                                new Dimension(700, 700));
                    } else if (tema.equalsIgnoreCase("Grupos")) {
                        GruposDAO gruposSQL = new GruposDAO();
                        Grupo grupo = gruposSQL.buscar(id);
                        VentanaSingleton.getInstance().mostrarVentana("Modificar Grupos", new Mostrar(2, grupo),
                                new Dimension(700, 700));
                    } else if (tema.equalsIgnoreCase("Departamentos")) {
                        DepartamentoDAO departamentosSQL = new DepartamentoDAO();
                        Departamento departamento = departamentosSQL.buscar(id);
                        VentanaSingleton.getInstance().mostrarVentana("Modificar Departamentos",
                                new Mostrar(4, departamento),
                                new Dimension(700, 700));
                    }
                }

            }
        };

        table.addMouseListener(evento2click);

        if (tema.equalsIgnoreCase("Profesores")) {
            datosProfesores();
        } else if (tema.equalsIgnoreCase("Cursos")) {
            datosCursos();
        } else if (tema.equalsIgnoreCase("Grupos")) {
            datosGrupos();
        } else if (tema.equalsIgnoreCase("Departamentos")) {
            datosDepartamentos();
        }

    }

    private void anchoTabla() {
        TableColumnModel columnModel = table.getColumnModel();
        String tema = (String) comboBox.getSelectedItem();
        if (tema.equalsIgnoreCase("Profesores")) {
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(75);
            columnModel.getColumn(2).setPreferredWidth(90);
            columnModel.getColumn(3).setPreferredWidth(80);
            columnModel.getColumn(4).setPreferredWidth(125);
            columnModel.getColumn(5).setPreferredWidth(180);
            columnModel.getColumn(6).setPreferredWidth(50);
            columnModel.getColumn(7).setPreferredWidth(30);
        } else if (tema.equalsIgnoreCase("Cursos")) {
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(50);
            columnModel.getColumn(2).setPreferredWidth(300);
            columnModel.getColumn(3).setPreferredWidth(50);
            columnModel.getColumn(4).setPreferredWidth(10);
        } else if (tema.equalsIgnoreCase("Grupos")) {
            columnModel.getColumn(0).setPreferredWidth(10);
            columnModel.getColumn(1).setPreferredWidth(80);
            columnModel.getColumn(2).setPreferredWidth(100);
            columnModel.getColumn(3).setPreferredWidth(10);
            columnModel.getColumn(4).setPreferredWidth(10);
        } else if (tema.equalsIgnoreCase("Departamentos")) {
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(50);
            columnModel.getColumn(2).setPreferredWidth(300);
            columnModel.getColumn(3).setPreferredWidth(100);
        }
    }

    public void datosProfesores() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Profesor> profesores = profesorSQL.listar();
        for (Profesor profesor : profesores) {
            model.addRow(new Object[] {
                    profesor.getId(),
                    departamentoSQL.buscar(profesor.getDepartamento().getId()).getCodigo(),
                    profesor.getDni(),
                    profesor.getNombre(),
                    profesor.getApellidos(),
                    profesor.getCorreo(),
                    profesor.getNivel().toString(),
                    profesor.isActivo() ? "1" : "0"
            });
        }
    }

    public void datosCursos() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Curso> cursos = cursoSQL.listar();
        for (Curso curso : cursos) {
            model.addRow(new Object[] {
                    curso.getId(),
                    curso.getCodigo(),
                    curso.getDescripcion(),
                    curso.getEtapa(),
                    curso.isActivo() ? "1" : "0"
            });
        }
    }

    public void datosGrupos() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Grupo> grupos = gruposSQL.listar();
        for (Grupo grupo : grupos) {
            model.addRow(new Object[] {
                    grupo.getId(),
                    grupo.getCurso().getCodigo(),
                    grupo.getCodigo(),
                    grupo.getNum_alumnos(),
                    grupo.isActivo() ? "1" : "0"
            });
        }
    }

    public void datosDepartamentos() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Departamento> departamentos = departamentoSQL.listar();
        for (Departamento departamento : departamentos) {
            model.addRow(new Object[] {
                    departamento.getId(),
                    departamento.getCodigo(),
                    departamento.getNombre(),
                    departamento.getJefe() == null ? ""
                            : departamento.getJefe().getNombre().concat(" ")
                                    .concat(departamento.getJefe().getApellidos())
            });
        }
    }
}