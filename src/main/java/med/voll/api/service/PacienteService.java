package med.voll.api.service;

import med.voll.api.request.PacienteRequestDto;
import med.voll.api.dto.PacienteDtoListagem;
import med.voll.api.dto.SolicitacaoPacienteAtualizarDto;
import org.springframework.data.domain.Page;

public interface PacienteService {
    void cadastarPaciente(PacienteRequestDto paciente);

    Page<PacienteDtoListagem> listarPacientes(int pagina);

    void atualizarPaciente(String cpf, SolicitacaoPacienteAtualizarDto solicitacaoPacienteAtualizarDto);

    void excluirPaciente(String cpf);
}
