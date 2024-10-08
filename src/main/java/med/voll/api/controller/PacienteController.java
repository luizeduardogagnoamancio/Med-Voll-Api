package med.voll.api.controller;

import lombok.extern.log4j.Log4j2;
import med.voll.api.dto.MedicoDto;
import med.voll.api.dto.PacienteDto;
import med.voll.api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping(value = "cadastrar")
    public ResponseEntity<Void> cadastrar(
            @RequestBody @Validated PacienteDto pacienteDto) {
        log.info("POST - entrou no endpoint paciente/cadastrar");
        pacienteService.cadastarPaciente(pacienteDto);
        log.info("POST - saindo do endpoint paciente/cadastrar");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "listar")
    public Page<PacienteDto> listarPacientesPorPagina(@RequestParam(defaultValue = "0") int pagina) {
        log.info("GET - entrou no endpoint medico/listar");
        return pacienteService.listarPacientes(pagina);
    }

}
