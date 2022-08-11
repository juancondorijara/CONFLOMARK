package modelo;

import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

@Data
//@Getter
//@Setter

public class Empleado {
    
    private Integer IDEMP;
    private String NOMEMP;
    private String APEEMP;
    private String DIREMP;
    private String DNIEMP;
    private String CELEMP;
    private String CODUBI;
    private String ESTEMP;
    
    public Empleado() {
    }

    public Empleado(Integer IDEMP,String NOMEMP,String APEEMP,String DIREMP,String DNIEMP,String CELEMP,String CODUBI,String ESTEMP) {
        this.IDEMP = IDEMP;
        this.NOMEMP = NOMEMP;
        this.APEEMP = APEEMP;
        this.DIREMP = DIREMP;
        this.DNIEMP = DNIEMP;
        this.CELEMP = CELEMP;
        this.CODUBI = CODUBI;
        this.ESTEMP = ESTEMP;
    }
    
}
