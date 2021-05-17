package controlador;

import dao.ProductosImpl;
import modelo.Productos;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import java.sql.SQLException;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.enterprise.context.SessionScoped;

//Notación CDI
@Named(value = "productosC")
//@Dependent
@SessionScoped
public class ProductosC implements Serializable{

    private Productos productos;
    private ProductosImpl dao;
    private List<Productos> listadoProductos;
    
    public ProductosC() {
        productos = new Productos();
        dao = new ProductosImpl();
    }
    
    public void registrar() throws Exception{
        try {
            productos.setFamilia(dao.obtenerCodigoFamilia(productos.getFamilia()));
            dao.registrar(productos);
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
            dao.modificar(productos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void eliminar(Productos productos) throws Exception{
        try {            
            dao.eliminar(productos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarC " + e.getMessage());
        }
    }
    
    public void eliminarEstado(Productos productos) throws Exception{
        try {            
            dao.EliminarEstado(productos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarEstadoC " + e.getMessage());
        }
    }
    
    public void restaurarEstado(Productos productos) throws Exception{
        try {            
            dao.RestaurarEstado(productos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Restaurado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en restaurarEstadoC " + e.getMessage());
        }
    }
    
    public List<String> completeTextFamilia(String query) throws SQLException, Exception {
        ProductosImpl daoFamilia = new ProductosImpl();
        return daoFamilia.autocompleteFamilia(query);
    }  
    
    public void limpiar(){
        productos = new Productos();
    }
    
    public void listar(){
        try {
            listadoProductos = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }
    
    
    
// Getter y Setter

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public ProductosImpl getDao() {
        return dao;
    }

    public void setDao(ProductosImpl dao) {
        this.dao = dao;
    }

    public List<Productos> getListadoProductos() {
        return listadoProductos;
    }

    public void setListadoProductos(List<Productos> listadoProductos) {
        this.listadoProductos = listadoProductos;
    }

}
