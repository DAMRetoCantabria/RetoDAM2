package ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Esta clase representa una conexión a una base de datos MySQL..
 */
public class MySQL {
    private Connection connection = null;
    private static final String BD = "ACEX_IESMH";
    private static final String USUARIO = "desarrollo";
    private static final String CLAVE = "retacantabria24";
    private static final String URL = "jdbc:mysql://10.0.16.34:3306/" + BD;    
    
    /**
     * Constructor privado para evitar la creación de instancias directamente.
     * Se establece la conexión a la base de datos MySQL.
     */
    private MySQL() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", USUARIO);
            properties.setProperty("password", CLAVE);
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");
            connection = (Connection) DriverManager.getConnection(URL, properties);
            if (connection == null) {
                System.out.println("Error de conexion");
            } else {
                System.out.println("Conexion correcta a " + URL);
            }
        } catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    /**
     * Método estático que devuelve la única instancia de la clase MySQL.
     * @return la instancia de MySQL
     */
    public static MySQL getInstance() {
        return AccesoHolder.INSTANCE;
    }
    
    /**
     * Clase interna que contiene la única instancia de MySQL.
     */
    private static class AccesoHolder {
        private static final MySQL INSTANCE = new MySQL();
    }
    
    /**
     * Método para obtener la conexión a la base de datos.
     * @return la conexión a la base de datos
     */
    public Connection getConnection() {
        return connection;
    }
    
    /**
     * Método para cerrar la conexión a la base de datos.
     * @return true si la conexión se cerró correctamente, false en caso contrario
     */
    public boolean cerrar() {
        boolean siCerrada = false ;
        try {
            connection.close();
            if (connection.isClosed()) {
                siCerrada = true;
            }
        } catch (SQLException sqe) {
            System.out.println("Se produjo un error en el cierre");
        }
        return siCerrada;
    }    
}
