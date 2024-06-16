package br.com.una.academicShare.controller;

import br.com.una.academicShare.Infra.security.TokenService;
import br.com.una.academicShare.model.Dto.AuthenticationDTO;
import br.com.una.academicShare.model.Dto.LoginResponseDTO;
import br.com.una.academicShare.model.Dto.RegisterDTO;
import br.com.una.academicShare.model.domain.Usuario;
import br.com.una.academicShare.model.repository.UsuarioRepositorio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("auth")
@CrossOrigin()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepositorio repositorio;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        var auth = this.authenticationManager.authenticate(userNamePassword);

        if (auth == null) {
            return ResponseEntity.badRequest().build();
        }

        var listaUsuarios = usuarioRepositorio.findAll();
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEmail().equals(data.email())) {
                return ResponseEntity.ok(new LoginResponseDTO(tokenService.generateToken((Usuario) auth.getPrincipal()), usuario));
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.repositorio.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario newUser = new Usuario(data.email(), encryptedPassword, data.role(), data.curso(), data.numeroDeMatricula(), data.nome(), data.avatar());

        this.repositorio.save(newUser);

        return login(new AuthenticationDTO(data.email(), data.senha()));
    }
}
