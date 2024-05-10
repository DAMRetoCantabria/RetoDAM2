package reto.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import reto.objects.Departamento;

/**
 * Esta clase implementa la interfaz Repositorio y proporciona métodos para
 * acceder a la tabla "departamentos" en la base de datos.
 */
public class DepartamentoDAO implements Repositorio<Departamento> {
    /**
     * Obtiene la conexión a la base de datos.
     * 
     * @return la conexión a la base de datos
     */
    private Connection getConnection() {
        return MySQL.getInstance().getConnection();
    }

    /**
     * Obtiene una lista de todos los departamentos en la base de datos.
     * 
     * @return una lista de Departamento
     */
    @Override
    public List<Departamento> listar() {
        List<Departamento> departamentos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM departamentos");) {
            while (rs.next()) {
                Departamento departamento = creaDepartamento(rs);
                if (!departamentos.add(departamento)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return departamentos;
    }

    /**
     * Busca un departamento por su ID en la base de datos.
     * 
     * @param id el ID del departamento a buscar
     * @return el objeto Departamento si se encuentra, null de lo contrario
     */
    @Override
    public Departamento buscar(int id) {
        Departamento departamento = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM departamentos WHERE id_depar = ?");) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    departamento = creaDepartamento(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return departamento;
    }

    /**
     * Busca un departamento por su código en la base de datos.
     * 
     * @param codigo el código del departamento a buscar
     * @return el objeto Departamento si se encuentra, null de lo contrario
     */
    public Departamento buscar(String codigo) {
        Departamento departamento = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM departamentos WHERE codigo = ?");) {
            statement.setString(1, codigo);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    departamento = creaDepartamento(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return departamento;
    }

    /**
     * Guarda un objeto Departamento en la base de datos.
     * 
     * @param departamento el objeto Departamento a guardar
     * @return true si se guardó correctamente, false de lo contrario
     */
    @Override
    public boolean guardar(Departamento departamento) {
        boolean correcto = false;
        String sql = null;
        if (departamento.getId() > 0) {
            sql = "UPDATE departamentos SET codigo=?,nombre=?, jefe=? WHERE id_depar=?";
        } else {
            sql = "INSERT INTO departamentos(codigo, nombre, jefe) VALUES (?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            if (departamento.getId() > 0) {
                stmt.setInt(4, departamento.getId());
            }
            stmt.setString(1, departamento.getCodigo());
            stmt.setString(2, departamento.getNombre());
            stmt.setInt(3, departamento.getJefe().getId());

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
    public boolean borrar(Departamento departamento) {
        boolean correcto = false;
        String sql = "DELETE FROM departamentos WHERE id_depar=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, departamento.getId());
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

    /**
     * Crea un objeto Departamento a partir de un ResultSet.
     * 
     * @param rs el ResultSet que contiene los datos del departamento
     * @return el objeto Departamento creado
     * @throws SQLException si ocurre un error al acceder a los datos del ResultSet
     */
    private Departamento creaDepartamento(final ResultSet rs) throws SQLException {
        ProfesorDAO profesorSQL = new ProfesorDAO();
        return new Departamento(rs.getInt("id_depar"), rs.getString("codigo"), rs.getString("nombre"),
                profesorSQL.buscar(rs.getInt("jefe")));
    }

}
