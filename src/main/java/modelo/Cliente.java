package modelo;

import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;

@Data
//@Getter
//@Setter

public class Cliente {
    
    private Integer IDCLI;
    private String NOMCLI;
    private String APECLI;
    private String DIRCLI;
    private String CELCLI;
    private String CODUBI;
    private String ESTCLI;
    
    public Cliente() {
    }

    public Cliente(Integer IDCLI,String NOMCLI,String APECLI,String DIRCLI,String CELCLI,String CODUBI,String ESTCLI) {
        this.IDCLI = IDCLI;
        this.NOMCLI = NOMCLI;
        this.APECLI = APECLI;
        this.DIRCLI = DIRCLI;
        this.CELCLI = CELCLI;
        this.CODUBI = CODUBI;
        this.ESTCLI = ESTCLI;
    }
    
}
