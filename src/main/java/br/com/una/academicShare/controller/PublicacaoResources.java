package br.com.una.academicShare.controller;

import br.com.una.academicShare.model.Dto.PublicacaoDTO;
import br.com.una.academicShare.model.domain.Publicacao;
import br.com.una.academicShare.model.repository.PublicacaoRepositorio;
import br.com.una.academicShare.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/publicacao")
@CrossOrigin()
public class PublicacaoResources  {

    @Autowired
    private PublicacaoRepositorio publicacaoRepositorio;

    // Retorna todas as publicacoes cadastradas
    @GetMapping
    public ResponseEntity<List<Publicacao>> get() {
        List<Publicacao> clienteList = publicacaoRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

    // Retorna uma publicacao pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Publicacao> getById(@PathVariable("id") String id) {
        Optional<Publicacao> clienteList = publicacaoRepositorio.findById(id);
        if (!clienteList.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList.get());
    }

    // Sava uma nova publicacao
    @PostMapping
    public ResponseEntity<Publicacao> salvar(@RequestBody PublicacaoDTO data) {
        Publicacao newPublicacao = new Publicacao(data.usuario(), data.assunto(), data.conteudo(), data.resumo());
        Publicacao saved = publicacaoRepositorio.save(newPublicacao);

        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdPublicacao());
        return ResponseEntity.created(uri).body(saved);
    }

    // Altera uma publicacao existente
    @PutMapping("/{id}")
    public ResponseEntity<Publicacao> alterar(@PathVariable("id") String id,
                                           @RequestBody Publicacao Publicacao) {
        Optional<Publicacao> usuarioDoBanco = publicacaoRepositorio.findById(id);
        if (!usuarioDoBanco.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuarioDoBanco.get().update(id, usuarioDoBanco.get());
        Publicacao saved = publicacaoRepositorio.save(Publicacao);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(saved);
    }

    // Remove uma publicacao pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") String id) {
        publicacaoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

