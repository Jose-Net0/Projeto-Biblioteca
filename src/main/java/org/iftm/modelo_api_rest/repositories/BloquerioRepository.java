package org.iftm.modelo_api_rest.repositories;

import org.iftm.modelo_api_rest.entities.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
    
 }