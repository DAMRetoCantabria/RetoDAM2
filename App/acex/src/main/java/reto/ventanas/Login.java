package reto.ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import reto.app.User;
import reto.components.MainForm;
import reto.objects.Nivel;
import reto.objects.Profesor;
import reto.sql.DepartamentoDAO;
import reto.sql.MySQL;
import reto.utilidades.Utils;

public class Login extends JPanel {

    private MainForm mainForm;
    private JTextField usuario;
    private JPasswordField password;
    private JCheckBox recordar;
    private JButton login;
    private JLabel errorLabel;
    public static User user;
    private DepartamentoDAO departamentoSQL = new DepartamentoDAO();

    public Login(MainForm mainForm) {
        this.mainForm = mainForm;
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        usuario = new JTextField();
        password = new JPasswordField();
        recordar = new JCheckBox("Recordar");
        login = new JButton("Iniciar Sesion");
        DocumentFilter filter;

        ActionListener loginListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        };
        login.addActionListener(loginListener);

        filter = Utils.limitaCaracteres(40);
        ((PlainDocument) usuario.getDocument()).setDocumentFilter(filter);
        filter = Utils.limitaCaracteres(20);
        ((PlainDocument) password.getDocument()).setDocumentFilter(filter);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");

        usuario.addActionListener(loginListener);
        usuario.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Introduzca su correo electronico");

        password.addActionListener(loginListener);
        password.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Introduzca su contraseña");
        password.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");

        JLabel titulo = new JLabel("Inicio de Sesion");
        titulo.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");

        JLabel descripcion = new JLabel("Por favor ingrese sus credenciales");
        descripcion.putClientProperty(FlatClientProperties.STYLE,
                "" + "[dark]foreground:darken(@foreground, 30%)");

        panel.add(titulo, "gapleft 60");
        panel.add(descripcion, "gapleft 45");
        panel.add(new JLabel("Usuario"), "gapy 8");
        panel.add(usuario);
        panel.add(new JLabel("Contraseña"), "gapy 8");
        panel.add(password);
        panel.add(recordar, "grow 0");
        panel.add(login, "gapy 10");
        panel.add(errorLabel, "gapy 10");

        add(panel);
    }

    private void login() {
        String usuario = this.usuario.getText();
        String password = new String(this.password.getPassword());

        if (Utils.isValidLogin(usuario)) {
            try {
                MySQL mysql = MySQL.getInstance();
                Connection connection = mysql.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(
                                "SELECT nombre, apellidos, nivel, foto, id FROM profesores WHERE correo = ? AND password = ? AND activo = 1");
                statement.setString(1, usuario);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String apellidos = resultSet.getString("apellidos");
                    String nivel = resultSet.getString("nivel");
                    String foto = resultSet.getString("foto");
                    int id = resultSet.getInt("id");
                    user = new User(nombre, apellidos, nivel, foto, id);
                    mainForm.setLogin(1);
                } else {
                    statement = connection.prepareStatement(
                            "SELECT * FROM profesores WHERE correo = ? AND password IS NULL AND ACTIVO = 1");
                    statement.setString(1, usuario);
                    resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        Profesor profesor = new Profesor(resultSet.getInt("id"), resultSet.getString("dni"),
                                resultSet.getString("nombre"), resultSet.getString("apellidos"),
                                resultSet.getString("correo"),
                                resultSet.getString("password"), resultSet.getString("nivel") == null ? Nivel.Profesor
                                        : Nivel.valueOf(resultSet.getString("nivel")),
                                resultSet.getBoolean("activo"), resultSet.getString("foto"),
                                departamentoSQL.buscar(resultSet.getInt("dep_id")));
                        mainForm.setNuevaCuenta(profesor);
                        mainForm.setLogin(2);
                    } else {
                        errorLabel.setText("Usuario o contraseña incorrecta");
                        mysql = null;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            errorLabel.setText("Debes introducir un usuario y contraseña validos");
        }
    }

}
