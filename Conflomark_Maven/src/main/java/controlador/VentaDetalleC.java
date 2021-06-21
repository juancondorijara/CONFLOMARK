package controlador;

import dao.VentaDetalleImpl;
import modelo.Venta;
import modelo.VentaDetalle;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Named;
//import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import java.sql.Date;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
//import servicios.UtilToSql;
import javax.enterprise.context.SessionScoped;
import lombok.Data;
@Data

//Notación CDI
@Named(value = "ventadetalleC")
//@Dependent
@SessionScoped
public class VentaDetalleC implements Serializable {
    
//    Venta vent;
//    Integer cantidad = 1;

    private Venta venta;
    private VentaDetalle ventadetalle;
    private VentaDetalleImpl dao;
    private List<VentaDetalle> listadoVentaDetalle;
    private List<VentaDetalle> listadoVentaDetalleFinal;

    public VentaDetalleC() {
        ventadetalle = new VentaDetalle();
        dao = new VentaDetalleImpl();
        venta = new Venta();
        listadoVentaDetalle = new ArrayList<>();
        listadoVentaDetalleFinal = new ArrayList<>();
//        vent = new Venta();
        venta.setFECVEN(GregorianCalendar.getInstance().getTime());
    }
    
    public void agregarFila() {
        try {
            VentaDetalle ventadet = dao.agregarFila(ventadetalle.getProducto().getIDPRO());
            ventadet.setIDPRO(this.ventadetalle.getProducto().getIDPRO());
            ventadet.setCANVENDET(this.ventadetalle.getCANVENDET());
//            ventadet.setCANVENDET(cantidad);  //prueba 1
//            ventadet.setPREPRO(ventadet.getProducto().getPREPRO());  //extra
            ventadet.setPREVENDET(ventadet.getProducto().getPREPRO() * this.ventadetalle.getCANVENDET());
            ventadet.setNOMPRO(ventadet.getProducto().getNOMPRO());
            this.listadoVentaDetalle.add(ventadet);
            ventadetalle = new VentaDetalle();
            for (VentaDetalle ventaDetalle : listadoVentaDetalle) {
                System.out.println(ventaDetalle);
            }
            sumar();
        } catch (Exception e) {
            System.out.println("Error en agregarFilaDAO " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void eliminarFila(VentaDetalle v) {
        try {
            listadoVentaDetalle.remove(v);
            sumar();
        } catch (Exception e) {
            System.out.println("Error en eliminarFilaDAO " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void registrarVenta() {
        try {
            venta.setIDEMP(dao.obtenerIdEmpleado(venta.getIDEMP()));
            dao.registrar(venta);
            int idven = dao.obtenerUltimoId();
            dao.registroMultiple(listadoVentaDetalle, idven);
            listadoVentaDetalle.clear();
            listar();
            venta = new Venta();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void modificar() throws Exception {
//        try {
//            dao.modificar(ventadetalle);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
//            limpiar();
//            listar();
//        } catch (Exception e) {
//            System.out.println("Error en ModificarC " + e.getMessage());
//        }
//    }
//
//    public void eliminar(VentaDetalle ventadetalle) throws Exception {
//        try {
//            dao.eliminar(ventadetalle);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
//            limpiar();
//            listar();
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Al Eliminar"));
//            System.out.println("Error en EliminarC " + e.getMessage());
//        }
//    }

//    public void eliminarEstado() throws Exception {
//        try {
//            dao.EliminarEstado(ventadetalle);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
//            limpiar();
//            listar();
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Al Eliminar"));
//            System.out.println("Error en eliminarEstadoC " + e.getMessage());
//        }
//    }
//
//    public void restaurarEstado(VentaDetalle ventadetalle) throws Exception {
//        try {
//            dao.RestaurarEstado(ventadetalle);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Restaurado con Exito"));
//            limpiar();
//            listar();
//        } catch (Exception e) {
//            System.out.println("Error en restaurarEstadoC " + e.getMessage());
//        }
//    }

//    public List<String> completeTextVenta(String query) throws SQLException, Exception {
//        VentaDetalleImpl daoVenta = new VentaDetalleImpl();
//        return daoVenta.autocompleteVenta(query);
//    }
//    
//    public List<String> completeTextProducto(String query) throws SQLException, Exception {
//        VentaDetalleImpl daoProducto = new VentaDetalleImpl();
//        return daoProducto.autocompleteProducto(query);
//    }
    
    public List<String> completeTextEmpleado(String query) throws SQLException, Exception {
        VentaDetalleImpl daoEmpleado = new VentaDetalleImpl();
        return daoEmpleado.autocompleteEmpleado(query);
    }

    public void limpiar() {
        this.ventadetalle = new VentaDetalle();
//        ventadetalle.setCANVENDET("");
        this.venta = new Venta();
    }
    
    public void anular() throws Exception {
        limpiar();
        listadoVentaDetalle.clear();
    }
    
    public void listar(){
        try {
            listadoVentaDetalleFinal = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    public void start(){
        listar();
    }
    
    public void addMessage() {
        String summary = venta.getTOGGLESWICHT() ? "Activado" : "Desactivado";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
    
    public void sumar() {
        double precioTotal = 0.0;
        for (VentaDetalle ventaDetalle : listadoVentaDetalle) {
            precioTotal += ventaDetalle.getPREVENDET();
        }
        System.out.println(precioTotal);
        venta.setTOTVEN(precioTotal);
    }
 
}
