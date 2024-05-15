package reto.sql;

import java.util.List;

/**
 * La interfaz Repositorio define los métodos básicos para interactuar con una
 * base de datos.
 * 
 * @param <T> el tipo de objeto que se va a almacenar en la base de datos
 */
public interface Repositorio<T> {

    /**
     * Retorna una lista de todos los objetos almacenados en la base de datos.
     * 
     * @return una lista de objetos
     */
    public List<T> listar();

    /**
     * Busca un objeto en la base de datos por su identificador.
     * 
     * @param id el identificador del objeto a buscar
     * @return el objeto encontrado, o null si no se encuentra
     */
    public T buscar(int id);

    /**
     * Busca un objeto en la base de datos por su código.
     * 
     * @param codigo el código del objeto a buscar
     * @return el objeto encontrado, o null si no se encuentra
     */
    public T buscar(String codigo);

    /**
     * Guarda un objeto en la base de datos.
     * 
     * @param objeto el objeto a guardar
     * @return true si el objeto se guarda correctamente, false en caso contrario
     */
    public boolean guardar(T objeto);

    /**
     * Borra un objeto de la base de datos.
     * 
     * @param objeto el objeto a borrar
     * @return true si el objeto se borra correctamente, false en caso contrario
     */
    public boolean borrar(T objeto);

}
