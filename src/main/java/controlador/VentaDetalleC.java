package controlador;

import dao.ClienteImpl;
import dao.ProductoImpl;
import dao.VentaDetalleImpl;
import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import modelo.VentaDetalle;
import modelo.TempVta;
import servicios.ReporteS;
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
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import lombok.Data;

@Data

//Notación CDI
@Named(value = "ventadetalleC")
//@Dependent
@SessionScoped
public class VentaDetalleC implements Serializable {

//    Venta vent;
//    Integer cantidad = 1;
    double precio = 0.0, monto = 0.0;
    Integer stockPro = 0, cantPed = 1;
    String cadenaPro = "";
    ProductoImpl daoPro;
    List<TempVta> productos;
    List<TempVta> selectedProduct;
    TempVta tempVta;
    

    private Producto producto;
    private Venta venta;
    private VentaDetalle ventadetalle;
    private VentaDetalleImpl dao;
    private List<VentaDetalle> listadoVentaDetalle;
    private List<VentaDetalle> listadoVentaDetalleFinal;

    public VentaDetalleC() {
        producto = new Producto();
        ventadetalle = new VentaDetalle();
        dao = new VentaDetalleImpl();
        venta = new Venta();
        listadoVentaDetalle = new ArrayList<>();
        listadoVentaDetalleFinal = new ArrayList<>();
        venta.setFECVEN(GregorianCalendar.getInstance().getTime());
    }

    public List<TempVta> agregarTmp() throws Exception {
        try {
            boolean repetido = false;
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getIdpro().equals(daoPro.obteneridProducto(cadenaPro))) {
                    repetido = true;
                    cadenaPro = "";
                    break;
                }
            }
            if (repetido == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Duplicado", "Ya se tiene el producto en la compra"));
            } else {
                tempVta = new TempVta();
                tempVta.setIdpro(daoPro.obteneridProducto(cadenaPro));
                tempVta.setNombre(daoPro.nombre);
                tempVta.setMarca(daoPro.marca);
                tempVta.setFamilia(daoPro.familia);
                tempVta.setDescripcion(daoPro.descripcion);
                tempVta.setPrecio(daoPro.precio);
                tempVta.setStockPro(daoPro.stockPro);
                tempVta.setCantidad(cantPed);
                tempVta.setSubTotal((double) (tempVta.getPrecio() * tempVta.getCantidad()));
                this.productos.add(tempVta);
                monto += tempVta.getSubTotal();
                limpiarCampos();
                
                
                VentaDetalle ventadet = dao.agregarFila(ventadetalle.getProducto().getIDPRO());
                ventadet.setIDPRO(this.ventadetalle.getProducto().getIDPRO());
                ventadet.setCANVENDET(this.ventadetalle.getCANVENDET());
                ventadet.setPREPRO(ventadet.getProducto().getPREPRO());  //extra
                ventadet.setPREVENDET(ventadet.getProducto().getPREPRO() * this.ventadetalle.getCANVENDET());
                ventadet.setNOMPRO(ventadet.getProducto().getNOMPRO());
                this.listadoVentaDetalle.add(ventadet);
                ventadetalle = new VentaDetalle();
                for (VentaDetalle ventaDetalle : listadoVentaDetalle) {
                    System.out.println(ventaDetalle);
                }
                sumar();
            }
        } catch (Exception e) {
            System.out.println("Error en agregarFilaDAO " + e.getMessage());
            e.printStackTrace();
        }
        return productos;
    }
    
    public void limpiarListaTemp() throws Exception {
        productos.clear();
        monto = 0.0;
    }
    
    public void limpiarCampos() throws Exception {
        cadenaPro = "";
        cantPed = 1;
    }
    
    public void anularTmp() throws Exception {
        limpiarCampos();
        productos.clear();
    }
    
    public void eliminarFilaTmp(TempVta tempVta) throws Exception {
        try {
            productos.remove(tempVta);
            sumarMontoTmp();
        } catch (Exception e) {
            System.out.println("Error en VentasC/eliminarFilaTmp " + e.getMessage());
        }
    }
    
    private void sumarMontoTmp(){
        for (TempVta tempVta: productos){
            monto += tempVta.getSubTotal();
        }
    }

    public void agregarFila() {
        try {
            VentaDetalle ventadet = dao.agregarFila(ventadetalle.getProducto().getIDPRO());
            ventadet.setIDPRO(this.ventadetalle.getProducto().getIDPRO());
            ventadet.setCANVENDET(this.ventadetalle.getCANVENDET());
            ventadet.setPREPRO(ventadet.getProducto().getPREPRO());  //extra
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
//            venta.setIDEMP(dao.obtenerIdEmpleado(venta.getIDEMP()));
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
        Calendar c1 = Calendar.getInstance();
    }

    public void anular() throws Exception {
        limpiar();
        listadoVentaDetalle.clear();
    }

    public void listar() {
        try {
            listadoVentaDetalleFinal = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void reporteVenta() throws Exception {
        ReporteS report = new ReporteS();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Venta.jasper", "Reporte de Ventas (15-07-2021).pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }

    @PostConstruct
    public void start() {
        listar();
        daoPro = new ProductoImpl();
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
    
    public void agregar() {
        try {
            VentaDetalle vd = new VentaDetalle();
            vd = dao.obtenerDatos(ventadetalle.getProducto().getIDPRO());
            listadoVentaDetalle.add(vd);
        } catch (Exception e) {
            System.out.println("Error en agregarFilaDAO " + e.getMessage());
            e.printStackTrace();
        }
    }

}
