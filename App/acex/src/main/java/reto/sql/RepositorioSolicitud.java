package reto.sql;

import java.util.List;

public interface RepositorioSolicitud<T> {

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
     * Guarda un objeto en la base de datos.
     * 
     * @param objeto el objeto a guardar
     * @return true si el objeto se guarda correctamente, false en caso contrario
     */
    public int guardar(T objeto);

}
