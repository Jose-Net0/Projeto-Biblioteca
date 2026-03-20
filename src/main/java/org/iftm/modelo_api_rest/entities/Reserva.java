package com.bibliotecar.entity;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="tb_reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo_reserva")
    private Long codigoReserva;

    @Column(name="data_reserva")
    private Date dataReserva;

    private String status;

    @ManyToOne
    @JoinColumn(name="codigo_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="codigo_livro")
    private Livro livro;

    public Reserva() {
    }

    public Reserva(Long codigoReserva, Date dataReserva, String status, Usuario usuario, Livro livro) {
        this.codigoReserva = codigoReserva;
        this.dataReserva = dataReserva;
        this.status = status;
        this.usuario = usuario;
        this.livro = livro;
    }

    public Long getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(Long codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}