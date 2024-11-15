package med.voll.api.controller;

import lombok.extern.log4j.Log4j2;
import med.voll.api.request.MedicoRequestDto;
import med.voll.api.dto.MedicoDtoListagem;
import med.voll.api.dto.SolicitacaoMedicoAtualizarDto;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping(value = "cadastrar")
    public ResponseEntity<Void> cadastrar(
            @RequestBody @Validated MedicoRequestDto medico) {
        log.info("POST - entrou no endpoint medico/cadastrar");
        medicoService.cadastarMedico(medico);
        log.info("POST - saindo do endpoint medico/cadastrar");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "listar")
    public Page<MedicoDtoListagem> listarMedicosPorPagina(@RequestParam(defaultValue = "0") int pagina) {
        log.info("GET - entrou no endpoint medico/listar");
        return medicoService.listarMedicos(pagina);
    }

    @PutMapping(value = "atualizar/{crm}")
    public ResponseEntity<Void> atualizarMedico(
            @PathVariable String crm,
            @RequestBody SolicitacaoMedicoAtualizarDto solicitacaoMedicoAtualizarDto) {
        log.info("PUT - entrou no endpoint medico/atualizar");
        medicoService.atualizarMedico(crm, solicitacaoMedicoAtualizarDto);
        log.info("PUT - saindo do endpoint medico/atualizar");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "excluir/{crm}")
    public ResponseEntity<Void> excluirMedico(@PathVariable String crm) {
        log.info("DELETE - entrou no endpoint medico/excluir");
        medicoService.excluirMedico(crm);
        log.info("DELETE - saindo do endpoint medico/excluir");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
