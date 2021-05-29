/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jesus
 */
public class VentaDetalle {

    private Integer CANVENDET;
    private Double PREVENDET;
    private Integer IDPRO;
    private String NOMPRO;
    private Integer IDVEN;
    private String NOMCLI;
    private Producto producto = new Producto();
    private Venta venta = new Venta();

    public VentaDetalle() {
    }

    public VentaDetalle(Integer CANVENDET, Double PREVENDET, Integer IDPRO, Integer IDVEN, Producto producto, Venta venta, String NOMPRO, String NOMCLI) {
        this.CANVENDET = CANVENDET;
        this.PREVENDET = PREVENDET;
        this.IDPRO = IDPRO;
        this.IDVEN = IDVEN;
        this.producto = producto;
        this.venta = venta;
        this.NOMPRO = NOMPRO;
        this.NOMCLI = NOMCLI;
    }

    public Integer getCANVENDET() {
        return CANVENDET;
    }

    public void setCANVENDET(Integer CANVENDET) {
        this.CANVENDET = CANVENDET;
    }

    public Double getPREVENDET() {
        return PREVENDET;
    }

    public void setPREVENDET(Double PREVENDET) {
        this.PREVENDET = PREVENDET;
    }

    public Integer getIDPRO() {
        return IDPRO;
    }

    public void setIDPRO(Integer IDPRO) {
        this.IDPRO = IDPRO;
    }

    public Integer getIDVEN() {
        return IDVEN;
    }

    public void setIDVEN(Integer IDVEN) {
        this.IDVEN = IDVEN;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public String getNOMPRO() {
        return NOMPRO;
    }

    public void setNOMPRO(String NOMPRO) {
        this.NOMPRO = NOMPRO;
    }

    @Override
    public String toString() {
        return "VentaDetalle{" + "CANVENDET=" + CANVENDET + ", PREVENDET=" + PREVENDET + ", IDPRO=" + IDPRO + ", NOMPRO=" + NOMPRO + ", IDVEN=" + IDVEN + ", producto=" + producto + ", venta=" + venta + '}';
    }

    public String getNOMCLI() {
        return NOMCLI;
    }

    public void setNOMCLI(String NOMCLI) {
        this.NOMCLI = NOMCLI;
    }

}
