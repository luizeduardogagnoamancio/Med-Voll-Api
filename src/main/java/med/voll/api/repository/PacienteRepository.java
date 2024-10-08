package med.voll.api.repository;

import med.voll.api.dto.PacienteDto;
import med.voll.api.entity.PacienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    @Query("SELECT p.nome, p.email, p.cpf FROM PacienteEntity p")
    Page<PacienteDto> listarPacientes(Pageable pageable);
}
