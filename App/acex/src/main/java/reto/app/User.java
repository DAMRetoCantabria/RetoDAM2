package reto.app;

/**
 * Clase que representa a un usuario.
 */
public class User {
    private String nombre;
    private String nivel;
    private String foto;
    private int id;

    /**
     * Constructor de la clase User.
     * 
     * @param nombre    El nombre del usuario.
     * @param apellidos Los apellidos del usuario.
     * @param nivel     El nivel del usuario.
     * @param foto      La foto del usuario.
     * @param id        El id del usuario.
     */
    public User(String nombre, String apellidos, String nivel, String foto, int id) {
        this.nombre = nombre.concat(" ").concat(apellidos);
        this.nivel = nivel;
        this.foto = foto;
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el nivel del usuario.
     * 
     * @return El nivel del usuario.
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Obtiene la foto del usuario.
     * 
     * @return La foto del usuario.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Obtiene el id del usuario.
     * 
     * @return El id del usuario.
     */
    public int getId() {
        return id;
    }
}
