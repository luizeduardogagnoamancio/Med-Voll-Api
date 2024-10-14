package med.voll.api.service;

import med.voll.api.dto.MedicoDto;
import med.voll.api.dto.MedicoDtoListagem;
import med.voll.api.dto.SolicitacaoMedicoAtualizarDto;
import org.springframework.data.domain.Page;

public interface MedicoService {

    void cadastarMedico(MedicoDto medicoDto);

    Page<MedicoDtoListagem> listarMedicos(int pagina);

    void atualizarMedico(String crm, SolicitacaoMedicoAtualizarDto solicitacaoMedicoAtualizarDto);

    void excluirMedico(String crm);


}
