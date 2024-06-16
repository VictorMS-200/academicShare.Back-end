package br.com.una.academicShare.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

import static jakarta.persistence.CascadeType.*;

@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name = "tb_publicacao")
public class Publicacao {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPublicacao;

    @Getter
    @Column(columnDefinition = "TEXT")
    private String resumo;

    @Getter
    @JsonFormat(pattern="HH:mm | dd/MM/yyyy", timezone="GMT-3")
    private Date dataPublicacao = new Date();

    @Getter
    @Column(columnDefinition = "TEXT")
    private String conteudo;

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "id_autor")
    private Usuario usuario;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "id_assunto")
    private Assunto assunto;

    public Publicacao() {
    }

    public Publicacao(Usuario usuario, Assunto assunto, String conteudo, String resumo) {
        this.conteudo = conteudo;
        this.usuario = usuario;
        this.assunto = new Assunto(assunto.getNome());
        this.dataPublicacao = new Date();
        this.resumo = resumo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publicacao that = (Publicacao) o;
        return Objects.equals(idPublicacao, that.idPublicacao) && Objects.equals(conteudo, that.conteudo) && Objects.equals(usuario, that.usuario) && Objects.equals(assunto, that.assunto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPublicacao, conteudo, usuario, assunto);
    }

    public void update(String idPublicacao, Publicacao publicacao) {
        this.idPublicacao = idPublicacao;
        this.conteudo = publicacao.getConteudo();
    }
}
