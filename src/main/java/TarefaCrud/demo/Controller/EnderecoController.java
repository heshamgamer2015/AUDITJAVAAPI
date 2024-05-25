package TarefaCrud.demo.Controller;

import TarefaCrud.demo.DTO.EnderecoDTO;
import TarefaCrud.demo.DTO.MensagemDTO;
import TarefaCrud.demo.Services.EnderecoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoServices services;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findEnderecoById(@PathVariable Long id) {
        EnderecoDTO enderecoDTO = services.findEnderecoById(id);
        if (enderecoDTO != null) {
            return ResponseEntity.ok(enderecoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<EnderecoDTO> listarEnderecos(){
        return services.listarEnderecos();
    }

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        try{
            return ResponseEntity.ok(services.cadastrarEndereco(enderecoDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        try {
            return ResponseEntity.ok(services.editarEndereco(id, enderecoDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletarEndereco(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(services.deletarEndereco(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}
