package med.voll.api.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtils {

  public static boolean isHorarioInValido(LocalTime time) {
    LocalTime start = LocalTime.of(7, 0);
    LocalTime end = LocalTime.of(19, 0);

    return time.isBefore(start) || time.isAfter(end);
  }

  public static boolean isIntervaloInValido(LocalDateTime consultaDateTime) {
    return consultaDateTime.isBefore(LocalDateTime.now().plusMinutes(30));
  }

  public static boolean isDiaSemanaInValido(DayOfWeek dayOfWeek) {
    return dayOfWeek == DayOfWeek.SUNDAY;
  }

}
