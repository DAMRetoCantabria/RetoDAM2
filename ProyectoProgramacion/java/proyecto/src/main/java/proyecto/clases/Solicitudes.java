/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.clases;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author samus
 */
public class Solicitudes {
    private int idSolicitud,idSolicitante;
    private String titulo;
    private Actividades actividad;
    private int idDepartamento;
    private LocalDate fini,ffin;
    private LocalTime hini,hfin;
    private boolean previsto,transporte;
    private String transpComentario;
    private boolean alojamiento;
    private String alojComentario,comentario;
    private Estado estado;
    private String estadoComentario;

    public Solicitudes(int idSolicitud, int idSolicitante, String titulo, Actividades actividad, int idDepartamento, LocalDate fini, LocalDate ffin, LocalTime hini, LocalTime hfin, boolean previsto, boolean transporte, String transpComentario, boolean alojamiento, String alojComentario, String comentario, Estado estado, String estadoComentario) {
        this.idSolicitud = idSolicitud;
        this.idSolicitante = idSolicitante;
        this.titulo = titulo;
        this.actividad = actividad;
        this.idDepartamento = idDepartamento;
        this.fini = fini;
        this.ffin = ffin;
        this.hini = hini;
        this.hfin = hfin;
        this.previsto = previsto;
        this.transporte = transporte;
        this.transpComentario = transpComentario;
        this.alojamiento = alojamiento;
        this.alojComentario = alojComentario;
        this.comentario = comentario;
        this.estado = estado;
        this.estadoComentario = estadoComentario;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public String getTitulo() {
        return titulo;
    }

    public Actividades getActividad() {
        return actividad;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public LocalDate getFini() {
        return fini;
    }

    public LocalDate getFfin() {
        return ffin;
    }

    public LocalTime getHini() {
        return hini;
    }

    public LocalTime getHfin() {
        return hfin;
    }

    public boolean isPrevisto() {
        return previsto;
    }

    public boolean isTransporte() {
        return transporte;
    }

    public String getTranspComentario() {
        return transpComentario;
    }

    public boolean isAlojamiento() {
        return alojamiento;
    }

    public String getAlojComentario() {
        return alojComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getEstadoComentario() {
        return estadoComentario;
    }
    
    
}
