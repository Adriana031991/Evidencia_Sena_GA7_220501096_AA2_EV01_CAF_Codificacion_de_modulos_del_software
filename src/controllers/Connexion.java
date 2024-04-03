package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String url = "jdbc:sqlserver://DESKTOP-ADRIANA;database=ejercicio_sena_clase_extra;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";


    public Connection ConectarBaseDeDatos() {
        Connection con= null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con= DriverManager.getConnection(url);

            System.out.println("Conexión exitosa a la base de datos " + "ejercicio_sena_clase_extra");
        }
        catch(ClassNotFoundException| SQLException ex ) {
            System.out.println("No se pudo Conectar a la base de datos "  + "ejercicio_sena_clase_extra");
            ex.printStackTrace();
        }

        return con;
    }
}
