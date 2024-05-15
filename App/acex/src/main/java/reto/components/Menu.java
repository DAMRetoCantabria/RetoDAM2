
package reto.components;

import net.miginfocom.swing.MigLayout;
import raven.drawer.component.header.SimpleHeader;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.data.Item;
import raven.swing.AvatarIcon;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;
import raven.drawer.component.menu.*;
import reto.ventanas.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Esta clase representa el menú de la aplicación.
 * Extiende de BlurChild para aplicar efecto de desenfoque al menú.
 */
public class Menu extends BlurChild {
    private MainForm mainForm;

    /**
     * Constructor de la clase Menu.
     * 
     * @param mainform La instancia de MainForm.
     */
    public Menu(MainForm mainform) {
        super(new Style()
                .setBlur(30)
                .setBorder(new StyleBorder(10)
                        .setOpacity(0.15f)
                        .setBorderWidth(1.2f)
                        .setBorderColor(new GradientColor(new Color(200, 200, 200), new Color(150, 150, 150),
                                new Point2D.Float(0, 0), new Point2D.Float(1f, 0))))
                .setOverlay(new StyleOverlay(new Color(0, 0, 0), 0.2f)));
        this.mainForm = mainform;
        init();
    }

    /**
     * Inicializa el menú.
     */
    private void init() {
        setLayout(new MigLayout("wrap,fill", "[fill]", "[grow 0][fill]"));
        simplemenu = new SimpleMenu(getMenuOption());

        simplemenu.setOpaque(false);
        JScrollPane scroll = new JScrollPane(simplemenu);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(10);

        SimpleHeader perfil = new SimpleHeader(getHeaderData());
        perfil.setOpaque(false);
        add(perfil, "gap 0 0 40 50");
        add(scroll);
    }

    /**
     * Crea y devuelve los datos del encabezado del menú.
     * 
     * @return Los datos del encabezado del menú.
     */
    private SimpleHeaderData getHeaderData() {
        return new SimpleHeaderData()
                .setTitle(Login.user.getNombre())
                .setDescription(Login.user.getNivel())
                .setIcon(new AvatarIcon(getClass().getResource(Login.user.getFoto()), 60, 60, 999));
    }

