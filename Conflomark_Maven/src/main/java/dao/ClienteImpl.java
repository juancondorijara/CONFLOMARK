package dao;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.Cliente;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.SQLException;

public class ClienteImpl extends Conexion implements ICRUD<Cliente> {

    @Override
    public void registrar(Cliente cliente) throws Exception {
        String sql = "insert into CLIENTE (NOMCLI, APECLI, DIRCLI, CELCLI, CODUBI, ESTCLI) values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getCelular());
            ps.setString(5, cliente.getUbigeo());
            ps.setString(6, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en RegistrarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Cliente cliente) throws Exception {
        String sql = "update CLIENTE set NOMCLI=?, APECLI=?, DIRCLI=?, CELCLI=?, CODUBI=?, ESTCLI=? where IDCLI=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getCelular());
            ps.setString(5, cliente.getUbigeo());
            ps.setString(6, "A");
            ps.setInt(7, cliente.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ModificarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Cliente cliente) throws Exception {
        String sql = "delete from CLIENTE where IDCLI=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, cliente.getCodigo());
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
        List<Cliente> listado = null;
        Cliente cliente;
//        String sql = "select * from CLIENTE";
        String sql = "select * from CLIENTE where ESTCLI='A' order by IDCLI desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setCodigo(rs.getInt("IDCLI"));
                cliente.setNombre(rs.getString("NOMCLI"));
                cliente.setApellido(rs.getString("APECLI"));
                cliente.setDireccion(rs.getString("DIRCLI"));
                cliente.setCelular(rs.getString("CELCLI"));
                cliente.setUbigeo(rs.getString("CODUBI"));
                listado.add(cliente);
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
    
    public void EliminarEstado(Cliente cliente) throws Exception {
        String sql = "update CLIENTE set ESTCLI=? where IDDCLI=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, cliente.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }
    
    public void RestaurarEstado(Cliente cliente) throws Exception {
        String sql = "update CLIENTE set ESTCLI=? where IDDCLI=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "A");
            ps.setInt(2, cliente.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }
    
    public List<String> autocompleteUbigeo(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "select top 171 concat(DISUBI, ', ', PROUBI, ', ',DEPUBI) as UBIGEODESC from UBIGEO where DISUBI like ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("UBIGEODESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteUbigeoDao" + e.getMessage());
        }
        return lista;
    }
    
    public String obtenerCodigoUbigeo(String cadenaUbi) throws SQLException, Exception {
        String sql = "select CODUBI from UBIGEO where concat(DISUBI, ', ', PROUBI, ', ',DEPUBI) = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaUbi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("CODUBI");
            }
            return rs.getString("CODUBI");
        } catch (Exception e) {
            System.out.println("Error en obtenerCodigoUbigeo " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void cambiarEstado(Cliente obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> listarTodos(char estado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
