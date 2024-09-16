package ufcg.ES.RU.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.DTO.LoginDTO;
import ufcg.ES.RU.Model.Usuario;
import ufcg.ES.RU.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;




    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginDTO login) {

        return  null;
    }

}

