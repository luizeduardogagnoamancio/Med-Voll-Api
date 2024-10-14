package med.voll.api.service;

import med.voll.api.dto.PacienteDto;
import med.voll.api.dto.PacienteDtoListagem;
import med.voll.api.dto.SolicitacaoPacienteAtualizarDto;
import org.springframework.data.domain.Page;

public interface PacienteService {
    void cadastarPaciente(PacienteDto paciente);

    Page<PacienteDtoListagem> listarPacientes(int pagina);

    void atualizarPaciente(String crm, SolicitacaoPacienteAtualizarDto solicitacaoMedicoAtualizarDto);
}
