package br.com.una.academicShare.controller;

import br.com.una.academicShare.model.domain.Assunto;
import br.com.una.academicShare.model.repository.AssuntoRepositorio;
import br.com.una.academicShare.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/assuntos")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
public class AssuntoResources {

    @Autowired
    private AssuntoRepositorio assuntoRepositorio;

    // Sava um novo assunto
    @PostMapping
    public ResponseEntity<Assunto> salvar(@RequestBody Assunto assunto) {
        Assunto saved = assuntoRepositorio.save(assunto);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdAssunto());
        return ResponseEntity.created(uri).body(saved);
    }

    // Altera um assunto existente
    @PutMapping("/{id}")
    public ResponseEntity<Assunto> alterar(@PathVariable("id") String id,
                                           @RequestBody Assunto Assunto) {
        Optional<Assunto> usuarioDoBanco = assuntoRepositorio.findById(id);
        if (!usuarioDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuarioDoBanco.get().update(id, usuarioDoBanco.get());
        Assunto saved = assuntoRepositorio.save(Assunto);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    // Retorna todos os assuntos cadastrados
    @GetMapping
    public ResponseEntity<List<Assunto>> get() {
        List<Assunto> clienteList = assuntoRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    // Retorna um assunto específico
    @GetMapping("/{id}")
    public ResponseEntity<Assunto> getById(@PathVariable("id") String id) {
        Optional<Assunto> clienteList = assuntoRepositorio.findById(id);
        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    // Remove um assunto pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") String id) {
        assuntoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

