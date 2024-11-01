package med.voll.api.service.impl;

import org.springframework.stereotype.Service;
import med.voll.api.request.ConsultaRequestDto;
import med.voll.api.service.ConsultaService;
import med.voll.api.validator.DateValidator;

@Service
public class ConsultaServiceImpl implements ConsultaService {

  @Override
  public void agendarConsulta(ConsultaRequestDto consultaRequestDto) {
    DateValidator.validarHorarioAgendamento(consultaRequestDto.getHorarioConsulta());

  }

}
