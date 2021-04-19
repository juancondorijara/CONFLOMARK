package controlador;

import dao.PersoneroImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Personero;


@Named(value = "personeroC")
@SessionScoped
public class PersoneroC implements Serializable {

    private Personero per;
    private PersoneroImpl dao;
    private List<Personero> listarPer;
    
    public PersoneroC() {
        per = new Personero();
        dao = new PersoneroImpl();
    }
    
    public void registrar() throws Exception{
        try {
            dao.registrar(per);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            
        }
    }
    
    public void limpiar(){
        per = new Personero();
    }
    
    public void listar(){
        try {
            listarPer = dao.listarTodos();
        } catch (Exception e) {
            
        }
    }
    
    
    
    
    
    
    
    // métodos generados

    public Personero getPer() {
        return per;
    }

    public void setPer(Personero per) {
        this.per = per;
    }

    public PersoneroImpl getDao() {
        return dao;
    }

    public void setDao(PersoneroImpl dao) {
        this.dao = dao;
    }

    public List<Personero> getListarPer() {
        return listarPer;
    }

    public void setListarPer(List<Personero> listarPer) {
        this.listarPer = listarPer;
    }
    
    
    
}
