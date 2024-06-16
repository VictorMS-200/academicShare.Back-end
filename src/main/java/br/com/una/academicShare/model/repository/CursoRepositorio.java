package br.com.una.academicShare.model.repository;

import br.com.una.academicShare.model.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepositorio extends JpaRepository<Curso, String> {
}
