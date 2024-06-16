package br.com.una.academicShare.controller;

import br.com.una.academicShare.model.domain.Usuario;
import br.com.una.academicShare.model.repository.UsuarioRepositorio;
import br.com.una.academicShare.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
public class UsuarioResources {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Retorna todos os usuários cadastrados
    @GetMapping
    public ResponseEntity<List<Usuario>> get() {
        List<Usuario> clienteList = usuarioRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    // Retorna um usuário pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") String id) {

        Optional<Usuario> clienteList = usuarioRepositorio.findById(id);

        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    // Sava um novo usuário
    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        Usuario saved = usuarioRepositorio.save(usuario);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdUsuario());
        return ResponseEntity.created(uri).body(saved);
    }

    // Altera um usuário
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable("id") String id,
            @RequestBody Usuario Usuario) {
        Optional<Usuario> usuarioDoBanco = usuarioRepositorio.findById(id);
        if (!usuarioDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuarioDoBanco.get().update(id, usuarioDoBanco.get());
        Usuario saved = usuarioRepositorio.save(Usuario);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    // Remove um usuário pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") String id) {

        usuarioRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
