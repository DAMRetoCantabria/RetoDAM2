package reto.components;

import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurBackground;
import reto.ventanas.Login;
import reto.objects.Profesor;
import reto.ventanas.Contrasena;
import java.awt.Dimension;
import javax.swing.*;

/**
 * La clase MainForm representa el formulario principal de la aplicación.
 * Extiende de BlurBackground para aplicar un efecto de desenfoque al fondo.
 */
public class MainForm extends BlurBackground {

    private static int login = 0;
    private static Menu menu;
    private static JDesktopPane ventana;
    private Profesor nuevaCuenta;

    /**
     * Constructor de la clase MainForm.
     * Configura la imagen de fondo y llama al método init().
     */
    public MainForm() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/fondo.jpeg"));
        setImage(icon.getImage());
        init();
    }

    /**
     * Inicializa el formulario principal.
     * Borra todos los componentes existentes, crea un nuevo JDesktopPane y agrega
     * los componentes según el estado de login.
     */
    private void init() {
        removeAll();
        ventana = new JDesktopPane();
        ventana.setLayout(null);
        ventana.setOpaque(false);
        if (login == 0) {
            setLayout(new MigLayout("center, insets 300 6 6 6", "[fill]", "[fill]"));
            Login loginPanel = new Login(this);
            Dimension size = loginPanel.getPreferredSize();
            loginPanel.setBounds((ventana.getWidth() - size.width) / 2, (ventana.getHeight() - size.height) / 2,
                    size.width, size.height);
            add(loginPanel);
        } else if (login == 1) {
            setLayout(new MigLayout("fill, insets 30 6 6 6", "[fill]", "[fill]"));
            menu = new Menu(this);
            VentanaSingleton.getInstance().setVentana(ventana);
            add(menu, "dock west, gap 6 6 6 6 , width 280!");
        } else {
            setLayout(new MigLayout("center, insets 300 6 6 6", "[fill]", "[fill]"));
            Contrasena contrasena = new Contrasena(this, nuevaCuenta);
            Dimension size = contrasena.getPreferredSize();
            contrasena.setBounds((ventana.getWidth() - size.width) / 2, (ventana.getHeight() - size.height) / 2,
                    size.width, size.height);
            add(contrasena);
        }

        add(ventana);
    }

    /**
     * Establece el estado de login.
     * 
     * @param login El estado de la pantalla de inicio.
     * 
     *              0 para la pantalla de inicio, 1 para la pantalla principal y 2
     *              para la pantalla de cambio de contraseña.
     */
    public void setLogin(int login) {
        MainForm.login = login;
        init();
        revalidate();
        repaint();
    }

    /**
     * Establece la nueva cuenta de profesor.
     * 
     * @param nuevaCuenta La nueva cuenta de profesor a establecer.
     */
    public void setNuevaCuenta(Profesor nuevaCuenta) {
        this.nuevaCuenta = nuevaCuenta;
    }

    /**
     * Obtiene el JDesktopPane de la ventana principal.
     * 
     * @return El JDesktopPane de la ventana principal.
     */
    public JDesktopPane getVentana() {
        return MainForm.ventana;
    }

}
