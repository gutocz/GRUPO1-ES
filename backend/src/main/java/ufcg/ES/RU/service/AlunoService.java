package ufcg.ES.RU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Aluno;
import ufcg.ES.RU.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Aluno saveUsuario(Aluno usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Aluno> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Aluno getUsuarioByMatricula(String matricula) {
        return usuarioRepository.findAlunoByMatricula(matricula);
    }

    public Aluno updateUsuario(String matricula, Aluno usuarioAtualizado) {
        Aluno usuarioExistente = usuarioRepository.findAlunoByMatricula(matricula);

        if (usuarioExistente != null) {
            return  usuarioRepository.save(usuarioAtualizado);
        } else {
            return null;
        }
    }

    public void deleteUsuario(String matricula) {
        usuarioRepository.deleteById(matricula);
    }

    public Aluno validaEmail( String email) {
        if (email == null || email.isEmpty()) new Throwable("Email invalido");
        return usuarioRepository.findAlunoByEmail(email);
    }

    // Método para incrementar saldo do aluno
    public Aluno incrementarSaldo(String matricula, double valor) {
        Aluno aluno = usuarioRepository.findAlunoByMatricula(matricula);

        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado!");
        }

        aluno.setSaldo(aluno.getSaldo() + valor);
        return usuarioRepository.save(aluno);
    }

    // Método para decrementar saldo do aluno
    public Aluno decrementarSaldo(String matricula, double valor) {
        Aluno aluno = usuarioRepository.findAlunoByMatricula(matricula);

        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado!");
        }

        if (aluno.getSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente!");
        }

        aluno.setSaldo(aluno.getSaldo() - valor);
        return usuarioRepository.save(aluno);
    }


}
