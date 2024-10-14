package med.voll.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MedicoDtoListagem implements Serializable {
    private String nome;
    private String email;
    private String crm;
    private String especialidade;



    public MedicoDtoListagem(String nome, String email, String crm, String especialidade) {
        this.nome = nome;
        this.email = email;
        this.crm = crm;
        this.especialidade = especialidade;
    }
}
