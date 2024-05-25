package TarefaCrud.demo.Respository;

import TarefaCrud.demo.Entity.Consulta;
import TarefaCrud.demo.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE p.dataHoraAlteracao IS NOT NULL")
    List<Paciente> findPacienteAlterados();

    @Query("SELECT p FROM Paciente p WHERE p.dataHoraCriacao IS NOT NULL")
    List<Paciente> findPacienteCadastrados();

    @Query("SELECT p FROM Paciente p WHERE p.dataHoraExclusao IS NOT NULL")
    List<Paciente> findPacienteExcluidos();
}
