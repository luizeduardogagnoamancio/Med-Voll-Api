package med.voll.api.repository;

import med.voll.api.dto.MedicoDtoListagem;
import med.voll.api.entity.MedicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    Optional<MedicoEntity> findByCrm(String crm);

    @Query("SELECT new med.voll.api.dto.MedicoDtoListagem(m.nome, m.email, m.crm, m.especialidade) FROM MedicoEntity m")
    Page<MedicoDtoListagem> listarMedicos(Pageable pageable);

    List<MedicoEntity> findAllByEspecialidade(String especialidade);
}
