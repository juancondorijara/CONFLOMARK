package controlador;

//import dao.EmpleadoImpl;
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

//Notación CDI
@Named(value = "empleadoC")
//@Dependent
@SessionScoped
public class EmpleadoC implements Serializable {

    private Empleado empleado;
//    private ClienteImpl dao;
    private List<Empleado> listadoEmpleado;
//    private String nombre;

    public EmpleadoC() {
        empleado = new Empleado();
//        dao = new ClienteImpl();
        listadoEmpleado = new ArrayList<>();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getListadoEmpleado() {
        return listadoEmpleado;
    }

    public void setListadoEmpleado(List<Empleado> listadoEmpleado) {
        this.listadoEmpleado = listadoEmpleado;
    }
    
}
