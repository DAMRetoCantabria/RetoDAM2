package reto.objects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import reto.sql.*;

public class Solicitud {
    private int id_solicitud;
    private Profesor solicitante;
    private String titulo;
    private TipoActividad tipo;
    private LocalDate fini;
    private LocalDate ffin;
    private LocalTime hini;
    private LocalTime hfin;
    private boolean previsto;
    private boolean transp_requerido;
    private String transp_comentario;
    private boolean aloj_requerido;
    private String aloj_comentario;
    private String comentario;
    private EstadoActividad estado;
    private String estado_comentario;
    private List<MediosTransporte> transportes = new ArrayList<>();
    private List<Profesor> participantes = new ArrayList<>();
    private List<Profesor> responsables = new ArrayList<>();
    private List<Curso> cursos = new ArrayList<>();
    private List<Grupo> grupos = new ArrayList<>();

    public Solicitud() {

    }

    public Solicitud(int id_solicitud, Profesor solicitante, String titulo, TipoActividad tipo,
            LocalDate fini, LocalDate ffin, LocalTime hini, LocalTime hfin, boolean previsto, boolean transp_requerido,
            String transp_comentario, boolean aloj_requerido, String aloj_comentario, String comentario,
            EstadoActividad estado, String estado_comentario, List<MediosTransporte> transportes,
            List<Profesor> participantes, List<Profesor> responsables, List<Curso> cursos, List<Grupo> grupos) {
        this.id_solicitud = id_solicitud;
        this.solicitante = solicitante;
        this.titulo = titulo;
        this.tipo = tipo;
        this.fini = fini;
        this.ffin = ffin;
        this.hini = hini;
        this.hfin = hfin;
        this.previsto = previsto;
        this.transp_requerido = transp_requerido;
        this.transp_comentario = transp_comentario;
        this.aloj_requerido = aloj_requerido;
        this.aloj_comentario = aloj_comentario;
        this.comentario = comentario;
        this.estado = estado;
        this.estado_comentario = estado_comentario;
        this.transportes = transportes;
        this.participantes = participantes;
        this.responsables = responsables;
        this.cursos = cursos;
        this.grupos = grupos;
    }

    public Solicitud(Profesor solicitante, String titulo, TipoActividad tipo,
            LocalDate fini, LocalDate ffin, LocalTime hini, LocalTime hfin, boolean previsto, boolean transp_requerido,
            String transp_comentario,
            boolean aloj_requerido, String aloj_comentario, String comentario,
            EstadoActividad estado, String estado_comentario, List<MediosTransporte> transportes,
            List<Profesor> participantes, List<Profesor> responsables, List<Curso> cursos, List<Grupo> grupos) {
        this.solicitante = solicitante;
        this.titulo = titulo;
        this.tipo = tipo;
        this.fini = fini;
        this.ffin = ffin;
        this.hini = hini;
        this.hfin = hfin;
        this.previsto = previsto;
        this.transp_requerido = transp_requerido;
        this.transp_comentario = transp_comentario;
        this.aloj_requerido = aloj_requerido;
        this.aloj_comentario = aloj_comentario;
        this.comentario = comentario;
        this.estado = estado;
        this.estado_comentario = estado_comentario;
        this.transportes = transportes;
        this.participantes = participantes;
        this.responsables = responsables;
        this.cursos = cursos;
        this.grupos = grupos;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public Profesor get_solicitante() {
        return solicitante;
    }

    public String getTitulo() {
        return titulo;
    }

    public TipoActividad getTipo() {
        return tipo;
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

    public boolean isTransp_requerido() {
        return transp_requerido;
    }

    public String getTransp_comentario() {
        return transp_comentario;
    }

    public boolean isAloj_requerido() {
        return aloj_requerido;
    }

    public String getAloj_comentario() {
        return aloj_comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public EstadoActividad getEstado() {
        return estado;
    }

    public String getEstado_comentario() {
        return estado_comentario;
    }

    public int getDepartamento() {
        return solicitante.getDepartamento().getId();
    }

    public Profesor getSolicitante() {
        return solicitante;
    }

    public String get_Solicitante() {
        return solicitante.getNombre().concat(" ")
                .concat(solicitante.getApellidos());
    }

    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }

    public void setEstado_comentario(String estado_comentario) {
        this.estado_comentario = estado_comentario;
    }

    public int getNumAlumnos() {
        int numAlumnos = 0;
        if (grupos.isEmpty()) {
            for (Curso c : cursos) {
                numAlumnos += c.getNumAlumnos();
            }
        } else {
            for (Grupo g : grupos) {
                numAlumnos += g.getNum_alumnos();
            }
        }

        return numAlumnos;
    }

    public List<MediosTransporte> getTransportes() {
        return transportes;
    }

    public List<Profesor> getParticipantes() {
        return participantes;
    }

    public List<Profesor> getResponsables() {
        return responsables;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public boolean equals(Object obj, List<Profesor> participantesNombre, List<Profesor> responsablesNombre,
            List<MediosTransporte> transportes, /* List<String> alumnosNombre, */List<Curso> cursos, List<Grupo> grupos,
            boolean curso) {
        SolicitudDAO solicitudSQL = new SolicitudDAO();
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Solicitud solicitud = (Solicitud) obj;

        boolean transporte = ((transp_comentario == null && solicitud.transp_comentario == null)
                || transp_comentario.equals(solicitud.transp_comentario));

        boolean alojamiento = ((aloj_comentario == null && solicitud.aloj_comentario == null)
                || aloj_comentario.equals(solicitud.aloj_comentario));

        boolean com = ((comentario == null && solicitud.comentario == null) || comentario.equals(solicitud.comentario));

        List<Integer> transportes_id = new ArrayList<>();

        for (MediosTransporte s : transportes) {
            transportes_id.add(s.getId());
        }

        boolean parti = (solicitudSQL.buscaParticipantes(solicitud.getId_solicitud()).containsAll(participantes)
                && solicitudSQL.buscaParticipantes(solicitud.getId_solicitud()).size() == participantes.size());

        boolean respo = (solicitudSQL.buscaResponsable(solicitud.getId_solicitud()).containsAll(responsables)
                && solicitudSQL
                        .buscaResponsable(solicitud.getId_solicitud()).size() == responsables.size());

        boolean tran = (solicitudSQL.buscaTransporte(solicitud.getId_solicitud()).containsAll(transportes)
                && solicitudSQL.buscaTransporte(solicitud.getId_solicitud()).size() == transportes.size());

        boolean alu = false;

        if (curso) {
            alu = (solicitudSQL.buscaCursos(solicitud.getId_solicitud()).containsAll(cursos)
                    && solicitudSQL.buscaCursos(solicitud.getId_solicitud()).size() == cursos.size());
        } else {
            alu = (solicitudSQL.buscaGrupos(solicitud.getId_solicitud()).containsAll(grupos)
                    && solicitudSQL.buscaGrupos(solicitud.getId_solicitud()).size() == grupos.size());
        }

        return id_solicitud == solicitud.id_solicitud &&
                solicitante.equals(solicitud.solicitante) &&
                previsto == solicitud.previsto &&
                transp_requerido == solicitud.transp_requerido &&
                aloj_requerido == solicitud.aloj_requerido &&
                titulo.equals(solicitud.titulo) &&
                tipo == solicitud.tipo &&
                fini.equals(solicitud.fini) &&
                ffin.equals(solicitud.ffin) &&
                hini.equals(solicitud.hini) &&
                hfin.equals(solicitud.hfin) &&
                transporte && alojamiento && parti &&
                respo && com && tran && alu;
    }

}
