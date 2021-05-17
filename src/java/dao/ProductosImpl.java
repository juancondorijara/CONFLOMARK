package dao;

//import com.mysql.jdbc.PreparedStatement;
//import java.sql.Date;
//import java.util.Date;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.Productos;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;

public class ProductosImpl extends Conexion implements ICRUD<Productos> {

//    DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//    public static Date stringToFecha(String fecha) throws ParseException {
//    return fecha != null ? new SimpleDateFormat("yyyy-MM-dd").parse(fecha) : null;
//    }

    @Override
    public void registrar(Productos productos) throws Exception {
        String sql = "insert into PRODUCTOS (NOMPRO, MARPRO, CODFAM, DESPRO, PREPRO, VENPRO, ESTPRO) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, productos.getNombre());
            ps.setString(2, productos.getMarca());
            ps.setString(3, productos.getFamilia());
            ps.setString(4, productos.getDescripcion());
            ps.setString(5, productos.getPrecio());
//            ps.setDate(6, new java.sql.Date(productos.getVencimiento().getTime()));
//            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
//            ps.setString(6, forma.format(productos.getVencimiento()));
            
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yy");
            ps.setDate(6, Date.valueOf(formatoFecha.format(productos.getVencimiento())));
            
//            ps.setString(6, formato.format(productos.getVencimiento()));
//            ps.setDate(6, productos.getVencimiento());
            ps.setString(7, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en RegistrarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Productos productos) throws Exception {
        String sql = "update PRODUCTOS set NOMPRO=?, MARPRO=?, CODFAM=?, DESPRO=?, PREPRO=?, VENPRO=?, ESTPRO=? where IDPRO=? "; //where IDPRO like?
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, productos.getNombre());
            ps.setString(2, productos.getMarca());
            ps.setString(3, productos.getFamilia());
            ps.setString(4, productos.getDescripcion());
            ps.setString(5, productos.getPrecio());
//            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
//            ps.setString(6, forma.format(productos.getVencimiento()));
            ps.setDate(6, productos.getVencimiento());
            ps.setString(7, "A");
            ps.setInt(8, productos.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ModificarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Productos productos) throws Exception {
        String sql = "delete from PRODUCTOS where IDPRO=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, productos.getCodigo());
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
        List<Productos> listado = null;
        Productos productos;
        String sql = "select * from PRODUCTOS where ESTPRO='A' order by IDPRO desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                productos = new Productos();
                productos.setCodigo(rs.getInt("IDPRO"));
                productos.setNombre(rs.getString("NOMPRO"));
                productos.setMarca(rs.getString("MARPRO"));
                productos.setFamilia(rs.getString("CODFAM"));
                productos.setDescripcion(rs.getString("DESPRO"));
                productos.setPrecio(rs.getString("PREPRO"));
                productos.setVencimiento(rs.getDate("VENPRO"));
                listado.add(productos);
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
    
    public void EliminarEstado(Productos productos) throws Exception {
        String sql = "update PRODUCTOS set ESTPRO=? where IDPRO=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, productos.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en EliminarEstadoD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }
    
    public void RestaurarEstado(Productos productos) throws Exception {
        String sql = "update PRODUCTOS set ESTPRO=? where IDPRO=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "A");
            ps.setInt(2, productos.getCodigo());
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
        String sql = "select top 52 concat(SUBFAM, ', ', NOMFAM) as FAMILIADESC from FAMILIA where SUBFAM like ?";
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
        String sql = "select CODFAM from FAMILIA where concat(SUBFAM, ', ', NOMFAM) = ?";
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

    @Override
    public void cambiarEstado(Productos obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Productos> listarTodos(char estado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
