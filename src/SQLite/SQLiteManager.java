/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import SQLite.Usuario;
import java.util.ArrayList;

/**
 *
 * @author ORLANDO
 */
public class SQLiteManager {

    private Connection conexionDB;
    //Nombre de la base de datos ubicada en el mismo paquete que esta clase Advertencia: si el nombre esta mal escrito
    //entonces DriverManager en el constructor creaba una base de datos con el nombre erroneo proporcionado
    private final String direccionDB = "Usuarios.db";

    public SQLiteManager() {
        //ESTABLECE LA CONEXION CON LA BASE DE DATOS LOCAL
        try {
            conexionDB = DriverManager.getConnection("jdbc:sqlite:" + direccionDB);
            System.out.println("Conexion EXITOSA");
        } catch (SQLException e) {
            System.out.println("Error al intentar conectar con " + direccionDB + ": mensaje de error es " + e.getMessage());
        }
    }

    public ArrayList<Usuario> getAllUsuarios() {
        String comandoSQL = "SELECT * FROM Usuario";
        ArrayList<Usuario> lista = new ArrayList();
        try {
            Statement ingresarComando = conexionDB.createStatement();
            ResultSet datosObtenidos = ingresarComando.executeQuery(comandoSQL);
            while (datosObtenidos.next()) {
                lista.add(new Usuario(datosObtenidos.getInt("usuario_id"), datosObtenidos.getInt("usuario"), datosObtenidos.getInt("edad"), datosObtenidos.getString("contraseña")));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener datos: " + e.getMessage());
        }
        return lista;

    }

}
