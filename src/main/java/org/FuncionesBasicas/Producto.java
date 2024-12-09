package org.FuncionesBasicas;

public class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private float stock;

    public Producto(float stock, double precio, String categoria, String nombre) {
        this.stock = stock;
        this.precio = precio;
        this.categoria = categoria;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public float getStock() {
        return stock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }
}
