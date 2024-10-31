package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.log4j.Log4j2;
import med.voll.api.request.ConsultaRequestDto;
import med.voll.api.service.ConsultaService;

@Log4j2
@RestController
@RequestMapping("/consulta")
public class ConsultaController {

  @Autowired
  private ConsultaService consultaService;

  @PostMapping(value = "agendar")
  public ResponseEntity<Void> agendarConsulta(@RequestBody
      @Validated ConsultaRequestDto consultaRequestDto) {
    log.info("POST - entrou no endpoint consulta/agendar");
    consultaService.agendarConsulta(consultaRequestDto);
    log.info("POST - saindo do endpoint consulta/agendar");
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
