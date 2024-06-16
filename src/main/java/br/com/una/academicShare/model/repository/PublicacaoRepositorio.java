package br.com.una.academicShare.model.repository;

import br.com.una.academicShare.model.domain.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepositorio extends JpaRepository<Publicacao, String> {
}
