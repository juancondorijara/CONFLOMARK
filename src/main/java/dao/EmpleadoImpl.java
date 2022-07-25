package dao;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.Empleado;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.SQLException;

public class EmpleadoImpl extends Conexion implements ICRUD<Empleado> {
        
    @Override
    public List listarTodos() throws Exception {
        List<Empleado> listado = null;
        Empleado empleado;
        String sql = "select * from EMPLEADO where ESTEMP='A' order by IDEMP desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                empleado = new Empleado();
                empleado.setIDEMP(rs.getInt("IDEMP"));
                empleado.setNOMEMP(rs.getString("NOMEMP"));
                empleado.setAPEEMP(rs.getString("APEEMP"));
                empleado.setDIREMP(rs.getString("DIREMP"));
                empleado.setDNIEMP(rs.getString("DNIEMP"));
                empleado.setCELEMP(rs.getString("CELEMP"));
                empleado.setCODUBI(rs.getString("CODUBI"));
                listado.add(empleado);
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
    public void registrar(Empleado obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Empleado obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Empleado obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cambiarEstado(Empleado obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empleado> listarTodos(char estado) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
