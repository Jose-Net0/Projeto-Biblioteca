package org.iftm.modelo_api_rest.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoBloqueio;
    private String motivo;
    private Date dataInicio;
    private Date dataFim;
    
    public Bloqueio(long codigoBloqueio, String motivo, Date dataInicio, Date dataFim) {
        this.codigoBloqueio = codigoBloqueio;
        this.motivo = motivo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public long getCodigoBloqueio() {
        return codigoBloqueio;
    }

    public String getMotivo() {
        return motivo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setCodigoBloqueio(int codigoBloqueio) {
        this.codigoBloqueio = codigoBloqueio;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
  
    
}
