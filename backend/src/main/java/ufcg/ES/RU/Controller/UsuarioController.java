package ufcg.ES.RU.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.Usuario;
import ufcg.ES.RU.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Usuário")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> createOrUpdateUsuario(@RequestBody @Valid Usuario usuario) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setSenha(encryptedPassword);
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok(savedUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Usuario> getUsuarioByMatricula(@PathVariable String matricula) {
        Optional<Usuario> usuario = usuarioService.getUsuarioByMatricula(matricula);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String matricula, @RequestBody Usuario usuario) {
        Usuario updatedUsuario = usuarioService.updateUsuario(matricula, usuario);
        if (updatedUsuario != null) {
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String matricula) {
        usuarioService.deleteUsuario(matricula);
        return ResponseEntity.noContent().build();
    }
}

