package TarefaCrud.demo.Services;

import TarefaCrud.demo.Config.LocalStorage;
import TarefaCrud.demo.DTO.EnderecoDTO;
import TarefaCrud.demo.DTO.MensagemDTO;
import TarefaCrud.demo.DTO.PacienteDTO;
import TarefaCrud.demo.Entity.Endereco;
import TarefaCrud.demo.Entity.Endereco;
import TarefaCrud.demo.Entity.Paciente;
import TarefaCrud.demo.Respository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnderecoServices {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private LocalStorage localStorage;

    public EnderecoDTO findEnderecoById(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereco não encontrado!"));
        return enderecoToDTO(endereco);
    }

    public List<EnderecoDTO> listarEnderecos() {
        return enderecoRepository.findAll().stream().map(this::enderecoToDTO).toList();
    }

    public MensagemDTO cadastrarEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = DTOToEndereco(enderecoDTO);

        endereco.setUserCriacao(this.localStorage.userName);
        endereco.setDataHoraCriacao(LocalDateTime.now());


        enderecoRepository.save(endereco);
        return new MensagemDTO("Endereco cadastrado com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarEndereco(Long id, EnderecoDTO enderecoDTO) {
        enderecoDTO.setId(id);
        Endereco endereco = DTOToEndereco(enderecoDTO);

        endereco.setUserAlteracao(this.localStorage.userName);
        endereco.setDataHoraAlteracao(LocalDateTime.now());


        enderecoRepository.save(endereco);
        return new MensagemDTO("Endereco atualizado com sucesso!", HttpStatus.CREATED);
    }

    public MensagemDTO deletarEndereco(Long id) {
        Endereco enderecoBanco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereco com ID " + id + " não existe!"));

        deletar(enderecoBanco);
        return new MensagemDTO("Endereco deledato com sucesso!", HttpStatus.CREATED);
    }

    private void deletar(Endereco endereco) {
        endereco.setUserExclusao(this.localStorage.userName);
        endereco.setDataHoraExclusao(LocalDateTime.now());

        enderecoRepository.save(endereco);
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