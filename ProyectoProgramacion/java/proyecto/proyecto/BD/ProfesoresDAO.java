/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.proyecto.BD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import proyecto.clases.Niveles;
import proyecto.clases.Profesores;

/**
 *
 * @author samus
 */
public class ProfesoresDAO implements Repositorio<Profesores>{
    
    private Connection getConnetion(){
        return conexionBD.getInstance().getConn();
    } 


    @Override
    public List<Profesores> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Profesores buscar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Profesores buscar(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar(Profesores t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(Profesores t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Profesores crearProfesor(final ResultSet rs) throws SQLException{
        Niveles nivel = Niveles.valueOf(rs.getString("tipo"));
        return new Profesores(rs.getInt("id"), rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("correo"),rs.getString("contrasenia"),nivel,rs.getBoolean("activo"),rs.getString("foto"),rs.getInt("departamento"));
    }
    
}
