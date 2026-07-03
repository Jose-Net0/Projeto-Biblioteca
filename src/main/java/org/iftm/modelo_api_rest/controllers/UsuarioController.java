package org.iftm.modelo_api_rest.controllers;

import java.util.List;
import java.util.Optional;
import org.iftm.modelo_api_rest.entities.Usuario;
import org.iftm.modelo_api_rest.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String matricula,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String tipoUsuario,
            @RequestParam(required = false) String status) {
        if (id != null) {
            Optional<Usuario> usuario = usuarioService.findById(id);
            return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        if (nome != null && !nome.isBlank()) {
            return ResponseEntity.ok(usuarioService.findByNome(nome));
        }
        if (cpf != null && !cpf.isBlank()) {
            return ResponseEntity.ok(usuarioService.findByCpf(cpf));
        }
        if (matricula != null && !matricula.isBlank()) {
            return ResponseEntity.ok(usuarioService.findByMatricula(matricula));
        }
        if (email != null && !email.isBlank()) {
            return ResponseEntity.ok(usuarioService.findByEmail(email));
        }
        if (tipoUsuario != null && !tipoUsuario.isBlank()) {
            return ResponseEntity.ok(usuarioService.findByTipoUsuario(tipoUsuario));
        }
        if (status != null && !status.isBlank()) {
            return ResponseEntity.ok(usuarioService.findByStatus(status));
        }
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.insert(usuario);
            return ResponseEntity.ok(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario atualizado = usuarioService.update(id, usuario);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
