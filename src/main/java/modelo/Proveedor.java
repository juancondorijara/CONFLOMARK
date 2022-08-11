package modelo;

import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

@Data
//@Getter
//@Setter

public class Proveedor {
    
    private String CODPROV;
    private String NOMPROV;
    private String DIRPROV;
    private String CELPROV;
    private String RUCPROV;
    private String COMPROV;
    private String EMAPROV;
    private String CODUBI;
    private String ESTPROV;
    
    public Proveedor() {
    }

    public Proveedor(String CODPROV,String NOMPROV,String DIRPROV,String CELPROV,String RUCPROV,String COMPROV,String EMAPROV,String CODUBI,String ESTPROV) {
        this.CODPROV = CODPROV;
        this.NOMPROV = NOMPROV;
        this.DIRPROV = DIRPROV;
        this.CELPROV = CELPROV;
        this.RUCPROV = RUCPROV;
        this.COMPROV = COMPROV;
        this.EMAPROV = EMAPROV;
        this.CODUBI = CODUBI;
        this.ESTPROV = ESTPROV;
    }
    
}
