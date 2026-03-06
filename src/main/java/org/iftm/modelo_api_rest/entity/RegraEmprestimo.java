package org.iftm.modelo_api_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RegraEmprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoRegraEmprestimo;
    private int prazoDias;
    private double multaPorDia;
    private double multaMax;
    private int limiteEmprestimos;
    private boolean ativa;
    
    public RegraEmprestimo(long codigoRegraEmprestimo, int prazoDias, double multaPorDia, double multaMax, int limiteEmprestimos, boolean ativa) {
        this.codigoRegraEmprestimo = codigoRegraEmprestimo;
        this.prazoDias = prazoDias;
        this.multaPorDia = multaPorDia;
        this.multaMax = multaMax;
        this.limiteEmprestimos = limiteEmprestimos;
        this.ativa = ativa; 
    }

    public long getCodigoRegraEmprestimo() {
        return codigoRegraEmprestimo;
    }

    public int getPrazoDias() {
        return prazoDias;
    }

    public double getMultaPorDia() {
        return multaPorDia;
    }

    public double getMultaMax() {
        return multaMax;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setCodigoRegraEmprestimo(int codigoRegraEmprestimo) {
        this.codigoRegraEmprestimo = codigoRegraEmprestimo;
    }

    public void setPrazoDias(int prazoDias) {
        this.prazoDias = prazoDias;
    }

    public void setMultaPorDia(double multaPorDia) {
        this.multaPorDia = multaPorDia;
    }

    public void setMultaMax(double multaMax) {
        this.multaMax = multaMax;
    }

    public void setLimiteEmprestimos(int limiteEmprestimos) {
        this.limiteEmprestimos = limiteEmprestimos;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}