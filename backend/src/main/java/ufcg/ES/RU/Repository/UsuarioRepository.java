package ufcg.ES.RU.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ufcg.ES.RU.Model.Aluno;

@Repository
public interface UsuarioRepository extends JpaRepository<Aluno, String> {

    Aluno findByMatriculaAndSenha(String matricula, String senha);

    Aluno findAlunoByEmail(String email);

    Aluno findAlunoByMatricula(String matricula);
}




