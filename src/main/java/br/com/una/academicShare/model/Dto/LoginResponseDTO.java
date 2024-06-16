package br.com.una.academicShare.model.Dto;

import br.com.una.academicShare.model.domain.Usuario;

public record LoginResponseDTO(String token, Usuario usuario) {
}
