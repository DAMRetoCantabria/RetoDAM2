package reto.objects;

/**
 * La clase Departamento representa un departamento en una organización.
 */
public class Departamento {
    private int id;
    private String codigo;
    private String nombre;
    private Profesor jefe;

    /**
     * Crea un nuevo objeto Departamento con el ID, código, nombre y jefee
     * especificados.
     * 
     * @param id     el ID del departamento
     * @param codigo el código del departamento
     * @param nombre el nombre del departamento
     * @param jefe   el objeto del jefe del departamento
     */
    public Departamento(int id, String codigo, String nombre, Profesor jefe) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        if (jefe != null) {
            this.jefe = jefe;
        } else {
            this.jefe = null;
        }
    }

    /**
     * Crea un nuevo objeto Departamento con el código, nombre y jefee
     * especificados.
     * 
     * @param codigo el código del departamento
     * @param nombre el nombre del departamento
     * @param jefe   el objeto del jefe del departamento
     */
    public Departamento(String codigo, String nombre, Profesor jefe) {
        this.codigo = codigo;
        this.nombre = nombre;
        if (jefe != null) {
            this.jefe = jefe;
        } else {
            this.jefe = null;
        }
    }

    /**
     * Obtiene el ID del departamento.
     * 
     * @return el ID del departamento
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el código del departamento.
     * 
     * @return el código del departamento
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del departamento.
     * 
     * @return el nombre del departamento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el ID del jefee del departamento.
     * 
     * @return el ID del jefee del departamento
     *
     *         public int getjefee() {
     *         return jefee;
     *         }
     */

    public Profesor getJefe() {
        return jefe;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Departamento other = (Departamento) obj;

        if (other.jefe == null) {
            return other.codigo.equals(codigo) &&
            other.nombre.equals(nombre);
        } else {
            boolean jefecond = ((other.jefe == null && jefe == null) || other.jefe.equals(jefe));
            return other.codigo.equals(codigo) &&
            other.nombre.equals(nombre) &&
            jefecond;
        }
    }
}
