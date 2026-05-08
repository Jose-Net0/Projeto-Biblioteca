package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.Bloqueio;
import org.iftm.modelo_api_rest.repositories.BloqueioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloqueioService {

    @Autowired
    private BloqueioRepository bloqueioRepository;

    public List<Bloqueio> findAll() {
        return bloqueioRepository.findAll();
    }

    public Optional<Bloqueio> findById(Long id) {
        return bloqueioRepository.findById(id);
    }

    public Bloqueio save(Bloqueio bloqueio) {
        // Validação: motivo não pode ser nulo
        if (bloqueio == null || bloqueio.getMotivo() == null || bloqueio.getMotivo().trim().isEmpty()) {
            return null;
        }
        // Data fim deve ser após data início
        if (bloqueio.getDataFim() != null && bloqueio.getDataFim().before(bloqueio.getDataInicio())) {
            return null;
        }
        return bloqueioRepository.save(bloqueio);
    }

    public void deleteById(Long id) {
        bloqueioRepository.deleteById(id);
    }
}