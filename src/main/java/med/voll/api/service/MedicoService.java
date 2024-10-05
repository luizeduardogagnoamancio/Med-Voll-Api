package med.voll.api.service;

import lombok.extern.log4j.Log4j2;
import med.voll.api.component.MedicoComponent;
import med.voll.api.dto.MedicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Log4j2
@Service
public class MedicoService {
    @Autowired
    private MedicoComponent medicoComponent;

    @Validated
    public void cadastarMedico(MedicoDto medico) {
        log.info("Entrou no Service para criar uma solicitação de cadastro de médico");
        //fazer validação
        validacoes(medico);
        medicoComponent.salvar(medicoComponent.criarMedico(medico));
    }

    private void validacoes(MedicoDto medico) {

    }

}
