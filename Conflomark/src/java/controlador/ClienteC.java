package controlador;

import dao.ClienteD;
import modelo.ClienteM;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "clienteC")
@Dependent
public class ClienteC implements Serializable{

    private ClienteM cli;
    private ClienteD dao;
    private List<ClienteM> listarCli;
    
    public ClienteC() {
        cli = new ClienteM();
        dao = new ClienteD();
    }
    
    public void registrar() throws Exception{
        try {
            dao.registrar(cli);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            
        }
    }
    
    public void limpiar(){
        cli = new ClienteM();
    }
    
    public void listar(){
        try {
            listarCli = dao.listarTodos();
        } catch (Exception e) {
            
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
