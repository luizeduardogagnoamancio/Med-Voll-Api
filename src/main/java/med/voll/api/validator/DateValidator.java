package med.voll.api.validator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import med.voll.api.exception.DiaInvalidoException;
import med.voll.api.exception.HorarioInvalidoException;
import med.voll.api.exception.IntervaloInvalidoException;
import med.voll.api.utils.DateUtils;

public class DateValidator {

  public static void validarHorarioAgendamento(Date horarioConsulta){
    LocalDateTime consultaDateTime = horarioConsulta.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();

    if (DateUtils.isHorarioValido(consultaDateTime.toLocalTime())) {
      throw new HorarioInvalidoException("Horário da consulta deve estar entre 07:00 e 19:00.");
    }

    if (DateUtils.isIntervaloValido(consultaDateTime)) {
      throw new IntervaloInvalidoException("A consulta deve ser agendada pelo menos 30 minutos antes do horário de atendimento.");
    }

    if (DateUtils.isDiaSemanaValido(consultaDateTime.getDayOfWeek())) {
      throw new DiaInvalidoException("As consultas só podem ser marcadas de segunda à sábado.");
    }
  }

}
