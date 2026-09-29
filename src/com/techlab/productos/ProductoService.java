package com.techlab.productos;

import com.techlab.excepciones.ProductoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private final ArrayList<Producto> productos = new ArrayList<>();

    public Producto agregar(String nombre, double precio, int stock) {
        Producto p = new Producto(nombre, precio, stock);
        productos.add(p);
        return p;
    }

    public List<Producto> listar() {
        return productos;
    }

    public Producto buscarPorId(int id) throws ProductoNoEncontradoException {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        throw new ProductoNoEncontradoException("No existe un producto con ID " + id + ".");
    }

    public List<Producto> buscarPorNombre(String texto) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase().trim())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public void eliminar(int id) throws ProductoNoEncontradoException {
        Producto p = buscarPorId(id);
        productos.remove(p);
    }
}