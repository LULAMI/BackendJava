package com.techlab.productos;

// Clase abstracta base para la jerarquía de productos
public abstract class Producto {
    private static int contador = 1; // Generador automático de IDs

    private final int id;
    private String nombre;
    private double precio;
    private int stock;

    // Constructor principal
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

    // Métodos de validación
    private static void validarPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
    }

    private static void validarStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // Setters con validación
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

    // ---------- Métodos Abstractos (Polimorfismo) ----------

    // Devuelve la categoría/tipo de producto (ej: "Electrónico", "Alimenticio")
    public abstract String getTipoProducto();

    // Devuelve los detalles propios de cada subclase (ej: garantía o vencimiento)
    public abstract String getDetalleEspecifico();

    // Reescritura del método toString aprovechando el polimorfismo
    @Override
    public String toString() {
        return String.format("ID: %-3d | %-25s | $%10.2f | Stock: %d | %s | %s",
                id, nombre, precio, stock, getTipoProducto(), getDetalleEspecifico());
    }
}