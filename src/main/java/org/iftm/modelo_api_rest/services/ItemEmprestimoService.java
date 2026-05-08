package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.ItemEmprestimo;
import org.iftm.modelo_api_rest.repositories.ItemEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemEmprestimoService {

    @Autowired
    private ItemEmprestimoRepository itemEmprestimoRepository;

    public List<ItemEmprestimo> findAll() {
        return itemEmprestimoRepository.findAll();
    }

    public Optional<ItemEmprestimo> findById(Long id) {
        return itemEmprestimoRepository.findById(id);
    }

    public ItemEmprestimo save(ItemEmprestimo itemEmprestimo) {
        // Validação: empréstimo deve existir
        if (itemEmprestimo == null || itemEmprestimo.getEmprestimo() == null) {
            return null;
        }
        return itemEmprestimoRepository.save(itemEmprestimo);
    }

    public void deleteById(Long id) {
        itemEmprestimoRepository.deleteById(id);
    }
}