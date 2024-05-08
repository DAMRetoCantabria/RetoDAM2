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
import proyecto.clases.Departamentos;

/**
 *
 * @author samus
 */
public class DepartamentoDAO implements Repositorio<Departamentos> {

    private Connection getConnection() {
        return conexionBD.getInstance().getConn();
    }

    @Override
    public List<Departamentos> listar() {
        List<Departamentos> dpto = new LinkedList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM departamento")) {
            while (rs.next()) {
                Departamentos d = crearDepartamento(rs);
                if (!dpto.add(d)) {
                    throw new Exception("No se puede mostrar los departamentos.");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return dpto;
    }

    @Override
    public Departamentos buscar(String codigo) {
        Departamentos dpto = null;
        String sql = "SELECT * FROM departamentos WHERE codigo=?";
        try (PreparedStatement smtm = getConnection().prepareStatement(sql);) {
            smtm.setString(1, codigo);
            try (ResultSet rs = smtm.executeQuery();) {
                if (rs.next()) {

                    dpto = crearDepartamento(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return dpto;
    }

    @Override
    public void guardar(Departamentos t) {
        String sql = "INSERT INTO departamento (codigo,nombre,jefe) VALUES(?,?,?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setString(1, t.getCodigo());
            stmt.setString(2, t.getNombre());
            stmt.setString(3, t.getJefe());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se pudo agregar el Departamento.");
            } else {
                System.out.println("Departamento agregado.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void borrar(Departamentos t) {
        String sql = "DELETE FROM departamentos WHERE id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, t.getId());
            int salida = stmt.executeUpdate();
            if (salida != 1) {
                throw new Exception("No se pudo eliminar el Departamento.");
            } else {
                System.out.println("Departamento eliminado.");
            }

        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Departamentos buscar(Departamentos t) {
        Departamentos dpto = null;
        String sql = "SELECT * FROM departamentos WHERE id=?";
        try (PreparedStatement smtm = getConnection().prepareStatement(sql);) {
            smtm.setInt(1, t.getId());
            try (ResultSet rs = smtm.executeQuery();) {
                if (rs.next()) {
                    dpto = crearDepartamento(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());           
        }
        return dpto;
    }

    public Departamentos crearDepartamento(final ResultSet rs) throws SQLException {
        return new Departamentos(rs.getInt("id"), rs.getString("codigo"), rs.getString("nombre"), rs.getString("jefe"));
    }

}
