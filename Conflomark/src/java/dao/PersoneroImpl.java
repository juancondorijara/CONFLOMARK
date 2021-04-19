package dao;

import com.mysql.jdbc.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import modelo.Personero;

public class PersoneroImpl extends Conexion implements ICRUD<Personero>{
// dni_per nom_per  nac_per  tel_per asig_mes   mes_per   obs_per
    @Override
    public void registrar(Personero per) throws Exception {
       String sql = "insert into personeros (dni_per, nom_per,  nac_per,  tel_per, asig_mes,   mes_per,   obs_per)"
               + "values (?,?,?,?,?,?,?)" ;
        try {
            PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql);
            ps.setString(1, per.getDni());
            ps.setString(2, per.getNombre());
            ps.setString(3, per.getNacimiento());
            ps.setString(4, per.getTelefono());
            ps.setString(5, per.getAsig());
            ps.setString(6, per.getMes());
            ps.setString(7, per.getObs());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ingresar personeroD " + e.getMessage());
        } finally{
            this.cerrarCnx();
        }
    }

    @Override
    public void modificar(Personero obj) throws Exception {
        
    }

    @Override
    public void eliminar(int codigo) throws Exception {
        
    }

    @Override
    public List<Personero> listarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//   @Override
//    public List listarTodos() throws Exception {
//        List<Personero> listado = null;
//        Personero pers;
//        String sql = "select * from personero";
//        try {
//            listado = new ArrayList<>();
//            Statement st = this.conectar().createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                pers = new Personero();
//                pers.setDni(rs.getString("dni_per"));
//                pers.setNombre(rs.getString("nom_per"));
////                pers.setNacimiento(rs.getString("nac_per"));
//                pers.setTelefono(rs.getString("tel_per"));
//                pers.setAsignacion(rs.getString("asig_mes"));
//                pers.setMesa(rs.getString("mes_per"));
//                pers.setObservacion(rs.getString("obs_per"));
//                pers.setUbigeo(rs.getString("cod_ubi"));
//                pers.setSexo(rs.getString("sex_per"));
//                listado.add(pers);
//            }
//            rs.close();
//            st.close();
//            
//        } catch (Exception e) {
//            System.out.println("Error en listarTodos Dao" + e.getMessage());
//        } finally {
//            this.cerrar();
//        }  
//        return listado;   
//    }
    
}
