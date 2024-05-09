/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto.proyecto.BD;

import java.util.List;

/**
 *
 * @author samus
 */
public interface Repositorio <T>{
    public List<T> listar();
    public T buscar(T t);
    public T buscar(String codigo);
    public void guardar(T t);
    public void borrar(T t);
}
