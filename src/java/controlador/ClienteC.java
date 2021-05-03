package controlador;

import dao.ClienteD;
import modelo.ClienteM;
import java.io.Serializable;
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

    private ClienteM cli;
    private ClienteD dao;
    private List<ClienteM> listarCli;

    public ClienteC() {
        cli = new ClienteM();
        dao = new ClienteD();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(cli);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en RegistrarC " + cli.getNombre());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(cli);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en ModificarC " + e.getMessage());
        }
    }
    
    public void eliminar(ClienteM clie) throws Exception{
        try {            
            dao.eliminar(clie);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en EliminarC " + e.getMessage());
        }
    }
    
    public void eliminarEstado(ClienteM clie) throws Exception{
        try {            
            dao.EliminarEstado(clie);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarEstadoC " + e.getMessage());
        }
    }
    
    public void restaurarEstado(ClienteM clie) throws Exception{
        try {            
            dao.RestaurarEstado(clie);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Restaurado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en restaurarEstadoC " + e.getMessage());
        }
    }
    
    public void limpiar() {
        cli = new ClienteM();
    }

    public void listar() {
        try {
            listarCli = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
        }
    }
    
    

// Métodos generados
    public ClienteM getCli() {
        return cli;
    }

    public void setCli(ClienteM cli) {
        this.cli = cli;
    }

    public ClienteD getDao() {
        return dao;
    }

    public void setDao(ClienteD dao) {
        this.dao = dao;
    }

    public List<ClienteM> getListarCli() {
        return listarCli;
    }

    public void setListarCli(List<ClienteM> listarCli) {
        this.listarCli = listarCli;
    }

}
