package org.iftm.modelo_api_rest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoEmprestimo;

    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;

}