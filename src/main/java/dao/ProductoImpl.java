package dao;

//import com.mysql.jdbc.PreparedStatement;
//import java.sql.Date;
import java.sql.CallableStatement;
import java.util.Date;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.Producto;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProductoImpl extends Conexion implements ICRUD<Producto> {

    DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }

    @Override
    public void registrar(Producto producto) throws Exception {
        String sql = "insert into PRODUCTO (NOMPRO, MARPRO, CODFAM, DESPRO, PREPRO, STOPRO, VENPRO, CODPROV, ESTPRO) values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, producto.getNOMPRO());
            ps.setString(2, producto.getMARPRO());
            ps.setString(3, producto.getCODFAM());
            ps.setString(4, producto.getDESPRO());
            ps.setDouble(5, producto.getPREPRO());
            ps.setInt(6, producto.getSTOPRO());
            ps.setString(7, formato.format(producto.getVENPRO()));
            ps.setString(8, producto.getCODPROV());
            ps.setString(9, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en RegistrarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Producto producto) throws Exception {
        String sql = "update PRODUCTO set NOMPRO=?, MARPRO=?, CODFAM=?, DESPRO=?, PREPRO=?, STOPRO=?, VENPRO=?, CODPROV=?, ESTPRO=? where IDPRO=?"; //where IDPRO like?
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, producto.getNOMPRO());
            ps.setString(2, producto.getMARPRO());
            ps.setString(3, producto.getCODFAM());
            ps.setString(4, producto.getDESPRO());
            ps.setDouble(5, producto.getPREPRO());
            ps.setInt(6, producto.getSTOPRO());
            ps.setString(7, formato.format(producto.getVENPRO()));
            ps.setString(8, producto.getCODPROV());
            ps.setString(9, "A");
            ps.setInt(10, producto.getIDPRO());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ModificarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Producto producto) throws Exception {
        String sql = "delete from PRODUCTO where IDPRO=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, producto.getIDPRO());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarD " + e.getMessage());
        }
    }

//    @Override
//    public List<ProductoM> listarTodos() throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    @Override
    public List listarTodos() throws Exception {
        List<Producto> listado = null;
        Producto producto;
        String sql = "SELECT * FROM vProducto";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                producto = new Producto();
                producto.setIDPRO(rs.getInt("IDPRO"));
                producto.setNOMPRO(rs.getString("NOMPRO"));
                producto.setMARPRO(rs.getString("MARPRO"));
                producto.setCODFAM(rs.getString("SUBFAM"));
                producto.setDESPRO(rs.getString("DESPRO"));
                producto.setPREPRO(rs.getDouble("PREPRO"));
                producto.setSTOPRO(rs.getInt("STOPRO"));
                producto.setVENPRO(rs.getDate("VENPRO"));
                producto.setCODPROV(rs.getString("NOMPROV"));
                listado.add(producto);
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

    public void EliminarEstado(Producto producto) throws Exception {
        String sql = "update PRODUCTO set ESTPRO=? where IDPRO=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, producto.getIDPRO());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void RestaurarEstado(Producto producto) throws Exception {
        String sql = "update PRODUCTO set ESTPRO=? where IDPRO=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "A");
            ps.setInt(2, producto.getIDPRO());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List<String> autocompleteFamilia(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "select top 52 SUBFAM as FAMILIADESC from FAMILIA where SUBFAM like ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("FAMILIADESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteFamiliaDao" + e.getMessage());
        }
        return lista;
    }

    public String obtenerCodigoFamilia(String cadenaFamilia) throws SQLException, Exception {
        String sql = "select CODFAM from FAMILIA where SUBFAM = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaFamilia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("CODFAM");
            }
            return rs.getString("CODFAM");
        } catch (Exception e) {
            System.out.println("Error en obtenerCodigoFamiliaDao " + e.getMessage());
            throw e;
        }
    }

    public List<String> autocompleteProveedor(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
//        String sql = "select top 10 (P.NOMPROV) as NOMPROV from PROVEEDOR P where NOMPROV like ?";
        String sql = "select top 10 NOMPROV as PROVEEDORDESC from PROVEEDOR where NOMPROV like ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("PROVEEDORDESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteProveedorDao" + e.getMessage());
        }
        return lista;
    }

    public String obtenerCodigoProveeedor(String cadenaProveedor) throws SQLException, Exception {
//        String sql = "select CODPROV from PROVEEDOR where (P.NOMPROV) = ?";
        String sql = "select CODPROV from PROVEEDOR where NOMPROV = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaProveedor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("CODPROV");
            }
            return rs.getString("CODPROV");
        } catch (Exception e) {
            System.out.println("Error en obtenerCodigoProveeedorDao " + e.getMessage());
            throw e;
        }
    }
    
    public Double precio=0.0;
    public Integer stockPro = 0;
    public String nombre="", marca="", familia="", descripcion="", proveedor = "";
    
    public Integer obteneridProducto(String cadenaPro) throws SQLException, Exception {
        Integer idProducto = 0;
        try {
            CallableStatement ps = this.conectar().prepareCall("{call spDatosAutoCompletPro(?)}");
            ps.setString(1, cadenaPro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idProducto = rs.getInt("IDPRO");
                nombre = rs.getString("NOMPRO");
                marca = rs.getString("MARPRO");
                familia = rs.getString("SUBFAM");
                descripcion = rs.getString("DESPRO");
                precio = rs.getDouble("PREPRO");
//                stockPro = rs.getInt("STOPRO");
                proveedor = rs.getString("NOMPROV");
            }            
        } catch (Exception e) {
            System.out.println("Error en obteneridProducto " + e.getMessage());            
        }
        return idProducto;
    }

    @Override
    public void cambiarEstado(Producto obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Producto> listarTodos(char estado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
