package ufcg.ES.RU.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufcg.ES.RU.Model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Custom queries, se necessário
}
