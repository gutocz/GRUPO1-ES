package ufcg.ES.RU.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Marmita {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("tipoRefeicao")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoMarmita", nullable = false)
    private TipoMarmita tipoMarmita;

    @OneToMany
    @JsonProperty("itens")
    private List<Item> itens;

}
