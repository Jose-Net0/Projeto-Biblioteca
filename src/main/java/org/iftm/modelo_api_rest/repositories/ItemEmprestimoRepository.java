package org.iftm.modelo_api_rest.repositories;

import org.iftm.modelo_api_rest.entities.ItemEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEmprestimoRepository extends JpaRepository<ItemEmprestimo, Long> {
    
 }