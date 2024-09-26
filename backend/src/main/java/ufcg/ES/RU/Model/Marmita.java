package ufcg.ES.RU.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "marmita_item",
            joinColumns = @JoinColumn(name = "marmita_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> itens = new HashSet<>();

}