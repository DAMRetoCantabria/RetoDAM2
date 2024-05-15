package reto.objects;

import reto.sql.CursosDAO;

/**
 * La clase Curso representa un curso en el sistema.
 */
public class Curso {
    private int id;
    private String codigo;
    private String descripcion;
    private Etapa etapa;
    private boolean activo;

    /**
     * Constructor de la clase Curso.
     * 
     * @param id          el ID del curso
     * @param codigo      el código del curso
     * @param descripcion la descripción del curso
     * @param etapa       la etapa del curso
     * @param activo      indica si el curso está activo o no
     */
    public Curso(int id, String codigo, String descripcion, Etapa etapa, boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.etapa = etapa;
        this.activo = activo;
    }

    /**
     * Constructor de la clase Curso sin ID.
     * 
     * @param codigo      el código del curso
     * @param descripcion la descripción del curso
     * @param etapa       la etapa del curso
     * @param activo      indica si el curso está activo o no
     */
    public Curso(String codigo, String descripcion, Etapa etapa, boolean activo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.etapa = etapa;
        this.activo = activo;
    }

    /**
     * Obtiene el ID del curso.
     * 
     * @return el ID del curso
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el código del curso.
     * 
     * @return el código del curso
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene la descripción del curso.
     * 
     * @return la descripción del curso
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene la etapa del curso.
     * 
     * @return la etapa del curso
     */
    public Etapa getEtapa() {
        return etapa;
    }

    /**
     * Verifica si el curso está activo.
     * 
     * @return true si el curso está activo, false de lo contrario
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Este método se utiliza para obtener el número de alumnos en un curso.
     * Crea una instancia de CursosDAO y llama a su método getNumAlumnos, pasando
     * el id de este curso como argumento.
     *
     * @return El número de alumnos en el curso.
     */
    public int getNumAlumnos() {
        CursosDAO cursoDAO = new CursosDAO();
        return cursoDAO.getNumAlumnos(this.id);
    }

    /**
     * Sobrescribe el método equals para comparar si dos objetos Curso son iguales.
     * Dos objetos Curso se consideran iguales si tienen el mismo id.
     *
     * @param obj El objeto a comparar con este Curso.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Curso other = (Curso) obj;
        return other.codigo.equals(codigo) && other.descripcion.equals(descripcion) && other.etapa.equals(etapa)
                && other.activo == activo;
    }

}