package controlador;

import dao.ClienteImpl;
import modelo.Cliente;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private String nombre;

    public ClienteC() {
        cliente = new Cliente();
        dao = new ClienteImpl();
        listadoCliente = new ArrayList<>();
    }

    public void registrar() throws Exception {
        try {
            cliente.setCODUBI(dao.obtenerCodigoUbigeo(cliente.getCODUBI()));
            dao.registrar(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Falta Completar Datos"));
            System.out.println("Error en RegistrarC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            cliente.setCODUBI(dao.obtenerCodigoUbigeo(cliente.getCODUBI()));
            dao.modificar(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en ModificarC " + e.getMessage());
        }
    }

    public void eliminar(Cliente cliente) throws Exception {
        try {
            dao.eliminar(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Al Eliminar"));
            System.out.println("Error en EliminarC " + e.getMessage());
        }
    }

    public void eliminarEstado() throws Exception {
        try {
            dao.EliminarEstado(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Al Eliminar"));
            System.out.println("Error en eliminarEstadoC " + e.getMessage());
        }
    }

    public void restaurarEstado(Cliente cliente) throws Exception {
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
        ClienteImpl daoUbigeo = new ClienteImpl();
        return daoUbigeo.autocompleteUbigeo(query);
    }

    public void limpiar() {
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Limpiado"));
        this.cliente = new Cliente();
    }

    public void limpia() throws Exception {
        try {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Limpiado"));
            this.cliente.setNOMCLI("");
            this.cliente.setAPECLI("");
            this.cliente.setDIRCLI("");
            this.cliente.setCELCLI("");
            this.cliente.setCODUBI("");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Al limpiar"));
            System.out.println("Error en limpiaC " + e.getMessage());
        }
    }

    public void listar() {
        try {
            listadoCliente = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
        }
    }
    
    @PostConstruct
    public void construir() {
        listar();
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
