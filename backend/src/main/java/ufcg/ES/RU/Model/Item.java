package ufcg.ES.RU.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pk_id_prato")
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    @Column(nullable = false, name = "nome")
    private String nome;

    @JsonProperty("descricao")
    @Column(nullable = false, name = "descricao")
    private String descricao;
}
