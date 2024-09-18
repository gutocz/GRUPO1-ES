package ufcg.ES.RU.Controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.DTO.LoginDTO;
import ufcg.ES.RU.Model.Usuario;
import ufcg.ES.RU.Repository.UsuarioRepository;
import ufcg.ES.RU.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;
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

    @PostMapping("/")
    public ResponseEntity login(@RequestBody @Valid LoginDTO login) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(login.senha());
        var usu = usuarioRepository.findByMatriculaAndSenha(login.matricula(),encryptedPassword);
        return  ResponseEntity.ok(usu);
    }
}

