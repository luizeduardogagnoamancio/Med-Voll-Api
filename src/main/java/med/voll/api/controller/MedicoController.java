package med.voll.api.controller;

import lombok.extern.log4j.Log4j2;
import med.voll.api.dto.MedicoDto;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;
    @PostMapping(value = "cadastrar")
    public ResponseEntity<Void> cadastrar(
            @RequestBody @Validated MedicoDto medico) {
        log.info("POST - entrou no endpoint medico/cadastrar");
        medicoService.cadastarMedico(medico);
        log.info("POST - saindo do endpoint campanha/apurar");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
