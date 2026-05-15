package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.Bloqueio;
import org.iftm.modelo_api_rest.repositories.BloqueioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloqueioService {

    // Serviço responsável por operações CRUD sobre bloqueios de usuários
    // Contém validações simples antes de persistir um bloqueio

    @Autowired
    private BloqueioRepository bloqueioRepository;

    public List<Bloqueio> findAll() {
        // Retorna todos os registros de bloqueio
        return bloqueioRepository.findAll();
    }

    public Optional<Bloqueio> findById(Long id) {
        // Busca um bloqueio por ID
        return bloqueioRepository.findById(id);
    }

    public Bloqueio save(Bloqueio bloqueio) {
        // Validação: motivo não pode ser nulo
        if (bloqueio == null || bloqueio.getMotivo() == null || bloqueio.getMotivo().trim().isEmpty()) {
            return null;
        }
        // Validação: período inválido (data fim antes da data início)
        if (bloqueio.getDataFim() != null && bloqueio.getDataFim().before(bloqueio.getDataInicio())) {
            return null;
        }
        // Persiste o bloqueio após validações
        return bloqueioRepository.save(bloqueio);
    }

    public void deleteById(Long id) {
        // Remove o bloqueio pelo ID
        bloqueioRepository.deleteById(id);
    }
}