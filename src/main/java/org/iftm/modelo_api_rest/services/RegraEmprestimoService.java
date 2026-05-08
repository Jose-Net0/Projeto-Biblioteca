package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.RegraEmprestimo;
import org.iftm.modelo_api_rest.repositories.RegraEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegraEmprestimoService {

    @Autowired
    private RegraEmprestimoRepository regraEmprestimoRepository;

    public List<RegraEmprestimo> findAll() {
        return regraEmprestimoRepository.findAll();
    }

    public Optional<RegraEmprestimo> findById(Long id) {
        return regraEmprestimoRepository.findById(id);
    }

    public RegraEmprestimo save(RegraEmprestimo regraEmprestimo) {
        if (regraEmprestimo == null) {
            return null;
        }
        // Validação: prazo deve ser positivo
        if (regraEmprestimo.getPrazoDias() <= 0) {
            return null;
        }
        // Multa por dia deve ser não negativa
        if (regraEmprestimo.getMultaPorDia() < 0) {
            return null;
        }
        // Limite de empréstimos deve ser positivo
        if (regraEmprestimo.getLimiteEmprestimos() <= 0) {
            return null;
        }
        return regraEmprestimoRepository.save(regraEmprestimo);
    }

    public void deleteById(Long id) {
        regraEmprestimoRepository.deleteById(id);
    }
}