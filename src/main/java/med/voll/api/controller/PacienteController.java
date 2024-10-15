package med.voll.api.controller;

import lombok.extern.log4j.Log4j2;
import med.voll.api.dto.PacienteDto;
import med.voll.api.dto.PacienteDtoListagem;
import med.voll.api.dto.SolicitacaoPacienteAtualizarDto;
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
    public Page<PacienteDtoListagem> listarPacientesPorPagina(@RequestParam(defaultValue = "0") int pagina) {
        log.info("GET - entrou no endpoint medico/listar");
        return pacienteService.listarPacientes(pagina);
    }

    @PutMapping(value = "atualizar/{cpf}")
    public ResponseEntity<Void> atualizarPaciente(
            @PathVariable String cpf,
            @RequestBody SolicitacaoPacienteAtualizarDto solicitacaoPacienteAtualizarDto) {
        log.info("PUT - entrou no endpoint paciente/atualizar");
        pacienteService.atualizarPaciente(cpf, solicitacaoPacienteAtualizarDto);
        log.info("PUT - saindo do endpoint paciente/atualizar");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "excluir/{cpf}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable String cpf) {
        log.info("DELETE - entrou no endpoint paciente/excluir");
        pacienteService.excluirPaciente(cpf);
        log.info("DELETE - saindo do endpoint paciente/excluir");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
