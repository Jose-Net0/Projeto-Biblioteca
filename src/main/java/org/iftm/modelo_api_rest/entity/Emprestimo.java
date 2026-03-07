package org.iftm.modelo_api_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name="tb_emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="codigo_emprestimo")
    private Long codigoEmprestimo;

    @Column (name="data_emprestimo")
    private Date dataEmprestimo;
    @Column (name="data_devolucao_prevista")
    private Date dataDevolucaoPrevista;
    public Emprestimo() {
    }
    public Emprestimo(Long codigoEmprestimo, Date dataEmprestimo, Date dataDevolucaoPrevista) {
        this.codigoEmprestimo = codigoEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;

    }
    public Long getCodigoEmprestimo() {
        return codigoEmprestimo;
    }
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    public void setCodigoEmprestimo(Long codigoEmprestimo) {
        this.codigoEmprestimo = codigoEmprestimo;
    }
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    
}