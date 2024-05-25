package TarefaCrud.demo.Respository;

import TarefaCrud.demo.Entity.Consulta;
import TarefaCrud.demo.Entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {
    @Query("SELECT p FROM Endereco p WHERE p.dataHoraAlteracao IS NOT NULL")
    List<Endereco> findEnderecoAlterados();

    @Query("SELECT p FROM Endereco p WHERE p.dataHoraCriacao IS NOT NULL")
    List<Endereco> findEnderecoCadastrados();

    @Query("SELECT p FROM Endereco p WHERE p.dataHoraExclusao IS NOT NULL")
    List<Endereco> findEnderecoExcluidos();
}
