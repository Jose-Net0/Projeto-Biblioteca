package org.iftm.modelo_api_rest.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Id
@Entity
@Table(name="tb_item_emprestimo")
public class ItemEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="codigo_item_emprestimo")
    private Long codigoItemEmprestimo;

    @Column (name="data_devolucao_prevista")
    private Date dataDevolucaoPrevista;

    @Column (name="data_devolucao_real")
    private Date dataDevolucaoReal;
    private String status;

    @Column (name="multa_gerada")
    private Double multaGerada;

    @OneToOne
    @JoinColumn(name = "address_fk", referencedColumnName = "id")
    private Emprestimo emprestimos;

    public ItemEmprestimo() {
    }

    public ItemEmprestimo(Long codigoItemEmprestimo, Date dataDevolucaoPrevista, Date dataDevolucaoReal, String status,
            Double multaGerada) {
        this.codigoItemEmprestimo = codigoItemEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.status = status;
        this.multaGerada = multaGerada;
    }
    public Long getCodigoItemEmprestimo() {
        return codigoItemEmprestimo;
    }
    public void setCodigoItemEmprestimo(Long codigoItemEmprestimo) {
        this.codigoItemEmprestimo = codigoItemEmprestimo;
    }
    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    public Date getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }
    public void setDataDevolucaoReal(Date dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Double getMultaGerada() {
        return multaGerada;
    }
    public void setMultaGerada(Double multaGerada) {
        this.multaGerada = multaGerada;
    }

    
}