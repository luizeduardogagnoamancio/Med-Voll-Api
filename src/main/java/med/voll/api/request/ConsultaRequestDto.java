package med.voll.api.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaRequestDto {
  private String cpfPaciente;
  private String crm;
  private Date horarioConsulta;

}
