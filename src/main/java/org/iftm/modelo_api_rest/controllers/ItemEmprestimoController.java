package org.iftm.modelo_api_rest.controllers;

import java.util.List;
import java.util.Optional;
import org.iftm.modelo_api_rest.entities.ItemEmprestimo;
import org.iftm.modelo_api_rest.services.ItemEmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens-emprestimo")
public class ItemEmprestimoController {

    @Autowired
    private ItemEmprestimoService itemEmprestimoService;

    @GetMapping
    public ResponseEntity<List<ItemEmprestimo>> findAll() {
        List<ItemEmprestimo> itens = itemEmprestimoService.findAll();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long emprestimoId,
            @RequestParam(required = false) String cpf) {
        if (id != null) {
            Optional<ItemEmprestimo> item = itemEmprestimoService.findById(id);
            return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        if (status != null && !status.isBlank()) {
            return ResponseEntity.ok(itemEmprestimoService.findByStatus(status));
        }
        if (emprestimoId != null) {
            return ResponseEntity.ok(itemEmprestimoService.findByEmprestimoId(emprestimoId));
        }
        if (cpf != null && !cpf.isBlank()) {
            return ResponseEntity.ok(itemEmprestimoService.findByCpfUsuario(cpf));
        }
        return ResponseEntity.ok(itemEmprestimoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEmprestimo> findById(@PathVariable Long id) {
        Optional<ItemEmprestimo> item = itemEmprestimoService.findById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemEmprestimo> save(@RequestBody ItemEmprestimo itemEmprestimo) {
        try {
            ItemEmprestimo salvo = itemEmprestimoService.save(itemEmprestimo);
            if (salvo == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            itemEmprestimoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
