/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.clases;

/**
 *
 * @author samus
 */
public class Departamentos {
    private int id;
    private String codigo,nombre,jefe;

    public Departamentos(int id, String codigo, String nombre, String jefe) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.jefe = jefe;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getJefe() {
        return jefe;
    }
    
    
}
