package org.iftm.modelo_api_rest.repositories;

import org.iftm.modelo_api_rest.entities.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório para bloquear/registrar restrições aplicadas a usuários
public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
    
 }