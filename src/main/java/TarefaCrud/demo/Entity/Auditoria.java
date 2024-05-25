package TarefaCrud.demo.Entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public abstract class Auditoria {

    private String userCriacao;
    private String userAlteracao;
    private String userExclusao;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraAlteracao;
    private LocalDateTime dataHoraExclusao;

}
