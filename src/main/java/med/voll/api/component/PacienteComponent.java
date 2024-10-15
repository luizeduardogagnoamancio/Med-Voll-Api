package med.voll.api.component;


import lombok.extern.log4j.Log4j2;
import med.voll.api.dto.*;
import med.voll.api.entity.PacienteEntity;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.Set;

@Log4j2
@Component
public class PacienteComponent {

    @Autowired
    private PacienteRepository pacienteRepository;

    private final int PACIENTES_POR_PAGINA = 10;

    public PacienteComponent(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

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

    public Page<PacienteDtoListagem> listarPacientes(int pagina) {
        log.info("Entrou no Component para listar pacientes");
        Pageable pageable = PageRequest.of(pagina, PACIENTES_POR_PAGINA, Sort.by("nome").ascending());

        return pacienteRepository.listarPacientes(pageable);
    }

    public PacienteEntity buscarPorCpf(String cpf) {
        log.info("Entrou no Component para buscar paciente por cpf");
        Optional<PacienteEntity> pacienteOptional = pacienteRepository.findByCpf(cpf);

        return pacienteOptional.orElseThrow(() -> new RuntimeException("Paciente não encontrado com o CPF: " + cpf));
    }

    public void atualizarPaciente(String cpf, SolicitacaoPacienteAtualizarDto solicitacaoPacienteAtualizarDto) {
        log.info("Entrou no Component para atualizar um paciente");
        PacienteEntity paciente = buscarPorCpf(cpf);
        Set<String> camposIgnorados = Set.of("email", "cpf");

        try {
            Utils.copiarNonNullProperties(solicitacaoPacienteAtualizarDto, paciente, camposIgnorados);
        } catch (IllegalAccessError e) {
            throw new RuntimeException("Erro ao atualizar paciente", e);
        }

        pacienteRepository.save(paciente);
    }

    public void excluirPaciente(String cpf) {
        log.info("Entrou no Component para excluir o paciente");
        PacienteEntity paciente = buscarPorCpf(cpf);

        pacienteRepository.delete(paciente);
    }
}
