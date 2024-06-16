package br.com.una.academicShare.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
@Entity
@Table(name = "tb_assunto")
public class Assunto {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idAssunto;

    @Getter
    private String Nome;

    public Assunto() {
    }

    public Assunto(String nome) {
        this.Nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assunto assunto = (Assunto) o;
        return Objects.equals(idAssunto, assunto.idAssunto) && Objects.equals(Nome, assunto.Nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAssunto, Nome);
    }

    public void update(String idAssunto, Assunto assunto) {
        this.idAssunto = idAssunto;
        this.Nome = assunto.getNome();
    }
}
