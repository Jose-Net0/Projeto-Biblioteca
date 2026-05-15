package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.RegraEmprestimo;
import org.iftm.modelo_api_rest.repositories.RegraEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegraEmprestimoService {

    // Serviço que gerencia regras de empréstimo (prazos, multas e limites)
    // Valida as regras antes de persistir

    @Autowired
    private RegraEmprestimoRepository regraEmprestimoRepository;

    public List<RegraEmprestimo> findAll() {
        // Retorna todas as regras de empréstimo cadastradas
        return regraEmprestimoRepository.findAll();
    }

    public Optional<RegraEmprestimo> findById(Long id) {
        // Busca uma regra por ID
        return regraEmprestimoRepository.findById(id);
    }

    public RegraEmprestimo save(RegraEmprestimo regraEmprestimo) {
        // Valida objeto não nulo
        if (regraEmprestimo == null) {
            return null;
        }
        // Validação: prazo deve ser positivo
        if (regraEmprestimo.getPrazoDias() <= 0) {
            return null;
        }
        // Validação: multa diária não pode ser negativa
        if (regraEmprestimo.getMultaPorDia() < 0) {
            return null;
        }
        // Validação: limite de empréstimos deve ser positivo
        if (regraEmprestimo.getLimiteEmprestimos() <= 0) {
            return null;
        }
        // Persiste a regra após validações
        return regraEmprestimoRepository.save(regraEmprestimo);
    }

    public void deleteById(Long id) {
        // Remove a regra de empréstimo pelo ID
        regraEmprestimoRepository.deleteById(id);
    }
}