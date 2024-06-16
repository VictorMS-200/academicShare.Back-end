package br.com.una.academicShare.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.una.academicShare.model.domain.Assunto;


public interface AssuntoRepositorio extends JpaRepository<Assunto, String> {
}
