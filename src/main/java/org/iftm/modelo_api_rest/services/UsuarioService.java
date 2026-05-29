package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.Usuario;
import org.iftm.modelo_api_rest.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public List<Usuario> findByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    public List<Usuario> findByTipoUsuario(String tipoUsuario) {
        return usuarioRepository.findByTipoUsuario(tipoUsuario);
    }

    public List<Usuario> findByRegraEmprestimoTipoUsuario(String tipoUsuario) {
        return usuarioRepository.findByRegraEmprestimoTipoUsuario(tipoUsuario);
    }

    public List<Usuario> findEligibleForLoan() {
        return usuarioRepository.findByTipoUsuario("ALUNO").stream()
                .filter(usuario -> usuario.getBloqueio() == null)
                .collect(Collectors.toList());
    }

    @Transactional
    public Usuario insert(Usuario usuario) {
        validateUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public List<Usuario> insertAll(List<Usuario> usuarios) {
        return usuarios.stream().map(this::insert).collect(Collectors.toList());
    }

    @Transactional
    public Usuario update(Long id, Usuario updatedUsuario) {
        Usuario existing = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        validateUsuario(updatedUsuario);
        existing.setNome(updatedUsuario.getNome());
        existing.setCpf(updatedUsuario.getCpf());
        existing.setMatricula(updatedUsuario.getMatricula());
        existing.setEmail(updatedUsuario.getEmail());
        existing.setSenha(updatedUsuario.getSenha());
        existing.setTipoUsuario(updatedUsuario.getTipoUsuario());
        existing.setBloqueio(updatedUsuario.getBloqueio());
        existing.setRegraEmprestimo(updatedUsuario.getRegraEmprestimo());
        return usuarioRepository.save(existing);
    }

    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }

    private void validateUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        validateName(usuario.getNome());
        validateCpf(usuario.getCpf());
        validateEmail(usuario.getEmail());
        validatePassword(usuario.getSenha());
        validateTipoUsuario(usuario.getTipoUsuario());
    }

    private void validateName(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }
        if (nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome do usuário deve ter pelo menos 3 caracteres");
        }
    }

    private void validateCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}") && !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        if (!email.contains("@") || email.contains(" ")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    private void validatePassword(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
        if (senha.length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
        }
    }

    private void validateTipoUsuario(String tipoUsuario) {
        if (tipoUsuario == null || tipoUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de usuário é obrigatório");
        }
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
