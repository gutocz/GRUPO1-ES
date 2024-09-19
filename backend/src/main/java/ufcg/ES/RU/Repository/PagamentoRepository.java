package ufcg.ES.RU.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufcg.ES.RU.Model.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    // Custom queries, se necessário
}
