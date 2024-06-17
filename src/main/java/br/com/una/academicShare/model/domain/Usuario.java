package br.com.una.academicShare.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.*;

@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String IdUsuario;

    @Getter
    private String nome;

    private String email;

    @Getter
    private String senha;

    @Getter
    private UserRole role;

    @Getter
    private String numeroDeMatricula;

    @Getter
    private String avatar;

    public Usuario(String email, String encryptedPassword, UserRole role, String nome, String avatar) {
        this.email = email;
        this.senha = encryptedPassword;
        this.role = role;
        this.nome = nome;
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    @Getter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public Usuario() { }

    public Usuario(String email, String senha, UserRole role, Curso curso, String numeroDeMatricula, String nome, String avatar) {
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.numeroDeMatricula = numeroDeMatricula;
        this.nome = nome;
        this.curso = new Curso(curso.getNome(), curso.getFaculdade());
        this.avatar = avatar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdUsuario, nome, email, senha, numeroDeMatricula, curso);
    }

    public void update(String Id, Usuario usuario) {
        this.IdUsuario = Id;
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.numeroDeMatricula = usuario.getNumeroDeMatricula();
        this.curso = usuario.getCurso();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        if (this.role == UserRole.GUEST)
            return List.of(new SimpleGrantedAuthority("ROLE_GUEST"));

        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
