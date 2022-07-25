package modelo;

import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

@Data
//@Getter
//@Setter

public class Familia {
    
    private String CODFAM;
    private String NOMFAM;
    private String SUBFAM;
    
    public Familia() {
    }

    public Familia(String CODFAM, String NOMFAM, String SUBFAM) {
        this.CODFAM = CODFAM;
        this.NOMFAM = NOMFAM;
        this.SUBFAM = SUBFAM;
    }
    
}
