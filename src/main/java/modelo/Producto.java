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
    private Familia familia = new Familia();
    private String CODFAM;
    private String DESPRO;
    private Double PREPRO;
    private Integer STOPRO;
    private Date VENPRO;
    private String CODPROV;
    private String ESTPRO;
    
    public Producto() {
    }

    public Producto(Integer IDPRO,String NOMPRO,String MARPRO,Familia familia,String CODFAM,String DESPRO,Double PREPRO,Integer STOPRO,Date VENPRO,String CODPROV,String ESTPRO) {
        this.IDPRO = IDPRO;
        this.NOMPRO = NOMPRO;
        this.MARPRO = MARPRO;
        this.familia = familia;
        this.CODFAM = CODFAM;
        this.DESPRO = DESPRO;
        this.PREPRO = PREPRO;
        this.STOPRO = STOPRO;
        this.VENPRO = VENPRO;
        this.CODPROV = CODPROV;
        this.ESTPRO = ESTPRO;
    }
    
    @Override
    public String toString() {
        return "Producto{" + "IDPRO=" + IDPRO + ",NOMPRO=" + NOMPRO + ",MARPRO=" + MARPRO + ",CODFAM=" + CODFAM + ",DESPRO=" + DESPRO + ",PREPRO=" + PREPRO + ",STOPRO=" + STOPRO + ",VENPRO=" + VENPRO + ",CODPROV=" + CODPROV + ",ESTPRO=" + ESTPRO + '}';
    }
    
}
