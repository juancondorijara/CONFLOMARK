package modelo;

//import java.util.Date;
import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

@Data
//@Getter
//@Setter

public class VentaDetalle {
    
    private Integer CANVENDET = 1;
    private Double PREVENDET;
    private int IDPRO;
    private String NOMPRO;
    private Double PREPRO; //extra
    private int IDVEN;
    private String NOMCLI;
    private Producto producto = new Producto();
    private Venta venta = new Venta();
    private String ESTVENDET;

    public VentaDetalle() {
    }

    public VentaDetalle(int CANVENDET, Double PREVENDET, Double PREPRO, int IDPRO, int IDVEN, Producto producto, Venta venta, String NOMPRO, String NOMCLI, String ESTVENDET) {
        this.CANVENDET = CANVENDET;
        this.PREVENDET = PREVENDET;
        this.IDPRO = IDPRO;
        this.NOMPRO = NOMPRO;
        this.PREPRO = PREPRO;
        this.IDVEN = IDVEN;
        this.NOMCLI = NOMCLI;
        this.producto = producto;
        this.venta = venta;
        this.ESTVENDET = ESTVENDET;
    }
    
    @Override
    public String toString() {
        return "VentaDetalle{" + "CANVENDET=" + CANVENDET + ", PREVENDET=" + PREVENDET + ", IDPRO=" + IDPRO + ", NOMPRO=" + PREPRO + ", PREPRO=" + NOMPRO + ", IDVEN=" + IDVEN + ", producto=" + producto + ",ESTVENDET=" + ESTVENDET + ", venta=" + venta + '}';
    }
   
}