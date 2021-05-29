package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection cnx = null;

    public static Connection conectar() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/TiendaTikTok","root","");
//            Class.forName("oracle.jdbc.pool.OracleDataSource");
//            cnx = DriverManager.getConnection("jdbc:oracle:thin:@kalidaoracle1_tp?TNS_ADMIN=./wallet_KALIDAORACLE1", "ADMIN", "KalidaOracle1");
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            cnx = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName= KALIDA", "sa", "sa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
        return cnx;
    }

    public static void cerrarCnx() throws Exception {
        if (Conexion.cnx != null) {
            cnx.close();
        }
    }

    public static void main(String[] args) throws Exception {
        conectar();
        if (cnx != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("Sin Conexión");
        }
    }

    public static Connection getCnx() {
        return cnx;
    }

    public static void setCnx(Connection aCnx) {
        cnx = aCnx;
    }

}