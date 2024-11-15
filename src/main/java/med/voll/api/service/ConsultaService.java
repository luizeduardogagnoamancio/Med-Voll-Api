package med.voll.api.service;

import med.voll.api.request.ConsultaCancelarRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import med.voll.api.request.ConsultaRequestDto;

public interface ConsultaService {

  @Autowired
  void agendarConsulta(ConsultaRequestDto consultaRequestDto);

  @Autowired
  void cancelarConsulta(ConsultaCancelarRequestDto consultaCancelarRequestDto);
}
