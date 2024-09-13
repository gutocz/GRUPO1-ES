package ufcg.ES.RU.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Usuario {
    private String nome;
    @Id
    @NotNull
    private String matricula;
    private String senha;
    private TIPO_USUARIO tipoUsuario;
}
