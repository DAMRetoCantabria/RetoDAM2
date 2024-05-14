package reto.ventanas;

import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import reto.objects.Curso;
import reto.objects.Departamento;
import reto.objects.Etapa;
import reto.objects.Grupo;
import reto.objects.Nivel;
import reto.objects.Profesor;
import reto.sql.*;
import reto.utilidades.Utils;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import reto.components.*;

/**
 * Clase que representa una ventana de visualización de datos.
 * Extiende de JPanel.
 */
public class Mostrar extends JPanel {

    private JTextField idField;
    private JTextField dniField;
    private JTextField nombreField;
    private JTextField apellidosField;
    private JTextField correoField;
    private JComboBox<String> nivelCombo;
    private JTextField nivelField;
    private JRadioButton activoRadio1;
    private JRadioButton activoRadio2;
    private JComboBox<String> departamentoCombo;
    private JTextField departamentoField;
    private JComboBox<String> cursoCombo;
    private JTextField cursoField;
    private JTextField codigoField;
    private JTextField alumnosField;
    private JTextField descripcionField;
    private JComboBox<String> etapaCombo;
    private JTextField etapaField;
    private JComboBox<String> jefeCombo;
    private JTextField jefeField;
    private String activoValue;

    /**
     * La clase Mostrar es una subclase de JPanel que se utiliza para mostrar los
     * datos de diferentes objetos en una interfaz gráfica.
     * Esta clase se utiliza para mostrar los datos de Profesor, Grupo, Curso y
     * Departamento.
     * 
     * Los datos se muestran en campos de texto y combos, y se pueden editar
     * dependiendo del nivel de acceso del usuario.
     * 
     * @param tipo   El tipo de objeto que se va a mostrar (1: Profesor, 2: Grupo,
     *               3: Curso, 4: Departamento).
     * @param object El objeto del cual se van a mostrar los datos.
     */
    public Mostrar(int tipo, Object object) {
        init(tipo, object);
    }

    /**
     * La clase Mostrar es una subclase de JPanel que se utiliza para mostrar los
     * datos de diferentes objetos en una interfaz gráfica.
     * Esta clase se utiliza para mostrar los datos de Profesor, Grupo, Curso y
     * Departamento.
     * 
     * Los datos se muestran en campos de texto y combos, y se pueden editar
     * dependiendo del nivel de acceso del usuario.
     * 
     * @param tipo El tipo de objeto que se va a mostrar (1: Profesor, 2: Grupo, 3:
     *             Curso, 4: Departamento).
     */
    public Mostrar(int tipo) {
        init(tipo, null);
    }

