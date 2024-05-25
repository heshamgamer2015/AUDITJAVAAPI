package TarefaCrud.demo.Controller;

import TarefaCrud.demo.DTO.LoginDTO;
import TarefaCrud.demo.DTO.UserDTO;
import TarefaCrud.demo.Entity.Paciente;
import TarefaCrud.demo.Entity.User;
import TarefaCrud.demo.Services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginServices loginService;

    @PostMapping
    public ResponseEntity<UserDTO> logar(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(loginService.logar(loginDTO));
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lista")
    public List<UserDTO> listar() {
        return loginService.listar();
    }

    @PostMapping("/user")
    public ResponseEntity<?> cadastrarUser(@RequestBody UserDTO userDTO) {
        try {
            User novoUser = loginService.cadastrarUser(userDTO);
            return ResponseEntity.ok("User Cadastrado com sucesso");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            User userAtalizado = loginService.editarUser(id, userDTO);
            if (userAtalizado != null) {
                return ResponseEntity.ok("Paciente atualizado com Sucesso");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar (@PathVariable Long id) {
        try {
            loginService.deletar(id);
            return ResponseEntity.ok("Paciente deletado com sucesso");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
