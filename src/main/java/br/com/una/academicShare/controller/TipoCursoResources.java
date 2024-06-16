package br.com.una.academicShare.controller;

import br.com.una.academicShare.model.domain.TipoCurso;
import br.com.una.academicShare.model.repository.TipoCursoRepositorio;
import br.com.una.academicShare.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tipocursos")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
public class TipoCursoResources  {

    // Injeta o repositório
    @Autowired
    private TipoCursoRepositorio tipoCursoRepositorio;

    // Retorna todos os tipos de curso
    @GetMapping
    public ResponseEntity<List<TipoCurso>> get() {
        List<TipoCurso> clienteList = tipoCursoRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    // Retorna um tipo de curso pelo id
    @GetMapping("/{id}")
    public ResponseEntity<TipoCurso> getById(@PathVariable("id") String id) {
        Optional<TipoCurso> clienteList = tipoCursoRepositorio.findById(id);
        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    // Salva um novo tipo de curso
    @PostMapping
    public ResponseEntity<TipoCurso> salvar(@RequestBody TipoCurso tipoCurso) {
        TipoCurso saved = tipoCursoRepositorio.save(tipoCurso);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdTipoCurso());
        return ResponseEntity.created(uri).body(saved);
    }

    // Altera um tipo de curso pelo id
    @PutMapping("/{id}")
    public ResponseEntity<TipoCurso> alterar(@PathVariable("id") String id, @RequestBody TipoCurso tipoCurso) {

        Optional<TipoCurso> usuarioDoBanco = tipoCursoRepositorio.findById(id);
        if (!usuarioDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuarioDoBanco.get().update(id, usuarioDoBanco.get());
        TipoCurso saved = tipoCursoRepositorio.save(tipoCurso);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    // Remove um tipo de curso pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") String id) {
        tipoCursoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
