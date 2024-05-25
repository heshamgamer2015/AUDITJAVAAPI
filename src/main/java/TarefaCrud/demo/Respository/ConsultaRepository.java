package TarefaCrud.demo.Respository;

import TarefaCrud.demo.Entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    @Query("SELECT p FROM Consulta p WHERE p.dataHoraAlteracao IS NOT NULL")
    List<Consulta> findConsultaAlterados();

    @Query("SELECT p FROM Consulta p WHERE p.dataHoraCriacao IS NOT NULL")
    List<Consulta> findConsultaCadastrados();

    @Query("SELECT p FROM Consulta p WHERE p.dataHoraExclusao IS NOT NULL")
    List<Consulta> findConsultaExcluidos();

}
