package ufcg.ES.RU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Usuario;
import ufcg.ES.RU.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioByMatricula(String matricula) {
        return usuarioRepository.findById(matricula);
    }

    public Usuario updateUsuario(String matricula, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(matricula);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
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
