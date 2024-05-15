package reto.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import reto.objects.Nivel;
import reto.objects.Profesor;

/**
 * Esta clase implementa la interfaz Repositorio y proporciona métodos para
 * acceder a la tabla "profesores" en la base de datos.
 */
public class ProfesorDAO implements Repositorio<Profesor> {
    private DepartamentoDAO departamentoSQL = new DepartamentoDAO();
    /**
     * Obtiene la conexión a la base de datos.
     * 
     * @return La conexión a la base de datos.
     */
    private Connection getConnection() {
        return MySQL.getInstance().getConnection();
    }

    /**
     * Obtiene una lista de todos los profesores en la base de datos.
     * 
     * @return Una lista de profesores.
     */
    @Override
    public List<Profesor> listar() {
        List<Profesor> profesores = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM profesores");) {
            while (rs.next()) {
                Profesor profesor = creaProfesor(rs);
                if (!profesores.add(profesor)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return profesores;
    }

    /**
     * Busca un profesor por su ID en la base de datos.
     * 
     * @param id El ID del profesor a buscar.
     * @return El profesor encontrado, o null si no se encontró ningún profesor con
     *         el ID especificado.
     */
    @Override
    public Profesor buscar(int id) {
        Profesor profesor = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM profesores WHERE id = ?");) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    profesor = creaProfesor(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return profesor;
    }

    /**
     * Busca un profesor por su nombre y apellidos en la base de datos.
     * 
     * @param nombreape El nombre y apellidos del profesor a buscar.
     * @return El profesor encontrado, o null si no se encontró ningún profesor con
     *         el nombre y apellidos especificados.
     */
    @Override
    public Profesor buscar(String nombreape) {
        Profesor profesor = null;
        String nombre[] = nombreape.trim().split(" ");
        String nom, ape;
        if (nombre.length > 3) {
            nom = (nombre[0] + " " + nombre[1]).trim();
            ape = (nombre[2] + " " + nombre[3]).trim();
        } else {
            nom = nombre[0];
            ape = (nombre[1] + " " + nombre[2]).trim();
        }
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM profesores WHERE nombre LIKE ? AND apellidos LIKE ?");) {
            statement.setString(1, nom);
            statement.setString(2, ape);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    profesor = creaProfesor(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return profesor;
    }

    /**
     * Guarda un profesor en la base de datos.
     * 
     * @param profesor El profesor a guardar.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    @Override
    public boolean guardar(Profesor profesor) {
        boolean correcto = false;
        String sql = null;
        if (profesor.getId() > 0) {
            sql = "UPDATE profesores SET dni=?,nombre=?, apellidos=?, correo=?, password=?, nivel=?, activo=?, dep_id=?, foto=? WHERE id=?";
        } else {
            sql = "INSERT INTO profesores(dni, nombre, apellidos, correo, password, nivel, activo, dep_id, foto) VALUES (?,?,?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            if (profesor.getId() > 0) {
                stmt.setInt(10, profesor.getId());
            }
            stmt.setString(1, profesor.getDni());
            stmt.setString(2, profesor.getNombre());
            stmt.setString(3, profesor.getApellidos());
            stmt.setString(4, profesor.getCorreo());
            stmt.setString(5, profesor.getPassword());
            stmt.setString(6, String.valueOf(profesor.getNivel()));
            stmt.setInt(7, profesor.isActivo() ? 1 : 0);
            stmt.setInt(8, profesor.getDepartamento().getId());
            stmt.setString(9, profesor.getFoto() != null ? profesor.getFoto() : "/images/perfil.jpg");

            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            } else {
                correcto = true;
            }
        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return correcto;
    }

    @Override
    public boolean borrar(Profesor profesor) {
        boolean correcto = false;
        String sql = "DELETE FROM profesores WHERE id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, profesor.getId());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            } else {
                correcto = true;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return correcto;
    }

    /**
     * Crea un objeto Profesor a partir de un ResultSet.
     * 
     * @param rs El ResultSet que contiene los datos del profesor.
     * @return El objeto Profesor creado.
     * @throws SQLException Si ocurre un error al acceder a los datos del ResultSet.
     */
    private Profesor creaProfesor(final ResultSet rs) throws SQLException {
        return new Profesor(rs.getInt("id"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
                rs.getString("correo"), rs.getString("password"),
                rs.getString("nivel") == null ? Nivel.Profesor : Nivel.valueOf(rs.getString("nivel")),
                rs.getBoolean("activo"),
                rs.getString("foto"), departamentoSQL.buscar(rs.getInt("dep_id")));
    }
}
