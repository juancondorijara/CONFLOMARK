package dao;

//import com.mysql.jdbc.PreparedStatement;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.ProductoM;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
//setDate(new java.util.Date());

public class ProductoD extends Conexion implements ICRUD<ProductoM> {

    @Override
    public void registrar(ProductoM pro) throws Exception {
        String sql = "insert into PRODUCTO (NOMPRO, MARPRO, FAMPRO, DESPRO, PREPRO, VENPRO) values (?,?,?,?,?,?)";
        try {
//            PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql);
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setString(2, pro.getMarca());
            ps.setString(3, pro.getFamilia());
            ps.setString(4, pro.getDescripcion());
            ps.setString(5, pro.getPrecio());
//            ps.setDate(6, new java.sql.Date(pro.getVencimiento().getTime()));
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(6, forma.format(pro.getVencimiento()));
//            ps.setDate(6, pro.getVencimiento());
            System.out.println("Vencimiento " + pro.getVencimiento());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en RegistrarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(ProductoM pro) throws Exception {
        String sql = "update PRODUCTO set NOMPRO=?, MARPRO=?, FAMPRO=?, DESPRO=?, PREPRO=?, VENPRO=? where CODPRO=? ";
        try {
//            PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql);
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setString(2, pro.getMarca());
            ps.setString(3, pro.getFamilia());
            ps.setString(4, pro.getDescripcion());
            ps.setString(5, pro.getPrecio());
            System.out.println("Vencimiento " + pro.getVencimiento());
            SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            ps.setString(6, forma.format(pro.getVencimiento()));
//            ps.setDate(6, pro.getVencimiento());
            ps.setInt(7, pro.getCodigo());
            ps.executeUpdate();
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ModificarD " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(ProductoM pro) throws Exception {
        String sql = "delete from PRODUCTO where CODPRO=? ";
        try {
//            PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql);
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, pro.getCodigo());
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
        List<ProductoM> listado = null;
        ProductoM pro;
        String sql = "select * from PRODUCTO where ESTPRO='A' order by CODPRO desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pro = new ProductoM();
                pro.setCodigo(rs.getInt("CODPRO"));
                pro.setNombre(rs.getString("NOMPRO"));
                pro.setMarca(rs.getString("MARPRO"));
                pro.setFamilia(rs.getString("FAMPRO"));
                pro.setDescripcion(rs.getString("DESPRO"));
                pro.setPrecio(rs.getString("PREPRO"));
                pro.setVencimiento(rs.getDate("VENPRO"));
                listado.add(pro);
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

}
