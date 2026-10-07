package com.techlab.productos;

import com.techlab.excepciones.ProductoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

// Servicio encargado de la gestión de productos en memoria
public class ProductoService {
    // Lista interna para almacenar los productos registrados
    private final List<Producto> productos = new ArrayList<>();

    // Registra una nueva instancia de Producto (Electrónico, Alimenticio, etc.)
    public Producto agregar(Producto producto) {
        productos.add(producto);
        return producto;
    } // <-- Se eliminó la llave extra que cerraba la clase aquí

    // Retorna la lista completa de productos
    public List<Producto> listar() {
        return productos;
    }

    // Busca un producto por su ID único; lanza excepción si no existe
    public Producto buscarPorId(int id) throws ProductoNoEncontradoException {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        throw new ProductoNoEncontradoException("No existe un producto con ID " + id + ".");
    }

    // Busca productos que contengan el texto ingresado en su nombre (ignora mayúsculas y espacios sobrantes)
    public List<Producto> buscarPorNombre(String texto) {
        List<Producto> resultado = new ArrayList<>();
        String filtro = texto.trim().toLowerCase();
        
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(filtro)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // Elimina un producto por su ID
    public void eliminar(int id) throws ProductoNoEncontradoException {
        Producto p = buscarPorId(id);
        productos.remove(p);
    }
}