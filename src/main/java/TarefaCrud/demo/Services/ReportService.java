package TarefaCrud.demo.Services;

import TarefaCrud.demo.DTO.ReportDTO;
import TarefaCrud.demo.Entity.Consulta;
import TarefaCrud.demo.Entity.Endereco;
import TarefaCrud.demo.Entity.Paciente;
import TarefaCrud.demo.Entity.User;
import TarefaCrud.demo.Respository.ConsultaRepository;
import TarefaCrud.demo.Respository.EnderecoRepository;
import TarefaCrud.demo.Respository.LoginRepository;
import TarefaCrud.demo.Respository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private LoginRepository loginRepository;

    public List<ReportDTO> listar() {
        List<ReportDTO> listaReports = new ArrayList<>();

        // PRODUTOS ALTERADOS
        List<Consulta> listaConsultasCadastradas = this.consultaRepository.findConsultaCadastrados();
        List<Consulta> listaConsultasAlteradas = this.consultaRepository.findConsultaAlterados();
        List<Consulta> listaConsultasExcluidas = this.consultaRepository.findConsultaExcluidos();

        List<Paciente> listaPacientesCadastrados = this.pacienteRepository.findPacienteCadastrados();
        List<Paciente> listaPacientesAlterados = this.pacienteRepository.findPacienteAlterados();
        List<Paciente> listaPacientesExcluidos = this.pacienteRepository.findPacienteExcluidos();

        List<Endereco> listaEnderecosCadastrados = this.enderecoRepository.findEnderecoCadastrados();
        List<Endereco> listaEnderecosAlterados = this.enderecoRepository.findEnderecoAlterados();
        List<Endereco> listaEnderecosExcluidos = this.enderecoRepository.findEnderecoExcluidos();

        List<User> listaUsersCadastrados = this.loginRepository.findUserCadastrados();
        List<User> listaUsersAlterados = this.loginRepository.findUserAlterados();
        List<User> listaUsersExcluidos = this.loginRepository.findUserExcluidos();

        
//CONSULTAS
        
        if(listaConsultasCadastradas != null){
            for(int i=0; i<listaConsultasCadastradas.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Consulta");
                report.setConsulta(listaConsultasCadastradas.get(i));
                report.setMensagem("Consulta de id: "+listaConsultasCadastradas.get(i).getId()+" CADASTRADO em "
                        +listaConsultasCadastradas.get(i).getDataHoraCriacao()+ " POR "+ listaConsultasCadastradas.get(i).getUserCriacao());
                report.setDataHoraCriacao(listaConsultasCadastradas.get(i).getDataHoraCriacao());
                report.setUserCriacao(listaConsultasCadastradas.get(i).getUserCriacao());

                listaReports.add(report);
            }
        }


        if(listaConsultasAlteradas != null){
            for(int i=0; i<listaConsultasAlteradas.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Consulta");
                report.setConsulta(listaConsultasAlteradas.get(i));
                report.setMensagem("Consulta de id: "+listaConsultasAlteradas.get(i).getId()+" ALTERADO em "
                        +listaConsultasAlteradas.get(i).getDataHoraAlteracao()+ " POR "+ listaConsultasAlteradas.get(i).getUserAlteracao());
                report.setDataHoraAlteracao(listaConsultasAlteradas.get(i).getDataHoraAlteracao());
                report.setUserAlteracao(listaConsultasAlteradas.get(i).getUserAlteracao());

                listaReports.add(report);
            }
        }

        if(listaConsultasExcluidas != null){
            for(int i=0; i<listaConsultasExcluidas.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Consulta");
                report.setConsulta(listaConsultasExcluidas.get(i));
                report.setMensagem("Consulta de id: "+listaConsultasExcluidas.get(i).getId()+" EXCLUIDO em "
                        +listaConsultasExcluidas.get(i).getDataHoraExclusao()+ " POR "+ listaConsultasExcluidas.get(i).getUserExclusao());
                report.setDataHoraExclusao(listaConsultasExcluidas.get(i).getDataHoraExclusao());
                report.setUserExclusao(listaConsultasExcluidas.get(i).getUserExclusao());

                listaReports.add(report);
            }
        }

//PACIENTE

        if(listaPacientesCadastrados != null){
            for(int i=0; i<listaPacientesCadastrados.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Paciente");
                report.setPaciente(listaPacientesCadastrados.get(i));
                report.setMensagem("Paciente de id: "+listaPacientesCadastrados.get(i).getId()+" CADASTRADO em "
                        +listaPacientesCadastrados.get(i).getDataHoraCriacao()+ " POR "+ listaPacientesCadastrados.get(i).getUserCriacao());
                report.setDataHoraCriacao(listaPacientesCadastrados.get(i).getDataHoraCriacao());
                report.setUserCriacao(listaPacientesCadastrados.get(i).getUserCriacao());

                listaReports.add(report);
            }
        }


        if(listaPacientesAlterados != null){
            for(int i=0; i<listaPacientesAlterados.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Paciente");
                report.setPaciente(listaPacientesAlterados.get(i));
                report.setMensagem("Paciente de id: "+listaPacientesAlterados.get(i).getId()+" ALTERADO em "
                        +listaPacientesAlterados.get(i).getDataHoraAlteracao()+ " POR "+ listaPacientesAlterados.get(i).getUserAlteracao());
                report.setDataHoraAlteracao(listaPacientesAlterados.get(i).getDataHoraAlteracao());
                report.setUserAlteracao(listaPacientesAlterados.get(i).getUserAlteracao());

                listaReports.add(report);
            }
        }

        if(listaPacientesExcluidos != null){
            for(int i=0; i<listaPacientesExcluidos.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Paciente");
                report.setPaciente(listaPacientesExcluidos.get(i));
                report.setMensagem("Paciente de id: "+listaPacientesExcluidos.get(i).getId()+" EXCLUIDO em "
                        +listaPacientesExcluidos.get(i).getDataHoraExclusao()+ " POR "+ listaPacientesExcluidos.get(i).getUserExclusao());
                report.setDataHoraExclusao(listaPacientesExcluidos.get(i).getDataHoraExclusao());
                report.setUserExclusao(listaPacientesExcluidos.get(i).getUserExclusao());

                listaReports.add(report);
            }
        }

//ENDERECOS

        if(listaEnderecosCadastrados != null){
            for(int i=0; i<listaEnderecosCadastrados.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Endereco");
                report.setEndereco(listaEnderecosCadastrados.get(i));
                report.setMensagem("Endereco de id: "+listaEnderecosCadastrados.get(i).getId()+" CADASTRADO em "
                        +listaEnderecosCadastrados.get(i).getDataHoraCriacao()+ " POR "+ listaEnderecosCadastrados.get(i).getUserCriacao());
                report.setDataHoraCriacao(listaEnderecosCadastrados.get(i).getDataHoraCriacao());
                report.setUserCriacao(listaEnderecosCadastrados.get(i).getUserCriacao());

                listaReports.add(report);
            }
        }


        if(listaEnderecosAlterados != null){
            for(int i=0; i<listaEnderecosAlterados.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Endereco");
                report.setEndereco(listaEnderecosAlterados.get(i));
                report.setMensagem("Endereco de id: "+listaEnderecosAlterados.get(i).getId()+" ALTERADO em "
                        +listaEnderecosAlterados.get(i).getDataHoraAlteracao()+ " POR "+ listaEnderecosAlterados.get(i).getUserAlteracao());
                report.setDataHoraAlteracao(listaEnderecosAlterados.get(i).getDataHoraAlteracao());
                report.setUserAlteracao(listaEnderecosAlterados.get(i).getUserAlteracao());

                listaReports.add(report);
            }
        }

        if(listaEnderecosExcluidos != null){
            for(int i=0; i<listaEnderecosExcluidos.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Endereco");
                report.setEndereco(listaEnderecosExcluidos.get(i));
                report.setMensagem("Endereco de id: "+listaEnderecosExcluidos.get(i).getId()+" EXCLUIDO em "
                        +listaEnderecosExcluidos.get(i).getDataHoraExclusao()+ " POR "+ listaEnderecosExcluidos.get(i).getUserExclusao());
                report.setDataHoraExclusao(listaEnderecosExcluidos.get(i).getDataHoraExclusao());
                report.setUserExclusao(listaEnderecosExcluidos.get(i).getUserExclusao());

                listaReports.add(report);
            }
        }
//USUARIOS

        if(listaUsersCadastrados != null){
            for(int i=0; i<listaUsersCadastrados.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Usuario");
                report.setUser(listaUsersCadastrados.get(i));
                report.setMensagem("Usuario de id: "+listaUsersCadastrados.get(i).getId()+" CADASTRADO em "
                        +listaUsersCadastrados.get(i).getDataHoraCriacao()+ " POR "+ listaUsersCadastrados.get(i).getUserCriacao());
                report.setDataHoraCriacao(listaUsersCadastrados.get(i).getDataHoraCriacao());
                report.setUserCriacao(listaUsersCadastrados.get(i).getUserCriacao());

                listaReports.add(report);
            }
        }


        if(listaUsersAlterados != null){
            for(int i=0; i<listaUsersAlterados.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Usuario");
                report.setUser(listaUsersAlterados.get(i));
                report.setMensagem("Usuario de id: "+listaUsersAlterados.get(i).getId()+" ALTERADO em "
                        +listaUsersAlterados.get(i).getDataHoraAlteracao()+ " POR "+ listaUsersAlterados.get(i).getUserAlteracao());
                report.setDataHoraAlteracao(listaUsersAlterados.get(i).getDataHoraAlteracao());
                report.setUserAlteracao(listaUsersAlterados.get(i).getUserAlteracao());

                listaReports.add(report);
            }
        }

        if(listaUsersExcluidos != null){
            for(int i=0; i<listaUsersExcluidos.size(); i++){
                ReportDTO report = new ReportDTO();
                report.setTipo("Usuario");
                report.setUser(listaUsersExcluidos.get(i));
                report.setMensagem("Usuario de id: "+listaUsersExcluidos.get(i).getId()+" EXCLUIDO em "
                        +listaUsersExcluidos.get(i).getDataHoraExclusao()+ " POR "+ listaUsersExcluidos.get(i).getUserExclusao());
                report.setDataHoraExclusao(listaUsersExcluidos.get(i).getDataHoraExclusao());
                report.setUserExclusao(listaUsersExcluidos.get(i).getUserExclusao());

                listaReports.add(report);
            }
        }
        return listaReports;
    }
}