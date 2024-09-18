package ufcg.ES.RU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Funcionario;
import ufcg.ES.RU.Repository.FuncionarioRepository;
import ufcg.ES.RU.Repository.UsuarioRepository;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario saveUsuario(Funcionario usuario) {
        return funcionarioRepository.save(usuario);
    }

    public List<Funcionario> getAllUsuarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario getUsuarioByCPF(String cpf) {
        return funcionarioRepository.findFuncionarioByCPF(cpf);
    }

    public Funcionario updateUsuario(String cpf, Funcionario usuarioAtualizado) {
        Funcionario usuarioExistente = funcionarioRepository.findFuncionarioByCPF(cpf);

        if (usuarioExistente != null) {
            return  funcionarioRepository.save(usuarioAtualizado);
        } else {
            return null;
        }
    }

    public void deleteUsuario(String cpf) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByCPF(cpf);
        if (funcionario != null){
            funcionarioRepository.deleteById(funcionario.getCPF());
        }

    }
    }
