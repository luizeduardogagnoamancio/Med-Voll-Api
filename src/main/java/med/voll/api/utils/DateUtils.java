package med.voll.api.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import med.voll.api.exception.DiaInvalidoException;
import med.voll.api.exception.HorarioInvalidoException;
import med.voll.api.exception.IntervaloInvalidoException;

public class DateUtils {
  public static void validarHorarioConsulta(LocalDateTime consultaDateTime) {
    validarHorario(consultaDateTime.toLocalTime());
    validarIntervalo(consultaDateTime);
    validarDiaSemana(consultaDateTime.getDayOfWeek());
  }

  private static void validarHorario(LocalTime time) {
    LocalTime start = LocalTime.of(7, 0);
    LocalTime end = LocalTime.of(19, 0);
    if (time.isBefore(start) || time.isAfter(end)) {
      throw new HorarioInvalidoException("Horário da consulta deve estar entre 07:00 e 19:00.");
    }
  }

  private static void validarIntervalo(LocalDateTime consultaDateTime) {
    LocalDateTime now = LocalDateTime.now();
    if (consultaDateTime.isBefore(now.plusMinutes(30))) {
      throw new IntervaloInvalidoException("A consulta deve ser agendada pelo menos 30 minutos antes do horário de atendimento.");
    }
  }

  private static void validarDiaSemana(DayOfWeek dayOfWeek) {
    if (dayOfWeek == DayOfWeek.SUNDAY) {
      throw new DiaInvalidoException("As consultas só podem ser marcadas de segunda à sábado.");
    }
  }

}
