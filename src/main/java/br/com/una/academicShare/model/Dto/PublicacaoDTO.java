package br.com.una.academicShare.model.Dto;

import br.com.una.academicShare.model.domain.Assunto;
import br.com.una.academicShare.model.domain.Usuario;

public record PublicacaoDTO(String conteudo, Usuario usuario, Assunto assunto, String resumo, String imagem, String professor) {
}
