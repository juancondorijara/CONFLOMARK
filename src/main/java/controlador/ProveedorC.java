package controlador;

import dao.ProveedorImpl;
import modelo.Proveedor;
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
import lombok.Data;
@Data

//Notación CDI
@Named(value = "proveedorC")
//@Dependent
@SessionScoped
public class ProveedorC implements Serializable {

    private Proveedor proveedor;
    private ProveedorImpl dao;
    private List<Proveedor> listadoProveedor;

    public ProveedorC() {
        proveedor = new Proveedor();
        dao = new ProveedorImpl();
        listadoProveedor = new ArrayList<>();
    }
    
    public void listar() {
        try {
            listadoProveedor = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
        }
    }
    
    @PostConstruct
    public void construir() {
        listar();
    }
    
}
