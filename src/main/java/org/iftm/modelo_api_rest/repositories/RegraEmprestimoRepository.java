package org.iftm.modelo_api_rest.repositories;

import org.iftm.modelo_api_rest.entities.RegraEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegraEmprestimoRepository extends JpaRepository<RegraEmprestimo, Long> {
    
 }