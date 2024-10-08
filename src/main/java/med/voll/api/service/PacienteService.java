package med.voll.api.service;

import lombok.extern.log4j.Log4j2;
import med.voll.api.component.PacienteComponent;
import med.voll.api.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Log4j2
public class PacienteService {

    @Autowired
    private PacienteComponent pacienteComponent;

    @Validated
    public void cadastarPaciente(PacienteDto paciente) {
        log.info("Entrou no Service para criar uma solicitação de cadastro de paciente");
        pacienteComponent.salvar(pacienteComponent.criarPaciente(paciente));
    }

    @Validated
    public Page<PacienteDto> listarPacientes(int pagina) {
        log.info("Entrou no Service para criar uma solicitação de listagem de médicos");
        return pacienteComponent.listarPacientes(pagina);
    }
}
