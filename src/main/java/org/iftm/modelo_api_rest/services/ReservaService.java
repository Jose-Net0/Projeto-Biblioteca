package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.Reserva;
import org.iftm.modelo_api_rest.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva save(Reserva reserva) {
        // Validação: usuário e livro devem existir
        if (reserva == null || reserva.getUsuario() == null) {
            return null;
        }
        if (reserva.getLivro() == null) {
            return null;
        }
        // Data de reserva não pode ser futura
        if (reserva.getDataReserva().after(new java.util.Date())) {
            return null;
        }
        return reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }
}