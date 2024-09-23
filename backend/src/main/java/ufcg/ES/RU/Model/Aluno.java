package ufcg.ES.RU.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name="Aluno")
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @NotNull
    private String matricula;

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private String senha;


    private String telefone;

    @NotNull
    private String curso;
}
