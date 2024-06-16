package br.com.una.academicShare.model.repository;

import br.com.una.academicShare.model.domain.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    UserDetails findByEmail(String email);
}
