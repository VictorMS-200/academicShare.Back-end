package br.com.una.academicShare.model.Dto;

import br.com.una.academicShare.model.domain.Curso;
import br.com.una.academicShare.model.domain.Faculdade;
import br.com.una.academicShare.model.domain.UserRole;

public record RegisterDTO(String nome, String email, String senha, UserRole role, Curso curso, String numeroDeMatricula, String avatar) {
}
