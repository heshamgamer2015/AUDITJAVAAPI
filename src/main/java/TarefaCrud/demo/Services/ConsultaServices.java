package TarefaCrud.demo.Services;

import TarefaCrud.demo.Config.LocalStorage;
import TarefaCrud.demo.DTO.ConsultaDTO;
import TarefaCrud.demo.DTO.MensagemDTO;
import TarefaCrud.demo.DTO.PacienteDTO;
import TarefaCrud.demo.Entity.Consulta;
import TarefaCrud.demo.Entity.Paciente;
import TarefaCrud.demo.Respository.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaServices {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private LocalStorage localStorage;

    public ConsultaDTO findConsultaById(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrado!"));
        return consultaToDTO(consulta);
    }

    public List<ConsultaDTO> listarConsultas() {
        return consultaRepository.findAll().stream().map(this::consultaToDTO).toList();
    }

    public MensagemDTO cadastrarConsulta(ConsultaDTO consultaDTO) {
        Consulta consulta = DTOToConsulta(consultaDTO);

        consulta.setUserCriacao(this.localStorage.userName);
        consulta.setDataHoraCriacao(LocalDateTime.now());


        consultaRepository.save(consulta);
        return new MensagemDTO("Consulta cadastrada com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarConsulta(Long id, ConsultaDTO consultaDTO) {
        consultaDTO.setId(id);
        Consulta consulta = DTOToConsulta(consultaDTO);

        consulta.setUserAlteracao(this.localStorage.userName);
        consulta.setDataHoraAlteracao(LocalDateTime.now());


        consultaRepository.save(consulta);
        return new MensagemDTO("Consulta atualizada com sucesso!", HttpStatus.CREATED);
    }

    public MensagemDTO deletarConsulta(Long id) {
        Consulta consultaBanco = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta com ID " + id + " não existe!"));

        deletar(consultaBanco);
        return new MensagemDTO("Consulta deledata com sucesso!", HttpStatus.CREATED);
    }

    private void deletar(Consulta consulta) {
        consulta.setUserExclusao(this.localStorage.userName);
        consulta.setDataHoraExclusao(LocalDateTime.now());

        consultaRepository.save(consulta);
    }
    
    public ConsultaDTO consultaToDTO(Consulta consulta){
        ConsultaDTO consultaDTO = new ConsultaDTO();

        consultaDTO.setId(consulta.getId());
        consultaDTO.setData(consulta.getData());
        
        PacienteDTO pacienteDTO = new PacienteDTO();
        if (consulta.getPaciente() != null){
            pacienteDTO.setId(consulta.getPaciente().getId());
            consultaDTO.setPaciente(pacienteDTO);
        }
        return consultaDTO;
    }
    
    public Consulta DTOToConsulta(ConsultaDTO consultaDTO){
        Consulta novaConsulta = new Consulta();

        novaConsulta.setId(consultaDTO.getId());
        novaConsulta.setData(consultaDTO.getData());
        
        Paciente paciente = new Paciente();
        if (consultaDTO.getPaciente() != null){
            paciente.setId(consultaDTO.getPaciente().getId());
            novaConsulta.setPaciente(paciente);
        }

        return novaConsulta;
    }
}