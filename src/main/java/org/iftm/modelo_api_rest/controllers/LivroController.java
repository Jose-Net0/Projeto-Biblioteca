package org.iftm.modelo_api_rest.controllers;

import java.util.List;
import java.util.Optional;
import org.iftm.modelo_api_rest.entities.Livro;
import org.iftm.modelo_api_rest.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "*")
public class LivroController {

    @Autowired
    private LivroService livroService;

        @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.ok(livroService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String status) {
        if (id != null) {
            Optional<Livro> livro = livroService.findById(id);
            return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        if (titulo != null && !titulo.isBlank()) {
            return ResponseEntity.ok(livroService.findByTitulo(titulo));
        }
        if (autor != null && !autor.isBlank()) {
            return ResponseEntity.ok(livroService.findByAutor(autor));
        }
        if (categoria != null && !categoria.isBlank()) {
            return ResponseEntity.ok(livroService.findByCategoria(categoria));
        }
        if (status != null && !status.isBlank()) {
            return ResponseEntity.ok(livroService.findByStatus(status));
        }
        return ResponseEntity.ok(livroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id) {
        Optional<Livro> livro = livroService.findById(id);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
