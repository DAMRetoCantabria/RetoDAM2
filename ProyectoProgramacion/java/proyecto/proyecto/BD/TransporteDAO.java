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
import proyecto.clases.Transporte;

/**
 *
 * @author samus
 */
public class TransporteDAO implements Repositorio<Transporte> {

    private Connection getConnection() {
        return conexionBD.getInstance().getConn();
    }

    @Override
    public List<Transporte> listar() {
        List<Transporte> transpor = new LinkedList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM transportes")) {
            while (rs.next()) {
                Transporte t = crearTransporte(rs);
                if (!transpor.add(t)) {
                    throw new Exception("No se pueden mostrar los transportes.");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return transpor;
    }

    @Override
    public Transporte buscar(Transporte t) {
        Transporte transpor = null;
        String sql = "SELECT * FROM transportes WHERE id=?";
        try (PreparedStatement smtm = getConnection().prepareStatement(sql);) {
            smtm.setInt(1, t.getId());
            try (ResultSet rs = smtm.executeQuery();) {
                if (rs.next()) {

                    transpor = crearTransporte(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return transpor;
    }

    @Override
    public Transporte buscar(String nombre) {
        Transporte transpor = null;
        String sql = "SELECT * FROM transportes WHERE nombre=?";
        try (PreparedStatement smtm = getConnection().prepareStatement(sql);) {
            smtm.setString(1, nombre);
            try (ResultSet rs = smtm.executeQuery();) {
                if (rs.next()) {

                    transpor = crearTransporte(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return transpor;
    }

    @Override
    public void guardar(Transporte t) {
        String sql = "INSERT INTO transportes (nombre) VALUES(?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, t.getNombre());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se pudo agregar el Transporte.");
            } else {
                System.out.println("Transporte agregado.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void borrar(Transporte t) {
        String sql = "DELETE FROM transportes WHERE id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, t.getId());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se pudo eliminar el Transporte.");
            } else {
                System.out.println("Transporte eliminado.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Transporte crearTransporte(final ResultSet rs) throws SQLException {
        return new Transporte(rs.getInt("id"), rs.getString("nombre"));
    }
}