    /**
     * Crea y devuelve las opciones del menú.
     * 
     * @return Las opciones del menú.
     */
    private SimpleMenuOption getMenuOption() {
        raven.drawer.component.menu.data.MenuItem items[] = null;
        if (Login.user.getNivel().equalsIgnoreCase("Administrador")
                || Login.user.getNivel().equalsIgnoreCase("Superusuario"))
            items = new raven.drawer.component.menu.data.MenuItem[] {
                    new Item.Label("GESTION"),
                    new Item("Cargar Datos", "cargar.svg"),
                    new Item("Gestionar Datos", "editar.svg")
                            .subMenu("Profesores")
                            .subMenu("Grupos")
                            .subMenu("Cursos")
                            .subMenu("Departamentos"),
                    new Item.Label("SOLICITUDES"),
                    new Item("Crear solicitud", "registrar.svg"),
                    new Item("Gestionar solicitudes", "gestionar.svg"),
                    new Item("Aprobadas", "aprobada.svg"),
                    new Item("Pendientes", "pendiente.svg"),
                    new Item.Label("USUARIO"),
                    new Item("Perfil de Usuario", "user.svg"),
                    new Item("Cerrar Sesion", "logout.svg")
            };
        else if (Login.user.getNivel().equalsIgnoreCase("Profesor")
                || Login.user.getNivel().equalsIgnoreCase("EquipoDirectivo"))
            items = new raven.drawer.component.menu.data.MenuItem[] {
                    new Item.Label("SOLICITUDES"),
                    new Item("Crear solicitud", "registrar.svg"),
                    new Item("Aprobadas", "aprobada.svg"),
                    new Item("Pendientes", "pendiente.svg"),
                    new Item.Label("USUARIO"),
                    new Item("Perfil de Usuario", "user.svg"),
                    new Item("Cerrar Sesion", "logout.svg")
            };
        return new SimpleMenuOption()
                .setBaseIconPath("icons/")
                .setIconScale(0.5f)
                .setMenus(items)
                .setMenuStyle(new SimpleMenuStyle() {
                    @Override
                    public void styleMenuPanel(JPanel panel, int[] index) {
                        panel.setOpaque(false);
                    }

                    @Override
                    public void styleMenuItem(JButton menu, int[] index) {
                        menu.setContentAreaFilled(false);
                    }
                })
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction menuAction, int[] ints) {
                        if (Login.user.getNivel().equalsIgnoreCase("Administrador")
                                || Login.user.getNivel().equalsIgnoreCase("Superusuario")) {
                            if (ints.length == 1) {
                                int index = ints[0];
                                if (index == 0) {
                                    VentanaSingleton.getInstance().mostrarVentana("Cargar Datos", new Carga(),
                                            new Dimension(700, 700));
                                } else if (index == 2) {
                                    VentanaSingleton.getInstance().mostrarVentana("Crear Solicitud",
                                            new Solicitudes(1),
                                            new Dimension(700, 700));
                                } else if (index == 3) {
                                    VentanaSingleton.getInstance().mostrarVentana("Gestionar Solicitud",
                                            new Gestionar(),
                                            new Dimension(700, 700));
                                } else if (index == 4) {
                                    VentanaSingleton.getInstance().mostrarVentana("Solicitudes Aprobadas",
                                            new Listado_actividades(1),
                                            new Dimension(700, 700));
                                } else if (index == 5) {
                                    VentanaSingleton.getInstance().mostrarVentana("Solicitudes Pendientes",
                                            new Listado_actividades(2),
                                            new Dimension(700, 700));
                                } else if (index == 6) {
                                    VentanaSingleton.getInstance().mostrarVentana("Perfil de usuario", new Perfil(),
                                            new Dimension(700, 700));
                                } else if (index == 7) {
                                    mainForm.setLogin(0);
                                }
                            } else if (ints.length == 2) {
                                int index = ints[0];
                                int subIndex = ints[1];
                                if (index == 1) {
                                    if (subIndex == 0) {
                                        VentanaSingleton.getInstance().mostrarVentana("Modificar Profesores",
                                                new Listado_admin(0), new Dimension(500, 500));
                                    } else if (subIndex == 1) {
                                        VentanaSingleton.getInstance().mostrarVentana("Modificar Grupos",
                                                new Listado_admin(2), new Dimension(700, 700));
                                    } else if (subIndex == 2) {
                                        VentanaSingleton.getInstance().mostrarVentana("Modificar Cursos",
                                                new Listado_admin(1), new Dimension(700, 700));
                                    } else if (subIndex == 3) {
                                        VentanaSingleton.getInstance().mostrarVentana("Modificar Departamentos",
                                                new Listado_admin(3), new Dimension(700, 700));
                                    }
                                }
                            }
                        } else if (Login.user.getNivel().equalsIgnoreCase("Profesor")
                                || Login.user.getNivel().equalsIgnoreCase("EquipoDirectivo")) {
                            if (ints.length == 1) {
                                int index = ints[0];
                                if (index == 0) {
                                    VentanaSingleton.getInstance().mostrarVentana("Crear Solicitud",
                                            new Solicitudes(1),
                                            new Dimension(700, 700));
                                } else if (index == 1) {
                                    VentanaSingleton.getInstance().mostrarVentana("Solicitudes Aprobadas",
                                            new Listado_actividades(1),
                                            new Dimension(700, 700));
                                } else if (index == 2) {
                                    VentanaSingleton.getInstance().mostrarVentana("Solicitudes Pendientes",
                                            new Listado_actividades(2),
                                            new Dimension(700, 700));
                                } else if (index == 3) {
                                    VentanaSingleton.getInstance().mostrarVentana("Perfil de usuario", new Perfil(),
                                            new Dimension(700, 700));
                                } else if (index == 4) {
                                    mainForm.setLogin(0);
                                }
                            }
                        }
                    }
                });
    }

    private SimpleMenu simplemenu;
}
