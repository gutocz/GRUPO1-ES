package ufcg.ES.RU.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.Aluno;
import ufcg.ES.RU.Model.DTO.SenhaDTO;
import ufcg.ES.RU.Model.Funcionario;
import ufcg.ES.RU.service.AlunoService;
import ufcg.ES.RU.service.FuncionarioService;

import java.util.List;

@Tag(name = "Funcinário")
@RestController
@RequestMapping("/api/Funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/create")
    public ResponseEntity createFuncionario(@RequestBody @Valid Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Dados inválidos!");
        }

            if (funcionario.getSenha() == null || funcionario.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha não pode ser nula ou vazia");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(funcionario.getSenha());
        funcionario.setSenha(encryptedPassword);

        Funcionario savedUsuario = funcionarioService.saveUsuario(funcionario);
        if (savedUsuario != null ){
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao salvar o aluno");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllAluno() {
        List<Funcionario> usuarios = funcionarioService.getAllUsuarios();
        if (usuarios.size() != 0){
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarios);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sem alunos cadastrados");
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity getAlunoByMatricula(@PathVariable String cpf) {
        if (cpf == null) throw  new IllegalArgumentException("cpf não informado");
        Funcionario funcionario = funcionarioService.getUsuarioByCPF(cpf);
        if (funcionario != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sem alunos cadastrados");
        }
    }

    @PutMapping("/atualizaFuncionario/{cpf}")
    public ResponseEntity updateAluno(@PathVariable String cpf, @RequestBody Funcionario funcionario) {
        Funcionario funcionarioAux = funcionarioService.getUsuarioByCPF(cpf);

        if (funcionario.getSenha() == null || funcionario.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha não pode ser nula ou vazia");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(funcionario.getSenha());
        funcionarioAux.setSenha(encryptedPassword);
        Funcionario updatedUsuario = funcionarioService.updateUsuario(cpf, funcionario);
        if (updatedUsuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcion não identificado!");
        }
    }

    @PutMapping("/validaCPF/{cpf}")
    public ResponseEntity validaEmail(@PathVariable String cpf) {
        Funcionario funcionario = funcionarioService.getUsuarioByCPF(cpf);
        if (funcionario != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(funcionario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email não identificado!");
        }
    }

    @DeleteMapping("deleteFuncionario/{cpf}")
    public ResponseEntity deleteUsuario(@PathVariable String cpf) {
        Funcionario funcionario = funcionarioService.getUsuarioByCPF(cpf);
        if (funcionario == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado");
        }
        funcionarioService.deleteUsuario(cpf);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário Removido");
    }

    @PutMapping("/atualizaSenha/")
    public ResponseEntity updateAluno( @RequestBody SenhaDTO senha) {

        Funcionario funcionario = funcionarioService.getUsuarioByCPF(senha.matricula());
        if (funcionario == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        if (funcionario.getSenha() == null || funcionario.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha não pode ser nula ou vazia");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(senha.novaSenha());
        funcionario.setSenha(encryptedPassword);

        Funcionario funcionario1 = funcionarioService.updateUsuario(senha.matricula(), funcionario);
        if (funcionario1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body(funcionario1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não identificado!");
        }
    }
}

