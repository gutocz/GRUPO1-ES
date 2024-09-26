package ufcg.ES.RU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Aluno;
import ufcg.ES.RU.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Aluno saveUsuario(Aluno usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Aluno> getAllUsuarios() {
        return usuarioRepository.findAll();
    }



    public Aluno updateUsuario(String matricula, Aluno usuarioAtualizado) {
        Optional<Aluno> usuarioExistente = usuarioRepository.findById(matricula);

        if (usuarioExistente.isPresent()) {
            Aluno usuario = usuarioExistente.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setSenha(usuarioAtualizado.getSenha());
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    public void deleteUsuario(String matricula) {
        usuarioRepository.deleteById(matricula);
    }
}
