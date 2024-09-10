package ufcg.ES.RU.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufcg.ES.RU.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
