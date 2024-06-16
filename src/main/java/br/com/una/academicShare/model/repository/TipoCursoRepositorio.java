package br.com.una.academicShare.model.repository;

import br.com.una.academicShare.model.domain.TipoCurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCursoRepositorio extends JpaRepository<TipoCurso, String> {
}
