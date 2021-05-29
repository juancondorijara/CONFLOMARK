package controlador;

import dao.ClienteImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Cliente;

@Named
@RequestScoped
public class ClienteC{

    private ClienteImpl dao;
    private Cliente cliente;
    private List<Cliente> listaclientes;
    private String nombre;

    public ClienteC() {
        cliente = new Cliente();
        dao = new ClienteImpl();
        listaclientes = new ArrayList<>();
    }

    public void registrar() {
        try {
            dao.registrar(cliente);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Registro fallido"));
            System.out.println("error en registrarClienteC" + e.getMessage());
        }
    }

    public void listar() {
        try {
            listaclientes = dao.listar();
        } catch (Exception e) {
            System.out.println("error en listarClienteC" + e.getMessage());
        }
    }
    
    public void limpiar() {
        try {
            cliente = new Cliente();
        } catch (Exception e) {
            System.out.println("error en limpiarClienteC" + e.getMessage());
        }
    }
    
    @PostConstruct
    public void construir() {
        listar();
    }

    public ClienteImpl getDao() {
        return dao;
    }

    public void setDao(ClienteImpl dao) {
        this.dao = dao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cliente> getListaclientes() {
        return listaclientes;
    }

    public void setListaclientes(List<Cliente> listaclientes) {
        this.listaclientes = listaclientes;
    }
}
