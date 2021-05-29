package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import modelo.VentaDetalle;
import servicios.UtilToSql;

public class VentaDetalleImpl extends Conexion {
    
    public VentaDetalle agregarFila(int idpro) throws SQLException, Exception {
        VentaDetalle v = null;
        String sql2 = "SELECT IDPRO,NOMPRO,PREPRO FROM PRODUCTO "
                + "WHERE IDPRO = " + idpro;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                v = new VentaDetalle();
                Producto p = new Producto();
                v.setIDPRO(rs.getInt("IDPRO"));
                p.setNOMPRO(rs.getString("NOMPRO"));
                p.setPREPRO(rs.getDouble("PREPRO"));
                v.setProducto(p);
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error en el nuevo metodo DetalleDAO " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
            return v;
        }
    }
    
    public void registroMultiple(List<VentaDetalle> listaVentaDetalle,
            int idVenta) throws Exception {
        String sql = "INSERT INTO VENTA_DETALLE (CANVENDET,PREVENDET,IDPRO,IDVEN) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            for (VentaDetalle ventadetalle : listaVentaDetalle) {
                ps.setInt(1, ventadetalle.getCANVENDET());
                ps.setDouble(2, ventadetalle.getPREVENDET());
                ps.setInt(3, ventadetalle.getIDPRO());
                ps.setInt(4, idVenta);
                ps.executeUpdate();
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();//si o si cerrar en caso funcione o no
        }
    }
    
    public void registrar(Venta ven) throws Exception {
        String sql = "INSERT INTO VENTA (FECVEN,FECENTVEN,IDCLI,TOTVEN) VALUES ((select now()),?,?,?)";
        String sql2 = "select now()";
        try {
//            Date d = null;
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            PreparedStatement ps1 = this.conectar().prepareStatement(sql2);
            if (ven.getFECENTVEN() == null) {
                ven.setFECENTVEN(UtilToSql.convert(Date.from(Instant.now())));
            }
            ps.setTimestamp(1, UtilToSql.convert(ven.getFECENTVEN()));//servicio covert
            ps.setInt(2, ven.getCliente().getIDCLI());
            ps.setDouble(3, ven.getTOTVEN());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }
    
    public int obtenerUltimoId() {
        try {
            
            PreparedStatement ps1 = this.conectar().prepareStatement("SELECT MAX(v.idven) as IDVEN\n"
                    + "FROM VENTA v");
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDVEN");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en obtenerUltimoId" + e.getMessage());
        }
        return -1;
    }
    
    public List<VentaDetalle> listar() throws Exception {
        List<VentaDetalle> listado = new ArrayList<>();
        VentaDetalle vendet;
        Producto pro;
        Venta ven;
        String sql = "SELECT vd.CANVENDET,vd.PREVENDET,p.IDPRO,"
                + "p.NOMPRO,v.IDVEN,v.FECVEN,v.FECENTVEN,c.NOMCLI FROM VENTA_DETALLE vd "
                + "INNER JOIN PRODUCTO p ON vd.IDPRO = p.IDPRO "
                + "INNER JOIN VENTA v ON vd.IDVEN = v.IDVEN "
                + "INNER JOIN CLIENTE c ON v.IDCLI = c.IDCLI "
                + "ORDER BY v.IDVEN DESC";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                vendet = new VentaDetalle();
                vendet.setCANVENDET(rs.getInt("CANVENDET"));
                vendet.setPREVENDET(rs.getDouble("PREVENDET"));
                pro = new Producto();
                pro.setIDPRO(rs.getInt("IDPRO"));
                pro.setNOMPRO(rs.getString("NOMPRO"));
                vendet.setProducto(pro);
                ven = new Venta();
                ven.setIDVEN(rs.getInt("IDVEN"));
                ven.setFECVEN(rs.getDate("FECENTVEN"));
                ven.setFECENTVEN(rs.getTimestamp("FECENTVEN"));
                vendet.setVenta(ven);
                vendet.setNOMCLI(rs.getString("NOMCLI"));
                listado.add(vendet);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en el listadoDaoVentaDetalle" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }
    
}
