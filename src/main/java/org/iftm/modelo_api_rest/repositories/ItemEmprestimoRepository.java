package org.iftm.modelo_api_rest.repositories;

import java.util.Date;
import java.util.List;

import org.iftm.modelo_api_rest.entities.ItemEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemEmprestimoRepository extends JpaRepository<ItemEmprestimo, Long> {

    List<ItemEmprestimo> findByCodigoItemEmprestimo(Long codigoItemEmprestimo);
    List<ItemEmprestimo> findByDataDevolucaoPrevista(Date dataDevolucaoPrevista);
    List<ItemEmprestimo> findByDataDevolucaoReal(Date dataDevolucaoReal);
    List<ItemEmprestimo> findByStatus(String status);
    List<ItemEmprestimo> findByMultaGerada(Double multaGerada);
    List<ItemEmprestimo> findByEmprestimoCodigoEmprestimo(Long codigoEmprestimo);

    @Query("SELECT i FROM ItemEmprestimo i WHERE i.emprestimo.codigoEmprestimo = :codigoEmprestimo")
    List<ItemEmprestimo> buscarItensEmprestimoDisponiveis();
    @Query("SELECT i FROM ItemEmprestimo i WHERE i.emprestimo.usuario.cpf = :cpf")
	List<ItemEmprestimo> buscarItensPorCpfUsuario(@Param("cpf") String cpf);
 }