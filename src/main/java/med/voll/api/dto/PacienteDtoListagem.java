package med.voll.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PacienteDtoListagem implements Serializable {
    private String nome;
    private String email;
    private String telefone;

    public PacienteDtoListagem(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
}
