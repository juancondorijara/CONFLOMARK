package controlador;

import dao.VentaDetalleImpl;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Venta;
import modelo.VentaDetalle;
import servicios.UtilToSql;

@Named(value = "ventaDetalleC")  //ManagedBean
@SessionScoped
public class VentaDetalleC implements Serializable {

    private List<VentaDetalle> listaVentaDetalle;
    private List<VentaDetalle> listaVentaDetalleFinal;
    private VentaDetalleImpl dao;
    private VentaDetalle ventadetalle;
    private Venta venta;

    public VentaDetalleC() {
        listaVentaDetalle = new ArrayList<>();
        dao = new VentaDetalleImpl();
        ventadetalle = new VentaDetalle();
        listaVentaDetalleFinal = new ArrayList<>();
        venta = new Venta();
    }

    public void agregarFila() {
        try {
            VentaDetalle ventadet = dao.agregarFila(ventadetalle.getProducto().getIDPRO());
            ventadet.setIDPRO(this.ventadetalle.getProducto().getIDPRO());
            ventadet.setCANVENDET(this.ventadetalle.getCANVENDET());
            ventadet.setPREVENDET((ventadet.getProducto().getPREPRO() + 0.50) * this.ventadetalle.getCANVENDET());
            ventadet.setNOMPRO(ventadet.getProducto().getNOMPRO());
            this.listaVentaDetalle.add(ventadet);
            ventadetalle = new VentaDetalle();
            for (VentaDetalle ventaDetalle : listaVentaDetalle) {
                System.out.println(ventaDetalle);
            }
            sumar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarFila(VentaDetalle v) {
        try {
            listaVentaDetalle.remove(v);
            sumar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarVenta() {
        try {
            dao.registrar(venta);
            int idven = dao.obtenerUltimoId();
            dao.registroMultiple(listaVentaDetalle, idven);
            listaVentaDetalle.clear();
            listar();
            venta = new Venta();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar(){
        try {
            listaVentaDetalleFinal = dao.listar();
        } catch (Exception e) {
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
        double precioTotal = 0;
        for (VentaDetalle ventaDetalle : listaVentaDetalle) {
            precioTotal += ventaDetalle.getPREVENDET();
        }
        System.out.println(precioTotal);
        venta.setTOTVEN(precioTotal);
    }

//    public void obtenerFecha() {
//        Timestamp fecha = UtilToSql.convert(venta.getFECENTVEN());
//        System.out.println(venta.getFECENTVEN());
//        System.out.println(fecha);
//    }

    public List<VentaDetalle> getListaVentaDetalle() {
        return listaVentaDetalle;
    }

    public void setListaVentaDetalle(List<VentaDetalle> listaVentaDetalle) {
        this.listaVentaDetalle = listaVentaDetalle;
    }

    public VentaDetalle getVentadetalle() {
        return ventadetalle;
    }

    public void setVentadetalle(VentaDetalle ventadetalle) {
        this.ventadetalle = ventadetalle;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<VentaDetalle> getListaVentaDetalleFinal() {
        return listaVentaDetalleFinal;
    }

    public void setListaVentaDetalleFinal(List<VentaDetalle> listaVentaDetalleFinal) {
        this.listaVentaDetalleFinal = listaVentaDetalleFinal;
    }

}
