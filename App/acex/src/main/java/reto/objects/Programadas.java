package reto.objects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import reto.sql.ProgramadasDAO;

/**
 * La clase Programadas representa una actividad programada en el sistema.
 * Contiene información sobre la actividad, como el solicitante, el título, las
 * fechas y horas, los comentarios, etc.
 */
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
    private int nalumnos_ausentes;
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

    /**
     * Constructor de la clase Programadas.
     * Crea una nueva instancia de Programadas con los parámetros especificados.
     * 
     * @param id_programada      El ID de la actividad programada.
     * @param solicitada         La solicitud asociada a la actividad.
     * @param solicitante        El profesor solicitante de la actividad.
     * @param titulo             El título de la actividad.
     * @param tipo               El tipo de actividad.
     * @param fini               La fecha de inicio de la actividad.
     * @param ffin               La fecha de fin de la actividad.
     * @param hini               La hora de inicio de la actividad.
     * @param hfin               La hora de fin de la actividad.
     * @param previsto           Indica si la actividad está prevista o no.
     * @param transp_requerido   Indica si se requiere transporte para la actividad.
     * @param transp_comentario  El comentario sobre el transporte requerido.
     * @param aloj_requerido     Indica si se requiere alojamiento para la
     *                           actividad.
     * @param aloj_comentario    El comentario sobre el alojamiento requerido.
     * @param comentario         El comentario general sobre la actividad.
     * @param estado             El estado de la actividad.
     * @param estado_comentario  El comentario sobre el estado de la actividad.
     * @param empresa_transporte La empresa de transporte asociada a la actividad.
     * @param precio_transporte  El precio del transporte para la actividad.
     * @param transportes        La lista de medios de transporte asociados a la
     *                           actividad.
     * @param responsables       La lista de profesores responsables de la
     *                           actividad.
     * @param participantes      La lista de profesores participantes en la
     *                           actividad.
     * @param cursos             La lista de cursos asociados a la actividad.
     * @param grupos             La lista de grupos asociados a la actividad.
     */
    public Programadas(int id_programada, Solicitud solicitada, Profesor solicitante, String titulo, TipoActividad tipo,
            LocalDate fini, LocalDate ffin, LocalTime hini, LocalTime hfin, boolean previsto, int nalumnos_ausentes,
            boolean transp_requerido,
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
        this.nalumnos_ausentes = nalumnos_ausentes;
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

    /**
     * Constructor de la clase Programadas.
     * Crea una nueva instancia de Programadas con los parámetros especificados.
     * El ID de la actividad programada se generará automáticamente.
     * 
     * @param solicitada         La solicitud asociada a la actividad.
     * @param solicitante        El profesor solicitante de la actividad.
     * @param titulo             El título de la actividad.
     * @param tipo               El tipo de actividad.
     * @param fini               La fecha de inicio de la actividad.
     * @param ffin               La fecha de fin de la actividad.
     * @param hini               La hora de inicio de la actividad.
     * @param hfin               La hora de fin de la actividad.
     * @param previsto           Indica si la actividad está prevista o no.
     * @param transp_requerido   Indica si se requiere transporte para la actividad.
     * @param transp_comentario  El comentario sobre el transporte requerido.
     * @param aloj_requerido     Indica si se requiere alojamiento para la
     *                           actividad.
     * @param aloj_comentario    El comentario sobre el alojamiento requerido.
     * @param comentario         El comentario general sobre la actividad.
     * @param estado             El estado de la actividad.
     * @param estado_comentario  El comentario sobre el estado de la actividad.
     * @param empresa_transporte La empresa de transporte asociada a la actividad.
     * @param precio_transporte  El precio del transporte para la actividad.
     * @param transportes        La lista de medios de transporte asociados a la
     *                           actividad.
     * @param responsables       La lista de profesores responsables de la
     *                           actividad.
     * @param participantes      La lista de profesores participantes en la
     *                           actividad.
     * @param cursos             La lista de cursos asociados a la actividad.
     * @param grupos             La lista de grupos asociados a la actividad.
     */
    public Programadas(Solicitud solicitada, Profesor solicitante, String titulo, TipoActividad tipo,
            LocalDate fini, LocalDate ffin, LocalTime hini, LocalTime hfin, boolean previsto, int nalumnos_ausentes,
            boolean transp_requerido,
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
        this.nalumnos_ausentes = nalumnos_ausentes;
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

    /**
     * Obtiene el ID de la actividad programada.
     * 
     * @return El ID de la actividad programada.
     */
    public int getId_programada() {
        return id_programada;
    }

    /**
     * Obtiene la solicitud asociada a la actividad.
     * 
     * @return La solicitud asociada a la actividad.
     */
    public Solicitud get_solicitada() {
        return solicitada;
    }

    /**
     * Obtiene el profesor solicitante de la actividad.
     * 
     * @return El profesor solicitante de la actividad.
     */
    public Profesor get_solicitante() {
        return solicitante;
    }

    /**
     * Obtiene el título de la actividad.
     * 
     * @return El título de la actividad.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el tipo de actividad.
     * 
     * @return El tipo de actividad.
     */
    public TipoActividad getTipo() {
        return tipo;
    }

    /**
     * Obtiene la fecha de inicio de la actividad.
     * 
     * @return La fecha de inicio de la actividad.
     */
    public LocalDate getFini() {
        return fini;
    }

    /**
     * Obtiene la fecha de fin de la actividad.
     * 
     * @return La fecha de fin de la actividad.
     */
    public LocalDate getFfin() {
        return ffin;
    }

    /**
     * Obtiene la hora de inicio de la actividad.
     * 
     * @return La hora de inicio de la actividad.
     */
    public LocalTime getHini() {
        return hini;
    }

    /**
     * Obtiene la hora de fin de la actividad.
     * 
     * @return La hora de fin de la actividad.
     */
    public LocalTime getHfin() {
        return hfin;
    }

    /**
     * Indica si la actividad está prevista o no.
     * 
     * @return true si la actividad está prevista, false de lo contrario.
     */
    public boolean isPrevisto() {
        return previsto;
    }

    /**
     * Indica si se requiere transporte para la actividad.
     * 
     * @return true si se requiere transporte, false de lo contrario.
     */
    public boolean isTransp_requerido() {
        return transp_requerido;
    }

    /**
     * Obtiene el comentario sobre el transporte requerido.
     * 
     * @return El comentario sobre el transporte requerido.
     */
    public String getTransp_comentario() {
        return transp_comentario;
    }

    /**
     * Indica si se requiere alojamiento para la actividad.
     * 
     * @return true si se requiere alojamiento, false de lo contrario.
     */
    public boolean isAloj_requerido() {
        return aloj_requerido;
    }

    /**
     * Obtiene el comentario sobre el alojamiento requerido.
     * 
     * @return El comentario sobre el alojamiento requerido.
     */
    public String getAloj_comentario() {
        return aloj_comentario;
    }

    /**
     * Obtiene el comentario general sobre la actividad.
     * 
     * @return El comentario general sobre la actividad.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Obtiene el estado de la actividad.
     * 
     * @return El estado de la actividad.
     */
    public EstadoActividad getEstado() {
        return estado;
    }

    /**
     * Obtiene el comentario sobre el estado de la actividad.
     * 
     * @return El comentario sobre el estado de la actividad.
     */
    public String getEstado_comentario() {
        return estado_comentario;
    }

    /**
     * Obtiene la empresa de transporte asociada a la actividad.
     * 
     * @return La empresa de transporte asociada a la actividad.
     */
    public String getEmpresa_transporte() {
        return empresa_transporte;
    }

    /**
     * Obtiene el precio del transporte para la actividad.
     * 
     * @return El precio del transporte para la actividad.
     */
    public double getPrecio_transporte() {
        return precio_transporte;
    }

    /**
     * Obtiene el ID del departamento del profesor solicitante.
     * 
     * @return El ID del departamento del profesor solicitante.
     */
    public int getDepartamento() {
        return solicitante.getDepartamento().getId();
    }

    /**
     * Obtiene el profesor solicitante de la actividad.
     * 
     * @return El profesor solicitante de la actividad.
     */
    public Profesor getSolicitante() {
        return solicitante;
    }

    /**
     * Obtiene la solicitud asociada a la actividad.
     * 
     * @return La solicitud asociada a la actividad.
     */
    public Solicitud getSolicitada() {
        return solicitada;
    }

    /**
     * Obtiene el nombre completo del profesor solicitante.
     * 
     * @return El nombre completo del profesor solicitante.
     */
    public String get_Solicitante() {
        return solicitante.getNombre().concat(" ")
                .concat(solicitante.getApellidos());
    }

    /**
     * Obtiene la lista de medios de transporte asociados a la actividad.
     * 
     * @return La lista de medios de transporte asociados a la actividad.
     */
    public List<MediosTransporte> getTransportes() {
        return transportes;
    }

    /**
     * Obtiene la lista de profesores responsables de la actividad.
     * 
     * @return La lista de profesores responsables de la actividad.
     */
    public List<Profesor> getResponsables() {
        return responsables;
    }

    /**
     * Obtiene la lista de profesores participantes en la actividad.
     * 
     * @return La lista de profesores participantes en la actividad.
     */
    public List<Profesor> getParticipantes() {
        return participantes;
    }

    /**
     * Obtiene la lista de cursos asociados a la actividad.
     * 
     * @return La lista de cursos asociados a la actividad.
     */
    public List<Curso> getCursos() {
        return cursos;
    }

    /**
     * Obtiene la lista de grupos asociados a la actividad.
     * 
     * @return La lista de grupos asociados a la actividad.
     */
    public List<Grupo> getGrupos() {
        return grupos;
    }

    /**
     * Obtiene el número de alumnos ausentes en la actividad.
     * 
     * @return El número de alumnos ausentes en la actividad.
     */
    public int getNalumnos_ausentes() {
        return nalumnos_ausentes;
    }

    /**
     * Compara si esta actividad programada es igual a otro objeto.
     * 
     * @param obj                 El objeto a comparar.
     * @param participantesNombre La lista de nombres de los participantes.
     * @param responsablesNombre  La lista de nombres de los responsables.
     * @param transportes         La lista de medios de transporte.
     * @param cursos              La lista de cursos.
     * @param grupos              La lista de grupos.
     * @param curso               Indica si se trata de un curso o no.
     * @return true si la actividad programada es igual al objeto especificado,
     *         false de lo contrario.
     */
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
