package reto.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import reto.objects.MediosTransporte;

/**
 * Esta clase implementa la interfaz Repositorio y se encarga de realizar
 * operaciones CRUD en la tabla "mediostransporte" de la base de datos.
 */
public class TransporteDAO implements Repositorio<MediosTransporte> {

    /**
     * Obtiene la conexión a la base de datos.
     * 
     * @return La conexión a la base de datos.
     */
    private Connection getConnection() {
        return MySQL.getInstance().getConnection();
    }

    /**
     * Obtiene una lista de todos los objetos MediosTransporte en la base de datos.
     * 
     * @return Una lista de objetos MediosTransporte.
     */
    public List<MediosTransporte> listar() {
        List<MediosTransporte> mediosTransporteList = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM mediostransporte");) {
            while (rs.next()) {
                MediosTransporte mediosTransporte = creaMediosTransporte(rs);
                if (!mediosTransporteList.add(mediosTransporte)) {
                    throw new Exception("error no se ha insertado el objeto en la colección");
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return mediosTransporteList;
    }

    /**
     * Crea un objeto MediosTransporte a partir de un ResultSet.
     * 
     * @param rs El ResultSet que contiene los datos del objeto MediosTransporte.
     * @return El objeto MediosTransporte creado.
     * @throws SQLException Si ocurre un error al acceder a los datos del ResultSet.
     */
    private MediosTransporte creaMediosTransporte(final ResultSet rs) throws SQLException {
        return new MediosTransporte(rs.getInt("id"), rs.getString("nombre"));
    }

    @Override
    public MediosTransporte buscar(int id) {
        MediosTransporte transporte = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM mediostransporte WHERE id = ?");) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    transporte = creaTransporte(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return transporte;
    }

    @Override
    public MediosTransporte buscar(String codigo) {
        MediosTransporte transporte = null;
        try (PreparedStatement statement = getConnection()
                .prepareStatement("SELECT * FROM mediostransporte WHERE nombre = ?");) {
            statement.setString(1, codigo);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    transporte = creaTransporte(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return transporte;
    }

    @Override
    public boolean guardar(MediosTransporte objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public boolean borrar(MediosTransporte objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrar'");
    }

    private MediosTransporte creaTransporte(final ResultSet rs) throws SQLException {
        return new MediosTransporte(rs.getInt("id"), rs.getString("nombre"));
    }

}
