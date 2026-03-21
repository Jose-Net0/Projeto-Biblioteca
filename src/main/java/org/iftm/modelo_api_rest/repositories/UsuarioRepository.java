package org.iftm.modelo_api_rest.repositories;

import org.iftm.modelo_api_rest.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
 }