package br.com.una.academicShare.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
@Entity
@Table(name = "tb_faculdade")
public class Faculdade {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idFaculdade;
    @Getter
    private String nome;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculdade faculdade = (Faculdade) o;
        return Objects.equals(idFaculdade, faculdade.idFaculdade) && Objects.equals(nome, faculdade.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFaculdade, nome);
    }

    public void update(String idFaculdade, Faculdade faculdade) {
        this.idFaculdade = idFaculdade;
        this.nome = faculdade.getNome();

    }
}
