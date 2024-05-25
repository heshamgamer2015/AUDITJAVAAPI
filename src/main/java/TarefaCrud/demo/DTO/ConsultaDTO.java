package TarefaCrud.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ConsultaDTO {
    private Long id;

    private LocalTime data;

    private PacienteDTO paciente;
}
