package ufcg.ES.RU.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufcg.ES.RU.Model.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {
}
