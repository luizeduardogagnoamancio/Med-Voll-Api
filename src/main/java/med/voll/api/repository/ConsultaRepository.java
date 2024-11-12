package med.voll.api.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import med.voll.api.entity.ConsultaEntity;
import med.voll.api.entity.MedicoEntity;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {
  boolean existsByHorarioConsulta(Date horarioConsulta);

  @Query("SELECT c FROM ConsultaEntity c " +
      "JOIN c.paciente p " +
      "WHERE p.cpf = :cpfPaciente " +
      "AND FUNCTION('DATE', c.horarioConsulta) = :data")
  List<ConsultaEntity> findByCpfAndDataConsulta(@Param("cpfPaciente") String cpfPaciente,
      @Param("data") LocalDate data);

  List<ConsultaEntity> findAllByPacienteCpf(String cpfPaciente);

  boolean existsByMedicoAndHorarioConsulta(MedicoEntity medico, Date horarioConsulta);
}
