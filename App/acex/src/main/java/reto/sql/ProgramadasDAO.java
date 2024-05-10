package reto.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import reto.objects.*;

public class ProgramadasDAO implements RepositorioSolicitud<Programadas> {

    /**
     * Obtiene la conexión a la base de datos.
     * 
     * @return La conexión a la base de datos.
     */
    private Connection getConnection() {
        return MySQL.getInstance().getConnection();
    }

    @Override
    public List<Programadas> listar() {
        List<Programadas> programadas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM programadas");) {
            while (rs.next()) {
                Programadas programada = creaProgramadas(rs);
                if (!programadas.add(programada)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return programadas;
    }

    @Override
    public int guardar(Programadas programada) {
        String sql = null;
        if (programada.getId_programada() > 0) {
            sql = "UPDATE programadas SET id_actividad=?, solicitante=?, titulo=?, tipo=?, fini=?, ffin=?, hini=?, hfin=?, prevista=?, transporte_req=?, coment_transporte=?, alojamiento_req=?, coment_alojamiento=?, comentarios=?, estado=?, coment_estado=?, empresa_transporte=?, precio_transporte=? WHERE id_programada=?";
        } else {
            sql = "INSERT INTO programadas(id_actividad, solicitante, titulo, tipo, fini, ffin, hini, hfin, prevista, transporte_req, coment_transporte, alojamiento_req, coment_alojamiento, comentarios, estado, coment_estado, empresa_transporte, precio_transporte) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            if (programada.getId_programada() > 0) {
                stmt.setInt(19, programada.getId_programada());
            }
            stmt.setInt(1, programada.get_solicitada().getId_solicitud());
            stmt.setInt(2, programada.get_solicitante().getId());
            stmt.setString(3, programada.getTitulo());
            stmt.setString(4, programada.getTipo().toString());
            stmt.setDate(5, java.sql.Date.valueOf(programada.getFini()));
            stmt.setDate(6, java.sql.Date.valueOf(programada.getFfin()));
            stmt.setTime(7, java.sql.Time.valueOf(programada.getHini()));
            stmt.setTime(8, java.sql.Time.valueOf(programada.getHfin()));
            stmt.setString(9, programada.isPrevisto() ? "1" : "0");
            stmt.setString(10, programada.isTransp_requerido() ? "1" : "0");
            stmt.setString(11, programada.getTransp_comentario());
            stmt.setString(12, programada.isAloj_requerido() ? "1" : "0");
            stmt.setString(13, programada.getAloj_comentario());
            stmt.setString(14, programada.getComentario());
            stmt.setString(15, programada.getEstado().toString());
            stmt.setString(16, programada.getEstado_comentario());
            stmt.setString(17, programada.getEmpresa_transporte());
            stmt.setDouble(18, programada.getPrecio_transporte());

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

    @Override
    public Programadas buscar(int id) {
        String sql = "SELECT * FROM programadas WHERE id_programada=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return creaProgramadas(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return null;
    }

    public void guardarResponsable(int id_programada, int id_responsable) {
        String sql = "INSERT INTO responsables_programada(id_programada, id_responsable) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_programada);
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

    public void guardarParticipante(int id_programada, int id_participante) {
        String sql = "INSERT INTO participantes_programada(id_programada, id_participante) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_programada);
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

    public void guardarTransporte(int id_programada, int id_transporte) {
        String sql = "INSERT INTO transporte_programado(id_programada, id_transporte) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_programada);
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

    public void guardarCurso(int id_programada, int id_curso) {
        String sql = "INSERT INTO cursos_programadas(id_programada, id_curso) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_programada);
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

    public void guardarGrupo(int id_programada, int id_grupo) {
        String sql = "INSERT INTO grupos_programadas(id_programada, id_grupo) VALUES (?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_programada);
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

    public void borraParticipantes(int id_programada) {
        String sql = "DELETE FROM participantes_programada WHERE id_programada=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_programada);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public void borraResponsables(int id_programada) {
        String sql = "DELETE FROM responsables_programada WHERE id_programada=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_programada);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public void borraTransportes(int id_programada) {
        String sql = "DELETE FROM transporte_programado WHERE id_programada=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_programada);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public void borraCursos(int id_programada) {
        String sql = "DELETE FROM cursos_programadas WHERE id_programada=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_programada);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public void borraGrupos(int id_programada) {
        String sql = "DELETE FROM grupos_programadas WHERE id_programada=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id_programada);
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
    private Programadas creaProgramadas(final ResultSet rs) throws SQLException {
        SolicitudDAO solicitudSQL = new SolicitudDAO();
        ProfesorDAO profesorSQL = new ProfesorDAO();
        return new Programadas(rs.getInt("id_programada"), solicitudSQL.buscar(rs.getInt("id_actividad")),
                profesorSQL.buscar(rs.getInt("solicitante")),
                rs.getString("titulo"),
                rs.getString("tipo") == "extraescolar" ? TipoActividad.Extraescolar : TipoActividad.Complementaria,
                rs.getDate("fini").toLocalDate(), rs.getDate("ffin").toLocalDate(), rs.getTime("hini").toLocalTime(),
                rs.getTime("hfin").toLocalTime(), rs.getBoolean("prevista"),
                rs.getBoolean("transporte_req"), rs.getString("coment_transporte"), rs.getBoolean("alojamiento_req"),
                rs.getString("coment_alojamiento"),
                rs.getString("comentarios"),
                rs.getString("estado").equalsIgnoreCase("Aprobada") ? EstadoActividad.Aprobada
                        : EstadoActividad.Realizada,
                rs.getString("coment_estado"), rs.getString("empresa_transporte"), rs.getDouble("precio_transporte"),
                buscaTransporte(rs.getInt("id_programada")), buscaResponsable(rs.getInt("id_programada")),
                buscaParticipantes(rs.getInt("id_programada")), buscaCursos(rs.getInt("id_programada")),
                buscaGrupos(rs.getInt("id_programada")));
    }

}
