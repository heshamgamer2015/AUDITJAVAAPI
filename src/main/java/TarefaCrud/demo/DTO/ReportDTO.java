package TarefaCrud.demo.DTO;

import TarefaCrud.demo.Entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReportDTO extends Auditoria {

    private Consulta consulta;
    private Paciente paciente;
    private Endereco endereco;
    private User user;
    private String tipo;
    private String mensagem;


}
