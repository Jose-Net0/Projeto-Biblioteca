package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.Reserva;
import org.iftm.modelo_api_rest.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    // Serviço para gerenciar reservas de livros
    // Verifica existência de usuário e livro e valida data antes de salvar

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        // Retorna todas as reservas
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        // Busca reserva por ID
        return reservaRepository.findById(id);
    }

    public Reserva save(Reserva reserva) {
        // Validação: reserva precisa referenciar usuário e livro
        if (reserva == null || reserva.getUsuario() == null) {
            return null;
        }
        if (reserva.getLivro() == null) {
            return null;
        }
        // Validação: data de reserva não pode ser futura
        if (reserva.getDataReserva().after(new java.util.Date())) {
            return null;
        }
        // Persiste a reserva após validações
        return reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {
        // Remove reserva pelo ID
        reservaRepository.deleteById(id);
    }
}