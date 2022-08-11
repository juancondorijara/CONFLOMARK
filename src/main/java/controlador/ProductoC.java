package controlador;

import dao.ProductoImpl;
import modelo.Producto;
import modelo.Familia;
import servicios.ReporteS;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;

//Notación CDI
@Named(value = "productoC")
//@Dependent
@SessionScoped
public class ProductoC implements Serializable{

    private Familia familia;
    private Producto producto;
    private ProductoImpl dao;
    private List<Producto> listadoProducto;
    
    public ProductoC() {
        producto = new Producto();
        dao = new ProductoImpl();
        familia = new Familia();
        listadoProducto = new ArrayList<>();
    }
    
    public void registrar() throws Exception{
        try {
//            producto.setCODFAM(dao.obtenerCodigoFamilia(producto.getCODFAM()));
//            producto.setCODPROV(dao.obtenerCodigoProveeedor(producto.getCODPROV()));
            dao.registrar(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Falta Completar Datos"));
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }
    
    public void modificar() throws Exception {
        try {
//            producto.setCODFAM(dao.obtenerCodigoFamilia(producto.getCODFAM()));
//            producto.setCODPROV(dao.obtenerCodigoProveeedor(producto.getCODPROV()));
            dao.modificar(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Al Modificar"));
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void eliminar(Producto producto) throws Exception{
        try {            
            dao.eliminar(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarC " + e.getMessage());
        }
    }
    
    public void eliminarEstado() throws Exception{
        try {            
            dao.EliminarEstado(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarEstadoC " + e.getMessage());
        }
    }
    
    public void restaurarEstado(Producto producto) throws Exception{
        try {            
            dao.RestaurarEstado(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Restaurado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en restaurarEstadoC " + e.getMessage());
        }
    }
    
    public List<String> completeTextFamilia(String query) throws SQLException, Exception {
        ProductoImpl daoFamilia = new ProductoImpl();
        return daoFamilia.autocompleteFamilia(query);
    }
    
    public List<String> completeTextProveedor(String query) throws SQLException, Exception {
        ProductoImpl daoProveedor = new ProductoImpl();
        return daoProveedor.autocompleteProveedor(query);
    }
    
    public void limpiar(){
        producto = new Producto();
    }
    
    public void listar(){
        try {
            listadoProducto = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }
    
    public void reporteProducto() throws Exception {
        ReporteS report = new ReporteS();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Producto.jasper", "Reporte de Productos (15-07-2021).pdf");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PDF GENERADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR AL GENERAR PDF", null));
            throw e;
        }
    }
    
    @PostConstruct
    public void construir() {
        listar();
    }
    
// Getter y Setter

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductoImpl getDao() {
        return dao;
    }

    public void setDao(ProductoImpl dao) {
        this.dao = dao;
    }

    public List<Producto> getListadoProducto() {
        return listadoProducto;
    }

    public void setListadoProducto(List<Producto> listadoProducto) {
        this.listadoProducto = listadoProducto;
    }

}
