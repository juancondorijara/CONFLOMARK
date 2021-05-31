package controlador;

import dao.VentaDetalleImpl;
import modelo.VentaDetalle;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Named;
//import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.enterprise.context.SessionScoped;

//Notación CDI
@Named(value = "ventadetalleC")
//@Dependent
@SessionScoped
public class VentaDetalleC implements Serializable {

    private VentaDetalle ventadetalle;
    private VentaDetalleImpl dao;
    private List<VentaDetalle> listadoVentaDetalle;

    public VentaDetalleC() {
        ventadetalle = new VentaDetalle();
        dao = new VentaDetalleImpl();
    }

    public void registrar() throws Exception {
        try {
            ventadetalle.setVenta(dao.obtenerIdVen(ventadetalle.getVenta()));
            ventadetalle.setProducto(dao.obtenerIdPro(ventadetalle.getProducto()));
            dao.registrar(ventadetalle);
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
            dao.modificar(ventadetalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en ModificarC " + e.getMessage());
        }
    }

    public void eliminar(VentaDetalle ventadetalle) throws Exception {
        try {
            dao.eliminar(ventadetalle);
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
            dao.EliminarEstado(ventadetalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Al Eliminar"));
            System.out.println("Error en eliminarEstadoC " + e.getMessage());
        }
    }

    public void restaurarEstado(VentaDetalle ventadetalle) throws Exception {
        try {
            dao.RestaurarEstado(ventadetalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Restaurado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en restaurarEstadoC " + e.getMessage());
        }
    }

    public List<String> completeTextVenta(String query) throws SQLException, Exception {
        VentaDetalleImpl daoVenta = new VentaDetalleImpl();
        return daoVenta.autocompleteVenta(query);
    }
    
    public List<String> completeTextProducto(String query) throws SQLException, Exception {
        VentaDetalleImpl daoProducto = new VentaDetalleImpl();
        return daoProducto.autocompleteProducto(query);
    }

    public void limpiar() {
        this.ventadetalle = new VentaDetalle();
    }

    public void listar() {
        try {
            listadoVentaDetalle = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
        }
    }
    
    
    
// Getter y Setter

    public VentaDetalle getVentadetalle() {
        return ventadetalle;
    }

    public void setVentadetalle(VentaDetalle ventadetalle) {
        this.ventadetalle = ventadetalle;
    }

    public VentaDetalleImpl getDao() {
        return dao;
    }

    public void setDao(VentaDetalleImpl dao) {
        this.dao = dao;
    }

    public List<VentaDetalle> getListadoVentaDetalle() {
        return listadoVentaDetalle;
    }

    public void setListadoVentaDetalle(List<VentaDetalle> listadoVentaDetalle) {
        this.listadoVentaDetalle = listadoVentaDetalle;
    }
 
}
