package reto.app;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import reto.components.MainForm;
import javax.swing.*;
import java.awt.*;

/**
 * La clase `app` es la clase principal de la aplicación.
 * Extiende de `JFrame` y representa la ventana principal de la aplicación.
 */
public class app extends JFrame {

    private MainForm mainform = new MainForm();

    /**
     * Constructor de la clase `app`.
     * Inicializa la ventana principal de la aplicación llamando al método `init()`.
     */
    public app() {
        init();
    }

    /**
     * Método privado que inicializa la ventana principal de la aplicación.
     * Configura las propiedades de la ventana, el tamaño, la ubicación y agrega el
     * formulario principal.
     */
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        setSize(new Dimension(1680, 1060));
        setLocationRelativeTo(null);
        getContentPane().add(mainform);
    }

    /**
     * Método estático que se ejecuta al iniciar la aplicación.
     * Configura la fuente por defecto, el aspecto visual y muestra la ventana
     * principal de la aplicación.
     * 
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatDarculaLaf.setup();
        EventQueue.invokeLater(() -> new app().setVisible(true));
    }
}
