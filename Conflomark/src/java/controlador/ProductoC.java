package controlador;

import dao.ProductoD;
import modelo.ProductoM;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "productoC")
@Dependent
public class ProductoC implements Serializable{

    private ProductoM pro;
    private ProductoD dao;
    private List<ProductoM> listarPro;
    
    public ProductoC() {
        pro = new ProductoM();
        dao = new ProductoD();
    }
    
    public void registrar() throws Exception{
        try {
            dao.registrar(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            
        }
    }
    
    public void limpiar(){
        pro = new ProductoM();
    }
    
    public void listar(){
        try {
            listarPro = dao.listarTodos();
        } catch (Exception e) {
            
        }
    }
    
    
    
// Métodos generados   

    public ProductoM getPro() {
        return pro;
    }

    public void setPro(ProductoM pro) {
        this.pro = pro;
    }

    public ProductoD getDao() {
        return dao;
    }

    public void setDao(ProductoD dao) {
        this.dao = dao;
    }

    public List<ProductoM> getListarPro() {
        return listarPro;
    }

    public void setListarPro(List<ProductoM> listarPro) {
        this.listarPro = listarPro;
    }
    
}
