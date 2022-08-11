package controlador;

import dao.FamiliaImpl;
import modelo.Familia;
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
@Named(value = "familiaC")
//@Dependent
@SessionScoped
public class FamiliaC implements Serializable {

    private Familia familia;
    private FamiliaImpl dao;
    private List<Familia> listadoFamilia;
//    private String nombre;

    public FamiliaC() {
        familia = new Familia();
        dao = new FamiliaImpl();
        listadoFamilia = new ArrayList<>();
    }
    
    public void listar() {
        try {
            listadoFamilia = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
        }
    }
    
    @PostConstruct
    public void construir() {
        listar();
    }

}
