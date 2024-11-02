package med.voll.api.component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import med.voll.api.entity.MedicoEntity;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;
import med.voll.api.entity.ConsultaEntity;
import med.voll.api.exception.ConsultaExistenteException;
import med.voll.api.exception.DiaInvalidoException;
import med.voll.api.exception.HorarioIndisponivelException;
import med.voll.api.exception.HorarioInvalidoException;
import med.voll.api.exception.IntervaloInvalidoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.utils.DateUtils;

@Log4j2
@Component
public class ConsultaComponent {

  private final ConsultaRepository consultaRepository;

  private final PacienteComponent pacienteComponent;

  private final MedicoComponent medicoComponent;

  public ConsultaComponent(ConsultaRepository consultaRepository, PacienteComponent pacienteComponent, MedicoComponent medicoComponent) {
    this.consultaRepository = consultaRepository;
    this.pacienteComponent = pacienteComponent;
    this.medicoComponent = medicoComponent;
  }

  public boolean buscaConsultaPorData(Date dataConsulta) {
    log.info("Entrou no Component para realizar a busca da consulta por data.");
    return this.consultaRepository.existsByHorarioConsulta(dataConsulta);
  }

  public void cadastraConsulta(ConsultaEntity consulta){
    log.info("Entrou no component para realizar o cadastro da consulta");
    this.consultaRepository.save(consulta);
  }

  public void validarPacienteConsulta(String cpfPaciente) {
    if (Objects.isNull(pacienteComponent.buscarPorCpf(cpfPaciente))) {
      throw new RuntimeException("Paciente não cadastrado no sistema.");
    }

    List<ConsultaEntity> consultas = consultaRepository.findAllByPacienteCpf(cpfPaciente);

    Map<LocalDate, List<ConsultaEntity>> consultasPorData = consultas.stream()
        .collect(Collectors.groupingBy(consulta -> consulta.getHorarioConsulta()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()));

    LocalDate hoje = LocalDate.now();
    if (consultasPorData.containsKey(hoje)) {
      throw new ConsultaExistenteException("O paciente já possui uma consulta marcada para hoje.");
    }
  }


  public void validarHorarioAgendamento(Date horarioConsulta) {
    log.info("Entrou no Validator para realizar as validações do horário da consulta");
    LocalDateTime consultaDateTime = horarioConsulta.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    if (DateUtils.isHorarioValido(consultaDateTime.toLocalTime())) {
      throw new HorarioInvalidoException("Horário da consulta deve estar entre 07:00 e 19:00.");
    }

    if (DateUtils.isIntervaloValido(consultaDateTime)) {
      throw new IntervaloInvalidoException(
          "A consulta deve ser agendada pelo menos 30 minutos antes do horário de atendimento.");
    }

    if (DateUtils.isDiaSemanaValido(consultaDateTime.getDayOfWeek())) {
      throw new DiaInvalidoException("As consultas só podem ser marcadas de segunda à sábado.");
    }

    if (buscaConsultaPorData(horarioConsulta)) {
      throw new HorarioIndisponivelException("Horário já ocupado para consulta.");
    }
  }

  public void validarMedicoConsulta(String especialidade, Date horarioConsulta) {
    MedicoEntity medico = medicoComponent.buscarPorEspecialidade(especialidade);


    if (Objects.isNull(medico)) {
      throw new RuntimeException("Não foi encontrado médico com a especialidade desejada");
    }

  }
}
