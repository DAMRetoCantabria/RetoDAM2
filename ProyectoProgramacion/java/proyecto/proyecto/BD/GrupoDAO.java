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
import java.util.LinkedList;
import java.util.List;
import proyecto.clases.Grupos;

/**
 *
 * @author samus
 */
public class GrupoDAO implements Repositorio<Grupos>{
    
    private Connection getConnection(){
        return conexionBD.getInstance().getConn();
    } 

    @Override
    public List<Grupos> listar() {
        List<Grupos> grupo = new LinkedList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM grupos")) {
            while (rs.next()) {
                Grupos g = crearGrupo(rs);
                if (!grupo.add(g)) {
                    throw new Exception("No se pueden mostrar los grupos.");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return grupo;
    }

    @Override
    public Grupos buscar(String codigo) {
        Grupos grupo = null;
        String sql = "SELECT * FROM grupos WHERE codigo=?";
        try (PreparedStatement smtm = getConnection().prepareStatement(sql);) {
            smtm.setString(1, codigo);
            try (ResultSet rs = smtm.executeQuery();) {
                if (rs.next()) {

                    grupo = crearGrupo(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return grupo;
    }

    @Override
    public void guardar(Grupos t) {
        String sql = "INSERT INTO grupos (curso,codigo,numAlumnos,activo) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, t.getCurso());
            stmt.setString(2, t.getCodigo());
            stmt.setInt(3, t.getNumAlumnos());
            stmt.setBoolean(4, t.isActivo());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se pudo agregar el Grupo.");
            } else {
                System.out.println("Grupo agregado.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void borrar(Grupos t) {
        String sql = "DELETE FROM grupos WHERE id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, t.getId());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se pudo eliminar el Grupo.");
            } else {
                System.out.println("Grupo eliminado.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public Grupos buscar(Grupos t) {
        Grupos grupo = null;
        String sql = "SELECT * FROM grupos WHERE id=?";
        try (PreparedStatement smtm = getConnection().prepareStatement(sql);) {
            smtm.setInt(1, t.getId());
            try (ResultSet rs = smtm.executeQuery();) {
                if (rs.next()) {

                    grupo = crearGrupo(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return grupo;
    }
    
    
    public Grupos crearGrupo(final ResultSet rs) throws SQLException{
        return new Grupos(rs.getInt("id"),rs.getInt("curso"),rs.getString("codigo"),rs.getInt("numAlumnos"),rs.getBoolean("activo"));
    }

    
}
