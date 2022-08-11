package dao;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.Familia;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.SQLException;

public class FamiliaImpl extends Conexion implements ICRUD<Familia> {
        
    @Override
    public List listarTodos() throws Exception {
        List<Familia> listado = null;
        Familia familia;
        String sql = "select * from FAMILIA";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                familia = new Familia();
                familia.setCODFAM(rs.getString("CODFAM"));
                familia.setNOMFAM(rs.getString("NOMFAM"));
                familia.setSUBFAM(rs.getString("SUBFAM"));
                listado.add(familia);
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
    public void registrar(Familia obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Familia obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Familia obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cambiarEstado(Familia obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Familia> listarTodos(char estado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
