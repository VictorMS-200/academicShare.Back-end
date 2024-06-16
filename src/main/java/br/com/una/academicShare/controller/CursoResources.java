package br.com.una.academicShare.controller;

import br.com.una.academicShare.Util.RestUtil;
import br.com.una.academicShare.model.domain.Curso;
import br.com.una.academicShare.model.repository.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cursos")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
public class CursoResources {

    @Autowired
    private CursoRepositorio cursoRepositorio;

    // Retorna todos os cursos cadastrados
    @GetMapping
    public ResponseEntity<List<Curso>> get() {
        List<Curso> clienteList = cursoRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    // Retorna um curso pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable("id") String id) {
        Optional<Curso> clienteList = cursoRepositorio.findById(id);
        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    // Sava um novo curso
    @PostMapping
    public ResponseEntity<Curso> salvar(@RequestBody Curso curso) {
        Curso saved = cursoRepositorio.save(curso);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdCurso());
        return ResponseEntity.created(uri).body(saved);
    }

    // Altera um curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> alterar(@PathVariable("id") String id,
                                         @RequestBody Curso Curso) {
        Optional<Curso> usuarioDoBanco = cursoRepositorio.findById(id);
        if (!usuarioDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuarioDoBanco.get().update(id, usuarioDoBanco.get());
        Curso saved = cursoRepositorio.save(Curso);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    // Remove um curso pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") String id) {
        cursoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


