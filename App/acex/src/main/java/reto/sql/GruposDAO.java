package reto.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import reto.objects.Grupo;

/**
 * Esta clase implementa la interfaz Repositorio y proporciona métodos para
 * acceder y manipular los datos de la tabla "grupos" en la base de datos.
 */
public class GruposDAO implements Repositorio<Grupo> {

    /**
     * Obtiene la conexión a la base de datos.
     * 
     * @return la conexión a la base de datos
     */
    private Connection getConnection() {
        return MySQL.getInstance().getConnection();
    }

    /**
     * Obtiene una lista de todos los grupos en la base de datos.
     * 
     * @return una lista de grupos
     */
    @Override
    public List<Grupo> listar() {
        List<Grupo> grupos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM grupos");) {
            while (rs.next()) {
                Grupo grupo = creaGrupo(rs);
                if (!grupos.add(grupo)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return grupos;
    }

    /**
     * Busca un grupo por su ID en la base de datos.
     * 
     * @param id el ID del grupo a buscar
     * @return el grupo encontrado, o null si no se encontró ningún grupo con el ID
     *         especificado
     */
    @Override
    public Grupo buscar(int id) {
        Grupo grupo = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM grupos WHERE id_grupo = ?");) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    grupo = creaGrupo(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return grupo;
    }

    /**
     * Busca un grupo por su código en la base de datos.
     * 
     * @param codigo el código del grupo a buscar
     * @return el grupo encontrado, o null si no se encontró ningún grupo con el
     *         código especificado
     */
    @Override
    public Grupo buscar(String codigo) {
        Grupo grupo = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM grupos WHERE cod_grupo = ?");) {
            statement.setString(1, codigo);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    grupo = creaGrupo(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return grupo;
    }

    /**
     * Guarda un grupo en la base de datos. Si el grupo ya existe, se actualiza; de
     * lo contrario, se inserta como un nuevo grupo.
     * 
     * @param grupo el grupo a guardar
     * @return true si se guardó correctamente, false de lo contrario
     */
    @Override
    public boolean guardar(Grupo grupo) {
        boolean correcto = false;
        String sql = null;
        if (grupo.getId() > 0) {
            sql = "UPDATE grupos SET curso=?,cod_grupo=?, num_alumnos=?, activo=? WHERE id_grupo=?";
        } else {
            sql = "INSERT INTO grupos(curso, cod_grupo, num_alumnos, activo) VALUES (?,?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            if (grupo.getId() > 0) {
                stmt.setInt(5, grupo.getId());
            }
            stmt.setInt(1, grupo.getCurso().getId());
            stmt.setString(2, grupo.getCodigo());
            stmt.setInt(3, grupo.getNum_alumnos());
            stmt.setBoolean(4, grupo.isActivo());

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
    public boolean borrar(Grupo grupo) {
        boolean correcto = false;
        String sql = "DELETE FROM grupos WHERE id_grupo=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, grupo.getId());
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
     * Crea un objeto Grupo a partir de los datos del ResultSet.
     * 
     * @param rs el ResultSet que contiene los datos del grupo
     * @return el objeto Grupo creado
     * @throws SQLException si ocurre un error al acceder a los datos del ResultSet
     */
    private Grupo creaGrupo(final ResultSet rs) throws SQLException {
        CursosDAO cursoSQL = new CursosDAO();
        return new Grupo(rs.getInt("id_grupo"), cursoSQL.buscar(rs.getInt("curso")), rs.getString("cod_grupo"), rs.getInt("num_alumnos"),
                rs.getBoolean("activo"));
    }

}
