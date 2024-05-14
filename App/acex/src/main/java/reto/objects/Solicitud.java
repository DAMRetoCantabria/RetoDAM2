package reto.objects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import reto.sql.*;

/**
 * La clase Solicitud representa una solicitud de actividad.
 * Contiene información sobre la solicitud, como el solicitante, el título, las
 * fechas y horas, los comentarios, etc.
 */
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
    private int nalumnos_ausentes;
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

    /**
     * Constructor vacío de la clase Solicitud.
     */
    public Solicitud() {

    }

    /**
     * Constructor de la clase Solicitud que inicializa todos los atributos.
     * 
     * @param id_solicitud      el ID de la solicitud
     * @param solicitante       el profesor solicitante
     * @param titulo            el título de la solicitud
     * @param tipo              el tipo de actividad
     * @param fini              la fecha de inicio
     * @param ffin              la fecha de fin
     * @param hini              la hora de inicio
     * @param hfin              la hora de fin
     * @param previsto          indica si la actividad está prevista
     * @param nalumnos_ausentes el número de alumnos ausentes
     * @param transp_requerido  indica si se requiere transporte
     * @param transp_comentario el comentario sobre el transporte
     * @param aloj_requerido    indica si se requiere alojamiento
     * @param aloj_comentario   el comentario sobre el alojamiento
     * @param comentario        el comentario adicional
     * @param estado            el estado de la actividad
     * @param estado_comentario el comentario sobre el estado
     * @param transportes       la lista de medios de transporte
     * @param participantes     la lista de participantes
     * @param responsables      la lista de responsables
     * @param cursos            la lista de cursos
     * @param grupos            la lista de grupos
     */
    public Solicitud(int id_solicitud, Profesor solicitante, String titulo, TipoActividad tipo,
            LocalDate fini, LocalDate ffin, LocalTime hini, LocalTime hfin, boolean previsto, int nalumnos_ausentes, boolean transp_requerido,
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
        this.nalumnos_ausentes = nalumnos_ausentes;
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

    /**
     * Constructor de la clase Solicitud que no incluye el ID de la solicitud.
     * 
     * @param solicitante       el profesor solicitante
     * @param titulo            el título de la solicitud
     * @param tipo              el tipo de actividad
     * @param fini              la fecha de inicio
     * @param ffin              la fecha de fin
     * @param hini              la hora de inicio
     * @param hfin              la hora de fin
     * @param previsto          indica si la actividad está prevista
     * @param nalumnos_ausentes el número de alumnos ausentes
     * @param transp_requerido  indica si se requiere transporte
     * @param transp_comentario el comentario sobre el transporte
     * @param aloj_requerido    indica si se requiere alojamiento
     * @param aloj_comentario   el comentario sobre el alojamiento
     * @param comentario        el comentario adicional
     * @param estado            el estado de la actividad
     * @param estado_comentario el comentario sobre el estado
     * @param transportes       la lista de medios de transporte
     * @param participantes     la lista de participantes
     * @param responsables      la lista de responsables
     * @param cursos            la lista de cursos
     * @param grupos            la lista de grupos
     */
    public Solicitud(Profesor solicitante, String titulo, TipoActividad tipo,
            LocalDate fini, LocalDate ffin, LocalTime hini, LocalTime hfin, boolean previsto, int nalumnos_ausentes, boolean transp_requerido,
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
        this.nalumnos_ausentes = nalumnos_ausentes;
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

    /**
     * Obtiene el ID de la solicitud.
     * 
     * @return el ID de la solicitud
     */
    public int getId_solicitud() {
        return id_solicitud;
    }

    /**
     * Obtiene el profesor solicitante.
     * 
     * @return el profesor solicitante
     */
    public Profesor get_solicitante() {
        return solicitante;
    }

    /**
     * Obtiene el título de la solicitud.
     * 
     * @return el título de la solicitud
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el tipo de actividad.
     * 
     * @return el tipo de actividad
     */
    public TipoActividad getTipo() {
        return tipo;
    }

    /**
     * Obtiene la fecha de inicio.
     * 
     * @return la fecha de inicio
     */
    public LocalDate getFini() {
        return fini;
    }

    /**
     * Obtiene la fecha de fin.
     * 
     * @return la fecha de fin
     */
    public LocalDate getFfin() {
        return ffin;
    }

    /**
     * Obtiene la hora de inicio.
     * 
     * @return la hora de inicio
     */
    public LocalTime getHini() {
        return hini;
    }

    /**
     * Obtiene la hora de fin.
     * 
     * @return la hora de fin
     */
    public LocalTime getHfin() {
        return hfin;
    }

    /**
     * Indica si la actividad está prevista.
     * 
     * @return true si la actividad está prevista, false de lo contrario
     */
    public boolean isPrevisto() {
        return previsto;
    }

    /**
     * Indica si se requiere transporte.
     * 
     * @return true si se requiere transporte, false de lo contrario
     */
    public boolean isTransp_requerido() {
        return transp_requerido;
    }

    /**
     * Obtiene el comentario sobre el transporte.
     * 
     * @return el comentario sobre el transporte
     */
    public String getTransp_comentario() {
        return transp_comentario;
    }

    /**
     * Indica si se requiere alojamiento.
     * 
     * @return true si se requiere alojamiento, false de lo contrario
     */
    public boolean isAloj_requerido() {
        return aloj_requerido;
    }

    /**
     * Obtiene el comentario sobre el alojamiento.
     * 
     * @return el comentario sobre el alojamiento
     */
    public String getAloj_comentario() {
        return aloj_comentario;
    }

    /**
     * Obtiene el comentario adicional.
     * 
     * @return el comentario adicional
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Obtiene el estado de la actividad.
     * 
     * @return el estado de la actividad
     */
    public EstadoActividad getEstado() {
        return estado;
    }

    /**
     * Obtiene el comentario sobre el estado.
     * 
     * @return el comentario sobre el estado
     */
    public String getEstado_comentario() {
        return estado_comentario;
    }

    /**
     * Obtiene el ID del departamento del solicitante.
     * 
     * @return el ID del departamento del solicitante
     */
    public int getDepartamento() {
        return solicitante.getDepartamento().getId();
    }

    /**
     * Obtiene el profesor solicitante.
     * 
     * @return el profesor solicitante
     */
    public Profesor getSolicitante() {
        return solicitante;
    }

    /**
     * Obtiene el nombre completo del solicitante.
     * 
     * @return el nombre completo del solicitante
     */
    public String get_Solicitante() {
        return solicitante.getNombre().concat(" ")
                .concat(solicitante.getApellidos());
    }

    /**
     * Establece el estado de la actividad.
     * 
     * @param estado el estado de la actividad
     */
    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }

    /**
     * Establece el comentario sobre el estado.
     * 
     * @param estado_comentario el comentario sobre el estado
     */
    public void setEstado_comentario(String estado_comentario) {
        this.estado_comentario = estado_comentario;
    }

    /**
     * Obtiene el número total de alumnos.
     * 
     * @return el número total de alumnos
     */
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

        return numAlumnos - nalumnos_ausentes;
    }

    /**
     * Obtiene la lista de medios de transporte.
     * 
     * @return la lista de medios de transporte
     */
    public List<MediosTransporte> getTransportes() {
        return transportes;
    }

    /**
     * Obtiene la lista de participantes.
     * 
     * @return la lista de participantes
     */
    public List<Profesor> getParticipantes() {
        return participantes;
    }

    /**
     * Obtiene la lista de responsables.
     * 
     * @return la lista de responsables
     */
    public List<Profesor> getResponsables() {
        return responsables;
    }

    /**
     * Obtiene la lista de cursos.
     * 
     * @return la lista de cursos
     */
    public List<Curso> getCursos() {
        return cursos;
    }

    /**
     * Obtiene la lista de grupos.
     * 
     * @return la lista de grupos
     */
    public List<Grupo> getGrupos() {
        return grupos;
    }

    /**
     * Obtiene el número de alumnos ausentes.
     * 
     * @return el número de alumnos ausentes
     */
    public int getAlumnos_ausentes() {
        return nalumnos_ausentes;
    }

    /**
     * Compara si dos objetos Solicitud son iguales.
     * 
     * @param obj                 el objeto a comparar
     * @param participantesNombre la lista de nombres de los participantes
     * @param responsablesNombre  la lista de nombres de los responsables
     * @param transportes         la lista de medios de transporte
     * @param cursos              la lista de cursos
     * @param grupos              la lista de grupos
     * @param curso               indica si se trata de un curso
     * @return true si los objetos son iguales, false de lo contrario
     */
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
