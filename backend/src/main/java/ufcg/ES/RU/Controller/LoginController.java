package ufcg.ES.RU.Controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.DTO.LoginDTO;
import ufcg.ES.RU.Repository.FuncionarioRepository;
import ufcg.ES.RU.Repository.UsuarioRepository;
import ufcg.ES.RU.service.LoginService;

@Tag(name = "Login")
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;
//    @PostMapping("/")
//    public ResponseEntity<Usuario> login(@RequestBody @Valid LoginDTO login) {
//        var username = new UsernamePasswordAuthenticationToken(login.matricula(),login.senha());
//        var auth = this.authenticationManager.authenticate(username);
//        return  ResponseEntity.ok().build();
//    }

    @PostMapping("/usuario")
    public ResponseEntity loginUsuario(@RequestBody @Valid LoginDTO login) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(login.senha());
        var usu = usuarioRepository.findByMatriculaAndSenha(login.matricula(),encryptedPassword);
        if (usu != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usu);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não identificado!");
        }
    }

    @PostMapping("/funcionario")
    public ResponseEntity loginFuncinario(@RequestBody @Valid LoginDTO login) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(login.senha());
        var usu = funcionarioRepository.findFuncionarioByCPFAndSenha (login.matricula(),encryptedPassword);
        if (usu != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usu);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não identificado!");
        }
    }
}

