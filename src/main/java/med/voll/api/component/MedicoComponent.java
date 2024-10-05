package med.voll.api.component;

import lombok.extern.log4j.Log4j2;
import med.voll.api.dto.MedicoDto;
import med.voll.api.entity.MedicoEntity;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Log4j2
@Component
public class MedicoComponent {
    @Autowired
    private final MedicoRepository medicoRepository;

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
}
