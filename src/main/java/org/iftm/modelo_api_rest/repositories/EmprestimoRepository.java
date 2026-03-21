package org.iftm.modelo_api_rest.repositories;

import org.iftm.modelo_api_rest.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    
 }