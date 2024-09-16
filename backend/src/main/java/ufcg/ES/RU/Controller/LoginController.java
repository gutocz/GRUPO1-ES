package ufcg.ES.RU.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.DTO.LoginDTO;
import ufcg.ES.RU.Model.Usuario;
import ufcg.ES.RU.service.LoginService;

@Tag(name = "Login")
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private AuthenticationManager authenticationManager = new AuthenticationManager() {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return null;
        }
    };
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginDTO login) {
        loginService.getUsuarioByMatricula(login.getMatricula());
        return  null;
    }

}

