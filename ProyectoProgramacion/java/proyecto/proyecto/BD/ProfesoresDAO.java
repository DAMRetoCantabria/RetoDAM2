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
import proyecto.clases.Niveles;
import proyecto.clases.Profesores;

/**
 *
 * @author samus
 */
public class ProfesoresDAO implements Repositorio<Profesores>{
    
    private Connection getConnection(){
        return conexionBD.getInstance().getConn();
    } 


    @Override
    public List<Profesores> listar() {
        List<Profesores> prof = new LinkedList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM profesores")) {
            while (rs.next()) {
                Profesores p = crearProfesor(rs);
                if (!prof.add(p)) {
                    throw new Exception("No se pueden mostrar los profesores.");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return prof;
    }

    @Override
    public Profesores buscar(Profesores t) {
        Profesores prof = null;
        String sql = "SELECT * FROM profesores WHERE nombre=? AND apellidos=?";
        try (PreparedStatement smtm = getConnection().prepareStatement(sql);) {
            smtm.setString(1, t.getNombre());
            smtm.setString(2, t.getApellidos());
            try (ResultSet rs = smtm.executeQuery();) {
                if (rs.next()) {

                    prof = crearProfesor(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return prof;
    }

    @Override
    public Profesores buscar(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(Profesores t) {
        String sql = "INSERT INTO profesores (dni,nombre,apellidos,correo,contrasenia,nivel,activo,foto,departamento) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, t.getDni());
            stmt.setString(2, t.getNombre());
            stmt.setString(3, t.getApellidos());
            stmt.setString(4, t.getCorreo());
            stmt.setString(5, t.getContrasenia());
            stmt.setString(6, String.valueOf(t.getNivel()));
            stmt.setBoolean(7, t.isActivo());
            stmt.setString(8, t.getFoto());
            stmt.setInt(9, t.getDepartamento());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se pudo agregar el Profesor.");
            } else {
                System.out.println("Profesor agregado.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void borrar(Profesores t) {
        String sql = "DELETE FROM profesores WHERE id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, t.getId());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se pudo eliminar el Profesor.");
            } else {
                System.out.println("Profesor eliminado.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Profesores crearProfesor(final ResultSet rs) throws SQLException{
        Niveles nivel = Niveles.valueOf(rs.getString("tipo"));
        return new Profesores(rs.getInt("id"), rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("correo"),rs.getString("contrasenia"),nivel,rs.getBoolean("activo"),rs.getString("foto"),rs.getInt("departamento"));
    }
    
}
