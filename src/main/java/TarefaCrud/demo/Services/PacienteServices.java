package TarefaCrud.demo.Services;

import TarefaCrud.demo.Config.LocalStorage;
import TarefaCrud.demo.DTO.EnderecoDTO;
import TarefaCrud.demo.DTO.PacienteDTO;
import TarefaCrud.demo.DTO.MensagemDTO;
import TarefaCrud.demo.DTO.PacienteDTO;
import TarefaCrud.demo.Entity.Endereco;
import TarefaCrud.demo.Entity.Paciente;
import TarefaCrud.demo.Entity.Paciente;
import TarefaCrud.demo.Respository.PacienteRepository;
import com.fasterxml.jackson.core.PrettyPrinter;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class PacienteServices {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private LocalStorage localStorage;

    public PacienteDTO findPacienteById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado!"));
        return pacienteToDTO(paciente);
    }

    public List<PacienteDTO> listarPacientes() {
        return pacienteRepository.findAll().stream().map(this::pacienteToDTO).toList();
    }

    public MensagemDTO cadastrarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = DTOToPaciente(pacienteDTO);

        paciente.setUserCriacao(this.localStorage.userName);
        paciente.setDataHoraCriacao(LocalDateTime.now());


        pacienteRepository.save(paciente);
        return new MensagemDTO("Paciente cadastrada com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarPaciente(Long id, PacienteDTO pacienteDTO) {
        pacienteDTO.setId(id);
        Paciente paciente = DTOToPaciente(pacienteDTO);

        paciente.setUserAlteracao(this.localStorage.userName);
        paciente.setDataHoraAlteracao(LocalDateTime.now());


        pacienteRepository.save(paciente);
        return new MensagemDTO("Paciente atualizada com sucesso!", HttpStatus.CREATED);
    }

    public MensagemDTO deletarPaciente(Long id) {
        Paciente pacienteBanco = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente com ID " + id + " não existe!"));

        deletar(pacienteBanco);
        return new MensagemDTO("Paciente deledata com sucesso!", HttpStatus.CREATED);
    }

    private void deletar(Paciente paciente) {
        paciente.setUserExclusao(this.localStorage.userName);
        paciente.setDataHoraExclusao(LocalDateTime.now());

        pacienteRepository.save(paciente);
    }

    public PacienteDTO pacienteToDTO(Paciente paciente){
        PacienteDTO pacienteDTO = new PacienteDTO();

        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setCpf(paciente.getCpf());
        pacienteDTO.setEmail(paciente.getEmail());
        pacienteDTO.setTelefone(paciente.getTelefone());

        List<EnderecoDTO> listaEnderecoDTO = new ArrayList<>();
        if(paciente.getEnderecos() != null)
            for(int i=0; i<paciente.getEnderecos().size(); i++){
                listaEnderecoDTO.add(enderecoToDTO(paciente.getEnderecos().get(i)));
            }
        pacienteDTO.setEnderecos(listaEnderecoDTO);

        return pacienteDTO;
    }

    public EnderecoDTO enderecoToDTO(Endereco endereco){
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setNumero(endereco.getNumero());

        PacienteDTO pacienteDTO = new PacienteDTO();
        if (endereco.getPaciente() != null){
            pacienteDTO.setId(endereco.getPaciente().getId());
            enderecoDTO.setPaciente(pacienteDTO);
        }
        return enderecoDTO;
    }

    public Paciente DTOToPaciente(PacienteDTO pacienteDTO){
        Paciente novoPaciente = new Paciente();

        novoPaciente.setId(pacienteDTO.getId());
        novoPaciente.setNome(pacienteDTO.getNome());
        novoPaciente.setCpf(pacienteDTO.getCpf());
        novoPaciente.setEmail(pacienteDTO.getEmail());
        novoPaciente.setTelefone(pacienteDTO.getTelefone());

        List<Endereco> listaEndereco = new ArrayList<>();
        if(pacienteDTO.getEnderecos() != null)
            for(int i=0; i<pacienteDTO.getEnderecos().size(); i++){
                listaEndereco.add(DTOToEndereco(pacienteDTO.getEnderecos().get(i)));
            }
        novoPaciente.setEnderecos(listaEndereco);

        return novoPaciente;
    }

    public Endereco DTOToEndereco(EnderecoDTO enderecoDTO){
        Endereco novaEndereco = new Endereco();

        novaEndereco.setId(enderecoDTO.getId());
        novaEndereco.setCep(enderecoDTO.getCep());
        novaEndereco.setRua(enderecoDTO.getRua());
        novaEndereco.setCidade(enderecoDTO.getCidade());
        novaEndereco.setEstado(enderecoDTO.getEstado());
        novaEndereco.setNumero(enderecoDTO.getNumero());

        Paciente paciente = new Paciente();
        if (enderecoDTO.getPaciente() != null){
            paciente.setId(enderecoDTO.getPaciente().getId());
            novaEndereco.setPaciente(paciente);
        }

        return novaEndereco;
    }
}