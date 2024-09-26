package ufcg.ES.RU.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id_cardapio")
    @JsonProperty("id")
    private Long id;

    @JsonProperty("tipoRefeicao")
    @Column(nullable = false, name = "TipoRefeicao")
    @Enumerated(EnumType.STRING)
    private TipoRefeicao tipoRefeicao;

    @JsonProperty("diaDaSemana")
    @Column(nullable = false, name = "DiaDaSemana")
    @Enumerated(EnumType.STRING)
    private DiaDaSemana diaDaSemana;

    @JsonProperty("itens")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="Item_id")
    private List<Item> itens;
}