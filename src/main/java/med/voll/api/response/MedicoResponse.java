package med.voll.api.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoResponse {
    private String nome;

    private String email;

    private String telefone;

    private String crm;

    private String especialidade;

    private String endereco;
}
