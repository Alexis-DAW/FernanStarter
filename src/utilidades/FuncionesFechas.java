package utilidades;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FuncionesFechas {

    public static LocalDate convertirAFecha(String fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha, formato);
    }

    public static String convertirAString(LocalDate fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formato);
    }

    public static long segundos(LocalDateTime fecha){
        LocalDateTime fechaActual= LocalDateTime.now();
        return ChronoUnit.SECONDS.between(fecha, fechaActual);
    }

    public static long minutos(LocalDateTime fecha){
        LocalDateTime fechaActual= LocalDateTime.now();
        return ChronoUnit.MINUTES.between(fecha, fechaActual);
    }

    public static long horas(LocalDateTime fecha){
        LocalDateTime fechaActual= LocalDateTime.now();
        return ChronoUnit.HOURS.between(fecha, fechaActual);
    }

    public static long dias(LocalDateTime fecha){
        LocalDateTime fechaActual= LocalDateTime.now();
        return ChronoUnit.DAYS.between(fecha, fechaActual);
    }

    public static boolean esFechaAnterior(LocalDate fecha) {
        return fecha.isBefore(LocalDate.now());
    }
}
