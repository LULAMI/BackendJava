package com.techlab.productos;

public class Producto {
    private static int contador = 1;

    private final int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        validarPrecio(precio);
        validarStock(stock);
        this.id = contador++;
        this.nombre = nombre.trim();
        this.precio = precio;
        this.stock = stock;
    }

    private static void validarPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
    }

    private static void validarStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public void setPrecio(double precio) {
        validarPrecio(precio);
        this.precio = precio;
    }

    public void setStock(int stock) {
        validarStock(stock);
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("ID: %-3d | %-25s | $%10.2f | Stock: %d", id, nombre, precio, stock);
    }
}