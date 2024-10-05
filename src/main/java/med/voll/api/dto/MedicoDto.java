package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoDto {

    @NotNull(message = "O nome é obrigatório")
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
    private String nome;

    @NotNull(message = "O email é obrigatório")
    @Size(min = 3, max = 255, message = "O email deve ter entre 3 e 255 caracteres")
    private String email;

    @NotNull(message = "O telefone é obrigatório")
    @Size(min = 1, max = 15, message = "O telefone deve ter entre 1 e 15 caracteres")
    private Long telefone;

    @NotNull(message = "O CRM é obrigatório")
    @Size(min = 10, max = 15, message = "O CRM deve ter entre 10 e 15 caracteres")
    private String crm;

    @NotNull(message = "A especialidade é obrigatória")
    @Size(min = 50, max = 100, message = "A especialidade deve ter entre 50 e 100 caracteres")
    private String especialidade;

    @NotNull(message = "O endereço é obrigatório")
    @Size(min = 200, max = 255, message = "O endereço deve ter entre 200 e 255 caracteres")
    private String endereco;

}
