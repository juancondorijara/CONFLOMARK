package controlador;

import dao.ClienteImpl;
import modelo.Cliente;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Named;
//import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.enterprise.context.SessionScoped;

//Notación CDI
@Named(value = "clienteC")
//@Dependent
@SessionScoped
public class ClienteC implements Serializable {

    private Cliente cliente;
    private ClienteImpl dao;
    private List<Cliente> listadoCliente;

    public ClienteC() {
        cliente = new Cliente();
        dao = new ClienteImpl();
    }

    public void registrar() throws Exception {
        try {
            cliente.setUbigeo(dao.obtenerCodigoUbigeo(cliente.getUbigeo()));
            dao.registrar(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Error al Registrar"));
            System.out.println("Error en RegistrarC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en ModificarC " + e.getMessage());
        }
    }
    
    public void eliminar(Cliente cliente) throws Exception{
        try {            
            dao.eliminar(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en EliminarC " + e.getMessage());
        }
    }
    
    public void eliminarEstado(Cliente cliente) throws Exception{
        try {            
            dao.EliminarEstado(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarEstadoC " + e.getMessage());
        }
    }
    
    public void restaurarEstado(Cliente cliente) throws Exception{
        try {            
            dao.RestaurarEstado(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Restaurado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en restaurarEstadoC " + e.getMessage());
        }
    }
    
    public List<String> completeTextUbigeo(String query) throws SQLException, Exception {
        ClienteImpl daoUbi = new ClienteImpl();
        return daoUbi.autocompleteUbigeo(query);
    }  
    
    public void limpiar() {
        cliente = new Cliente();
    }

    public void listar() {
        try {
            listadoCliente = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
        }
    }
    
    

// Getter y Setter

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteImpl getDao() {
        return dao;
    }

    public void setDao(ClienteImpl dao) {
        this.dao = dao;
    }

    public List<Cliente> getListadoCliente() {
        return listadoCliente;
    }

    public void setListadoCliente(List<Cliente> listadoCliente) {
        this.listadoCliente = listadoCliente;
    }

}
