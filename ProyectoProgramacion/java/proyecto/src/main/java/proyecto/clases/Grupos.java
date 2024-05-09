/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.clases;

/**
 *
 * @author samus
 */
public class Grupos {
    private int id,curso;
    private String codigo;
    private int numAlumnos;
    private boolean activo;

    public Grupos(int id, int curso, String codigo, int numAlumnos, boolean activo) {
        this.id = id;
        this.curso = curso;
        this.codigo = codigo;
        this.numAlumnos = numAlumnos;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public int getCurso() {
        return curso;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getNumAlumnos() {
        return numAlumnos;
    }

    public boolean isActivo() {
        return activo;
    }
    
    
    
}
