package org.iftm.modelo_api_rest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
public class ItemEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoItemEmprestimo;

    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoReal;
    private String status;
    private Double multaGerada;

}