/**
 * Esta clase implementa la interfaz RepositorioSolicitud y proporciona métodos para acceder y manipular los datos de las solicitudes en la base de datos.
 */
package reto.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import reto.objects.*;

/**
 * Esta clase implementa la interfaz RepositorioSolicitud y proporciona métodos
 * para acceder a la base de datos
 * y realizar operaciones CRUD en la tabla "solicitadas".
 */
public class SolicitudDAO implements RepositorioSolicitud<Solicitud> {
    private ProfesorDAO profesorSQL = new ProfesorDAO();

    /**
     * Obtiene la conexión a la base de datos.
     * 
     * @return La conexión a la base de datos.
     */
    private Connection getConnection() {
        return MySQL.getInstance().getConnection();
    }

    /**
     * Devuelve una lista de todas las solicitudes almacenadas en la base de datos.
     * 
     * @return Una lista de objetos Solicitud.
     */
    @Override
    public List<Solicitud> listar() {
        List<Solicitud> solicitudes = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM actividades");) {
            while (rs.next()) {
                Solicitud solicitud = creaSolicitud(rs);
                if (!solicitudes.add(solicitud)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return solicitudes;
    }

    /**
     * Guarda una solicitud en la base de datos.
     * Si la solicitud ya tiene un ID, se actualiza en la base de datos.
     * Si la solicitud no tiene un ID, se inserta como un nuevo registro en la base
     * de datos.
     * 
     * @param solicitud La solicitud a guardar.
     * @return El ID de la solicitud guardada.
     */
    @Override
    public int guardar(Solicitud solicitud) {
        String sql = null;
        if (solicitud.getId_solicitud() > 0) {
            sql = "UPDATE actividades SET solicitante=?,titulo=?, tipo=?, fini=?, ffin=?, hini=?, hfin=?, prevista=?, nalumnos_ausentes=?, transporte_req=?, coment_transporte=?, alojamiento_req=?, coment_alojamiento=?, comentarios=?, estado=?, coment_estado=? WHERE id_actividad=?";
        } else {
            sql = "INSERT INTO actividades(solicitante, titulo, tipo, fini, ffin, hini, hfin, prevista, nalumnos_ausentes, transporte_req, coment_transporte, alojamiento_req, coment_alojamiento, comentarios, estado, coment_estado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            if (solicitud.getId_solicitud() > 0) {
                stmt.setInt(16, solicitud.getId_solicitud());
            }
            stmt.setInt(1, solicitud.get_solicitante().getId());
            stmt.setString(2, solicitud.getTitulo());
            stmt.setString(3, solicitud.getTipo().toString());
            stmt.setDate(4, java.sql.Date.valueOf(solicitud.getFini()));
            stmt.setDate(5, java.sql.Date.valueOf(solicitud.getFfin()));
            stmt.setTime(6, java.sql.Time.valueOf(solicitud.getHini()));
            stmt.setTime(7, java.sql.Time.valueOf(solicitud.getHfin()));
            stmt.setString(8, solicitud.isPrevisto() ? "1" : "0");
            stmt.setInt(9, solicitud.getAlumnos_ausentes());
            stmt.setString(10, solicitud.isTransp_requerido() ? "1" : "0");
            stmt.setString(11, solicitud.getTransp_comentario());
            stmt.setString(12, solicitud.isAloj_requerido() ? "1" : "0");
            stmt.setString(13, solicitud.getAloj_comentario());
            stmt.setString(14, solicitud.getComentario());
            stmt.setString(15, solicitud.getEstado().toString());
            stmt.setString(16, solicitud.getEstado_comentario());

            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException ex) {
            // errores
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    /**
     * Busca una solicitud en la base de datos por su ID.
     * 
     * @param id El ID de la solicitud a buscar.
     * @return La solicitud encontrada, o null si no se encuentra ninguna solicitud
     *         con el ID especificado.
     */
    @Override
    public Solicitud buscar(int id) {
        String sql = "SELECT * FROM actividades WHERE id_actividad=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return creaSolicitud(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Guarda un responsable para una actividad en la base de datos.
     * 
     * @param id_actividad   El ID de la actividad.
     * @param id_responsable El ID del responsable.
     */
    public void guardarResponsable(int id_actividad, int id_responsable) {
        String sql = "INSERT INTO responsables(id_actividad, id_responsable) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_actividad);
            stmt.setInt(2, id_responsable);

            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Guarda un participante para una actividad en la base de datos.
     * 
     * @param id_actividad    El ID de la actividad.
     * @param id_participante El ID del participante.
     */
    public void guardarParticipante(int id_actividad, int id_participante) {
        String sql = "INSERT INTO participantes(id_actividad, id_participante) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_actividad);
            stmt.setInt(2, id_participante);

            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Guarda un medio de transporte para una actividad en la base de datos.
     * 
     * @param id_actividad  El ID de la actividad.
     * @param id_transporte El ID del medio de transporte.
     */
    public void guardarTransporte(int id_actividad, int id_transporte) {
        String sql = "INSERT INTO transporte(id_actividad, id_transporte) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_actividad);
            stmt.setInt(2, id_transporte);

            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Guarda un curso para una actividad en la base de datos.
     * 
     * @param id_actividad El ID de la actividad.
     * @param id_curso     El ID del curso.
     */
    public void guardarCurso(int id_actividad, int id_curso) {
        String sql = "INSERT INTO cursos_participa(id_actividad, id_curso) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_actividad);
            stmt.setInt(2, id_curso);

            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Guarda un grupo para una actividad en la base de datos.
     * 
     * @param id_actividad El ID de la actividad.
     * @param id_grupo     El ID del grupo.
     */
    public void guardarGrupo(int id_actividad, int id_grupo) {
        String sql = "INSERT INTO grupos_participa(id_actividad, id_grupo) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_actividad);
            stmt.setInt(2, id_grupo);

            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Busca los responsables de una solicitud en la base de datos.
     * 
     * @param id_solicitud El ID de la solicitud.
     * @return Una lista de objetos Profesor que son responsables de la solicitud.
     */
    public List<Profesor> buscaResponsable(int id_solicitud) {
        String sql = "SELECT id_responsable FROM responsables WHERE id_actividad=?";
        ProfesorDAO profesorSQL = new ProfesorDAO();
        List<Profesor> participantes = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id_solicitud);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (!participantes.add(profesorSQL.buscar(rs.getInt("id_responsable")))) {
                        throw new Exception("error no se ha insertado el objeto en la colección");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return participantes;
    }

    /**
     * Busca los medios de transporte de una solicitud en la base de datos.
     * 
     * @param id_solicitud El ID de la solicitud.
     * @return Una lista de objetos MediosTransporte que son utilizados en la
     *         solicitud.
     */
    public List<MediosTransporte> buscaTransporte(int id_solicitud) {
        String sql = "SELECT id_transporte FROM transporte WHERE id_actividad=?";
        TransporteDAO transporteSQL = new TransporteDAO();
        List<MediosTransporte> transportes = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id_solicitud);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (!transportes.add(transporteSQL.buscar(rs.getInt("id_transporte")))) {
                        throw new Exception("error no se ha insertado el objeto en la colección");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return transportes;
    }

    /**
     * Este método busca los cursos asociados a una solicitud específica.
     * 
     * @param id_solicitud El identificador de la solicitud.
     * @return Una lista de cursos asociados a la solicitud.
     */
    public List<Curso> buscaCursos(int id_solicitud) {
        String sql = "SELECT id_curso FROM cursos_participa WHERE id_actividad=?";
        CursosDAO cursoSQL = new CursosDAO();
        List<Curso> cursos = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id_solicitud);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (!cursos.add(cursoSQL.buscar(rs.getInt("id_curso")))) {
                        throw new Exception("error no se ha insertado el objeto en la colección");
                    }
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
     * Este método busca los grupos asociados a una solicitud específica.
     * 
     * @param id_solicitud El identificador de la solicitud.
     * @return Una lista de grupos asociados a la solicitud.
     */
    public List<Grupo> buscaGrupos(int id_solicitud) {
        String sql = "SELECT id_grupo FROM grupos_participa WHERE id_actividad=?";
        GruposDAO grupoSQL = new GruposDAO();
        List<Grupo> grupos = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id_solicitud);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (!grupos.add(grupoSQL.buscar(rs.getInt("id_grupo")))) {
                        throw new Exception("error no se ha insertado el objeto en la colección");
                    }
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
     * Este método busca los participantes asociados a una solicitud específica.
     * 
     * @param id_solicitud El identificador de la solicitud.
     * @return Una lista de profesores participantes en la solicitud.
     */
    public List<Profesor> buscaParticipantes(int id_solicitud) {
        String sql = "SELECT id_participante FROM participantes WHERE id_actividad=?";
        ProfesorDAO profesorSQL = new ProfesorDAO();
        List<Profesor> participantes = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id_solicitud);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (!participantes.add(profesorSQL.buscar(rs.getInt("id_participante")))) {
                        throw new Exception("error no se ha insertado el objeto en la colección");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return participantes;
    }

    /**
     * Este método borra los participantes asociados a una solicitud específica.
     * 
     * @param id_solicitud El identificador de la solicitud.
     */
    public void borraParticipantes(int id_solicitud) {
        String sql = "DELETE FROM participantes WHERE id_actividad=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_solicitud);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    /**
     * Este método borra los responsables asociados a una solicitud específica.
     * 
     * @param id_solicitud El identificador de la solicitud.
     */
    public void borraResponsables(int id_solicitud) {
        String sql = "DELETE FROM responsables WHERE id_actividad=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_solicitud);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    /**
     * Este método borra los transportes asociados a una solicitud específica.
     * 
     * @param id_solicitud El identificador de la solicitud.
     */
    public void borraTransportes(int id_solicitud) {
        String sql = "DELETE FROM transporte WHERE id_actividad=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_solicitud);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    /**
     * Este método borra los cursos asociados a una solicitud específica.
     * 
     * @param id_solicitud El identificador de la solicitud.
     */
    public void borraCursos(int id_solicitud) {
        String sql = "DELETE FROM cursos_participa WHERE id_actividad=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_solicitud);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    /**
     * Este método borra los grupos asociados a una solicitud específica.
     * 
     * @param id_solicitud El identificador de la solicitud.
     */
    public void borraGrupos(int id_solicitud) {
        String sql = "DELETE FROM grupos_participa WHERE id_actividad=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_solicitud);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    /**
     * Crea un objeto Solicitud a partir de un ResultSet.
     * 
     * @param rs El ResultSet que contiene los datos de la solicitud.
     * @return El objeto Profesor creado.
     * @throws SQLException Si ocurre un error al acceder a los datos del ResultSet.
     */
    private Solicitud creaSolicitud(final ResultSet rs) throws SQLException {
        return new Solicitud(rs.getInt("id_actividad"), profesorSQL.buscar(rs.getInt("solicitante")),
                rs.getString("titulo"),
                rs.getString("tipo") == "extraescolar" ? TipoActividad.Extraescolar : TipoActividad.Complementaria,
                rs.getDate("fini").toLocalDate(), rs.getDate("ffin").toLocalDate(), rs.getTime("hini").toLocalTime(),
                rs.getTime("hfin").toLocalTime(), rs.getBoolean("prevista"), rs.getInt("nalumnos_ausentes"), 
                rs.getBoolean("transporte_req"), rs.getString("coment_transporte"), rs.getBoolean("alojamiento_req"),
                rs.getString("coment_alojamiento"),
                rs.getString("comentarios"),
                rs.getString("estado").equalsIgnoreCase("Solicitada") ? EstadoActividad.Solicitada
                        : rs.getString("estado").equalsIgnoreCase("Denegada") ? EstadoActividad.Denegada
                                : rs.getString("estado").equalsIgnoreCase("Aprobada") ? EstadoActividad.Aprobada
                                        : EstadoActividad.Realizada,
                rs.getString("coment_estado"), buscaTransporte(rs.getInt("id_actividad")),
                buscaParticipantes(rs.getInt("id_actividad")), buscaResponsable(rs.getInt("id_actividad")),
                buscaCursos(rs.getInt("id_actividad")), buscaGrupos(rs.getInt("id_actividad")));

    }

}
