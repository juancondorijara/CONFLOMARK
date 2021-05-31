package controlador;

import dao.VentaImpl;
import modelo.Venta;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Named;
//import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.enterprise.context.SessionScoped;

//Notación CDI
@Named(value = "ventaC")
//@Dependent
@SessionScoped
public class VentaC implements Serializable {

    private Venta venta;
    private VentaImpl dao;
    private List<Venta> listadoVenta;

    public VentaC() {
        venta = new Venta();
        dao = new VentaImpl();
    }

    public void registrar() throws Exception {
        try {
            venta.setCliente(dao.obtenerIdCli(venta.getCliente()));
            venta.setEmpleado(dao.obtenerIdEmp(venta.getEmpleado()));
            dao.registrar(venta);
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
            dao.modificar(venta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en ModificarC " + e.getMessage());
        }
    }

    public void eliminar(Venta venta) throws Exception {
        try {
            dao.eliminar(venta);
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
            dao.EliminarEstado(venta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Al Eliminar"));
            System.out.println("Error en eliminarEstadoC " + e.getMessage());
        }
    }

    public void restaurarEstado(Venta venta) throws Exception {
        try {
            dao.RestaurarEstado(venta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Restaurado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en restaurarEstadoC " + e.getMessage());
        }
    }

    public List<String> completeTextCliente(String query) throws SQLException, Exception {
        VentaImpl daoCliente = new VentaImpl();
        return daoCliente.autocompleteCliente(query);
    }
    
    public List<String> completeTextEmpleado(String query) throws SQLException, Exception {
        VentaImpl daoEmpleado = new VentaImpl();
        return daoEmpleado.autocompleteEmpleado(query);
    }

    public void limpiar() {
        this.venta = new Venta();
    }

    public void listar() {
        try {
            listadoVenta = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
        }
    }
    
    
    
// Getter y Setter

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public VentaImpl getDao() {
        return dao;
    }

    public void setDao(VentaImpl dao) {
        this.dao = dao;
    }

    public List<Venta> getListadoVenta() {
        return listadoVenta;
    }

    public void setListadoVenta(List<Venta> listadoVenta) {
        this.listadoVenta = listadoVenta;
    }
    
}
