package TarefaCrud.demo.Controller;

import TarefaCrud.demo.DTO.PacienteDTO;
import TarefaCrud.demo.DTO.MensagemDTO;
import TarefaCrud.demo.Entity.Paciente;
import TarefaCrud.demo.Services.PacienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping ("/paciente")
public class PacienteController {

    @Autowired
    private PacienteServices services;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findPacienteById(@PathVariable Long id) {
        PacienteDTO pacienteDTO = services.findPacienteById(id);
        if (pacienteDTO != null) {
            return ResponseEntity.ok(pacienteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<PacienteDTO> listarPacientes(){
        return services.listarPacientes();
    }

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        try{
            return ResponseEntity.ok(services.cadastrarPaciente(pacienteDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        try {
            return ResponseEntity.ok(services.editarPaciente(id, pacienteDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletarPaciente(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(services.deletarPaciente(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}
