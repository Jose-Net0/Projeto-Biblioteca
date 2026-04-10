package org.iftm.modelo_api_rest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

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

    @ManyToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "codigo_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "emprestimo", fetch = FetchType.LAZY)    
    private List<ItemEmprestimo> itemEmprestimo = new ArrayList<>();

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
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<ItemEmprestimo> getItemEmprestimo() {
        return itemEmprestimo;
    }
    public void setItemEmprestimo(List<ItemEmprestimo> itemEmprestimo) {
        this.itemEmprestimo = itemEmprestimo;
    }
    
}