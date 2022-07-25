package controlador;

import dao.EmpleadoImpl;
import modelo.Empleado;
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
@Named(value = "empleadoC")
//@Dependent
@SessionScoped
public class EmpleadoC implements Serializable {

    private Empleado empleado;
    private EmpleadoImpl dao;
    private List<Empleado> listadoEmpleado;

    public EmpleadoC() {
        empleado = new Empleado();
        dao = new EmpleadoImpl();
        listadoEmpleado = new ArrayList<>();
    }
    
    public void listar() {
        try {
            listadoEmpleado = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
        }
    }
    
    @PostConstruct
    public void construir() {
        listar();
    }
    
}
