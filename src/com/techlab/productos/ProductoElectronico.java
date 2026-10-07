package com.techlab.productos;

public class ProductoElectronico extends Producto {
    private int garantiaMeses;

    public ProductoElectronico(String nombre, double precio, int stock, int garantiaMeses) {
        super(nombre, precio, stock);
        validarGarantia(garantiaMeses);
        this.garantiaMeses = garantiaMeses;
    }

    private static void validarGarantia(int garantiaMeses) {
        if (garantiaMeses < 0) {
            throw new IllegalArgumentException("La garantía no puede ser negativa.");
        }
    }

    public int getGarantiaMeses() { return garantiaMeses; }

    public void setGarantiaMeses(int garantiaMeses) {
        validarGarantia(garantiaMeses);
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipoProducto() {
        return "Electrónico";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Garantía: " + garantiaMeses + " meses";
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo electrónico]";
    }
}