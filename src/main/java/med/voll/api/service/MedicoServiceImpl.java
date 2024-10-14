package med.voll.api.service;

import lombok.extern.log4j.Log4j2;
import med.voll.api.component.MedicoComponent;
import med.voll.api.dto.MedicoDto;
import med.voll.api.dto.MedicoDtoListagem;
import med.voll.api.dto.SolicitacaoMedicoAtualizarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Log4j2
@Service
public class MedicoServiceImpl implements MedicoService {
    @Autowired
    private MedicoComponent medicoComponent;

    @Validated
    public void cadastarMedico(MedicoDto medico) {
        log.info("Entrou no Service para criar uma solicitação de cadastro de médico");
        medicoComponent.salvar(medicoComponent.criarMedico(medico));
    }

    @Validated
    public Page<MedicoDtoListagem> listarMedicos(int pagina) {
        log.info("Entrou no Service para criar uma solicitação de listagem de médicos");
        return medicoComponent.listarMedicos(pagina);
    }

    @Validated
    public void atualizarMedico(String crm, SolicitacaoMedicoAtualizarDto solicitacaoMedicoAtualizarDto) {
        log.info("Entrou no Service para criar uma solicitação de atualização de médico");
        medicoComponent.atualizarMedico(crm, solicitacaoMedicoAtualizarDto);
    }

    @Validated
    public void excluirMedico(String crm) {
        log.info("Entrou no Service para criar uma solicitação de exclusão de médico");
        medicoComponent.excluirMedico(crm);
    }

}
