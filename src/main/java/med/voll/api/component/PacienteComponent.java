package med.voll.api.component;

import lombok.extern.log4j.Log4j2;
import med.voll.api.dto.*;
import med.voll.api.entity.PacienteEntity;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.request.PacienteRequestDto;
import med.voll.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Log4j2
@Component
public class PacienteComponent {
    @Autowired
    private final PacienteRepository pacienteRepository;

    private final int PACIENTES_POR_PAGINA = 10;

    public PacienteComponent(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteEntity criarPaciente(PacienteRequestDto pacienteRequestDto) {
        log.info("Entrou no Component para criar uma instância de PacienteEntity");
        PacienteEntity pacienteEntity = new PacienteEntity();
        pacienteEntity.setNome(pacienteRequestDto.getNome());
        pacienteEntity.setTelefone(pacienteRequestDto.getTelefone());
        pacienteEntity.setEmail(pacienteRequestDto.getEmail());
        pacienteEntity.setCpf(pacienteRequestDto.getCpf());
        pacienteEntity.setEndereco(pacienteRequestDto.getEndereco());

        return pacienteEntity;

    }

    public void salvar(PacienteRequestDto pacienteRequestDto) {
        log.info("Entrou no Component para salvar uma instância de Paciente");
        PacienteEntity paciente =  criarPaciente(pacienteRequestDto);
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
