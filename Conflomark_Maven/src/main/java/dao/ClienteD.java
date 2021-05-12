package dao;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.ClienteM;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.SQLException;

public class ClienteD extends Conexion implements ICRUD<ClienteM> {

    @Override
    public void registrar(ClienteM cli) throws Exception {
//        String sql = "insert into CLIENTE (NOMCLI, APECLI, DIRCLI, CELCLI, CODUBI)"
//                + "values (?,?,?,?,?)";
        String sql = "insert into CLIENTE (NOMCLI, APECLI, DIRCLI, CELCLI, CODUBI, ESTCLI) values (?,?,?,?,?,?)";
        try {
//            PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql);
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellido());
            ps.setString(3, cli.getDireccion());
            ps.setString(4, cli.getCelular());
            ps.setString(5, cli.getUbigeo());
            ps.setString(6, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en RegistrarD " + e.getMessage());
//            System.out.println("NOMCLI " + cli.getNombre());
        } finally {
            this.cerrar();
        }

    }

    @Override
    public void modificar(ClienteM cli) throws Exception {
        String sql = "update CLIENTE set NOMCLI=?, APECLI=?, DIRCLI=?, CELCLI=?, CODUBI=?, ESTCLI=? where CODCLI=? ";
        try {
//            PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql);
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellido());
            ps.setString(3, cli.getDireccion());
            ps.setString(4, cli.getCelular());
            ps.setString(5, cli.getUbigeo());
            ps.setString(6, "A");
            ps.setInt(7, cli.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ModificarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(ClienteM cli) throws Exception {
        String sql = "delete from CLIENTE where CODCLI=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, cli.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }
    
    
    
//    @Override
//    public List<ClienteM> listarTodos() throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    
    @Override
    public List listarTodos() throws Exception {
        List<ClienteM> listado = null;
        ClienteM cli;
//        String sql = "select * from CLIENTE";
        String sql = "select * from CLIENTE where ESTCLI='A' ";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cli = new ClienteM();
                cli.setCodigo(rs.getInt("CODCLI"));
                cli.setNombre(rs.getString("NOMCLI"));
                cli.setApellido(rs.getString("APECLI"));
                cli.setDireccion(rs.getString("DIRCLI"));
                cli.setCelular(rs.getString("CELCLI"));
                cli.setUbigeo(rs.getString("CODUBI"));
                listado.add(cli);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Error en ListarTodosD" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;
    }
    
    public void EliminarEstado(ClienteM cli) throws Exception {
        String sql = "update CLIENTE set ESTCLI=? where CODCLI=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, cli.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }
    
    public void RestaurarEstado(ClienteM cli) throws Exception {
        String sql = "update CLIENTE set ESTCLI=? where CODCLI=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "A");
            ps.setInt(2, cli.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

}
