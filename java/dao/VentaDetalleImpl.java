package dao;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.VentaDetalle;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.text.DateFormat;
import java.text.ParseException;

public class VentaDetalleImpl extends Conexion implements ICRUD<VentaDetalle> {

    @Override
    public void registrar(VentaDetalle ventadetalle) throws Exception {
        String sql = "insert into VENTA_DETALLE (CANVENDET, SUBVENDET, IDVEN, IDPRO, ESTVENDET) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, ventadetalle.getCantidad());
            ps.setString(2, ventadetalle.getSubtotal());
            ps.setString(3, ventadetalle.getVenta());
            ps.setString(4, ventadetalle.getProducto());
            ps.setString(5, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en RegistrarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(VentaDetalle ventadetalle) throws Exception {
        String sql = "update VENTA_DETALLE set CANVENDET=?, SUBVENDET=?, IDVEN=?, IDPRO=?, ESTVENDET=? where IDVENDET=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, ventadetalle.getCantidad());
            ps.setString(2, ventadetalle.getSubtotal());
            ps.setString(3, ventadetalle.getVenta());
            ps.setString(4, ventadetalle.getProducto());
            ps.setString(5, "A");
            ps.setInt(6, ventadetalle.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ModificarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(VentaDetalle ventadetalle) throws Exception {
        String sql = "delete from VENTA_DETALLE where IDVENDET=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, ventadetalle.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List listarTodos() throws Exception {
        List<VentaDetalle> listado = null;
        VentaDetalle ventadetalle;
        String sql = "select * from VENTA_DETALLE where ESTVENDET='A' order by IDVENDET desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ventadetalle = new VentaDetalle();
                ventadetalle.setCodigo(rs.getInt("IDVENDET"));
                ventadetalle.setCantidad(rs.getString("CANVENDET"));
                ventadetalle.setSubtotal(rs.getString("SUBVENDET"));
                ventadetalle.setVenta(rs.getString("IDVEN"));
                ventadetalle.setProducto(rs.getString("IDPRO"));
                listado.add(ventadetalle);
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

    public void EliminarEstado(VentaDetalle ventadetalle) throws Exception {
        String sql = "update VENTA_DETALLE set ESTVENDET=? where IDVENDET=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, ventadetalle.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void RestaurarEstado(VentaDetalle ventadetalle) throws Exception {
        String sql = "update VENTA_DETALLE set ESTVENDET=? where IDVENDET=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "A");
            ps.setInt(2, ventadetalle.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en RestaurarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List<String> autocompleteVenta(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "select top 20 concat(FECVEN, ', ', IDCLI, ', ',IDEMP, ', ',TOTVEN) as VENTADESC from VENTA where FECVEN like ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("VENTADESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteVentaD" + e.getMessage());
        }
        return lista;
    }
    
    public String obtenerIdVen(String cadenaVenta) throws SQLException, Exception {
        String sql = "select IDVEN from VENTA where concat(FECVEN, ', ', IDCLI, ', ',IDEMP, ', ',TOTVEN) = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaVenta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("IDVEN");
            }
            return rs.getString("IDVEN");
        } catch (Exception e) {
            System.out.println("Error en obtenerIdVenD " + e.getMessage());
            throw e;
        }
    }
    
    public List<String> autocompleteProducto(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "select top 20 concat(NOMPRO, ', ', MARPRO, ', ',CODFAM, ', ',DESPRO, ', ',PREPRO) as PRODUCTODESC from PRODUCTO where NOMPRO like ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("PRODUCTODESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteProductoD" + e.getMessage());
        }
        return lista;
    }
    
    public String obtenerIdPro(String cadenaProducto) throws SQLException, Exception {
        String sql = "select IDPRO from PRODUCTO where concat(NOMPRO, ', ', MARPRO, ', ',CODFAM, ', ',DESPRO, ', ',PREPRO) = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaProducto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("IDPRO");
            }
            return rs.getString("IDPRO");
        } catch (Exception e) {
            System.out.println("Error en obtenerIdProD " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void cambiarEstado(VentaDetalle obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VentaDetalle> listarTodos(char estado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
