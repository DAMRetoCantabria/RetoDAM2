/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.clases;

/**
 *
 * @author samus
 */
public class Profesores {
    private int id;
    private String dni,nombre,apellidos,correo,contrasenia;
    private Niveles nivel;
    private boolean activo;
    private String foto;
    private int departamento;

    public Profesores(int id, String dni, String nombre, String apellidos, String correo, String contrasenia, Niveles nivel, boolean activo, String foto, int departamento) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.nivel = nivel;
        this.activo = activo;
        this.foto = foto;
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Niveles getNivel() {
        return nivel;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getFoto() {
        return foto;
    }

    public int getDepartamento() {
        return departamento;
    }
    
    
}
