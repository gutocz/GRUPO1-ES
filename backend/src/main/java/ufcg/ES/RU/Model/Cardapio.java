package ufcg.ES.RU.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cardapio_item",
            joinColumns = @JoinColumn(name = "cardapio_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> itens = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id, diaDaSemana, tipoRefeicao); // Não incluir 'itens' no hashCode
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cardapio cardapio = (Cardapio) o;
        return Objects.equals(id, cardapio.id) &&
                Objects.equals(diaDaSemana, cardapio.diaDaSemana) &&
                Objects.equals(tipoRefeicao, cardapio.tipoRefeicao);
    }

}