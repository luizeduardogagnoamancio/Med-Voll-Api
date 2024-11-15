package med.voll.api.service;

import med.voll.api.request.MedicoRequestDto;
import med.voll.api.dto.MedicoDtoListagem;
import med.voll.api.dto.SolicitacaoMedicoAtualizarDto;
import org.springframework.data.domain.Page;

public interface MedicoService {

    void cadastarMedico(MedicoRequestDto medicoRequestDto);

    Page<MedicoDtoListagem> listarMedicos(int pagina);

    void atualizarMedico(String crm, SolicitacaoMedicoAtualizarDto solicitacaoMedicoAtualizarDto);

    void excluirMedico(String crm);


}
