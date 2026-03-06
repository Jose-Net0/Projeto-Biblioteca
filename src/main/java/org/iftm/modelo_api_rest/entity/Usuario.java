package com.bibliotecando.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoUsuario;

    private String nome;
    private String cpf;
    private String matricula;
    private String email;
    private String senha;
    private String tipoUsuario;

}