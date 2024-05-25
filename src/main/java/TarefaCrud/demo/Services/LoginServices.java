package TarefaCrud.demo.Services;

import TarefaCrud.demo.Config.JwtServiceGenerator;
import TarefaCrud.demo.DTO.LoginDTO;
import TarefaCrud.demo.DTO.UserDTO;
import TarefaCrud.demo.Entity.User;
import TarefaCrud.demo.Respository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServices {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private JwtServiceGenerator jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDTO logar(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        User user = loginRepository.findByUsername(loginDTO.getUsername()).orElse(null);
        if (user != null) {
            var jwtToken = jwtService.generateToken(user);
            return toUserDTO(user, jwtToken);
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }

    public List<UserDTO> listar() {
        return loginRepository.findUserByAtivo().stream().map(this::userToDTO).toList();
    }

    public User cadastrarUser(UserDTO userDTO) {
        User user = toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return loginRepository.save(user);
    }

    public User editarUser(Long id, UserDTO userDTO) {
        User user = toUser(userDTO);
        String senha = loginRepository.findSenhaById(user.getId());
        user.setPassword(senha);
        return loginRepository.save(user);
    }

    public void deletar(Long id) {
        loginRepository.deleteById(id);
    }

    public UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setAtivo(user.getAtivo());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    public User toUser(UserDTO userDTO) {
        User novoUser = new User();

        novoUser.setId(userDTO.getId());
        novoUser.setAtivo(userDTO.getAtivo());
        novoUser.setUsername(userDTO.getUsername());
        novoUser.setPassword(userDTO.getPassword());
        novoUser.setRole(userDTO.getRole());

        return novoUser;
    }


    private UserDTO toUserDTO(User user, String token) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setToken(token);
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

}
