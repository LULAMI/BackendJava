package com.techlab;

import com.techlab.excepciones.ProductoNoEncontradoException;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.Pedido;
import com.techlab.pedidos.PedidoService;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ProductoService productoService = new ProductoService();
    private static final PedidoService pedidoService = new PedidoService();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elija una opción: ");
            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> buscarActualizarProducto();
                case 4 -> eliminarProducto();
                case 5 -> crearPedido();
                case 6 -> listarPedidos();
                case 7 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("\n=================================== SISTEMA DE GESTIÓN - TECHLAB ==================================\n");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar/Actualizar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Crear un pedido");
        System.out.println("6) Listar pedidos");
        System.out.println("7) Salir\n");
    }

    // ---------- Opciones ----------

    private static void agregarProducto() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        double precio = leerDouble("Precio: ");
        int stock = leerEntero("Stock: ");
        try {
            Producto p = productoService.agregar(nombre, precio, stock);
            System.out.println("Producto agregado: " + p);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarProductos() {
        List<Producto> lista = productoService.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (Producto p : lista) {
            System.out.println(p);
        }
    }

    private static void buscarActualizarProducto() {
        System.out.println("Buscar por: 1) ID  2) Nombre");
        int modo = leerEntero("Opción: ");
        Producto encontrado = null;

        try {
            if (modo == 1) {
                encontrado = productoService.buscarPorId(leerEntero("ID: "));
            } else if (modo == 2) {
                System.out.print("Nombre (o parte): ");
                List<Producto> res = productoService.buscarPorNombre(sc.nextLine());
                if (res.isEmpty()) {
                    System.out.println("No se encontraron productos.");
                    return;
                }
                for (Producto p : res) System.out.println(p);
                if (res.size() == 1) {
                    encontrado = res.get(0);
                } else {
                    encontrado = productoService.buscarPorId(leerEntero("Elegí el ID a trabajar: "));
                }
            } else {
                System.out.println("Opción inválida.");
                return;
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("Producto encontrado: " + encontrado);
        System.out.print("¿Querés actualizarlo? (s/n): ");
        if (!sc.nextLine().trim().equalsIgnoreCase("s")) return;

        try {
            System.out.print("Nuevo precio (Enter para dejar igual): ");
            String precioTxt = sc.nextLine().trim();
            if (!precioTxt.isEmpty()) {
                encontrado.setPrecio(Double.parseDouble(precioTxt.replace(',', '.')));
            }
            System.out.print("Nuevo stock (Enter para dejar igual): ");
            String stockTxt = sc.nextLine().trim();
            if (!stockTxt.isEmpty()) {
                encontrado.setStock(Integer.parseInt(stockTxt));
            }
            System.out.println("Producto actualizado: " + encontrado);
        } catch (NumberFormatException e) {
            System.out.println("Error: ingresaste un valor no numérico.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarProducto() {
        int id = leerEntero("ID del producto a eliminar: ");
        try {
            Producto p = productoService.buscarPorId(id);
            System.out.println(p);
            System.out.print("¿Confirmás la eliminación? (s/n): ");
            if (sc.nextLine().trim().equalsIgnoreCase("s")) {
                productoService.eliminar(id);
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crearPedido() {
        if (productoService.listar().isEmpty()) {
            System.out.println("No hay productos para armar un pedido.");
            return;
        }
        listarProductos();
        Pedido pedido = new Pedido();
        int n = leerEntero("¿Cuántos productos distintos querés agregar? ");

        for (int i = 1; i <= n; i++) {
            System.out.println("--- Producto " + i + " de " + n + " ---");
            try {
                Producto p = productoService.buscarPorId(leerEntero("ID del producto: "));
                int cantidad = leerEntero("Cantidad: ");
                pedido.agregarLinea(p, cantidad);
            } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                System.out.println("Error: " + e.getMessage() + " (se omitió este producto)");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + " (se omitió este producto)");
            }
        }

        if (pedido.estaVacio()) {
            System.out.println("El pedido quedó vacío, se cancela.");
            return;
        }
        System.out.println("\n" + pedido);
        System.out.print("¿Confirmar pedido? (s/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("s")) {
            pedidoService.confirmar(pedido);
            System.out.println("Pedido confirmado. Stock actualizado.");
        } else {
            System.out.println("Pedido cancelado.");
        }
    }

    private static void listarPedidos() {
        List<Pedido> lista = pedidoService.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay pedidos realizados.");
            return;
        }
        for (Pedido p : lista) {
            System.out.println(p + "\n");
        }
    }

    // ---------- Lectura segura ----------

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número válido (ej: 1500.50).");
            }
        }
    }
}