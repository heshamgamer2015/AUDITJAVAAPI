package TarefaCrud.demo.Respository;

import java.util.List;
import java.util.Optional;

import TarefaCrud.demo.Entity.Consulta;
import TarefaCrud.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginRepository extends JpaRepository<User, Long>{
    @Query("FROM User WHERE ativo = true AND username = :login")
    public Optional<User> findByUsername(String login);

    @Query("FROM User WHERE ativo = true")
    List<User> findUserByAtivo();

    @Query(value = "SELECT password FROM usuario WHERE id = :id",nativeQuery = true)
    String findSenhaById(Long id);

    @Query("SELECT p FROM User p WHERE p.dataHoraAlteracao IS NOT NULL")
    List<User> findUserAlterados();

    @Query("SELECT p FROM User p WHERE p.dataHoraCriacao IS NOT NULL")
    List<User> findUserCadastrados();

    @Query("SELECT p FROM User p WHERE p.dataHoraExclusao IS NOT NULL")
    List<User> findUserExcluidos();
}
