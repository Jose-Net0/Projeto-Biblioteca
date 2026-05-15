package org.iftm.modelo_api_rest.repositories;

import org.iftm.modelo_api_rest.entities.RegraEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório para gerenciar regras de empréstimo (limites, prazos, multas)
public interface RegraEmprestimoRepository extends JpaRepository<RegraEmprestimo, Long> {
    
 }