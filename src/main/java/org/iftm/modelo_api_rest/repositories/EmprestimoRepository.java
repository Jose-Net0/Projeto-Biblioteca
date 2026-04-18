package org.iftm.modelo_api_rest.repositories;

import java.util.Date;
import java.util.List;

import org.iftm.modelo_api_rest.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByCodigoEmprestimo(Long codigoEmprestimo);
    List<Emprestimo> findByDataEmprestimo(Date dataEmprestimo);
    List<Emprestimo> findByDataDevolucaoPrevista(Date dataDevolucaoPrevista);
    List<Emprestimo> findByUsuarioCodigoUsuario(Long codigoUsuario);
    List<Emprestimo> findByItemEmprestimoCodigoItemEmprestimo(Long codigoItemEmprestimo);
    
    @Query("SELECT e FROM Emprestimo e WHERE e.usuario.codigoUsuario = :codigoUsuario")
    List<Emprestimo> findByUsuarioCodigoUsuario1(@Param("codigoUsuario") Long codigoUsuario);

    @Query("SELECT e FROM Emprestimo e WHERE e.codigoEmprestimo = :codigoEmprestimo")
    List<Emprestimo> findByCodigoEmprestimo1(@Param("codigoEmprestimo") Long codigoEmprestimo);
 }