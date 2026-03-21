package org.iftm.modelo_api_rest.repositories;

import org.iftm.modelo_api_rest.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
 }