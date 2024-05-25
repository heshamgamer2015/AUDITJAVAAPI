package TarefaCrud.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PacienteDTO {
    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private List<EnderecoDTO> enderecos;
}
