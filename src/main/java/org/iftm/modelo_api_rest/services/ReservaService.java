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
        if (reserva.getUsuario() == null) {
            throw new IllegalArgumentException("Usuário é obrigatório para reserva");
        }
        if (reserva.getLivro() == null) {
            throw new IllegalArgumentException("Livro é obrigatório para reserva");
        }
        // Data de reserva não pode ser futura
        if (reserva.getDataReserva().after(new java.util.Date())) {
            throw new IllegalArgumentException("Data de reserva não pode ser futura");
        }
        return reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }
}