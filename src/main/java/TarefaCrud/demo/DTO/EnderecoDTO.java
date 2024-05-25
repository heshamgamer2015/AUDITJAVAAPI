package TarefaCrud.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
    private Long id;

    private String estado;

    private String cidade;

    private String rua;

    private String numero;

    private String cep;

    private PacienteDTO paciente;
}
