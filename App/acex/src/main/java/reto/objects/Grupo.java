package reto.objects;

/**
 * La clase Grupo representa un grupo de alumnos en un curso.
 */
public class Grupo {
    private int id;
    // private int curso;
    private Curso curso;
    private String codigo;
    private int num_alumnos;
    private boolean activo;

    /**
     * Constructor de la clase Grupo.
     * 
     * @param id          el ID del grupo
     * @param curso       el número del curso al que pertenece el grupo
     * @param codigo      el código del grupo
     * @param num_alumnos el número de alumnos en el grupo
     * @param activo      indica si el grupo está activo o no
     */
    public Grupo(int id, /* int curso */ Curso curso, String codigo, int num_alumnos, boolean activo) {
        this.id = id;
        // this.curso = curso;
        this.curso = curso;
        this.codigo = codigo;
        this.num_alumnos = num_alumnos;
        this.activo = activo;
    }

    /**
     * Constructor de la clase Grupo sin ID.
     * 
     * @param curso       el número del curso al que pertenece el grupo
     * @param codigo      el código del grupo
     * @param num_alumnos el número de alumnos en el grupo
     * @param activo      indica si el grupo está activo o no
     */
    public Grupo(/* int curso */ Curso curso, String codigo, int num_alumnos, boolean activo) {
        this.curso = curso;
        this.codigo = codigo;
        this.num_alumnos = num_alumnos;
        this.activo = activo;
    }

    /**
     * Obtiene el ID del grupo.
     * 
     * @return el ID del grupo
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el número del curso al que pertenece el grupo.
     * 
     * @return el número del curso
     *
     *         public int getCurso() {
     *         return curso;
     *         }
     */

    public Curso getCurso() {
        return curso;
    }

    /**
     * Obtiene el código del grupo.
     * 
     * @return el código del grupo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el número de alumnos en el grupo.
     * 
     * @return el número de alumnos
     */
    public int getNum_alumnos() {
        return num_alumnos;
    }

    /**
     * Verifica si el grupo está activo.
     * 
     * @return true si el grupo está activo, false de lo contrario
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Establece el estado de activo del grupo.
     * 
     * @param activo el estado de activo a establecer
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Compara si dos objetos Grupo son iguales basándose en su ID.
     * 
     * @param obj el objeto a comparar
     * @return true si los objetos son iguales por su ID, false de lo contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Grupo other = (Grupo) obj;
        return id == other.id;
    }
}
