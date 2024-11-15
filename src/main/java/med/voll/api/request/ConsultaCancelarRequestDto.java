package med.voll.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaCancelarRequestDto {
    private Long consultaId;

    @NotBlank(message = "O motivo do cancelamento é obrigatório")
    private String motivoCancelamento;
}
