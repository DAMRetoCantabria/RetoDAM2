package reto.objects;

/**
 * La clase MediosTransporte representa un medio de transporte con un
 * identificador y un nombre.
 */
public class MediosTransporte {
    private int id;
    private String nombre;

    /**
     * Crea una instancia de MediosTransporte con el identificador y nombre
     * especificados.
     * 
     * @param id     el identificador del medio de transporte
     * @param nombre el nombre del medio de transporte
     */
    public MediosTransporte(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador del medio de transporte.
     * 
     * @return el identificador del medio de transporte
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del medio de transporte.
     * 
     * @return el nombre del medio de transporte
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Compara si este objeto MediosTransporte es igual a otro objeto
     * basándose únicamente en la igualdad de sus identificadores.
     * 
     * @param obj el objeto a comparar
     * @return true si los objetos son iguales en cuanto a su identificador, false
     *         en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediosTransporte other = (MediosTransporte) obj;
        return id == other.id;
    }
}