    private void init(int tipo, Object object) {

        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");

        JButton aceptar = new JButton("Aceptar");
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAceptarClicked(tipo);
            }
        });

        if (tipo == 1) {
            Profesor profesor = (Profesor) object;
            JLabel titulo = new JLabel("Datos de Profesor");
            titulo.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            titulo.setHorizontalAlignment(JTextField.CENTER);
            panel.add(titulo, "gapbottom 20, span");
            panel.add(new JSeparator(), "gapy 5 5");
            panel.add(MuestraId(tipo, profesor));
            panel.add(MuestraDepartamento(profesor));
            panel.add(MuestraDNI(profesor));
            panel.add(MuestraNombre(tipo, profesor, "Nombre"));
            panel.add(MuestraApellidos(profesor));
            panel.add(MuestraCorreo(profesor));
            panel.add(MuestraNivel(profesor));
            panel.add(MuestraActivo(tipo, profesor));
            panel.add(new JSeparator(), "gapy 5 5");
            panel.add(aceptar, "span");
        } else if (tipo == 2) {
            Grupo grupo = (Grupo) object;
            JLabel titulo = new JLabel("Datos de Grupo");
            titulo.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            titulo.setHorizontalAlignment(JTextField.CENTER);
            panel.add(titulo, "gapbottom 20, span");
            panel.add(new JSeparator(), "gapy 5 5");
            panel.add(MuestraId(tipo, grupo));
            panel.add(MuestraCurso(grupo));
            panel.add(MuestraCodigo(tipo, grupo));
            panel.add(MuestraNombre(tipo, grupo, "Alumnos"));
            panel.add(MuestraActivo(tipo, grupo));
            panel.add(new JSeparator(), "gapy 5 5");
            panel.add(aceptar, "span");

        } else if (tipo == 3) {
            Curso curso = (Curso) object;
            JLabel titulo = new JLabel("Datos de Curso");
            titulo.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            titulo.setHorizontalAlignment(JTextField.CENTER);
            panel.add(titulo, "gapbottom 20, span");
            panel.add(new JSeparator(), "gapy 5 5");
            panel.add(MuestraId(tipo, curso));
            panel.add(MuestraCodigo(tipo, curso));
            panel.add(MuestraNombre(tipo, curso, "Descripcion"));
            panel.add(MostrarEtapa(curso));
            panel.add(MuestraActivo(tipo, curso));
            panel.add(new JSeparator(), "gapy 5 5");
            panel.add(aceptar, "span");

        } else if (tipo == 4) {
            Departamento departamento = (Departamento) object;
            JLabel titulo = new JLabel("Datos de Departamento");
            titulo.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            titulo.setHorizontalAlignment(JTextField.CENTER);
            panel.add(titulo, "gapbottom 20, span");
            panel.add(new JSeparator(), "gapy 5 5");
            panel.add(MuestraId(tipo, departamento));
            panel.add(MuestraCodigo(tipo, departamento));
            panel.add(MuestraNombre(tipo, departamento, "Nombre"));
            panel.add(MuestraJefe(departamento));
            panel.add(new JSeparator(), "gapy 5 5");
            panel.add(aceptar, "span");
        }
        add(panel);
    }

    /**
     * Este método crea un componente que muestra el ID de un objeto.
     * Dependiendo del tipo de objeto (Profesor, Grupo, Curso, Departamento), se
     * obtiene el ID de diferentes maneras.
     *
     * @param tipo   El tipo de objeto (1 = Profesor, 2 = Grupo, 3 = Curso, 4 =
     *               Departamento).
     * @param object El objeto del que se obtendrá el ID.
     * @return Un componente que muestra el ID del objeto.
     */
    private Component MuestraId(int tipo, Object object) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel id = new JLabel("Id");
        if (tipo == 1) {
            Profesor profesor = (Profesor) object;
            idField = new JTextField((profesor == null) ? "" : String.valueOf(profesor.getId()));
        } else if (tipo == 2) {
            Grupo grupo = (Grupo) object;
            idField = new JTextField((grupo == null) ? "" : String.valueOf(grupo.getId()));
        } else if (tipo == 3) {
            Curso curso = (Curso) object;
            idField = new JTextField((curso == null) ? "" : String.valueOf(curso.getId()));
        } else if (tipo == 4) {
            Departamento departamento = (Departamento) object;
            idField = new JTextField((departamento == null) ? "" : String.valueOf(departamento.getId()));
        }
        idField.setEditable(false);
        idField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(id, "gapright 20, split 2");
        panel.add(idField, "width 200!, dock east");

        return panel;
    }

    /**
     * Este método crea un componente que muestra el código de un objeto.
     * Dependiendo del tipo de objeto (Grupo, Curso, Departamento), se obtiene el
     * código de diferentes maneras.
     *
     * @param tipo   El tipo de objeto (2 = Grupo, 3 = Curso, 4 = Departamento).
     * @param object El objeto del que se obtendrá el código.
     * @return Un componente que muestra el código del objeto.
     */
    private Component MuestraCodigo(int tipo, Object object) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel codigo = new JLabel("Codigo");
        if (tipo == 2) {
            Grupo grupo = (Grupo) object;
            codigoField = new JTextField((grupo == null) ? "" : grupo.getCodigo());
            setDocumentfilter(codigoField, 8);
        } else if (tipo == 3) {
            Curso curso = (Curso) object;
            codigoField = new JTextField((curso == null) ? "" : curso.getCodigo());
            setDocumentfilter(codigoField, 5);
        } else if (tipo == 4) {
            Departamento departamento = (Departamento) object;
            codigoField = new JTextField((departamento == null) ? "" : departamento.getCodigo());
            setDocumentfilter(codigoField, 3);
        }
        codigoField.setEditable(Login.user.getNivel().equalsIgnoreCase("Superusuario"));
        codigoField.setHorizontalAlignment(JTextField.RIGHT);

        panel.add(codigo, "split 2");
        panel.add(codigoField, "width 200!, dock east");

        return panel;
    }

    private Component MuestraNombre(int tipo, Object object, String tit) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel titulo = new JLabel(tit);
        if (tipo == 1) {
            Profesor profesor = (Profesor) object;
            nombreField = new JTextField((profesor == null) ? "" : String.valueOf(profesor.getNombre()));
            setDocumentfilter(nombreField, 45);
            nombreField.setEditable(Login.user.getNivel().equalsIgnoreCase("Superusuario"));
            nombreField.setHorizontalAlignment(JTextField.RIGHT);
            panel.add(titulo);
            panel.add(nombreField, "width 200!, dock east");
        } else if (tipo == 2) {
            Grupo grupo = (Grupo) object;
            alumnosField = new JTextField((grupo == null) ? "" : String.valueOf(grupo.getNum_alumnos()));
            setDocumentfilter(alumnosField, 2);
            alumnosField.setEditable(Login.user.getNivel().equalsIgnoreCase("Superusuario"));
            alumnosField.setHorizontalAlignment(JTextField.RIGHT);
            panel.add(titulo, "split 2");
            panel.add(alumnosField, "width 200!, dock east");
        } else if (tipo == 3) {
            Curso curso = (Curso) object;
            descripcionField = new JTextField((curso == null) ? "" : curso.getDescripcion());
            setDocumentfilter(descripcionField, 99);
            descripcionField.setEditable(Login.user.getNivel().equalsIgnoreCase("Superusuario"));
            descripcionField.setHorizontalAlignment(JTextField.RIGHT);
            panel.add(titulo, "split 2");
            panel.add(descripcionField, "width 200!, dock east");
        } else if (tipo == 4) {
            Departamento departamento = (Departamento) object;
            nombreField = new JTextField((departamento == null) ? "" : departamento.getNombre());
            setDocumentfilter(nombreField, 99);
            nombreField.setEditable(Login.user.getNivel().equalsIgnoreCase("Superusuario"));
            nombreField.setHorizontalAlignment(JTextField.RIGHT);
            panel.add(titulo, "split 2");
            panel.add(nombreField, "width 200!, dock east");
        }
        return panel;
    }

    private Component MuestraJefe(Departamento departamento) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel jefe = new JLabel("Jefe");
        jefeCombo = null;
        jefeField = null;
        ProfesorDAO profesorSQL = new ProfesorDAO();
        if (Login.user.getNivel().equalsIgnoreCase("Superusuario")) {
            jefeCombo = new JComboBox<String>();
            List<Profesor> profesores = profesorSQL.listar();
            int index = -1;
            Profesor profesor = departamento == null ? null
                    : departamento.getJefe();
            for (int i = 0; i < profesores.size(); i++) {
                jefeCombo.addItem(String.valueOf(profesores.get(i).getId()).concat("-")
                        .concat(profesores.get(i).getNombre()).concat(" ")
                        .concat(profesores.get(i).getApellidos()));
                if (departamento != null && profesores.get(i).equals(departamento.getJefe())) {
                    index = i;
                }
            }
            if (departamento != null && index != -1 && profesor != null) {
                jefeCombo.setSelectedIndex(index);
            } else {
                jefeCombo.setSelectedIndex(-1);
            }
        } else {
            jefeField = new JTextField(
                    (departamento == null) ? ""
                            : departamento.getJefe() == null ? ""
                                    : String.valueOf(departamento.getJefe().getId()).concat("-")
                                            .concat(departamento.getJefe().getNombre()).concat(" ")
                                            .concat(departamento.getJefe().getApellidos()));
            jefeField.setEditable(false);
            jefeField.setHorizontalAlignment(JTextField.RIGHT);
        }
        panel.add(jefe, "split 2");
        panel.add((jefeField == null) ? jefeCombo : jefeField, "width 200!, dock east");

        return panel;
    }

    private Component MostrarEtapa(Curso curso) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel etapa = new JLabel("Etapa");
        etapaCombo = null;
        etapaField = null;
        if (Login.user.getNivel().equalsIgnoreCase("Superusuario")) {
            etapaCombo = new JComboBox<String>();
            for (Etapa et : Etapa.values()) {
                etapaCombo.addItem(et.name());
            }
            if (curso != null) {
                etapaCombo.setSelectedItem(curso.getEtapa().name());
            } else {
                etapaCombo.setSelectedIndex(-1);
            }
        } else {
            etapaField = new JTextField((curso == null) ? "" : String.valueOf(curso.getEtapa()));
            etapaField.setEditable(false);
            etapaField.setHorizontalAlignment(JTextField.RIGHT);
        }
        panel.add(etapa, "split 2");
        panel.add((etapaField == null) ? etapaCombo : etapaField, "width 200!, dock east");

        return panel;
    }

    private Component MuestraActivo(int tipo, Object objetct) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel activo = new JLabel("Activo");
        JTextField activoField = null;
        activoRadio1 = null;
        activoRadio2 = null;
        if (Login.user.getNivel().equalsIgnoreCase("Superusuario")) {
            activoRadio1 = new JRadioButton("Si");
            activoRadio2 = new JRadioButton("No");
            ButtonGroup group = new ButtonGroup();
            group.add(activoRadio1);
            group.add(activoRadio2);
            panel.add(activo, "split 3, gapright 80");
            panel.add(activoRadio1, "gapright 50");
            panel.add(activoRadio2);
        } else {
            activoField.setEditable(false);
            activoField.setHorizontalAlignment(JTextField.RIGHT);
            panel.add(activo, "split 2");
            panel.add(activoField, "width 200!, dock east");
        }
        if (tipo == 1) {
            Profesor profesor = (Profesor) objetct;
            if (Login.user.getNivel().equalsIgnoreCase("Superusuario")) {
                if (profesor != null) {
                    if (profesor.isActivo()) {
                        activoRadio1.setSelected(true);
                    } else {
                        activoRadio2.setSelected(true);
                    }
                }
            } else {
                activoField = new JTextField(profesor.isActivo() ? "1" : "0");
            }
        } else if (tipo == 2) {
            Grupo grupo = (Grupo) objetct;
            if (Login.user.getNivel().equalsIgnoreCase("Superusuario")) {
                if (grupo != null) {
                    if (grupo.isActivo()) {
                        activoRadio1.setSelected(true);
                    } else {
                        activoRadio2.setSelected(true);
                    }
                }
            } else {
                activoField = new JTextField(grupo.isActivo() ? "1" : "0");
            }
        } else if (tipo == 3) {
            Curso curso = (Curso) objetct;
            if (Login.user.getNivel().equalsIgnoreCase("Superusuario")) {
                if (curso != null) {
                    if (curso.isActivo()) {
                        activoRadio1.setSelected(true);
                    } else {
                        activoRadio2.setSelected(true);
                    }
                }
            } else {
                activoField = new JTextField(curso.isActivo() ? "1" : "0");
            }
        }
        return panel;
    }

    private Component MuestraCurso(Grupo grupo) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel curso = new JLabel("Curso");
        cursoCombo = null;
        cursoField = null;
        CursosDAO cursosSQL = new CursosDAO();
        if (Login.user.getNivel().equalsIgnoreCase("Superusuario")) {
            cursoCombo = new JComboBox<String>();
            List<Curso> cursos = cursosSQL.listar();
            int index = -1;
            Curso curs = grupo == null ? null : grupo.getCurso();
            for (int i = 0; i < cursos.size(); i++) {
                cursoCombo.addItem(cursos.get(i).getCodigo());
                if (grupo != null && cursos.get(i).getId() == grupo.getCurso().getId()) {
                    index = i;
                }
            }
            if (grupo != null && index != -1 && curs != null) {
                cursoCombo.setSelectedIndex(index);
            } else {
                cursoCombo.setSelectedIndex(-1);
            }
        } else {
            cursoField = new JTextField(
                    (grupo == null) ? "" : String.valueOf(grupo.getCurso().getCodigo()));
            cursoField.setEditable(false);
            cursoField.setHorizontalAlignment(JTextField.RIGHT);
        }
        panel.add(curso, "split 2");
        panel.add((cursoField == null) ? cursoCombo : cursoField, "width 200!, dock east");

        return panel;
    }

    private Component MuestraNivel(Profesor profesor) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel nivel = new JLabel("Nivel");
        nivelCombo = null;
        nivelField = null;
        if (Login.user.getNivel().equalsIgnoreCase("Superusuario")) {
            nivelCombo = new JComboBox<String>();
            for (Nivel niv : Nivel.values()) {
                nivelCombo.addItem(niv.name());
            }
            if (profesor != null) {
                nivelCombo.setSelectedItem(profesor.getNivel().name());
            } else {
                nivelCombo.setSelectedIndex(-1);
            }
        } else {
            nivelField = new JTextField((profesor == null) ? "" : String.valueOf(profesor.getNivel()));
            nivelField.setEditable(false);
            nivelField.setHorizontalAlignment(JTextField.RIGHT);
        }
        panel.add(nivel, "split 2");
        panel.add((nivelField == null) ? nivelCombo : nivelField, "width 200!, dock east");

        return panel;
    }

    private Component MuestraDNI(Profesor profesor) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel dni = new JLabel("Dni");
        dniField = new JTextField((profesor == null) ? "" : profesor.getDni());
        setDocumentfilter(dniField, 9);
        dniField.setEditable(Login.user.getNivel().equalsIgnoreCase("Superusuario"));
        dniField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(dni, "split 2");
        panel.add(dniField, "width 200!, dock east");

        return panel;
    }

    private Component MuestraApellidos(Profesor profesor) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel apellidos = new JLabel("Apellidos");
        apellidosField = new JTextField((profesor == null) ? "" : profesor.getApellidos());
        setDocumentfilter(apellidosField, 45);
        apellidosField.setEditable(Login.user.getNivel().equalsIgnoreCase("Superusuario"));
        apellidosField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(apellidos, "split 2");
        panel.add(apellidosField, "width 200!, dock east");

        return panel;
    }

    private Component MuestraCorreo(Profesor profesor) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel correo = new JLabel("Correo");
        correoField = new JTextField((profesor == null) ? "" : profesor.getCorreo());
        setDocumentfilter(correoField, 80);
        correoField.setEditable(Login.user.getNivel().equalsIgnoreCase("Superusuario"));
        correoField.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(correo, "split 2");
        panel.add(correoField, "width 200!, dock east");

        return panel;
    }

    private Component MuestraDepartamento(Profesor profesor) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JLabel departamento = new JLabel("Departamento");
        departamentoCombo = null;
        departamentoField = null;
        DepartamentoDAO departamentosSQL = new DepartamentoDAO();
        if (Login.user.getNivel().equalsIgnoreCase("Superusuario")) {
            departamentoCombo = new JComboBox<String>();
            List<Departamento> departamentos = departamentosSQL.listar();
            int index = -1;
            Departamento dep = profesor == null ? null : departamentosSQL.buscar(profesor.getDepartamento().getId());
            for (int i = 0; i < departamentos.size(); i++) {
                departamentoCombo.addItem(departamentos.get(i).getCodigo());
                if (profesor != null && departamentos.get(i).getId() == profesor.getDepartamento().getId()) {
                    index = i;
                }
            }
            if (profesor != null && index != -1 && dep != null) {
                departamentoCombo.setSelectedIndex(index);
            } else {
                departamentoCombo.setSelectedIndex(-1);
            }
        } else {
            departamentoField = new JTextField(
                    (profesor == null) ? ""
                            : departamentosSQL.buscar(profesor.getDepartamento().getId()) == null ? ""
                                    : departamentosSQL.buscar(profesor.getDepartamento().getId()).getCodigo());
            departamentoField.setEditable(false);
            departamentoField.setHorizontalAlignment(JTextField.RIGHT);
        }
        panel.add(departamento, "split 2");
        panel.add((departamentoField == null) ? departamentoCombo : departamentoField, "width 200!, dock east");

        return panel;
    }

    private void setDocumentfilter(JTextField campo, int caracteres) {
        DocumentFilter filter = Utils.limitaCaracteres(caracteres);
        ((PlainDocument) campo.getDocument()).setDocumentFilter(filter);
    }

    private void onAceptarClicked(int tipo) {
        DepartamentoDAO departamentoSQL = new DepartamentoDAO();
        ProfesorDAO profesorSQL = new ProfesorDAO();
        CursosDAO cursoSQL = new CursosDAO();
        GruposDAO grupoSQL = new GruposDAO();

        if (tipo == 1) {
            String idValue = idField.getText();
            String dniValue = dniField.getText();
            String nombreValue = nombreField.getText().trim();
            String apellidosValue = apellidosField.getText().trim();
            String correoValue = correoField.getText();
            String nivelValue = (nivelField == null)
                    ? nivelCombo.getSelectedIndex() == -1 ? null : nivelCombo.getSelectedItem().toString()
                    : nivelField.getText();
            String departamentoValue = (String) departamentoCombo.getSelectedItem();

            if (validaProfesor(dniValue, nombreValue, apellidosValue, correoValue, nivelValue, departamentoValue)) {
                Profesor profe;
                if (Integer.parseInt(idValue.equals("") ? "0" : idValue) > 0) {
                    profe = new Profesor(Integer.parseInt(idValue), dniValue, nombreValue,
                            apellidosValue,
                            correoValue,
                            profesorSQL.buscar(Integer.valueOf(idValue)).getPassword() == null ? null
                                    : profesorSQL.buscar(Integer.valueOf(idValue)).getPassword(),
                            Nivel.valueOf(nivelValue), activoValue.equals("1") ? true : false,
                            profesorSQL.buscar(Integer.valueOf(idValue)).getFoto() == null ? null
                                    : profesorSQL.buscar(Integer.valueOf(idValue)).getFoto(),
                            departamentoSQL.buscar(departamentoValue));
                } else {
                    profe = new Profesor(dniValue, nombreValue,
                            apellidosValue,
                            correoValue,
                            null, Nivel.valueOf(nivelValue), activoValue.equals("1") ? true : false, null,
                            departamentoSQL.buscar(departamentoValue));
                }
                JOptionPane.showMessageDialog(null, "Se ha actualizado el profesor con éxito.");
                VentanaSingleton.getInstance().cerrarVentana("Modificar Profesor");
                profesorSQL.guardar(profe);
            }
        } else if (tipo == 2) {
            String idValue = idField.getText();
            String cursoValue = (String) cursoCombo.getSelectedItem();
            String codigoValue = codigoField.getText();
            String alumnosValue = alumnosField.getText();

            if (validaGrupo(codigoValue, alumnosValue, cursoValue)) {
                Grupo grupo;
                if (Integer.parseInt(idValue.equals("") ? "0" : idValue) > 0) {
                    grupo = new Grupo(Integer.parseInt(idValue), cursoSQL.buscar(cursoValue), codigoValue,
                            Integer.parseInt(alumnosValue),
                            activoValue.equals("1") ? true : false);
                } else {
                    grupo = new Grupo(cursoSQL.buscar(cursoValue), codigoValue, Integer.parseInt(alumnosValue),
                            activoValue.equals("1") ? true : false);
                }
                JOptionPane.showMessageDialog(null, "Se ha actualizado el grupo con éxito.");
                VentanaSingleton.getInstance().cerrarVentana("Modificar Grupos");
                grupoSQL.guardar(grupo);
            }
        } else if (tipo == 3) {
            String idValue = idField.getText();
            String codigoValue = codigoField.getText();
            String descripcionValue = descripcionField.getText();
            String etapaValue = (etapaField == null)
                    ? etapaCombo.getSelectedIndex() == -1 ? null : etapaCombo.getSelectedItem().toString()
                    : etapaField.getText();

            if (validaCurso(codigoValue, descripcionValue, etapaValue)) {
                Curso curso;
                if (Integer.parseInt(idValue.equals("") ? "0" : idValue) > 0) {
                    curso = new Curso(Integer.parseInt(idValue), codigoValue, descripcionValue,
                            Etapa.valueOf(etapaValue),
                            activoValue.equals("1") ? true : false);
                } else {
                    curso = new Curso(codigoValue, descripcionValue, Etapa.valueOf(etapaValue),
                            activoValue.equals("1") ? true : false);
                }
                JOptionPane.showMessageDialog(null, "Se ha actualizado el curso con éxito.");
                VentanaSingleton.getInstance().cerrarVentana("Modificar Cursos");
                cursoSQL.guardar(curso);
            }

        } else if (tipo == 4) {
            String idValue = idField.getText();
            String codigoValue = codigoField.getText();
            String nombreValue = nombreField.getText();
            String jefeValue = (String) jefeCombo.getSelectedItem();
            int id_jefe = Integer.parseInt(jefeValue.split("-")[0]);

            if (validaDepartamento(codigoValue, nombreValue, jefeValue)) {
                Departamento departamento;
                if (Integer.parseInt(idValue.equals("") ? "0" : idValue) > 0) {
                    departamento = new Departamento(Integer.parseInt(idValue), codigoValue, nombreValue,
                            profesorSQL.buscar(id_jefe));
                } else {
                    departamento = new Departamento(codigoValue, nombreValue,
                            profesorSQL.buscar(id_jefe));
                }
                JOptionPane.showMessageDialog(null, "Se ha actualizado el departamento con éxito.");
                VentanaSingleton.getInstance().cerrarVentana("Modificar Departamentos");
                departamentoSQL.guardar(departamento);
            }

        }
    }

    private boolean validaActivo() {
        if (activoRadio1.isSelected() == true || activoRadio2.isSelected() == true) {
            activoValue = activoRadio1.isSelected() ? "1" : "0";
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar si está activo.");
            return false;
        }
    }

    private boolean validaDepartamento(String codigo, String nombre, String jefe) {
        if (!codigo.matches("^[A-Z]{2,}$")) {
            JOptionPane.showMessageDialog(null, "Código no válido.");
            return false;
        } else if (!nombre.matches("^([A-Z][a-záéíóú]*)(\\s[A-Za-záéíóú]*)*$")) {
            JOptionPane.showMessageDialog(null, "Nombre no válido.");
            return false;
        } else if (jefe == null) {
            JOptionPane.showMessageDialog(null, "Jefe no válido.");
            return false;
        }
        return true;
    }

    private boolean validaCurso(String codigo, String descripcion, String etapa) {
        if (validaActivo()) {
            if (!codigo.matches("^[A-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Código no válido.");
                return false;
            } else if (!descripcion.matches("^([A-Z][a-záéíóú]*)(\\s[A-Za-záéíóú]*)*$")) {
                JOptionPane.showMessageDialog(null, "Descripción no válida.");
                return false;
            } else if (etapa == null) {
                JOptionPane.showMessageDialog(null, "Etapa no válida.");
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean validaGrupo(String codigo, String alumnos, String curso) {
        if (validaActivo()) {
            if (!codigo.matches("^[A-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Código no válido.");
                return false;
            } else if (!alumnos.matches("^[0-9]{1,2}$")) {
                JOptionPane.showMessageDialog(null, "Numero de alumnos no válido.");
                return false;
            } else if (curso == null) {
                JOptionPane.showMessageDialog(null, "Curso no válido.");
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean validaProfesor(String dni, String nombre, String apellidos, String correo, String nivel,
            String departamento) {
        if (validaActivo()) {
            if (!dni.matches("^[0-9]{8}[A-Z]{1}$") || !Utils.validarDNI(dni)) {
                JOptionPane.showMessageDialog(null, "DNI no válido.");
                return false;
            } else if (!nombre.matches("^([A-Z][a-záéíóú]*)(\\s[A-Z][a-záéíóú]*)?$")) {
                JOptionPane.showMessageDialog(null, "Nombre no válido.");
                return false;
            } else if (!apellidos.matches("^([A-Z][a-záéíóú]*)(\\s[A-Z][a-záéíóú]*)*$")) {
                JOptionPane.showMessageDialog(null, "Apellidos no válidos.");
                return false;
            } else if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                JOptionPane.showMessageDialog(null, "Correo no válido.");
                return false;
            } else if (nivel == null) {
                JOptionPane.showMessageDialog(null, "Nivel no válido.");
                return false;
            } else if (departamento == null) {
                JOptionPane.showMessageDialog(null, "Departamento no válido.");
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}