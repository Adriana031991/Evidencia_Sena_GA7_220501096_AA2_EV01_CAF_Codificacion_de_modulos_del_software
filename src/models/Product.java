package models;

public class Product {
    private int idProducto;
    private String nombre;
    private double precio;
    private int inventario;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public Product(int idProducto, String nombre, double precio, int inventario) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }

    public Product(String nombre, double precio, int inventario) {
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }



    @Override
    public String toString() {
        return
                "Id = " + idProducto + '\n'+
                "Nombre = " + nombre + '\n' +
                "Precio = " + precio + '\n' +
                "Inventario = " + inventario + '\n';
    }
}
