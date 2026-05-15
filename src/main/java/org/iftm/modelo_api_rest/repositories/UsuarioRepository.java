package org.iftm.modelo_api_rest.repositories;

import java.util.List;
import java.util.Optional;

import org.iftm.modelo_api_rest.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


// Repositório para operações de persistência e consultas relacionadas a usuários
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    List<Usuario> findByCodigoUsuario(Long codigoUsuario);
    List<Usuario> findByNome(String nome);
    List<Usuario> findByCpf(String cpf);
    List<Usuario> findByMatricula(String matricula);
    List<Usuario> findByEmail(String email);
    List<Usuario> findByTipoUsuario(String tipoUsuario);
    List<Usuario> findByStatus(String status);
    boolean existsByCpf(String cpf);
    boolean existsByLogin(String login);
    Optional<Usuario> findByLogin(String login);

    @Query("SELECT u FROM Usuario u WHERE u.tipoUsuario = :tipoUsuario")
    List<Usuario> findByRegraEmprestimoTipoUsuario(String tipoUsuario);
    
 }