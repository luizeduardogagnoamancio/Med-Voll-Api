package med.voll.api.component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import med.voll.api.entity.MedicoEntity;
import med.voll.api.request.ConsultaCancelarRequestDto;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;
import med.voll.api.entity.ConsultaEntity;
import med.voll.api.entity.PacienteEntity;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.request.ConsultaRequestDto;
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

  public void cadastrarConsulta(ConsultaRequestDto consultaRequestDto) {
    log.info("Entrou no component para validar a consulta");
    this.validarHorarioAgendamento(consultaRequestDto.getHorarioConsulta());
    PacienteEntity paciente = this.validarPacienteConsulta(consultaRequestDto.getCpfPaciente());
    MedicoEntity medico = this.buscarMedicoConsulta(consultaRequestDto.getEspecialidade(), consultaRequestDto.getHorarioConsulta());
    ConsultaEntity consulta = this.consultaBuilder(consultaRequestDto, medico, paciente);
    this.cadastra(consulta);
  }

  public boolean buscaConsultaPorData(Date dataConsulta) {
    log.info("Entrou no Component para realizar a busca da consulta por data.");
    return this.consultaRepository.existsByHorarioConsulta(dataConsulta);
  }

  public void cadastra(ConsultaEntity consulta){
    log.info("Entrou no component para realizar o cadastro da consulta");

    this.consultaRepository.save(consulta);
  }

  private ConsultaEntity consultaBuilder(ConsultaRequestDto consultaRequestDto, MedicoEntity medico, PacienteEntity paciente) {
    ConsultaEntity consulta = new ConsultaEntity();
    consulta.setHorarioConsulta(consultaRequestDto.getHorarioConsulta());
    consulta.setPaciente(paciente);
    consulta.setMedico(medico);
    
    return consulta;
  }

  public PacienteEntity validarPacienteConsulta(String cpfPaciente) {
    PacienteEntity paciente = pacienteComponent.buscarPorCpf(cpfPaciente);
    if (Objects.isNull(paciente)) {
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
      throw new RuntimeException("O paciente já possui uma consulta marcada para hoje.");
    }
    
    return paciente;
  }


  public void validarHorarioAgendamento(Date horarioConsulta) {
    log.info("Entrou no Validator para realizar as validações do horário da consulta");
    LocalDateTime consultaDateTime = horarioConsulta.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    if (DateUtils.isHorarioInValido(consultaDateTime.toLocalTime())) {
      throw new RuntimeException("Horário da consulta deve estar entre 07:00 e 19:00.");
    }

    if (DateUtils.isIntervaloInValido(consultaDateTime)) {
      throw new RuntimeException("A consulta deve ser agendada pelo menos 30 minutos antes do horário de atendimento.");
    }

    if (DateUtils.isDiaSemanaInValido(consultaDateTime.getDayOfWeek())) {
      throw new RuntimeException("As consultas só podem ser marcadas de segunda à sábado.");
    }

    if (buscaConsultaPorData(horarioConsulta)) {
      throw new RuntimeException("Horário já ocupado para consulta.");
    }
  }

  public MedicoEntity buscarMedicoConsulta(String especialidade, Date horarioConsulta) {
    List<MedicoEntity> medicos = medicoComponent.buscarMedicoDisponivel(especialidade);
    
    for (MedicoEntity medico : medicos) {
      boolean consultaExistente = consultaRepository.existsByMedicoAndHorarioConsulta(medico, horarioConsulta);
      if (!consultaExistente) {
        return medico;
      }
    }

    throw new RuntimeException("Nenhum médico disponível com a especialidade desejada para o horário solicitado");
  }

  private ConsultaEntity buscarPorId(Long id) {
    return consultaRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe consulta com esse Id"));
  }

  private void validarConsulta(Date horarioConsulta) {
    LocalDateTime horarioDateTime = horarioConsulta.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();

    if (horarioDateTime.isBefore(LocalDateTime.now().plusHours(24))) {
      throw new RuntimeException("A consulta deve ser agendada com pelo menos 24 horas de antecedência");
    }
  }

  private void excluirConsulta(ConsultaEntity consulta) {
    consultaRepository.delete(consulta);
  }

  public void cancelarConsulta(ConsultaCancelarRequestDto consultaCancelarRequestDto) {
    ConsultaEntity consulta = buscarPorId(consultaCancelarRequestDto.getConsultaId());
    this.validarConsulta(consulta.getHorarioConsulta());
    excluirConsulta(consulta);
  }
}
