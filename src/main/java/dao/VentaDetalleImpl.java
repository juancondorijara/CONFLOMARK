package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.Familia;
import modelo.Producto;
import modelo.Venta;
import modelo.VentaDetalle;
//import servicios.UtilToSql;

public class VentaDetalleImpl extends Conexion {

    DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }

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
            System.out.println("Error en agregarFilaDAO " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.cerrar();
            return v;
        }
    }

    public void registroMultiple(List<VentaDetalle> listaVentaDetalle, int idVenta) throws Exception {
        String sql = "INSERT INTO VENTA_DETALLE (CANVENDET,PREVENDET,IDPRO,IDVEN,ESTVENDET) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            for (VentaDetalle ventadetalle : listaVentaDetalle) {
                ps.setInt(1, ventadetalle.getCANVENDET());
                ps.setDouble(2, ventadetalle.getPREVENDET());
                ps.setInt(3, ventadetalle.getIDPRO());
                ps.setInt(4, idVenta);
                ps.setString(5, "A");
                ps.executeUpdate();
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registroMultipleDAO " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    public void registrar(Venta venta) throws Exception {
        String sql = "INSERT INTO VENTA (FECVEN,IDCLI,IDEMP,TOTVEN,ESTVEN) VALUES (?,?,?,?,?)";
        String sql2 = "select now()";
        try {
//            Date d = null;
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            PreparedStatement ps1 = this.conectar().prepareStatement(sql2);
            ps.setString(1, formato.format(venta.getFECVEN()));
            ps.setInt(2, venta.getCliente().getIDCLI());
            ps.setInt(3, venta.getEmpleado().getIDEMP());
//            ps.setString(3, venta.getIDEMP());
            ps.setDouble(4, venta.getTOTVEN());
            ps.setString(5, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    public int obtenerUltimoId() {
        try {
            PreparedStatement ps1 = this.conectar().prepareStatement("SELECT MAX(v.idven) as IDVEN FROM VENTA v");
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDVEN");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en obtenerUltimoIdDAO" + e.getMessage());
        }
        return -1;
    }

    public List<VentaDetalle> listar() throws Exception {
        List<VentaDetalle> listado = new ArrayList<>();
        VentaDetalle ventadetalle;
        Producto producto;
        Venta venta;
        String sql = "SELECT \n"
                + "    VD.CANVENDET,\n"
                + "    VD.PREVENDET,\n"
                + "	P.IDPRO,\n"
                + "    P.NOMPRO,\n"
                + "	V.IDVEN,\n"
                + "	V.FECVEN,\n"
                + "	C.NOMCLI \n"
                + "FROM VENTA_DETALLE VD\n"
                + "      INNER JOIN PRODUCTO AS P ON \n"
                + "	  VD.IDPRO = P.IDPRO\n"
                + "      INNER JOIN VENTA AS V ON \n"
                + "	  VD.IDVEN = V.IDVEN\n"
                + "      INNER JOIN CLIENTE AS C ON \n"
                + "	  V.IDCLI = C.IDCLI\n"
                + "ORDER BY \n"
                + "    V.IDVEN DESC";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ventadetalle = new VentaDetalle();
                ventadetalle.setCANVENDET(rs.getInt("CANVENDET"));
                ventadetalle.setPREVENDET(rs.getDouble("PREVENDET"));
                producto = new Producto();
                producto.setIDPRO(rs.getInt("IDPRO"));
                producto.setNOMPRO(rs.getString("NOMPRO"));
                ventadetalle.setProducto(producto);
                venta = new Venta();
                venta.setIDVEN(rs.getInt("IDVEN"));
                venta.setFECVEN(rs.getDate("FECVEN"));
                ventadetalle.setVenta(venta);
                ventadetalle.setNOMCLI(rs.getString("NOMCLI"));
                listado.add(ventadetalle);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en el listadoDaoVentaDetalle" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<String> autocompleteEmpleado(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "select top 10 CONCAT(NOMEMP, ' ', APEEMP) as EMPLEADODESC from EMPLEADO where NOMEMP like ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("EMPLEADODESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteEmpleadoDao" + e.getMessage());
        }
        return lista;
    }

    public String obtenerIdEmpleado(String cadenaEmpleado) throws SQLException, Exception {
        String sql = "select IDEMP from EMPLEADO where CONCAT(NOMEMP, ' ', APEEMP) = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaEmpleado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("IDEMP");
            }
            return rs.getString("IDEMP");
        } catch (Exception e) {
            System.out.println("Error en obtenerIdEmpleadoDao " + e.getMessage());
            throw e;
        }
    }

    public VentaDetalle obtenerDatos(int idpro) throws SQLException, Exception {
        VentaDetalle ventadetalle = null;
        String sql = "SELECT P.NOMPRO, P.MARPRO, F.NOMFAM, P.PREPRO, P.IDPRO, F.CODFAM \n"
                + "FROM PRODUCTO P\n"
                + "INNER JOIN FAMILIA F ON\n"
                + "P.CODFAM = F.CODFAM\n"
                + "WHERE P.IDPRO = " + idpro;
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ventadetalle = new VentaDetalle();
                ventadetalle.setIDPRO(rs.getInt("IDPRO"));
                Producto producto = new Producto();
                producto.setNOMPRO(rs.getString("NOMPRO"));
                producto.setMARPRO(rs.getString("MARPRO"));
                producto.setCODFAM(rs.getString("CODFAM"));
                producto.setPREPRO(rs.getDouble("PREPRO"));
                Familia familia = new Familia();
                familia.setNOMFAM(rs.getString("NOMFAM"));
                producto.setFamilia(familia);
                ventadetalle.setProducto(producto);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en obtenerDatos " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return ventadetalle;
    }

}
