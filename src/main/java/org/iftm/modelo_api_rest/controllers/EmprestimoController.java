package org.iftm.modelo_api_rest.controllers;

import java.util.List;
import java.util.Optional;
import org.iftm.modelo_api_rest.entities.Emprestimo;
import org.iftm.modelo_api_rest.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<List<Emprestimo>> findAll() {
        List<Emprestimo> emprestimos = emprestimoService.findAll();
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long itemId) {
        if (id != null) {
            Optional<Emprestimo> emprestimo = emprestimoService.findById(id);
            return emprestimo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        if (usuarioId != null) {
            return ResponseEntity.ok(emprestimoService.findByUsuarioId(usuarioId));
        }
        if (itemId != null) {
            return ResponseEntity.ok(emprestimoService.findByItemCodigoItemEmprestimo(itemId));
        }
        return ResponseEntity.ok(emprestimoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> findById(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoService.findById(id);
        return emprestimo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Emprestimo> save(@RequestBody Emprestimo emprestimo) {
        try {
            Emprestimo salvo = emprestimoService.save(emprestimo);
            if (salvo == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
