package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.ItemEmprestimo;
import org.iftm.modelo_api_rest.repositories.ItemEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemEmprestimoService {

    // Serviço para gerenciar itens dentro de um empréstimo (cada exemplar retirado)
    // Realiza operações simples de CRUD com validações básicas

    @Autowired
    private ItemEmprestimoRepository itemEmprestimoRepository;

    public List<ItemEmprestimo> findAll() {
        // Recupera todos os itens de empréstimo
        return itemEmprestimoRepository.findAll();
    }

    public Optional<ItemEmprestimo> findById(Long id) {
        // Busca item de empréstimo por ID
        return itemEmprestimoRepository.findById(id);
    }

    public List<ItemEmprestimo> findByStatus(String status) {
        return itemEmprestimoRepository.findByStatus(status);
    }

    public List<ItemEmprestimo> findByEmprestimoId(Long emprestimoId) {
        return itemEmprestimoRepository.findByEmprestimoCodigoEmprestimo(emprestimoId);
    }

    public List<ItemEmprestimo> findByCpfUsuario(String cpf) {
        return itemEmprestimoRepository.buscarItensPorCpfUsuario(cpf);
    }

    public ItemEmprestimo save(ItemEmprestimo itemEmprestimo) {
        // Validação: um ItemEmprestimo precisa referenciar um Empréstimo existente
        if (itemEmprestimo == null || itemEmprestimo.getEmprestimo() == null) {
            return null;
        }
        // Persiste o item após validação
        return itemEmprestimoRepository.save(itemEmprestimo);
    }

    public List<ItemEmprestimo> buscarItensPorCpfUsuario(String cpf) {
        return itemEmprestimoRepository.buscarItensPorCpfUsuario(cpf);
    }

    public void deleteById(Long id) {
        // Remove um item de empréstimo pelo ID
        itemEmprestimoRepository.deleteById(id);
    }
}