package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
//import java.util.Properties;

public class Conexion {

    public static Connection cnx = null;

    public static Connection conectar() throws Exception {
        try {
//            String user = "conflomark";
//            String pwd = "mark12345-";
//            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//            String url = "jdbc:sqlserver://conflomark.database.windows.net:1433;databaseName=dbconflomark";
            String user = "sa";
            String pwd = "12345";
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=dbCONFLOMARK";
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión, revisa");
            System.out.println("Error de conexion " + e.getMessage());
        }
        return cnx;
    }

    public void cerrar() throws Exception {
        if (cnx != null) {
            cnx.close();
        }
    }

    public static void main(String[] args) throws Exception {
        conectar();
        if (cnx != null) {
            System.out.println("CONECTADO");
        } else {
            System.out.println("SIN CONEXIÓN REVISA...");
        }
    }
    
    public Connection getCn() {
        return cnx;
    }

    public void setCn(Connection cnx) {
        this.cnx = cnx;
    }
}
