package reto.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Esta clase representa un renderizador personalizado para las celdas de una
 * tabla.
 * Aplica un efecto de gradiente de color a las celdas seleccionadas y a las
 * celdas pares.
 * El gradiente de color se define mediante dos colores: color1 y color2.
 * 
 * @see javax.swing.table.DefaultTableCellRenderer
 */
public class PerfilTabla extends DefaultTableCellRenderer {

    private Color color1;
    private Color color2;
    private int x;
    private int anchura;
    private boolean seleccionado;
    private int row;

    /**
     * Crea una instancia de PerfilTabla con los colores predeterminados.
     */
    public PerfilTabla() {
        this(Color.decode("#0F2027"), Color.decode("#2C5364"));
    }

    /**
     * Crea una instancia de PerfilTabla con los colores especificados.
     * 
     * @param color1 El primer color del gradiente.
     * @param color2 El segundo color del gradiente.
     */
    public PerfilTabla(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
        setOpaque(false);
    }

    /**
     * Devuelve el componente de la celda de la tabla con el efecto de gradiente
     * aplicado.
     * 
     * @param table      La tabla que contiene la celda.
     * @param value      El valor de la celda.
     * @param isSelected Indica si la celda está seleccionada.
     * @param hasFocus   Indica si la celda tiene el foco.
     * @param row        El índice de la fila de la celda.
     * @param column     El índice de la columna de la celda.
     * @return El componente de la celda con el efecto de gradiente aplicado.
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Rectangle cellRec = table.getCellRect(row, column, true);
        x = -cellRec.x;
        anchura = table.getWidth() - cellRec.x;
        this.seleccionado = isSelected;
        this.row = row;
        return com;
    }

    /**
     * Pinta el componente de la celda con el efecto de gradiente.
     * 
     * @param g El objeto Graphics utilizado para pintar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        if (seleccionado) {
            g2.setPaint(new GradientPaint(x, 0, color1, anchura, 0, color2));
            g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        } else if (row % 2 == 0) {
            g2.setPaint(new GradientPaint(x, 0, Color.decode("#616161"), anchura, 0, Color.decode("#434343")));
            g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        }
        g2.dispose();
        super.paintComponent(g);
    }

}
