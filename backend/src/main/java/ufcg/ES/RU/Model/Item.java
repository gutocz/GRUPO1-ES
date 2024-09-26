package ufcg.ES.RU.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pk_id_item")
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    @Column(nullable = false, name = "nome")
    private String nome;

    @JsonProperty("descricao")
    @Column(nullable = false, name = "descricao")
    private String descricao;

    @ManyToMany(mappedBy = "itens")
    private Set<Cardapio> cadapios = new HashSet<>();

    @ManyToMany(mappedBy = "itens")
    private Set<Marmita> marmitas = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id, nome); // Não incluir 'cardapios' no hashCode
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(nome, item.nome);
    }

}