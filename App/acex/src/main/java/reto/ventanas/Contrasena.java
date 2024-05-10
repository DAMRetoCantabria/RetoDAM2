package reto.ventanas;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import reto.objects.Profesor;
import reto.sql.ProfesorDAO;
import reto.utilidades.Utils;
import reto.components.*;

public class Contrasena extends JPanel {

    private JLabel contrasena;
    private JLabel text_anterior;
    private JPasswordField contrasena_anterior;
    private JPasswordField contrasena1;
    private FuerzaPasswordComponente fuerza;
    private JLabel contrasena2;
    private JPasswordField contrasena3;
    private JButton aceptar;
    private JButton cancelar;
    private MainForm mainForm;
    private Profesor profesor;
    private ProfesorDAO profesorDAO;
    private JLabel titulo;

    public Contrasena(MainForm mainform, Profesor profesor) {
        this.mainForm = mainform;
        this.profesor = profesor;
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        profesorDAO = new ProfesorDAO();
        text_anterior = new JLabel("Introduzca su contraseña actual");
        contrasena_anterior = new JPasswordField();
        contrasena = new JLabel("Introduzca su nueva contraseña");
        contrasena1 = new JPasswordField();
        contrasena2 = new JLabel("Repita su nueva contraseña");
        contrasena3 = new JPasswordField();
        fuerza = new FuerzaPasswordComponente();
        DocumentFilter filter;

        filter = Utils.limitaCaracteres(32);
        ((PlainDocument) contrasena1.getDocument()).setDocumentFilter(filter);
        ((PlainDocument) contrasena3.getDocument()).setDocumentFilter(filter);

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");

        contrasena_anterior.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        contrasena1.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        contrasena3.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");

        contrasena_anterior.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Introduzca su contraseña actual");
        contrasena1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Introduzca su contraseña");
        contrasena3.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Repita su contraseña");

        if (mainForm != null) {
            titulo = new JLabel("Creacion de Contraseña");
        } else {
            titulo = new JLabel("Cambio de Contraseña");
        }

        titulo.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");

        fuerza.initPasswordField(contrasena1);

        panel.add(titulo, "gapy 5 30, gapleft 20");
        panel.add(new JSeparator(), "gapy 5 30");
        if (mainForm == null) {
            panel.add(text_anterior);
            panel.add(contrasena_anterior, "gapy 5 10");
            panel.add(new JSeparator(), "gapy 5 30");
        }
        panel.add(contrasena);
        panel.add(contrasena1, "gapy 5 10");
        panel.add(fuerza, "gapy 5 5");
        panel.add(contrasena2);
        panel.add(contrasena3, "gapy 5 30");
        panel.add(creaBotones());
        add(panel);
    }

    public Component creaBotones() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(e -> aceptar());
        panel.add(aceptar);
        if (mainForm != null) {
            cancelar = new JButton("Cancelar");
            cancelar.addActionListener(e -> mainForm.setLogin(0));
            panel.add(cancelar);
        }

        return panel;
    }

    private void aceptar() {
        if (contrasena1.getPassword().length == 0 || contrasena3.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Debes introducir los dos campos de contraseña.");
        } else if (contrasena1.getPassword().length < 8) {
            JOptionPane.showMessageDialog(null, "La contraseña tiene que tener una longitud minima de 8 caracteres..");
        } else if (!String.valueOf(contrasena1.getPassword()).equals(String.valueOf(contrasena3.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
        } else if (fuerza.getFuerza() < 2) {
            JOptionPane.showMessageDialog(null,
                    "Debes introducir una contraseña mas segura. Prueba a añadir mayusculas, minusculas, numeros y caracteres especiales");
        } else {
            if (mainForm == null) {
                if (!String.valueOf(contrasena_anterior.getPassword()).equals(profesor.getPassword())) {
                    JOptionPane.showMessageDialog(null, "La contraseña actual no es correcta.");
                    return;
                }

            }
            profesor.setPassword(String.valueOf(contrasena1.getPassword()));
            if (profesorDAO.guardar(profesor)) {
                JOptionPane.showMessageDialog(null, "Se ha actualizado la contraseña con éxito.");
                if (mainForm != null) {
                    mainForm.setLogin(0);
                } else {
                    VentanaSingleton.getInstance().cerrarVentana("Cambio de Contraseña");
                }
            }
        }

    }

}
