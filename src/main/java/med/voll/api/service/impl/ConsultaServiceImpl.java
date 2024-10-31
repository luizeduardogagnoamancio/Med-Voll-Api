package med.voll.api.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;
import med.voll.api.request.ConsultaRequestDto;
import med.voll.api.service.ConsultaService;
import med.voll.api.utils.DateUtils;

@Service
public class ConsultaServiceImpl implements ConsultaService {

  @Override
  public void agendarConsulta(ConsultaRequestDto consultaRequestDto) {
    validarHorario(consultaRequestDto.getHorarioConsulta());

  }

  private void validarHorario(Date horarioConsulta) {
    LocalDateTime consultaDateTime = horarioConsulta.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    DateUtils.validarHorarioConsulta(consultaDateTime);
  }
}
