/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.clases;

/**
 *
 * @author samus
 */
public class Cursos {
    private int id;
    private String codigo,descripcion;
    private Etapas Etapa;
    private boolean estado;

    public Cursos(int id, String codigo, String descripcion, Etapas Etapas, boolean estado) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.Etapa = Etapas;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Enum getEtapas() {
        return Etapa;
    }

    public boolean isEstado() {
        return estado;
    }
    
    
}
