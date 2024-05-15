package reto.components;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase VentanaSingleton representa una ventana de la aplicación que sigue
 * el patrón Singleton.
 * Solo puede haber una instancia de esta clase en todo el programa.
 */
public class VentanaSingleton {

    private static VentanaSingleton instance;
    private JDesktopPane ventana;
    private Map<String, JInternalFrame> ventanas = new HashMap<>(); // Mapa de ventanas

    /**
     * Obtiene la instancia única de VentanaSingleton.
     * Si no existe una instancia, se crea una nueva.
     *
     * @return La instancia única de VentanaSingleton.
     */
    public static VentanaSingleton getInstance() {
        if (instance == null) {
            instance = new VentanaSingleton();
        }
        return instance;
    }

    private VentanaSingleton() {
        // Constructor privado para evitar la creación de instancias directamente.
    }

    /**
     * Establece el panel de escritorio de la ventana.
     *
     * @param ventana El panel de escritorio de la ventana.
     */
    public void setVentana(JDesktopPane ventana) {
        this.ventana = ventana;
    }

    /**
     * Obtiene el panel de escritorio de la ventana.
     *
     * @return El panel de escritorio de la ventana.
     */
    public JDesktopPane getVentana() {
        return ventana;
    }

    /**
     * Muestra una nueva ventana interna con el título y contenido especificados.
     *
     * @param titulo    El título de la ventana interna.
     * @param contenido El componente que se mostrará dentro de la ventana interna.
     * @param size      El tamaño de la ventana interna.
     */
    public void mostrarVentana(String titulo, Component contenido, Dimension size) {
        JInternalFrame frame = new JInternalFrame(titulo, true, true, true, true);
        frame.setSize(size);
        frame.add(contenido);
        frame.pack();
        frame.setVisible(true);
        ventana.add(frame, 0);
        ventanas.put(titulo, frame); // Guarda la ventana en el mapa
    }

    /**
     * Este método se utiliza para cerrar una ventana interna (JInternalFrame)
     * y eliminarla del mapa de ventanas. La ventana a cerrar se identifica
     * por su título.
     *
     * @param titulo El título de la ventana que se va a cerrar.
     */
    public void cerrarVentana(String titulo) {
        JInternalFrame frame = ventanas.get(titulo);
        if (frame != null) {
            frame.dispose();
            ventanas.remove(titulo); // Elimina la ventana del mapa
        }
    }
}
