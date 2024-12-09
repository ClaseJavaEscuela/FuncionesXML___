package org.FuncionesBasicas;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FuncionesXML funcion = new FuncionesXML("inventario.xml");
        funcion.ingresarProducto(new Producto(100, 300, "ropa", "pantalon"));
        funcion.ingresarProducto(new Producto(100, 300, "ropa", "pantalon2"));
        funcion.ingresarProducto(new Producto(100, 300, "ropa", "pantalon3"));
        funcion.mostrarInventario();
        System.out.println("agregamos un nuevo producto");
        funcion.pruebaIngresarProducto(new Producto(100, 300, "ropa", "pantalon4"));
        System.out.println("\n");
        funcion.mostrarInventario();
    }
}