/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProductoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Producto;

@Named
@RequestScoped
public class ProductoC {

    private Producto producto;
    private ProductoImpl dao;
    private List<Producto> listaProductos;

    public ProductoC() {
        producto = new Producto();
        dao = new ProductoImpl();
        listaProductos = new ArrayList<>();
    }

    public void registrar() {
        try {
            dao.registrar(producto);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Registro fallido"));
            System.out.println("error en registrarProductoC" + e.getMessage());
        }
    }

    public void listar() {
        try {
            listaProductos = dao.listar();
        } catch (Exception e) {
            System.out.println("error en listarProductoC" + e.getMessage());
        }
    }

    @PostConstruct
    public void construir() {
        listar();
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

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
