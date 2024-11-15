package med.voll.api.service.impl;

import lombok.extern.log4j.Log4j2;
import med.voll.api.component.PacienteComponent;
import med.voll.api.request.PacienteRequestDto;
import med.voll.api.dto.PacienteDtoListagem;
import med.voll.api.dto.SolicitacaoPacienteAtualizarDto;
import med.voll.api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Log4j2
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteComponent pacienteComponent;

    @Validated
    public void cadastarPaciente(PacienteRequestDto paciente) {
        log.info("Entrou no Service para criar uma solicitação de cadastro de paciente");
        pacienteComponent.salvar(pacienteComponent.criarPaciente(paciente));
    }

    @Validated
    public Page<PacienteDtoListagem> listarPacientes(int pagina) {
        log.info("Entrou no Service para criar uma solicitação de listagem de pacientes");
        return pacienteComponent.listarPacientes(pagina);
    }

    @Validated
    public void  atualizarPaciente(String cpf, SolicitacaoPacienteAtualizarDto solicitacaoPacienteAtualizarDto) {
        log.info("Entrou no Service para criar uma solicitação de atualização de pacientes");
        pacienteComponent.atualizarPaciente(cpf, solicitacaoPacienteAtualizarDto);
    }

    public void excluirPaciente(String cpf) {
        log.info("Entrou no Service para criar uma solicitação de exclusão do paciente");
        pacienteComponent.excluirPaciente(cpf);
    }
}
