/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

/**
 *
 * @author jesus
 */
public class ProductoImpl extends Conexion implements CRUD<Producto>{

    @Override
    public void registrar(Producto pro) throws Exception {
                String sql = "INSERT INTO PRODUCTO (NOMPRO,PREPRO,FECREGPRO,ESTPRO) VALUES (?,?,(select now()),?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pro.getNOMPRO());
            ps.setDouble(2, pro.getPREPRO());
            ps.setString(3, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificar(Producto g) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Producto g) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Producto> listar() throws Exception {
         List<Producto> listado = new ArrayList<>();
        Producto pro;
        String sql = "SELECT IDPRO,NOMPRO,PREPRO,FECREGPRO FROM PRODUCTO WHERE ESTPRO = 'A'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pro = new Producto();
                pro.setIDPRO(rs.getInt("IDPRO"));
                pro.setNOMPRO(rs.getString("NOMPRO"));
                pro.setPREPRO(rs.getDouble("PREPRO"));
                pro.setFECREGPRO(rs.getDate("FECREGPRO"));
                listado.add(pro);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en el listadoDaoProducto" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }
    
}
