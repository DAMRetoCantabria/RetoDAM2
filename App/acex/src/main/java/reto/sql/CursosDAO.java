package reto.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import reto.objects.Curso;
import reto.objects.Etapa;

/**
 * Esta clase implementa la interfaz Repositorio y se encarga de realizar
 * operaciones CRUD en la tabla "cursos" de la base de datos.
 */
public class CursosDAO implements Repositorio<Curso> {

    /**
     * Obtiene la conexión a la base de datos.
     * 
     * @return la conexión a la base de datos
     */
    private Connection getConnection() {
        return MySQL.getInstance().getConnection();
    }

    /**
     * Obtiene una lista de todos los cursos en la base de datos.
     * 
     * @return una lista de cursos
     */
    @Override
    public List<Curso> listar() {
        List<Curso> cursos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM cursos");) {
            while (rs.next()) {
                Curso curso = creaCurso(rs);
                if (!cursos.add(curso)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cursos;
    }

    /**
     * Busca un curso por su ID en la base de datos.
     * 
     * @param id el ID del curso a buscar
     * @return el curso encontrado, o null si no se encontró ningún curso con el ID
     *         especificado
     */
    @Override
    public Curso buscar(int id) {
        Curso curso = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM cursos WHERE id_curso = ?");) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    curso = creaCurso(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return curso;
    }

    /**
     * Busca un curso por su código en la base de datos.
     * 
     * @param codigo el código del curso a buscar
     * @return el curso encontrado, o null si no se encontró ningún curso con el
     *         código especificado
     */
    @Override
    public Curso buscar(String codigo) {
        Curso curso = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM cursos WHERE cod_curso = ?");) {
            statement.setString(1, codigo);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    curso = creaCurso(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return curso;
    }

    /**
     * Guarda un curso en la base de datos. Si el curso ya tiene un ID asignado, se
     * realiza una actualización, de lo contrario se realiza una inserción.
     * 
     * @param curso el curso a guardar
     * @return true si la operación fue exitosa, false de lo contrario
     */
    @Override
    public boolean guardar(Curso curso) {
        boolean correcto = false;
        String sql = null;
        if (curso.getId() > 0) {
            sql = "UPDATE cursos SET cod_curso=?,descripcion=?, etapa=?, activo=? WHERE id_curso=?";
        } else {
            sql = "INSERT INTO cursos(cod_curso, descripcion, etapa, activo) VALUES (?,?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            if (curso.getId() > 0) {
                stmt.setInt(5, curso.getId());
            }
            stmt.setString(1, curso.getCodigo());
            stmt.setString(2, curso.getDescripcion());
            stmt.setString(3, String.valueOf(curso.getEtapa()));
            stmt.setBoolean(4, curso.isActivo());

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

    @Override
    public boolean borrar(Curso curso) {
        boolean correcto = false;
        String sql = "DELETE FROM cursos WHERE id_curso=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, curso.getId());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha eliminado un solo registro");
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

    public int getNumAlumnos(int curso) {
        int numAlumnos = 0;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT SUM(num_alumnos) FROM grupos WHERE curso = ?");) {
            statement.setInt(1, curso);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    numAlumnos = rs.getInt("SUM(num_alumnos)");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return numAlumnos;
    }

    /**
     * Crea un objeto Curso a partir de un ResultSet.
     * 
     * @param rs el ResultSet que contiene los datos del curso
     * @return el curso creado
     * @throws SQLException si ocurre un error al acceder a los datos del ResultSet
     */
    private Curso creaCurso(final ResultSet rs) throws SQLException {
        return new Curso(rs.getInt("id_curso"), rs.getString("cod_curso"), rs.getString("descripcion"),
                Etapa.valueOf(rs.getString("etapa")),
                rs.getBoolean("activo"));
    }

}
