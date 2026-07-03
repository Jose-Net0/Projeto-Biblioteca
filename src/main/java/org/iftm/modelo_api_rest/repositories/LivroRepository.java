package org.iftm.modelo_api_rest.repositories;

import java.util.List;
import org.iftm.modelo_api_rest.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório para operações no catálogo de livros
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findByAutorContainingIgnoreCase(String autor);
    List<Livro> findByCategoriaContainingIgnoreCase(String categoria);
    List<Livro> findByStatusContainingIgnoreCase(String status);
 }