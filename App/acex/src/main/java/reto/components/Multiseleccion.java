
package reto.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
import com.formdev.flatlaf.ui.FlatComboBoxUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.ComboPopup;
import net.miginfocom.swing.MigLayout;

/**
 * Esta clase representa un componente de selección múltiple personalizado que
 * extiende JComboBox.
 * Permite seleccionar múltiples elementos de una lista desplegable.
 * Los elementos seleccionados se muestran en un panel debajo del cuadro de
 * texto.
 * Los elementos se pueden agregar o eliminar de la selección.
 * 
 * @param <E> el tipo de elementos en la lista desplegable
 */
public class Multiseleccion<E> extends JComboBox<E> {

    /**
     * Obtiene los elementos seleccionados en el componente.
     * 
     * @return una lista de los elementos seleccionados
     */
    public List<Object> getSelectedItems() {
        return selectedItems;
    }

    /**
     * Establece los elementos seleccionados en el componente.
     * 
     * @param selectedItems una lista de elementos a seleccionar
     */
    public void setSelectedItems(List<Object> selectedItems) {
        List<Object> comboItem = new ArrayList<>();
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            comboItem.add(getItemAt(i));
        }
        for (Object obj : selectedItems) {
            if (comboItem.contains(obj)) {
                addItemObject(obj);
            }
        }
        comboItem.clear();
    }

    /**
     * Limpia los elementos seleccionados en el componente.
     */
    public void clearSelectedItems() {
        selectedItems.clear();
        Component editorCom = getEditor().getEditorComponent();
        if (editorCom instanceof JScrollPane) {
            JScrollPane scroll = (JScrollPane) editorCom;
            JPanel panel = (JPanel) scroll.getViewport().getComponent(0);
            panel.removeAll();
            revalidate();
            repaint();
            comboList.repaint();
        }
    }

    private final List<Object> selectedItems = new ArrayList<>();
    private final ComboBoxMultiCellEditor comboBoxMultiCellEditor;
    private Component comboList;

    /**
     * Este método se utiliza para eliminar un objeto de la lista de elementos
     * seleccionados
     * y del comboBoxMultiCellEditor. Si comboList no es nulo, se repinta.
     *
     * @param obj El objeto que se va a eliminar.
     */
    private void removeItemObject(Object obj) {
        selectedItems.remove(obj);
        comboBoxMultiCellEditor.removeItem(obj);
        if (comboList != null) {
            comboList.repaint();
        }
    }

    /**
     * Este método se utiliza para agregar un objeto a la lista de elementos
     * seleccionados
     * y al comboBoxMultiCellEditor. Si comboList no es nulo, se repinta.
     *
     * @param obj El objeto que se va a agregar.
     */
    private void addItemObject(Object obj) {
        selectedItems.add(obj);
        comboBoxMultiCellEditor.addItem(obj);
        if (comboList != null) {
            comboList.repaint();
        }
    }

    /**
     * Crea una nueva instancia de Multiseleccion.
     */
    public Multiseleccion() {
        setUI(new ComboBoxMultiUI());
        comboBoxMultiCellEditor = new ComboBoxMultiCellEditor();
        setRenderer(new ComboBoxMultiCellRenderer());
        setEditor(comboBoxMultiCellEditor);
        setEditable(true);
        addActionListener((e) -> {
            if (e.getModifiers() == ActionEvent.MOUSE_EVENT_MASK) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                Object obj = combo.getSelectedItem();
                if (selectedItems.contains(obj)) {
                    removeItemObject(obj);
                } else {
                    addItemObject(obj);
                }
            }
        });
    }

    /**
     * Sobrescribe el método setPopupVisible. Este método se utiliza para controlar
     * la visibilidad del menú desplegable de un JComboBox. En esta implementación,
     * el cuerpo del método está vacío, lo que significa que no realiza ninguna
     * acción
     * cuando se le llama.
     *
     * @param v Un valor booleano que indica si el menú desplegable debe ser visible
     *          o no.
     */
    @Override
    public void setPopupVisible(boolean v) {

    }

    private class ComboBoxMultiUI extends FlatComboBoxUI {

        @Override
        protected ComboPopup createPopup() {
            return new MultiComboPopup(comboBox);
        }

        private class MultiComboPopup extends FlatComboPopup {

            public <T> MultiComboPopup(JComboBox<T> combo) {
                super(combo);
            }
        }

        @Override
        protected Dimension getDisplaySize() {
            Dimension size = super.getDefaultSize();
            return new Dimension(0, size.height);
        }
    }

    private class ComboBoxMultiCellRenderer extends BasicComboBoxRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (comboList != list) {
                comboList = list;
            }
            setIcon(new CheckBoxIcon(selectedItems.contains(value)));
            return this;
        }
    }

    private class ComboBoxMultiCellEditor extends BasicComboBoxEditor {

        protected final JScrollPane scroll;
        protected final JPanel panel;

        protected void addItem(Object obj) {
            Item item = new Item(obj);
            panel.add(item);
            panel.repaint();
            panel.revalidate();
        }

        protected void removeItem(Object obj) {
            int count = panel.getComponentCount();
            for (int i = 0; i < count; i++) {
                Item item = (Item) panel.getComponent(i);
                if (item.getItem() == obj) {
                    panel.remove(i);
                    panel.revalidate();
                    panel.repaint();
                    break;
                }
            }
        }

        public ComboBoxMultiCellEditor() {
            this.panel = new JPanel(new MigLayout("insets 0,filly,gapx 2", "", "fill"));
            this.scroll = new JScrollPane(panel);
            scroll.putClientProperty(FlatClientProperties.STYLE, ""
                    + "border:2,2,2,2;"
                    + "background:$ComboBox.editableBackground");
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "background:$ComboBox.editableBackground");
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            JScrollBar scrollBar = scroll.getHorizontalScrollBar();
            scrollBar.putClientProperty(FlatClientProperties.STYLE, ""
                    + "width:3;"
                    + "thumbInsets:0,0,0,1;"
                    + "hoverTrackColor:null");
            scrollBar.setUnitIncrement(10);

        }

        @Override
        public Component getEditorComponent() {
            return scroll;
        }

    }

    private class CheckBoxIcon extends FlatCheckBoxIcon {

        private final boolean selected;

        public CheckBoxIcon(boolean selected) {
            this.selected = selected;
        }

        @Override
        protected boolean isSelected(Component c) {
            return selected;
        }
    }

    private class Item extends JLabel {

        public Object getItem() {
            return item;
        }

        private final Object item;

        public Item(Object item) {
            super(item != null ? item.toString() : "null");
            this.item = item;
            init();
        }

        private void init() {
            putClientProperty(FlatClientProperties.STYLE, ""
                    + "border:0,5,0,20;"
                    + "background:darken($ComboBox.background,10%)");
            JButton cmd = new JButton(new FlatSVGIcon(getClass().getResource("/icons/close.svg")));
            cmd.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:999;"
                    + "margin:1,1,1,1;"
                    + "background:null;"
                    + "focusWidth:0");
            cmd.addActionListener((e) -> {
                removeItemObject(item);
            });
            cmd.setFocusable(false);
            setLayout(new MigLayout("fill"));
            add(cmd, "pos 1al 0.5al 10 10");
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            FlatUIUtils.setRenderingHints(g2);
            int arc = UIScale.scale(10);
            g2.setColor(getBackground());
            FlatUIUtils.paintComponentBackground(g2, 0, 0, getWidth(), getHeight(), 0, arc);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}