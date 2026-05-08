package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.Usuario;
import org.iftm.modelo_api_rest.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        // Validação: nome não pode ser nulo ou vazio
        if (usuario == null || usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            return null;
        }
        // Validação: CPF deve ter 11 dígitos (simples)
        if (usuario.getCpf() == null || !usuario.getCpf().matches("\\d{11}")) {
            return null;
        }
        // Validação: email deve conter @
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            return null;
        }
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}