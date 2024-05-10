package reto.ventanas;

import com.formdev.flatlaf.FlatClientProperties;
import com.opencsv.*;
import net.miginfocom.swing.MigLayout;
import reto.objects.Profesor;
import reto.sql.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Carga extends JPanel {

    private JLabel texto1;
    private JLabel texto2;
    private JButton buscar;
    private JButton update;
    private String selectedFileName = "<html><font color='red'> (archivo.csv) </font></html>";
    private File archivoSeleccionado;
    private int repetidos;
    private int introducidos;
    private JLabel resultado;

    public Carga() {
        init();
    }

    private void init() {
        resultado = new JLabel("");
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "[fill, 350]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");

        JLabel titulo = new JLabel("Carga de Datos de Profesores");
        titulo.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");

        update = new JButton("Actualizar");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (archivoSeleccionado != null) {
                    repetidos = 0;
                    introducidos = 0;
                    if (csvValido(archivoSeleccionado)) {
                        updateaConCsv(archivoSeleccionado);
                    } else {
                        JOptionPane.showMessageDialog(null, "El archivo seleccionado no es un archivo CSV válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún archivo.");
                }
            }
        });

        panel.add(titulo, "align center, gapleft 50, gapbottom 20");
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(creaBoton(), "width 100!, height 100!, align center");
        panel.add(new JSeparator(), "gapy 50 5");
        panel.add(crearEstado());
        panel.add(resultado, "gapleft 120, gapright 100, span");
        panel.add(update);
        add(panel);
    }

    private Component creaBoton() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        buscar = new JButton(new ImageIcon(getClass().getResource("/icons/disquette.png")));
        buscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedFileName = selectedFile.getName();
                    texto2.setText("<html><font color='green'>" + selectedFileName + "</font></html>");
                    archivoSeleccionado = selectedFile;
                    repaint();
                }
            }
        });
        panel.add(buscar);

        return panel;
    }

    private Component crearEstado() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        texto1 = new JLabel("Seleccione un archivo .csv para subir");
        texto2 = new JLabel(selectedFileName);
        panel.add(texto1);
        panel.add(texto2, "dock east, gapleft 100, gapright 10, align right, wrap");
        return panel;
    }

    private void updateaConCsv(File selectedFile) {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(selectedFile))
                    .withCSVParser(new CSVParserBuilder().withSeparator(',').build())
                    .build();
            String[] nextLine;
            int cabecera = 0;
            ponerACero();
            while ((nextLine = reader.readNext()) != null) {
                if (cabecera != 0 && nextLine.length == 4) {
                    String apellidos = null;
                    String nombre = nextLine[0];
                    String[] corta = nombre.split("[,]", 0);
                    for (int i = 0; i < corta.length; i++) {
                        if (i == 0) {
                            apellidos = corta[i].toString().trim();
                        } else {
                            nombre = corta[i].toString().trim();
                        }
                    }
                    String dni = nextLine[1];
                    String correo = nextLine[2];
                    int departamento = Integer.parseInt(nextLine[3]);

                    MySQL mysql = MySQL.getInstance();
                    Connection connection = mysql.getConnection();

                    PreparedStatement checkStatement = connection
                            .prepareStatement("SELECT COUNT(*), id FROM profesores WHERE correo = ?");
                    checkStatement.setString(1, correo);
                    ResultSet resultSet = checkStatement.executeQuery();
                    resultSet.next();
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        ProfesorDAO profesorDAO = new ProfesorDAO();
                        Profesor profe = profesorDAO.buscar(resultSet.getInt("id"));
                        profe.setActivo(true);
                        profesorDAO.guardar(profe);
                        repetidos++;
                        resultado.setText("Introducidos: " + introducidos + " | Repetidos: " + repetidos);
                        continue;
                    } else {
                        PreparedStatement statement = connection
                                .prepareStatement(
                                        "INSERT INTO profesores (dni, nombre, apellidos, correo, dep_id, activo) VALUES (?, ?, ?, ?, ?, ?)");
                        statement.setString(1, dni);
                        statement.setString(2, nombre);
                        statement.setString(3, apellidos);
                        statement.setString(4, correo);
                        statement.setInt(5, departamento);
                        statement.setBoolean(6, true);
                        statement.executeUpdate();
                        introducidos++;
                        resultado.setText("Introducidos: " + introducidos + " | Repetidos: " + repetidos);
                    }
                } else {
                    cabecera++;
                }
            }
            JOptionPane.showMessageDialog(null, "Datos cargados con éxito.");
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean csvValido(File selectedFile) {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(selectedFile))
                    .withCSVParser(new CSVParserBuilder().withSeparator(',').build())
                    .build();
            String[] headers = reader.readNext();

            if (headers.length != 4 || !headers[0].equals("apellidosnombre") || !headers[1].equals("dni")
                    || !headers[2].equals("mail") || !headers[3].equals("departamento")) {
                reader.close();
                return false;
            }

            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void ponerACero() {
        ProfesorDAO profesorDAO = new ProfesorDAO();
        profesorDAO.listar().forEach(profesor -> {
            profesor.setActivo(false);
            profesorDAO.guardar(profesor);
        });
    }
}
