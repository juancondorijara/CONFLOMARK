package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteImpl extends Conexion implements CRUD<Cliente> {

    @Override
    public void registrar(Cliente cli) throws Exception {
        String sql = "INSERT INTO CLIENTE (NOMCLI,APECLI,DNICLI,ESTCLI) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getNOMCLI());
            ps.setString(2, cli.getAPECLI());
            ps.setString(3, cli.getDNICLI());
            ps.setString(4, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificar(Cliente cli) throws Exception {
        String sql = "UPDATE cliente SET NOMCLI=?,APECLI=?,DNICLI=? WHERE IDCLI=?";

        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getNOMCLI());
            ps.setString(2, cli.getAPECLI());
            ps.setString(3, cli.getDNICLI());
            ps.setInt(4, cli.getIDCLI());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en modificarPerDao" + e.getMessage());
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void eliminar(Cliente cli) throws Exception {
        String sql = "UPDATE CLIENTE SET ESTCLI = 'A' WHERE IDCLI = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getESTCLI());
            ps.setInt(1, cli.getIDCLI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public List<Cliente> listar() throws Exception {
        List<Cliente> listado = new ArrayList<>();
        Cliente cli;
        String sql = "SELECT IDCLI,NOMCLI,APECLI,DNICLI FROM CLIENTE WHERE ESTCLI = 'A'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cli = new Cliente();
                cli.setIDCLI(rs.getInt("IDCLI"));
                cli.setNOMCLI(rs.getString("NOMCLI"));
                cli.setAPECLI(rs.getString("APECLI"));
                cli.setDNICLI(rs.getString("DNICLI"));
                listado.add(cli);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en el listadoDaoCliente" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }
    
}
