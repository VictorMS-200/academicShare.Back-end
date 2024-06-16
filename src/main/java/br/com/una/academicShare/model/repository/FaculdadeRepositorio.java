package br.com.una.academicShare.model.repository;

import br.com.una.academicShare.model.domain.Faculdade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaculdadeRepositorio extends JpaRepository<Faculdade, String> {
}
