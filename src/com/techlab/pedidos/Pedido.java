package com.techlab.pedidos;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 1;

    private final int id;
    private final ArrayList<LineaPedido> lineas = new ArrayList<>();

    public Pedido() {
        this.id = contador++;
    }

    public void agregarLinea(Producto producto, int cantidad) throws StockInsuficienteException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }
        LineaPedido existente = buscarLinea(producto);
        int yaPedido = (existente == null) ? 0 : existente.getCantidad();

        if (yaPedido + cantidad > producto.getStock()) {
            throw new StockInsuficienteException(
                "Stock insuficiente para '" + producto.getNombre() + "'. Disponible: "
                + producto.getStock() + ", solicitado: " + (yaPedido + cantidad) + ".");
        }
        if (existente != null) {
            existente.sumarCantidad(cantidad);
        } else {
            lineas.add(new LineaPedido(producto, cantidad));
        }
    }

    private LineaPedido buscarLinea(Producto producto) {
        for (LineaPedido l : lineas) {
            if (l.getProducto().getId() == producto.getId()) return l;
        }
        return null;
    }

    public double getTotal() {
        double total = 0;
        for (LineaPedido l : lineas) {
            total += l.getSubtotal();
        }
        return total;
    }

    public int getId() { return id; }
    public List<LineaPedido> getLineas() { return lineas; }
    public boolean estaVacio() { return lineas.isEmpty(); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(id).append("\n");
        for (LineaPedido l : lineas) {
            sb.append(l).append("\n");
        }
        sb.append(String.format("  TOTAL: $%.2f", getTotal()));
        return sb.toString();
    }
}