package ufcg.ES.RU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Aluno;
import ufcg.ES.RU.Repository.UsuarioRepository;

@Service
public class AuthorizationService  {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Aluno loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByMatriculaAndSenha (null,null);
    }
}

