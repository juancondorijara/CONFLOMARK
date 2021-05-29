/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author jesus
 */
public class Producto {

    private Integer IDPRO;
    private String NOMPRO;
    private Double PREPRO;
    private Date FECREGPRO;
    private String ESTPRO;

    public Producto() {
    }

    public Producto(Integer IDPRO, String NOMPRO, Double PREPRO, Date FECREGPRO, String ESTPRO) {
        this.IDPRO = IDPRO;
        this.NOMPRO = NOMPRO;
        this.PREPRO = PREPRO;
        this.FECREGPRO = FECREGPRO;
        this.ESTPRO = ESTPRO;
    }

    public Integer getIDPRO() {
        return IDPRO;
    }

    public void setIDPRO(Integer IDPRO) {
        this.IDPRO = IDPRO;
    }

    public String getNOMPRO() {
        return NOMPRO;
    }

    public void setNOMPRO(String NOMPRO) {
        this.NOMPRO = NOMPRO;
    }

    public Double getPREPRO() {
        return PREPRO;
    }

    public void setPREPRO(Double PREPRO) {
        this.PREPRO = PREPRO;
    }

    public Date getFECREGPRO() {
        return FECREGPRO;
    }

    public void setFECREGPRO(Date FECREGPRO) {
        this.FECREGPRO = FECREGPRO;
    }

    public String getESTPRO() {
        return ESTPRO;
    }

    public void setESTPRO(String ESTPRO) {
        this.ESTPRO = ESTPRO;
    }

    @Override
    public String toString() {
        return "Producto{" + "IDPRO=" + IDPRO + ", NOMPRO=" + NOMPRO + ", PREPRO=" + PREPRO + ", FECREGPRO=" + FECREGPRO + ", ESTPRO=" + ESTPRO + '}';
    }

}
