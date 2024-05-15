package reto.ventanas;

import java.awt.Dimension;
import javax.swing.*;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.swing.AvatarIcon;
import reto.components.*;
import reto.sql.*;

/**
 * La clase Perfil representa una ventana que muestra el perfil de un usuario.
 * Esta clase extiende de JPanel y utiliza el gestor de diseño MigLayout para
 * organizar los componentes.
 */
public class Perfil extends JPanel {
    /**
     * Crea una nueva instancia de la clase Perfil.
     * Se llama al método init() para inicializar la ventana.
     */
    public Perfil() {
        init();
    }

    /**
     * Inicializa la ventana del perfil.
     * Configura el gestor de diseño, crea y configura los componentes de la
     * ventana,
     * y los agrega al panel principal.
     */
    private void init() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));

        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;" + "[dark]background:lighten(@background, 3%)");

        JButton foto = new JButton();
        JLabel nombre = new JLabel();
        JLabel nivel = new JLabel();
        JButton contraseña = new JButton("Cambiar contraseña");
        contraseña.addActionListener(e -> {
            ProfesorDAO profesorSQL = new ProfesorDAO();
            VentanaSingleton.getInstance().mostrarVentana("Cambio de Contraseña",
                    new Contrasena(null, profesorSQL.buscar(Login.user.getId())),
                    new Dimension(700, 700));
        });

        Icon icon = new AvatarIcon(getClass().getResource(Login.user.getFoto()), 100, 100, 999);

        foto.setIcon(icon);
        nombre.setText(Login.user.getNombre());
        nivel.setText(Login.user.getNivel());

        panel.add(foto,
                "span, align 50% 50%, wrap, width " + icon.getIconWidth() + "!, height " + icon.getIconHeight()
                        + "!");
        panel.add(nombre, "span, align 50% 50%, wrap");
        panel.add(nivel, "span, align 50% 50%, wrap");
        panel.add(contraseña, "span, align 50% 50%, wrap");

        add(panel);
    }
}