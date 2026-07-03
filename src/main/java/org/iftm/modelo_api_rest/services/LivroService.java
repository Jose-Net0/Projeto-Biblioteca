package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.Livro;
import org.iftm.modelo_api_rest.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    // Serviço para operações CRUD de livros e validações comerciais simples

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> findAll() {
        // Retorna todos os livros cadastrados
        return livroRepository.findAll();
    }

    public Optional<Livro> findById(Long id) {
        // Busca um livro por ID
        return livroRepository.findById(id);
    }

    public List<Livro> findByTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> findByAutor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Livro> findByCategoria(String categoria) {
        return livroRepository.findByCategoriaContainingIgnoreCase(categoria);
    }

    public List<Livro> findByStatus(String status) {
        return livroRepository.findByStatusContainingIgnoreCase(status);
    }

    public Livro save(Livro livro) {
        // Validação básica: título não pode ser nulo ou vazio
        if (livro == null || livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            return null;
        }
        // Validação: ano de publicação deve ser razoável (ex.: não no futuro)
        if (livro.getAnoPublicacao() < 0 || livro.getAnoPublicacao() > 2026) {
            return null;
        }
        // Quantidade de exemplares não pode ser negativa
        if (livro.getQuantidadeExemplares() < 0) {
            return null;
        }
        // Persiste o livro após validações
        return livroRepository.save(livro);
    }

    public void deleteById(Long id) {
        // Exclui o livro pelo ID
        livroRepository.deleteById(id);
    }
}