package TarefaCrud.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO  {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String token;
    private Boolean ativo = true;
}