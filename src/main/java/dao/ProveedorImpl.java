package dao;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.Proveedor;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.SQLException;

public class ProveedorImpl extends Conexion implements ICRUD<Proveedor> {

    @Override
    public List listarTodos() throws Exception {
        List<Proveedor> listado = null;
        Proveedor proveedor;
        String sql = "select * from PROVEEDOR where ESTPROV='A' order by CODPROV desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setCODPROV(rs.getString("CODPROV"));
                proveedor.setNOMPROV(rs.getString("NOMPROV"));
                proveedor.setDIRPROV(rs.getString("DIRPROV"));
                proveedor.setCELPROV(rs.getString("CELPROV"));
                proveedor.setRUCPROV(rs.getString("RUCPROV"));
                proveedor.setCOMPROV(rs.getString("COMPROV"));
                proveedor.setEMAPROV(rs.getString("EMAPROV"));
                proveedor.setCODUBI(rs.getString("CODUBI"));
                listado.add(proveedor);
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
    public void registrar(Proveedor obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Proveedor obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Proveedor obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cambiarEstado(Proveedor obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Proveedor> listarTodos(char estado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
