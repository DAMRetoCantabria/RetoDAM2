/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author samus
 */
public class Imagen implements Border{
    private BufferedImage img = null;
    
    public Imagen(String ruta) {
    try {
        img = ImageIO.read(new File(ruta));
    } catch (IOException e) {
        System.err.println("Error al leer la imagen: " + e.getMessage());
    }
}

    
    public void setBorder(JFrame frame) {
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setBorder(this);
    }
    
    @Override
public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    if (img != null) {
        g.drawImage(img, 0, 0, width, height, null);
    }
}

@Override
public Insets getBorderInsets(Component c) {
    return new Insets(0, 0, 0, 0);
}

@Override
public boolean isBorderOpaque() {
    return true;
}
    
}
