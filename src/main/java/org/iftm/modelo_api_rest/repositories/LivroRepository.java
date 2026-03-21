package org.iftm.modelo_api_rest.repositories;

import org.iftm.modelo_api_rest.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
 }