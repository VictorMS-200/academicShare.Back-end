package br.com.una.academicShare.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

import static jakarta.persistence.CascadeType.*;

@Data
@Entity
@Table(name = "tb_curso")
public class Curso {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idCurso;

    @Getter
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_Tipo_Curso")
    private TipoCurso idTipoCurso;

    @Getter
    @ManyToOne(cascade={PERSIST, MERGE, REMOVE, REFRESH, DETACH})
    @JoinColumn(name = "ID_Faculdade")
    private Faculdade faculdade;

    public Curso() {
    }

    public Curso(String nome, Faculdade faculdade) {
        this.nome = nome;
        this.faculdade = faculdade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(idCurso, curso.idCurso) && Objects.equals(nome, curso.nome) && Objects.equals(idTipoCurso, curso.idTipoCurso) && Objects.equals(faculdade, curso.faculdade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurso, nome, idTipoCurso, faculdade);
    }

    public void update(String idCurso , Curso curso) {
        this.idCurso = idCurso;
        this.nome = getNome();

    }
}
