package ufcg.ES.RU.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufcg.ES.RU.Model.Aluno;
import ufcg.ES.RU.Model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {

    Funcionario findFuncionarioByCPFAndSenha(String matricula, String senha);
    Funcionario findFuncionarioByCPF(String matricula);
}
