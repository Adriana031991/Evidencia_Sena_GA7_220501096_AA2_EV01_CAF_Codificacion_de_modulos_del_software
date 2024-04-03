package controllers;

import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private static final String sqlSelectAll ="SELECT * FROM Producto";
    private static final String sqlInsert ="INSERT INTO Producto (nombre, precio, inventario) VALUES (?,?,?)";
    private static final String sqlDelete ="DELETE FROM Producto WHERE idProducto = ?";
    private static final String sqlUpdate = "UPDATE Producto SET nombre = ?, precio = ?, inventario = ? WHERE idProducto = ?";
    private static final String sqlSelectProducto = "SELECT * FROM Producto WHERE idProducto = ?";

    Connexion conexion = new Connexion();
    Connection con = conexion.ConectarBaseDeDatos();
    Statement ps;
    ResultSet rs;


    public List listar() {
    List<Product> products = new ArrayList<>();

        try{
        //con = conexion.ConectarBaseDeDatos();
        ps = con.createStatement();
        rs = ps.executeQuery(sqlSelectAll);
        while(rs.next())
        {
            // Obtener los datos de cada columna para un producto
            int id = rs.getInt("idProducto");
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            int inventario = rs.getInt("inventario");


            // Crear un objeto Product con los datos obtenidos
            Product product = new Product(id, nombre, precio, inventario);

            // Agregar el producto a la lista
            products.add(product);

        }

        } catch (SQLException e) {
            System.out.println("Error al Listar" + e);
        }


        return products;

    }


    public boolean ingresar(Product producto){
        try {

            PreparedStatement pd= con.prepareStatement(sqlInsert);
            pd.setString(1, producto.getNombre());
            pd.setDouble(2, producto.getPrecio());
            pd.setInt(3, producto.getInventario());

            int filasInsertadas = pd.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al ingresar producto" + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idProducto) {
        try {
             PreparedStatement pd = con.prepareStatement(sqlDelete);

            // Establecer el parámetro ID en la sentencia SQL
            pd.setInt(1, idProducto);

            // Ejecutar la sentencia SQL DELETE
            int filasEliminadas = pd.executeUpdate();

            // Verificar si se eliminó correctamente
            return filasEliminadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(int idProducto, String nuevoNombre, double nuevoPrecio, int nuevoInventario ) {
        try  {
            PreparedStatement pd = con.prepareStatement(sqlUpdate);

            // Establecer los parámetros en la sentencia SQL
            pd.setInt(4, idProducto);
            pd.setString(1, nuevoNombre);
            pd.setDouble(2, nuevoPrecio);
            pd.setInt(3, nuevoInventario);

            // Ejecutar la sentencia SQL UPDATE
            int filasActualizadas = pd.executeUpdate();

            // Verificar si se actualizó correctamente
            return filasActualizadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
            return false;
        }
    }

}

