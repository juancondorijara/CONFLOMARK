package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {

    public static Connection cnx = null;

    public static Connection conectar() throws Exception {

        InputStream input = Conexion.class.getClassLoader().getResourceAsStream("properties/db.properties");
        Properties properties = new Properties();
        properties.load(input);
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String pwd = properties.getProperty("pwd");
        try {
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url,user,pwd);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage() + e.getStackTrace());
        }
        return cnx;
    }
    
    public void cerrarCnx() throws Exception{
        if (Conexion.cnx != null)
            cnx.close();
    }
    
    public static void main(String[] args) throws Exception{
        Conexion.conectar();
        if (Conexion.cnx != null) {
            System.out.println("CONECTADO");
        } else {
            System.out.println("SIN CONEXIÓN REVISA...");
        }
    }
}