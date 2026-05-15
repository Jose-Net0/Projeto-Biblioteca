package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.Usuario;
import org.iftm.modelo_api_rest.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    // Serviço que gerencia usuários: CRUD, validações e autenticação
    // Inclui controle de tentativas de login e bloqueio temporário

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        // Retorna todos os usuários cadastrados
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        // Busca usuário por ID
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findByName(String name) {
        // Busca usuários pelo nome
        return usuarioRepository.findByNome(name);
    }

    public List<Usuario> findByStatus(String status) {
        // Filtra usuários pelo status (ATIVO / INATIVO)
        return usuarioRepository.findByStatus(status);
    }

    public Usuario authenticate(String login, String senha) {
        // Valida campos de entrada
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login é obrigatório.");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }
        // Recupera usuário pelo login
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Login ou senha incorretos"));

        // Verifica status e bloqueio temporário
        if (!"ATIVO".equalsIgnoreCase(usuario.getStatus())) {
            throw new IllegalStateException("Usuário inativo não pode autenticar.");
        }

        if (isLocked(usuario)) {
            throw new IllegalStateException("Acesso temporariamente bloqueado. Tente novamente mais tarde.");
        }

        // Verifica senha (aqui comparativo simples; ideal usar hash)
        if (!passwordMatches(senha, usuario.getSenha())) {
            // Registra tentativa falha e possivelmente bloqueia
            registerFailedLoginAttempt(usuario);
            throw new RuntimeException("Login ou senha incorretos");
        }

        // Reset de tentativas após sucesso
        resetLoginAttempts(usuario);
        return usuario;
    }

    private boolean isLocked(Usuario usuario) {
        // Verifica se o usuário está temporariamente bloqueado
        Date now = new Date();
        return usuario.getLockoutUntil() != null && usuario.getLockoutUntil().after(now);
    }

    private void registerFailedLoginAttempt(Usuario usuario) {
        // Incrementa contador de tentativas e bloqueia por 5 minutos após 3 falhas
        int attempts = usuario.getLoginAttempts() + 1;
        usuario.setLoginAttempts(attempts);
        if (attempts >= 3) {
            usuario.setLockoutUntil(new Date(System.currentTimeMillis() + 5 * 60 * 1000));
        }
        usuarioRepository.save(usuario);
    }

    private void resetLoginAttempts(Usuario usuario) {
        // Restaura contador e remove bloqueio após autenticação bem sucedida
        usuario.setLoginAttempts(0);
        usuario.setLockoutUntil(null);
        usuarioRepository.save(usuario);
    }

    private boolean passwordMatches(String rawPassword, String storedPassword) {
        // Comparação direta de senha (substituir por hashing em produção)
        return rawPassword.equals(storedPassword);
    }

    @Transactional(readOnly = true)
    public boolean canDeleteOrInactivate(Long id) {
        // Verifica se usuário não possui empréstimos ativos (pré-requisito para exclusão)
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuario.getEmprestimos() == null || usuario.getEmprestimos().isEmpty();
    }

    @Transactional
    public Usuario insert(Usuario usuario) {
        // Insere novo usuário após validações completas
        validateRequiredFields(usuario);
        validateLogin(usuario.getLogin());
        validatePassword(usuario.getSenha());
        validateCpf(usuario.getCpf());
        validateUniqueCpf(usuario.getCpf(), null);
        validateUniqueLogin(usuario.getLogin(), null);
        validateEmail(usuario.getEmail());
        validateTelefone(usuario.getTelefone());
        validateTipoUsuario(usuario.getTipoUsuario());

        if (usuario.getStatus() == null || usuario.getStatus().trim().isEmpty()) {
            usuario.setStatus("ATIVO");
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Long id, Usuario updatedUsuario) {
        Usuario entity = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        validateRequiredFields(updatedUsuario);
        validateLogin(updatedUsuario.getLogin());
        validatePassword(updatedUsuario.getSenha());
        validateCpf(updatedUsuario.getCpf());
        if (!updatedUsuario.getCpf().equals(entity.getCpf())) {
            validateUniqueCpf(updatedUsuario.getCpf(), id);
        }
        if (!updatedUsuario.getLogin().equals(entity.getLogin())) {
            validateUniqueLogin(updatedUsuario.getLogin(), id);
        }
        validateEmail(updatedUsuario.getEmail());
        validateTelefone(updatedUsuario.getTelefone());
        validateTipoUsuario(updatedUsuario.getTipoUsuario());

        String previousStatus = entity.getStatus();

        entity.setNome(updatedUsuario.getNome());
        entity.setCpf(updatedUsuario.getCpf());
        entity.setMatricula(updatedUsuario.getMatricula());
        entity.setEmail(updatedUsuario.getEmail());
        entity.setSenha(updatedUsuario.getSenha());
        entity.setTipoUsuario(updatedUsuario.getTipoUsuario());
        entity.setTelefone(updatedUsuario.getTelefone());
        entity.setEndereco(updatedUsuario.getEndereco());
        entity.setStatus(updatedUsuario.getStatus() == null ? previousStatus : updatedUsuario.getStatus());

        registrarHistoricoAlteracao(entity, updatedUsuario);

        return usuarioRepository.save(entity);
    }

    @Transactional
    public Usuario inactivate(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!canDeleteOrInactivate(id)) {
            throw new IllegalStateException("Usuário possui empréstimos ativos ou pendências e não pode ser inativado.");
        }
        usuario.setStatus("INATIVO");
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Long id) {
        if (!canDeleteOrInactivate(id)) {
            throw new IllegalStateException("Usuário possui empréstimos ativos ou pendências e não pode ser excluído.");
        }
        usuarioRepository.deleteById(id);
    }

    private void validateRequiredFields(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Dados do usuário são obrigatórios.");
        }
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome completo é obrigatório.");
        }
        if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("Login é obrigatório.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }
        if (usuario.getCpf() == null || usuario.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }
        if (usuario.getTipoUsuario() == null || usuario.getTipoUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de usuário é obrigatório.");
        }
        if (usuario.getTelefone() == null || usuario.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }
    }

    private void validateCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("CPF inválido. Use o formato NNN.NNN.NNN-NN.");
        }
    }

    private void validateUniqueCpf(String cpf, Long currentId) {
        if (usuarioRepository.existsByCpf(cpf)) {
            if (currentId == null) {
                throw new IllegalArgumentException("CPF já cadastrado.");
            }
            Usuario existing = usuarioRepository.findByCpf(cpf).stream().findFirst().orElse(null);
            if (existing != null && !existing.getCodigoUsuario().equals(currentId)) {
                throw new IllegalArgumentException("CPF já cadastrado para outro usuário.");
            }
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.matches("^[^@\s]+@[^@\s]+\\.[^@\s]+$")) {
            throw new IllegalArgumentException("Email inválido.");
        }
    }

    private void validateTelefone(String telefone) {
        if (telefone == null || !telefone.matches("^\\(\\d{2}\\) \\d{4,5}-\\d{4}$")) {
            throw new IllegalArgumentException("Telefone inválido. Use o formato (NN) NNNNN-NNNN.");
        }
    }

    private void validateLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login é obrigatório.");
        }
        if (login.length() > 50) {
            throw new IllegalArgumentException("Login não pode ter mais de 50 caracteres.");
        }
    }

    private void validatePassword(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }
        if (senha.length() > 20) {
            throw new IllegalArgumentException("Senha não pode ter mais de 20 caracteres.");
        }
    }

    private void validateUniqueLogin(String login, Long currentId) {
        if (usuarioRepository.existsByLogin(login)) {
            if (currentId == null) {
                throw new IllegalArgumentException("Login já cadastrado.");
            }
            Usuario existing = usuarioRepository.findByLogin(login).orElse(null);
            if (existing != null && !existing.getCodigoUsuario().equals(currentId)) {
                throw new IllegalArgumentException("Login já cadastrado para outro usuário.");
            }
        }
    }

    private void validateTipoUsuario(String tipoUsuario) {
        if (tipoUsuario == null) {
            throw new IllegalArgumentException("Tipo de usuário inválido.");
        }
        String normalized = tipoUsuario.trim().toUpperCase();
        if (!normalized.equals("ALUNO") && !normalized.equals("PROFESSOR") && !normalized.equals("VISITANTE")) {
            throw new IllegalArgumentException("Tipo de usuário deve ser Aluno, Professor ou Visitante.");
        }
    }

    private void registrarHistoricoAlteracao(Usuario original, Usuario updated) {
        System.out.printf("AUDIT: Usuário %d alterado. Nome[%s->%s], CPF[%s->%s], Tipo[%s->%s], Status[%s->%s], Telefone[%s->%s], Endereço[%s->%s]%n",
                original.getCodigoUsuario(),
                original.getNome(), updated.getNome(),
                original.getCpf(), updated.getCpf(),
                original.getTipoUsuario(), updated.getTipoUsuario(),
                original.getStatus(), updated.getStatus(),
                original.getTelefone(), updated.getTelefone(),
                original.getEndereco(), updated.getEndereco());
    }
}
