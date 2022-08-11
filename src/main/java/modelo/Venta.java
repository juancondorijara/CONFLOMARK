package modelo;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.validation.constraints.Future;
import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

@Data
//@Getter
//@Setter

public class Venta {
    private Integer IDVEN;
    private Date FECVEN = GregorianCalendar.getInstance().getTime();
    private Cliente cliente = new Cliente();
    private Empleado empleado = new Empleado();
    private String IDEMP;
    private Double TOTVEN;
    private String ESTVEN;
    private Boolean TOGGLESWICHT = false;

    public Venta() {
    }

    public Venta(Integer IDVEN, Date FECVEN, String IDEMP, Double TOTVEN, String ESTVEN, Cliente cliente, Empleado empleado) {
        this.IDVEN = IDVEN;
        this.FECVEN = FECVEN;
        this.IDEMP = IDEMP;
        this.TOTVEN = TOTVEN;
        this.ESTVEN = ESTVEN;
        this.cliente = cliente;
        this.empleado = empleado;
    }
    
}