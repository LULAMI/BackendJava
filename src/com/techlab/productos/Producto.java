package com.techlab.productos;

import com.techlab.productos.ProductoAlimenticio;
import com.techlab.productos.ProductoElectronico;

/**
 * Clase abstracta base que representa la estructura común de un Producto en el sistema.
 * Sirve como superclase para tipos específicos como ProductoAlimenticio y ProductoElectronico.
 */
public abstract class Producto {
    // Generador automático e incremental de IDs únicos
    private static int contador = 1;

    private final int id;
    private String nombre;
    private double precio;
    private int stock;

    /**
     * Constructor principal de la clase Producto.
     * Valida los datos recibidos y asigna un ID único.
     */
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

    // ---------- Métodos Auxiliares de Validación ----------

    private static void validarPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
    }

    private static void validarStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
    }

    // ---------- Getters ----------

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // ---------- Setters con Validación ----------

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

    /**
     * Devuelve la categoría o tipo de producto (ej: "Electrónico", "Alimenticio").
     * Debe ser implementado por cada subclase concreta.
     */
    public abstract String getTipoProducto();

    /**
     * Devuelve la descripción de los atributos específicos propios de cada subclase.
     * Debe ser implementado por cada subclase concreta.
     */
    public abstract String getDetalleEspecifico();

    /**
     * Formatea la representación en texto del producto en consola,
     * invocando de forma polimórfica los métodos de cada subclase.
     */
    @Override
    public String toString() {
        return String.format("ID: %-3d | %-25s | $%10.2f | Stock: %d | %s | %s",
                id, nombre, precio, stock, getTipoProducto(), getDetalleEspecifico());
    }
}