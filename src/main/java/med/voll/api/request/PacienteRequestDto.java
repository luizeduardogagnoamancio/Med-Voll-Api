package med.voll.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.entity.PacienteEntity;

import java.io.Serializable;

@Getter
@Setter
public class PacienteRequestDto implements Serializable {
    @NotNull(message = "O nome é obrigatório")
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
    private String nome;

    @NotNull(message = "O telefone é obrigatório")
    @Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 caracteres")
    private String telefone;

    @NotNull(message = "O email é obrigatório")
    @Size(min = 3, max = 255, message = "O email deve ter entre 3 e 255 caracteres")
    private String email;

    @NotNull(message = "O CPF é obrigatório")
    @Size(min = 11, max = 14, message = "O CPF deve ter 11 caracteres")
    private String cpf;

    @NotNull(message = "O endereço é obrigatório")
    @Size(min = 25, max = 255, message = "O endereço deve ter entre 200 e 255 caracteres")
    private String endereco;

    public PacienteRequestDto(PacienteEntity paciente) {
        this.nome = paciente.getNome();
        this.telefone = paciente.getTelefone();
        this.email = paciente.getEmail();
        this.cpf = paciente.getCpf();
        this.endereco = paciente.getEndereco();
    }

    public PacienteRequestDto(){}
}
