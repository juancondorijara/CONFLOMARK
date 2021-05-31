package dao;

//import com.mysql.jdbc.PreparedStatement;
//import java.sql.Date;
import java.util.Date;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.Venta;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class VentaImpl extends Conexion implements ICRUD<Venta> {

    DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    public static Date stringToFecha(String fecha) throws ParseException {
    return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }
    
    @Override
    public void registrar(Venta venta) throws Exception {
        String sql = "insert into VENTA (FECVEN, IDCLI, IDEMP, TOTVEN, ESTVEN) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, formato.format(venta.getFecha()));
            ps.setString(2, venta.getCliente());
            ps.setString(3, venta.getEmpleado());
            ps.setString(4, venta.getTotal());
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
    public void modificar(Venta venta) throws Exception {
        String sql = "update VENTA set FECVEN=?, IDCLI=?, IDEMP=?, TOTVEN=?, ESTVEN=? where IDVEN=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, formato.format(venta.getFecha()));
            ps.setString(2, venta.getCliente());
            ps.setString(3, venta.getEmpleado());
            ps.setString(4, venta.getTotal());
            ps.setString(5, "A");
            ps.setInt(6, venta.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ModificarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Venta venta) throws Exception {
        String sql = "delete from VENTA where IDVEN=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, venta.getCodigo());
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
        List<Venta> listado = null;
        Venta venta;
        String sql = "select * from VENTA where ESTVEN='A' order by IDVEN desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                venta = new Venta();
                venta.setCodigo(rs.getInt("IDVEN"));
                venta.setFecha(rs.getDate("FECVEN"));
                venta.setCliente(rs.getString("IDCLI"));
                venta.setEmpleado(rs.getString("IDEMP"));
                venta.setTotal(rs.getString("TOTVEN"));
                listado.add(venta);
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

    public void EliminarEstado(Venta venta) throws Exception {
        String sql = "update VENTA set ESTVEN=? where IDVEN=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, venta.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void RestaurarEstado(Venta venta) throws Exception {
        String sql = "update VENTA set ESTVEN=? where IDVEN=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "A");
            ps.setInt(2, venta.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en RestaurarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List<String> autocompleteCliente(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "select top 20 concat(NOMCLI, ', ', APECLI, ', ',DIRCLI, ', ',CELCLI, ', ',CODUBI) as CLIENTEDESC from CLIENTE where NOMCLI like ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("CLIENTEDESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteClienteD" + e.getMessage());
        }
        return lista;
    }
    
    public String obtenerIdCli(String cadenaCliente) throws SQLException, Exception {
        String sql = "select IDCLI from CLIENTE where concat(NOMCLI, ', ', APECLI, ', ',DIRCLI, ', ',CELCLI, ', ',CODUBI) = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("IDCLI");
            }
            return rs.getString("IDCLI");
        } catch (Exception e) {
            System.out.println("Error en obtenerIdCliD " + e.getMessage());
            throw e;
        }
    }
    
    public List<String> autocompleteEmpleado(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "select top 20 concat(NOMEMP, ', ', APEEMP, ', ',DIREMP, ', ',DNIEMP, ', ',CELEMP, ', ',CODUBI) as EMPLEADODESC from EMPLEADO where NOMEMP like ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("EMPLEADODESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteEmpleadoD" + e.getMessage());
        }
        return lista;
    }
    
    public String obtenerIdEmp(String cadenaEmpleado) throws SQLException, Exception {
        String sql = "select IDEMP from EMPLEADO where concat(NOMEMP, ', ', APEEMP, ', ',DIREMP, ', ',DNIEMP, ', ',CELEMP, ', ',CODUBI) = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaEmpleado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("IDEMP");
            }
            return rs.getString("IDEMP");
        } catch (Exception e) {
            System.out.println("Error en obtenerIdEmpD " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public void cambiarEstado(Venta obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> listarTodos(char estado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
