package reto.objects;

/**
 * La clase Profesor representa a un profesor en el sistema.
 */
public class Profesor {
    private int id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String correo;
    private String password;
    private Nivel nivel;
    private boolean activo;
    private String foto;
    // private int departamento;
    private Departamento departamento;

    /**
     * Constructor de la clase Profesor.
     * 
     * @param id           el ID del profesor
     * @param dni          el DNI del profesor
     * @param nombre       el nombre del profesor
     * @param apellidos    los apellidos del profesor
     * @param correo       el correo electrónico del profesor
     * @param password     la contraseña del profesor
     * @param nivel        el nivel del profesor
     * @param activo       indica si el profesor está activo o no
     * @param foto         la foto del profesor
     * @param departamento el departamento al que pertenece el profesor
     */
    public Profesor(int id, String dni, String nombre, String apellidos, String correo, String password, Nivel nivel,
            boolean activo, String foto, /* int departamento */ Departamento departamento) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        if (password == null) {
            password = "";
        } else {
            this.password = password;
        }
        if (nivel == null) {
            nivel = Nivel.Profesor;
        } else {
            this.nivel = nivel;
        }
        this.activo = activo;
        if (foto == null) {
            foto = "";
        } else {
            this.foto = foto;
        }
        if (departamento == null) {
            departamento = null;
        } else {
            this.departamento = departamento;
        }
        // this.departamento = departamento;
    }

    /**
     * Constructor de la clase Profesor.
     * 
     * @param dni          el DNI del profesor
     * @param nombre       el nombre del profesor
     * @param apellidos    los apellidos del profesor
     * @param correo       el correo electrónico del profesor
     * @param password     la contraseña del profesor
     * @param nivel        el nivel del profesor
     * @param activo       indica si el profesor está activo o no
     * @param foto         la foto del profesor
     * @param departamento el departamento al que pertenece el profesor
     */
    public Profesor(String dni, String nombre, String apellidos, String correo, String password, Nivel nivel,
            boolean activo, String foto, /* int departamento */ Departamento departamento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        if (password == null) {
            password = "";
        } else {
            this.password = password;
        }
        if (nivel == null) {
            nivel = Nivel.Profesor;
        } else {
            this.nivel = nivel;
        }
        this.activo = activo;
        if (foto == null) {
            foto = "";
        } else {
            this.foto = foto;
        }
        if (departamento == null) {
            departamento = null;
        } else {
            this.departamento = departamento;
        }
        // this.departamento = departamento;
    }

    /**
     * Obtiene el ID del profesor.
     * 
     * @return el ID del profesor
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el DNI del profesor.
     * 
     * @return el DNI del profesor
     */
    public String getDni() {
        return dni;
    }

    /**
     * Obtiene el nombre del profesor.
     * 
     * @return el nombre del profesor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos del profesor.
     * 
     * @return los apellidos del profesor
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene el correo electrónico del profesor.
     * 
     * @return el correo electrónico del profesor
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Obtiene la contraseña del profesor.
     * 
     * @return la contraseña del profesor
     */
    public String getPassword() {
        return password;
    }

    /**
     * Obtiene el nivel del profesor.
     * 
     * @return el nivel del profesor
     */
    public Nivel getNivel() {
        return nivel;
    }

    /**
     * Indica si el profesor está activo o no.
     * 
     * @return true si el profesor está activo, false de lo contrario
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Obtiene la foto del profesor.
     * 
     * @return la foto del profesor
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Obtiene el departamento al que pertenece el profesor.
     * 
     * @return el departamento del profesor
     *
     *         public int getDepartamento() {
     *         return departamento;
     *         }
     */

    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * Establece la contraseña del profesor.
     * 
     * @param password la nueva contraseña del profesor
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Establece si el profesor esta activo
     * 
     * @param nivel el nuevo estado del profesor
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Profesor getProfesor() {
        return this;
    }

    /**
     * Compara si este profesor es igual a otro profesor basado en su ID.
     * 
     * @param obj el objeto a comparar
     * @return true si los profesores son iguales por su ID, false de lo contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Profesor other = (Profesor) obj;
        return id == other.id;
    }
}
