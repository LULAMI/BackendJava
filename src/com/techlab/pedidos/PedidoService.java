package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private final ArrayList<Pedido> pedidos = new ArrayList<>();

    // Confirma el pedido: descuenta stock y lo guarda
    public void confirmar(Pedido pedido) {
        for (LineaPedido l : pedido.getLineas()) {
            l.getProducto().setStock(l.getProducto().getStock() - l.getCantidad());
        }
        pedidos.add(pedido);
    }

    public List<Pedido> listar() {
        return pedidos;
    }
}