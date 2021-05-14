package dao;

//import com.mysql.jdbc.PreparedStatement;
//import java.sql.Date;
//import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.Producto;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;

public class ProductoImpl extends Conexion implements ICRUD<Producto> {

//    DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//    public static Date stringToFecha(String fecha) throws ParseException {
//    return fecha != null ? new SimpleDateFormat("yyyy-MM-dd").parse(fecha) : null;
//    }

    @Override
    public void registrar(Producto producto) throws Exception {
        String sql = "insert into PRODUCTO (NOMPRO, MARPRO, FAMPRO, DESPRO, PREPRO, VENPRO) values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getMarca());
            ps.setString(3, producto.getFamilia());
            ps.setString(4, producto.getDescripcion());
            ps.setString(5, producto.getPrecio());
//            ps.setDate(6, new java.sql.Date(producto.getVencimiento().getTime()));
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(6, forma.format(producto.getVencimiento()));
//            ps.setString(6, formato.format(producto.getVencimiento()));
//            ps.setDate(6, producto.getVencimiento());
//            ps.setString(7, "A");
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
        String sql = "update PRODUCTO set NOMPRO=?, MARPRO=?, FAMPRO=?, DESPRO=?, PREPRO=?, VENPRO=?, ESTPRO=? where IDPRO=? "; //where IDPRO like?
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getMarca());
            ps.setString(3, producto.getFamilia());
            ps.setString(4, producto.getDescripcion());
            ps.setString(5, producto.getPrecio());
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(6, forma.format(producto.getVencimiento()));
//            ps.setDate(6, producto.getVencimiento());
            ps.setString(7, "A");
            ps.setInt(8, producto.getCodigo());
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
            ps.setInt(1, producto.getCodigo());
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
        String sql = "select * from PRODUCTO where ESTPRO='A' order by IDPRO desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                producto = new Producto();
                producto.setCodigo(rs.getInt("IDPRO"));
                producto.setNombre(rs.getString("NOMPRO"));
                producto.setMarca(rs.getString("MARPRO"));
                producto.setFamilia(rs.getString("FAMPRO"));
                producto.setDescripcion(rs.getString("DESPRO"));
                producto.setPrecio(rs.getString("PREPRO"));
                producto.setVencimiento(rs.getDate("VENPRO"));
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

    @Override
    public void cambiarEstado(Producto obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Producto> listarTodos(char estado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
