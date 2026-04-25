package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.Livro;
import org.iftm.modelo_api_rest.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public Optional<Livro> findById(Long id) {
        return livroRepository.findById(id);
    }

    public Livro save(Livro livro) {
        // Validação básica: título não pode ser nulo ou vazio
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título do livro é obrigatório");
        }
        // Validação: ano de publicação deve ser razoável
        if (livro.getAnoPublicacao() < 0 || livro.getAnoPublicacao() > 2026) {
            throw new IllegalArgumentException("Ano de publicação inválido");
        }
        // Quantidade de exemplares deve ser positiva
        if (livro.getQuantidadeExemplares() < 0) {
            throw new IllegalArgumentException("Quantidade de exemplares deve ser não negativa");
        }
        return livroRepository.save(livro);
    }

    public void deleteById(Long id) {
        livroRepository.deleteById(id);
    }
}