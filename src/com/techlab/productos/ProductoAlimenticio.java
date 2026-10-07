package com.techlab.productos;

public class ProductoAlimenticio extends Producto {
    private int diasParaVencimiento;

    public ProductoAlimenticio(String nombre, double precio, int stock, int diasParaVencimiento) {
        super(nombre, precio, stock);
        validarDias(diasParaVencimiento);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    private static void validarDias(int dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Los días para el vencimiento no pueden ser negativos.");
        }
    }

    public int getDiasParaVencimiento() { return diasParaVencimiento; }

    public void setDiasParaVencimiento(int diasParaVencimiento) {
        validarDias(diasParaVencimiento);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    @Override
    public String getTipoProducto() {
        return "Alimenticio";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Vence en " + diasParaVencimiento + " días";
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo alimenticio]";
    }
}