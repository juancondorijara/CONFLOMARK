package modelo;

//import java.sql.Date;
import java.util.Date;
import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

@Data
//@Getter
//@Setter

public class Producto {
    
    private Integer IDPRO;
    private String NOMPRO;
    private String MARPRO;
    private String CODFAM;
    private String DESPRO;
    private Double PREPRO;
    private Date VENPRO;
    private String CODPROV;
    private String ESTPRO;
    
    public Producto() {
    }

    public Producto(Integer IDPRO,String NOMPRO,String MARPRO,String CODFAM,String DESPRO,Double PREPRO,Date VENPRO,String CODPROV,String ESTPRO) {
        this.IDPRO = IDPRO;
        this.NOMPRO = NOMPRO;
        this.MARPRO = MARPRO;
        this.CODFAM = CODFAM;
        this.DESPRO = DESPRO;
        this.PREPRO = PREPRO;
        this.VENPRO = VENPRO;
        this.CODPROV = CODPROV;
        this.ESTPRO = ESTPRO;
    }
    
    @Override
    public String toString() {
        return "Producto{" + "IDPRO=" + IDPRO + ",NOMPRO=" + NOMPRO + ",MARPRO=" + MARPRO + ",CODFAM=" + CODFAM + ",DESPRO=" + DESPRO + ",PREPRO=" + PREPRO + ",VENPRO=" + VENPRO + ",CODPROV=" + CODPROV + ",ESTPRO=" + ESTPRO + '}';
    }
    
}
