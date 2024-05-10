package reto.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;
import reto.utilidades.Utils;

/**
 * El componente FuerzaPasswordComponente es un panel que muestra la fuerza de
 * una contraseña.
 * La fuerza de la contraseña se determina según su longitud y complejidad.
 * El componente muestra una barra de colores que indica la fuerza de la
 * contraseña.
 * Además, muestra un texto descriptivo que indica si la contraseña es débil,
 * media o fuerte.
 */
public class FuerzaPasswordComponente extends JPanel {

    @SuppressWarnings("unused")
    private JPasswordField password;
    private DocumentListener listener;
    private JLabel desc;
    private int fuerza;

    /**
     * Crea una instancia de FuerzaPasswordComponente.
     * Se inicializan los componentes.
     */
    public FuerzaPasswordComponente() {
        init();
    }

    /**
     * Inicializa el componente FuerzaPasswordComponente.
     * Configura las propiedades del panel, establece el diseño del panel y crea e
     * inicializa los componentes que se van a añadir al panel.
     * Los componentes añadidos al panel son un JLabel para mostrar la descripción
     * de la fuerza de la contraseña y un descStatus para mostrar la barra de fuerza
     * de la contraseña.
     */
    private void init() {
        putClientProperty(FlatClientProperties.STYLE, "background:null");
        setLayout(new MigLayout("fill, insets 0", "3[100, fill, grow0][]", "[fill, grow 0]"));
        desc = new JLabel("none");
        desc.setVisible(false);
        add(new descStatus());
        add(desc);
    }

    /**
     * Comprueba la fuerza de la contraseña proporcionada y actualiza la interfaz de
     * usuario en consecuencia.
     * Si la contraseña está vacía, se oculta la descripción de la fuerza de la
     * contraseña.
     * Si la contraseña no está vacía, se muestra la descripción de la fuerza de la
     * contraseña y se actualiza con un mensaje y un color correspondientes a la
     * fuerza de la contraseña.
     * 
     * @param password la contraseña a comprobar
     */
    private void checkPassword(String password) {
        this.fuerza = password.isEmpty() ? 0 : Utils.comprobarFuerzaPassword(password);
        if (fuerza == 0) {
            desc.setText("none");
            desc.setVisible(false);
        } else {
            desc.setVisible(true);
            if (fuerza == 1) {
                desc.setText("Contraseña debil");
            } else if (fuerza == 2) {
                desc.setText("Contraseña media");
            } else {
                desc.setText("Contraseña fuerte");
            }
            desc.setForeground(seleccionaColor(fuerza));
        }
        repaint();
    }

    /**
     * Selecciona el color correspondiente al nivel de fuerza de la contraseña.
     * 
     * @param fuerza el nivel de fuerza de la contraseña
     * @return el color correspondiente al nivel de fuerza
     */
    private Color seleccionaColor(int fuerza) {
        if (fuerza == 1) {
            return Color.RED;
        } else if (fuerza == 2) {
            return Color.ORANGE;
        } else {
            return Color.GREEN;
        }
    }

    /**
     * Inicializa el campo de contraseña.
     * Se agrega un DocumentListener al campo de contraseña para detectar cambios en
     * el texto.
     * Cuando se detecta un cambio, se llama al método checkPassword para actualizar
     * la fuerza de la contraseña.
     * 
     * @param password el campo de contraseña
     */
    public void initPasswordField(JPasswordField password) {
        if (listener == null) {
            listener = new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    checkPassword(String.valueOf(password.getPassword()));
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    checkPassword(String.valueOf(password.getPassword()));
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    checkPassword(String.valueOf(password.getPassword()));
                }
            };
        }
        if (password != null) {
            password.getDocument().removeDocumentListener(listener);
        }
        password.getDocument().addDocumentListener(listener);
        this.password = password;
    }

    /**
     * Clase interna descStatus que extiende JLabel.
     * Esta clase se utiliza para dibujar la barra de fuerza de la contraseña en el
     * componente FuerzaPasswordComponente.
     * La barra de fuerza de la contraseña se divide en tres secciones, cada una de
     * las cuales se colorea en función de la fuerza de la contraseña.
     * Si la fuerza de la contraseña es menor que el número de la sección, la
     * sección se colorea con un color deshabilitado.
     * De lo contrario, la sección se colorea con un color seleccionado en función
     * de la fuerza de la contraseña.
     */
    private class descStatus extends JLabel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            int size = (int) (height * 0.3f);
            Graphics2D g2 = (Graphics2D) g.create();
            FlatUIUtils.setRenderingHints(g2);
            int gap = UIScale.scale(5);
            int w = (width - gap * 2) / 3;
            int y = (height - size) / 2;
            Color disableColor = Color.decode(FlatLaf.isLafDark() ? "#404040" : "#CECECE");
            if (fuerza >= 1) {
                g2.setColor(seleccionaColor(1));
            } else {
                g2.setColor(disableColor);
            }
            FlatUIUtils.paintComponentBackground(g2, 0, y, w, size, 0, 999);
            if (fuerza >= 2) {
                g2.setColor(seleccionaColor(2));
            } else {
                g2.setColor(disableColor);
            }
            FlatUIUtils.paintComponentBackground(g2, w + gap, y, w, size, 0, 999);
            if (fuerza >= 3) {
                g2.setColor(seleccionaColor(3));
            } else {
                g2.setColor(disableColor);
            }
            FlatUIUtils.paintComponentBackground(g2, (w + gap) * 2, y, w, size, 0, 999);
            g2.dispose();
        }
    }

    /**
     * Obtiene la fuerza de la contraseña actual.
     * 
     * @return la fuerza de la contraseña actual
     */
    public int getFuerza() {
        return fuerza;
    }
}
