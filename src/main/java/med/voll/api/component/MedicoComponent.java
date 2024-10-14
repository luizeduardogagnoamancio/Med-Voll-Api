package med.voll.api.component;

import lombok.extern.log4j.Log4j2;
import med.voll.api.dto.MedicoDto;
import med.voll.api.dto.MedicoDtoListagem;
import med.voll.api.dto.SolicitacaoMedicoAtualizarDto;
import med.voll.api.entity.MedicoEntity;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.utils.MedicoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Log4j2
@Component
public class MedicoComponent {
    @Autowired
    private final MedicoRepository medicoRepository;

    final int MEDICOS_POR_PAGINA = 10;

    public MedicoComponent(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public MedicoEntity criarMedico(@Validated MedicoDto medico) {
        log.info("Entrou no Component para criar uma instância de MedicoEntity");
        MedicoEntity medicoEntity = new MedicoEntity();
        medicoEntity.setNome(medico.getNome());
        medicoEntity.setTelefone(medico.getTelefone());
        medicoEntity.setEmail(medico.getEmail());
        medicoEntity.setCrm(medico.getCrm());
        medicoEntity.setEspecialidade(medico.getEspecialidade());
        medicoEntity.setEndereco(medico.getEndereco());

        return medicoEntity;

    }

    public void salvar(MedicoEntity medico) {
        log.info("Entrou no Component para salvar uma instância de Médico");
        medicoRepository.save(medico);
    }

    public Page<MedicoDtoListagem> listarMedicos(int pagina) {
        log.info("Entrou no Component para listar médicos");
        Pageable pageable = PageRequest.of(pagina, MEDICOS_POR_PAGINA, Sort.by("nome").ascending());

        return medicoRepository.listarMedicos(pageable);
    }

    public MedicoEntity buscarPorCrm(String crm) {
        log.info("Entrou no Component para buscar médico por crm");
        Optional<MedicoEntity> medicoOptional = medicoRepository.findByCrm(crm);

        return medicoOptional.orElseThrow(() -> new RuntimeException("Médico não encontrado com o CRM: " + crm));
    }

    public void atualizarMedico(String crm, SolicitacaoMedicoAtualizarDto solicitacaoMedicoAtualizarDto) {
        log.info("Entrou no Component para atualizar um médico");
        MedicoEntity medico = buscarPorCrm(crm);

        try {
            MedicoUtils.copiarNonNullProperties(solicitacaoMedicoAtualizarDto, medico);
        } catch (IllegalAccessError e) {
            throw new RuntimeException("Erro ao atualizar médico", e);
        }

        medicoRepository.save(medico);
    }

    public void excluirMedico(String crm) {
        log.info("Entrou no Component para excluir um médico");
        MedicoEntity medico = buscarPorCrm(crm);

        medicoRepository.delete(medico);
    }


}
