package ufcg.ES.RU.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufcg.ES.RU.Model.Pedido;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByToken(String token);

    // Buscar pedidos de um aluno que ainda não foram entregues
//    List<Pedido> findByAlunoIdAndEntregueFalse(String matricula);

    List<Pedido> findByAluno_MatriculaAndEntregueFalse(String matricula);
}
