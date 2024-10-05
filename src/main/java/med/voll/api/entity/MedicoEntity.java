package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_MEDICOS")
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private Long telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "crm")
    private String crm;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "endereco")
    private String endereco;
}

