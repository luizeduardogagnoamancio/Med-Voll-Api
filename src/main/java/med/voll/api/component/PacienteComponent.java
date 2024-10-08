package med.voll.api.component;


import lombok.extern.log4j.Log4j2;
import med.voll.api.dto.MedicoDto;
import med.voll.api.dto.PacienteDto;
import med.voll.api.entity.MedicoEntity;
import med.voll.api.entity.PacienteEntity;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Log4j2
@Component
public class PacienteComponent {

    @Autowired
    private PacienteRepository pacienteRepository;

    private final int PACIENTES_POR_PAGINA = 10;

    public PacienteEntity criarPaciente(@Validated PacienteDto pacienteDto) {
        log.info("Entrou no Component para criar uma instância de PacienteEntity");
        PacienteEntity pacienteEntity = new PacienteEntity();
        pacienteEntity.setNome(pacienteDto.getNome());
        pacienteEntity.setTelefone(pacienteDto.getTelefone());
        pacienteEntity.setEmail(pacienteDto.getEmail());
        pacienteEntity.setCpf(pacienteDto.getCpf());
        pacienteEntity.setEndereco(pacienteDto.getEndereco());

        return pacienteEntity;

    }

    public void salvar(PacienteEntity paciente) {
        log.info("Entrou no Component para salvar uma instância de Paciente");
        pacienteRepository.save(paciente);
    }

    public Page<PacienteDto> listarPacientes(int pagina) {
        log.info("Entrou no Component para listar pacientes");
        Pageable pageable = PageRequest.of(pagina, PACIENTES_POR_PAGINA, Sort.by("nome").ascending());

        return pacienteRepository.listarPacientes(pageable);
    }
}
