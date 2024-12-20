package med.voll.api.service.impl;

import med.voll.api.request.ConsultaCancelarRequestDto;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;
import med.voll.api.component.ConsultaComponent;
import med.voll.api.request.ConsultaRequestDto;
import med.voll.api.service.ConsultaService;

@Log4j2
@Service
public class ConsultaServiceImpl implements ConsultaService {
  private final ConsultaComponent consultaComponent;

  public ConsultaServiceImpl(ConsultaComponent consultaComponent) {
      this.consultaComponent = consultaComponent;
  }

  @Override
  public void agendarConsulta(ConsultaRequestDto consultaRequestDto) {
      log.info("Entrou no Service para realizar o agendamento da consulta.");
      this.consultaComponent.cadastrarConsulta(consultaRequestDto);

  }

    @Override
    public void cancelarConsulta(ConsultaCancelarRequestDto consultaCancelarRequestDto) {
      log.info("Entrou no Service para realiar o cancelamento da consulta");
      this.consultaComponent.cancelarConsulta(consultaCancelarRequestDto);
    }

}
