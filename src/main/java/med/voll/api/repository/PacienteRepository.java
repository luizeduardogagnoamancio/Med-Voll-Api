package med.voll.api.repository;

import med.voll.api.dto.PacienteDtoListagem;
import med.voll.api.entity.PacienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    Optional<PacienteEntity> findByCpf(String cpf);

    @Query("SELECT new med.voll.api.dto.PacienteDtoListagem(p.nome, p.email, p.cpf) FROM PacienteEntity p")
    Page<PacienteDtoListagem> listarPacientes(Pageable pageable);
}
