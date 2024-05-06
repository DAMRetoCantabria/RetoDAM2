/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.proyecto.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import proyecto.clases.Cursos;
import proyecto.clases.Etapas;

/**
 *
 * @author samus
 */
public class CursosDAO implements Repositorio<Cursos> {

    private Connection getConnection() {
        return conexionBD.getInstance().getConn();
    }

    @Override
    public List<Cursos> listar() {
        List<Cursos> cursos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM cursos");) {
            while (rs.next()) {
                Cursos curso = crearCurso(rs);
                if (!cursos.add(curso)) {
                    throw new Exception("No se puede mostrar");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cursos;
    }

    @Override
    public Cursos buscar(int id) {
        Cursos curso = null;
        String sql = "SELECT * FROM cursos WHERE id=?";
        try (PreparedStatement smtm = getConnection().prepareStatement(sql);) {
            smtm.setInt(1, id);
            try (ResultSet rs = smtm.executeQuery();) {
                if (rs.next()) {

                    curso = crearCurso(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return curso;
    }

    @Override
    public Cursos buscar(String codigo) {
        Cursos curso = null;
        String sql = "SELECT * FROM cursos WHERE codigo=?";
        try (PreparedStatement smtm = getConnection().prepareStatement(sql);) {
            smtm.setString(1, codigo);
            try (ResultSet rs = smtm.executeQuery();) {
                if (rs.next()) {

                    curso = crearCurso(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return curso;
    }

    @Override
    public void guardar(Cursos t) {
        String sql = "INSERT INTO cursos (codigo,descripcion,etapas,estado) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, t.getCodigo());
            stmt.setString(2, t.getDescripcion());
            stmt.setString(3, String.valueOf(t.getEtapas()));
            stmt.setBoolean(4, true);
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se ha guardado");
            } else {
                System.out.println("Agregado");
            }

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void borrar(Cursos t) {
         String sql = "DELETE FROM cursos WHERE id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, t.getId());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se pudo elimianr");
            } else {
                System.out.println("Eliminado con exito.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Cursos crearCurso(final ResultSet rs) throws SQLException {
        Etapas etapa = Etapas.valueOf(rs.getString("etapa"));
        return new Cursos(rs.getInt("id"), rs.getString("codigo"), rs.getString("descripcion"), etapa, rs.getBoolean("estado"));
    }

}
