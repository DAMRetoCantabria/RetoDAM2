package reto.objects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import reto.sql.ProgramadasDAO;
import reto.sql.SolicitudDAO;

public class Programadas {
    private int id_programada;
    private Solicitud solicitada;
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
    private String empresa_transporte;
    private double precio_transporte;
    private List<MediosTransporte> transportes = new ArrayList<>();
    private List<Profesor> responsables = new ArrayList<>();
    private List<Profesor> participantes = new ArrayList<>();
    private List<Curso> cursos = new ArrayList<>();
    private List<Grupo> grupos = new ArrayList<>();

    public Programadas(int id_programada, Solicitud solicitada, Profesor solicitante, String titulo, TipoActividad tipo,
            LocalDate fini, LocalDate ffin, LocalTime hini, LocalTime hfin, boolean previsto, boolean transp_requerido,
            String transp_comentario, boolean aloj_requerido, String aloj_comentario, String comentario,
            EstadoActividad estado, String estado_comentario, String empresa_transporte, double precio_transporte,
            List<MediosTransporte> transportes, List<Profesor> responsables, List<Profesor> participantes,
            List<Curso> cursos, List<Grupo> grupos) {
        this.id_programada = id_programada;
        this.solicitada = solicitada;
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
        this.empresa_transporte = empresa_transporte;
        this.precio_transporte = precio_transporte;
        this.transportes = transportes;
        this.responsables = responsables;
        this.participantes = participantes;
        this.cursos = cursos;
        this.grupos = grupos;
    }

    public Programadas(Solicitud solicitada, Profesor solicitante, String titulo, TipoActividad tipo,
            LocalDate fini, LocalDate ffin, LocalTime hini, LocalTime hfin, boolean previsto, boolean transp_requerido,
            String transp_comentario, boolean aloj_requerido, String aloj_comentario, String comentario,
            EstadoActividad estado, String estado_comentario, String empresa_transporte, double precio_transporte,
            List<MediosTransporte> transportes, List<Profesor> responsables, List<Profesor> participantes,
            List<Curso> cursos, List<Grupo> grupos) {
        this.solicitada = solicitada;
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
        this.empresa_transporte = empresa_transporte;
        this.precio_transporte = precio_transporte;
        this.transportes = transportes;
        this.responsables = responsables;
        this.participantes = participantes;
        this.cursos = cursos;
        this.grupos = grupos;
    }

    public int getId_programada() {
        return id_programada;
    }

    public Solicitud get_solicitada() {
        return solicitada;
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

    public String getEmpresa_transporte() {
        return empresa_transporte;
    }

    public double getPrecio_transporte() {
        return precio_transporte;
    }

    public int getDepartamento() {
        return solicitante.getDepartamento().getId();
    }

    public Profesor getSolicitante() {
        return solicitante;
    }

    public Solicitud getSolicitada() {
        return solicitada;
    }

    public String get_Solicitante() {
        return solicitante.getNombre().concat(" ")
                .concat(solicitante.getApellidos());
    }

    public List<MediosTransporte> getTransportes() {
        return transportes;
    }

    public List<Profesor> getResponsables() {
        return responsables;
    }

    public List<Profesor> getParticipantes() {
        return participantes;
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
        ProgramadasDAO programadaSQL = new ProgramadasDAO();
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Programadas programada = (Programadas) obj;

        boolean transporte = ((transp_comentario == null && programada.transp_comentario == null)
                || transp_comentario.equals(programada.transp_comentario));

        boolean alojamiento = ((aloj_comentario == null && programada.aloj_comentario == null)
                || aloj_comentario.equals(programada.aloj_comentario));

        boolean com = ((comentario == null && programada.comentario == null)
                || comentario.equals(programada.comentario));

        List<Integer> transportes_id = new ArrayList<>();

        for (MediosTransporte s : transportes) {
            transportes_id.add(s.getId());
        }

        boolean parti = (programadaSQL.buscaParticipantes(programada.getId_programada()).containsAll(participantes)
                && programadaSQL.buscaParticipantes(programada.getId_programada()).size() == participantes.size());

        boolean respo = (programadaSQL.buscaResponsable(programada.getId_programada()).containsAll(responsables)
                && programadaSQL
                        .buscaResponsable(programada.getId_programada()).size() == responsables.size());

        boolean tran = (programadaSQL.buscaTransporte(programada.getId_programada()).containsAll(transportes)
                && programadaSQL.buscaTransporte(programada.getId_programada()).size() == transportes.size());

        boolean alu = false;

        if (curso) {
            alu = (programadaSQL.buscaCursos(programada.getId_programada()).containsAll(cursos)
                    && programadaSQL.buscaCursos(programada.getId_programada()).size() == cursos.size());
        } else {
            alu = (programadaSQL.buscaGrupos(programada.getId_programada()).containsAll(grupos)
                    && programadaSQL.buscaGrupos(programada.getId_programada()).size() == grupos.size());
        }

        return id_programada == programada.id_programada &&
                solicitante.equals(programada.solicitante) &&
                previsto == programada.previsto &&
                transp_requerido == programada.transp_requerido &&
                aloj_requerido == programada.aloj_requerido &&
                titulo.equals(programada.titulo) &&
                tipo == programada.tipo &&
                fini.equals(programada.fini) &&
                ffin.equals(programada.ffin) &&
                hini.equals(programada.hini) &&
                hfin.equals(programada.hfin) &&
                transporte && alojamiento && parti &&
                respo && com && tran && alu;
    }

}
