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
public class Cliente {

    private Integer IDCLI;
    private String NOMCLI;
    private String APECLI;
    private String DNICLI;
    private String ESTCLI;

    public Cliente() {
    }

    public Cliente(Integer IDCLI, String NOMCLI, String APECLI, String DNICLI,String ESTCLI) {
        this.IDCLI = IDCLI;
        this.NOMCLI = NOMCLI;
        this.APECLI = APECLI;
        this.DNICLI = DNICLI;
        this.ESTCLI =ESTCLI;
    }

    public Integer getIDCLI() {
        return IDCLI;
    }

    public void setIDCLI(Integer IDCLI) {
        this.IDCLI = IDCLI;
    }

    public String getNOMCLI() {
        return NOMCLI;
    }

    public void setNOMCLI(String NOMCLI) {
        this.NOMCLI = NOMCLI;
    }

    public String getAPECLI() {
        return APECLI;
    }

    public void setAPECLI(String APECLI) {
        this.APECLI = APECLI;
    }

    public String getDNICLI() {
        return DNICLI;
    }

    public void setDNICLI(String DNICLI) {
        this.DNICLI = DNICLI;
    }

    public String getESTCLI() {
        return ESTCLI;
    }

    public void setESTCLI(String ESTCLI) {
        this.ESTCLI = ESTCLI;
    }

}
