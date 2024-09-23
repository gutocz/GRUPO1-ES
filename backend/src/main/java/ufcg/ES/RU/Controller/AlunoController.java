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
import ufcg.ES.RU.service.AlunoService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Usuário")
@RestController
@RequestMapping("/api/usuarios")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/create")
    public ResponseEntity createAluno(@RequestBody @Valid Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Dados inválidos!");
        }

        if (aluno.getSenha() == null || aluno.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha não pode ser nula ou vazia");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(aluno.getSenha());
        aluno.setSenha(encryptedPassword);

        Aluno savedUsuario = alunoService.saveUsuario(aluno);
        if (savedUsuario != null ){
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao salvar o aluno");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllAluno() {
        List<Aluno> usuarios = alunoService.getAllUsuarios();
        if (usuarios.size() != 0){
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarios);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sem alunos cadastrados");
        }
    }

    @GetMapping("/Aluno/{matricula}")
    public ResponseEntity getAlunoByMatricula(@PathVariable String matricula) {
        if (matricula == null) throw  new IllegalArgumentException("matricula não informada");
        Aluno aluno = alunoService.getUsuarioByMatricula(matricula);
        if (aluno != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sem alunos cadastrados");
        }
    }

    @PutMapping("/atualizaAluno/{matricula}")
    public ResponseEntity updateAluno(@PathVariable String matricula, @RequestBody Aluno usuario) {
        Aluno aluno = alunoService.getUsuarioByMatricula(matricula);

        if (aluno.getSenha() == null || aluno.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha não pode ser nula ou vazia");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(aluno.getSenha());
        aluno.setSenha(encryptedPassword);
        Aluno updatedUsuario = alunoService.updateUsuario(matricula, usuario);
        if (updatedUsuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não identificado!");
        }
    }

    @PutMapping("/validaemail/{email}")
    public ResponseEntity validaEmail(@PathVariable String email) {
        Aluno aluno = alunoService.validaEmail(email);
        if (aluno != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(aluno);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email não identificado!");
        }
    }

    @DeleteMapping("/deleteAluno/{matricula}")
    public ResponseEntity deleteUsuario(@PathVariable String matricula) {
        Aluno aluno = alunoService.getUsuarioByMatricula(matricula);
        if (aluno == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        alunoService.deleteUsuario(matricula);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário Removido");
    }

    @PutMapping("/atualizaSenha/")
    public ResponseEntity updateAluno( @RequestBody SenhaDTO senha) {

        Aluno aluno = alunoService.getUsuarioByMatricula(senha.matricula());
        if (aluno == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        if (aluno.getSenha() == null || aluno.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha não pode ser nula ou vazia");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(aluno.getSenha());
        aluno.setSenha(encryptedPassword);

        Aluno updatedUsuario = alunoService.updateUsuario(senha.matricula(), aluno);
        if (updatedUsuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não identificado!");
        }
    }
}

