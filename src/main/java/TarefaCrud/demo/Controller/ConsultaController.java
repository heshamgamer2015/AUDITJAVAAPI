package TarefaCrud.demo.Controller;

import TarefaCrud.demo.DTO.ConsultaDTO;
import TarefaCrud.demo.DTO.MensagemDTO;
import TarefaCrud.demo.Services.ConsultaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/consulta")

public class ConsultaController {

    @Autowired
    private ConsultaServices services;

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> findConsultaById(@PathVariable Long id) {
        ConsultaDTO consultaDTO = services.findConsultaById(id);
        if (consultaDTO != null) {
            return ResponseEntity.ok(consultaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<ConsultaDTO> listarConsultas(){
        return services.listarConsultas();
    }

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        try{
            return ResponseEntity.ok(services.cadastrarConsulta(consultaDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarConsulta(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {
        try {
            return ResponseEntity.ok(services.editarConsulta(id, consultaDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletarConsulta(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(services.deletarConsulta(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}
