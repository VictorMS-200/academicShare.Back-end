package br.com.una.academicShare.controller;

import br.com.una.academicShare.model.domain.Faculdade;
import br.com.una.academicShare.model.repository.FaculdadeRepositorio;
import br.com.una.academicShare.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/faculdades")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
public class FaculdadeResources  {

    // Injeta o repositório
    @Autowired
    private FaculdadeRepositorio faculdadeRepositorio;

    // Retorna todas as faculdades cadastradas
    @GetMapping
    public ResponseEntity<List<Faculdade>> get() {
        List<Faculdade> clienteList = faculdadeRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    // Retorna uma faculdade pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Faculdade> getById(@PathVariable("id") String id) {
        Optional<Faculdade> clienteList = faculdadeRepositorio.findById(id);
        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    // Sava uma nova faculdade
    @PostMapping
    public ResponseEntity<Faculdade> salvar(@RequestBody Faculdade faculdade) {
        Faculdade saved = faculdadeRepositorio.save(faculdade);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdFaculdade());
        return ResponseEntity.created(uri).body(saved);
    }

    // Altera uma faculdade existente
    @PutMapping("/{id}")
    public ResponseEntity<Faculdade> alterar(@PathVariable("id") String id,
                                             @RequestBody Faculdade Faculdade) {
        Optional<Faculdade> usuarioDoBanco = faculdadeRepositorio.findById(id);
        if (!usuarioDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuarioDoBanco.get().update(id, usuarioDoBanco.get());
        Faculdade saved = faculdadeRepositorio.save(Faculdade);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    // Remove uma faculdade pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") String id) {
        faculdadeRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


