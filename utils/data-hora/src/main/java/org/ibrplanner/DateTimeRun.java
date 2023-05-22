package org.ibrplanner;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeRun {
    public static void main(String[] args) {
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatterBuilder.html
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html

        DateTimeFormatter formatDateBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatDateTimeBR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter formatDateTimeGlobal = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());
        DateTimeFormatter formatDateTimeGlobalWithDefautFormatLocal = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter formatDateTimeGlobalWithDefautFormatGlobal = DateTimeFormatter.ISO_INSTANT;

        // (agora) -> Data-hora
        LocalDate d01 = LocalDate.now();
        LocalDateTime d02 = LocalDateTime.now();
        Instant d03 = Instant.now();

        // Texto ISO 8601 -> Data-hora (Recebe um texto e converte para ISO)
        LocalDate d04 = LocalDate.parse("2023-05-02");
        LocalDateTime d05 = LocalDateTime.parse("2023-05-02T01:30:26");
        Instant d06 = Instant.parse("2023-05-02T01:30:26Z");
        Instant d07 = Instant.parse("2023-05-02T01:30:26-03:00");

        // Texto em formato customizado -> Data-hora ISO (Recebe customizado e converte para ISO)
        LocalDate d08 = LocalDate.parse("02/05/2023", formatDateBR);
        LocalDateTime d09 = LocalDateTime.parse("02/05/2023 17:24", formatDateTimeBR);

        // dia, mês, ano, [horário] (dados isolados) -> Data-hora local
        LocalDate d10 = LocalDate.of(2023, 5, 2);
        LocalDateTime d11 = LocalDateTime.of(2023, 5, 2, 17, 48);

        // Formatação (Data-hora -> Texto ISO 8601 | Texto formato customizado) / Data para Texto customizado
        LocalDate d12 = LocalDate.parse("2023-05-02");
        LocalDateTime d13 = LocalDateTime.parse("2023-05-02T01:30:26");
        Instant d14 = Instant.parse("2023-05-02T01:30:26Z"); // timezone (3h antes)

        // converter data-hora global para local (precisa informar o timezone)
        LocalDate r1 = LocalDate.ofInstant(d14, ZoneId.systemDefault());
        LocalDate r2 = LocalDate.ofInstant(d14, ZoneId.of("Portugal"));
        LocalDateTime r3 = LocalDateTime.ofInstant(d14, ZoneId.systemDefault());
        LocalDateTime r4 = LocalDateTime.ofInstant(d14, ZoneId.of("Portugal"));

        // Cálculos com Data-hora
        LocalDate pastWeekLocalDate = d12.minusDays(7);            // uma semana antes  LocalDate
        LocalDate nextWeekLocalDate = d12.plusDays(7);                // uma semana depois LocalDate
        LocalDateTime pastWeekLocalDateTime = d13.minusDays(7);                // uma semana antes  LocalDateTime
        LocalDateTime nextWeekLocalDateTime = d13.plusDays(7);                 // uma semana depois LocalDateTime
        Instant pastWeekInstant = d14.minus(7, ChronoUnit.DAYS); // uma semana antes  Instant
        Instant nextWeekInstant = d14.plus(7, ChronoUnit.DAYS);     // uma semana depois Instant

        System.out.println("d01 = " + d01);
        System.out.println("d02 = " + d02);
        System.out.println("d03 = " + d03);
        System.out.println("d04 = " + d04);
        System.out.println("d05 = " + d05);
        System.out.println("d06 = " + d06);
        System.out.println("d07 = " + d07);
        System.out.println("d08 = " + d08);
        System.out.println("d09 = " + d09);
        System.out.println("d10 = " + d10);
        System.out.println("d11 = " + d11);

        // formas diferentes de chamar e converter uma Data-hora para um formato customizado
        // Local
        System.out.println("d12.1 = " + d12.format(formatDateBR));
        System.out.println("d12.2 = " + formatDateBR.format(d12));
        System.out.println("d12.3 = " + d12.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        System.out.println("d13.1 = " + d13.format(formatDateBR));
        System.out.println("d13.2 = " + d13.format(formatDateTimeBR));
        System.out.println("d13.3 = " + d13.format(formatDateTimeGlobalWithDefautFormatLocal));
        // Global
        System.out.println("d14.1 = " + formatDateTimeGlobal.format(d14));
        System.out.println("d14.2 = " + formatDateTimeGlobalWithDefautFormatGlobal.format(d14));
        System.out.println("d14.3 = " + d14);

        // data-hora global para local
        System.out.println("r1 = " + r1);
        System.out.println("r2 = " + r2);
        System.out.println("r3 = " + r3);
        System.out.println("r4 = " + r4);

        // obter dados de uma data-hora local (dia, mês, ano, horário)
        System.out.println("d12 dia (LocalDate) = " + d12.getDayOfMonth());
        System.out.println("d12 mês (LocalDate) = " + d12.getMonthValue());
        System.out.println("d12 ano (LocalDate) = " + d12.getYear());

        System.out.println("d13 hora    (LocalDateTime) = " + d13.getHour());
        System.out.println("d13 minuto  (LocalDateTime) = " + d13.getMinute());
        System.out.println("d13 segundo (LocalDateTime) = " + d13.getSecond());
        System.out.println("d13 dia     (LocalDateTime) = " + d13.getDayOfMonth());
        System.out.println("d13 mês     (LocalDateTime) = " + d13.getMonthValue());
        System.out.println("d13 ano     (LocalDateTime) = " + d13.getYear());

        System.out.println("d12 (pastWeekLocalDate) = " + pastWeekLocalDate);
        System.out.println("d12 (pastWeekLocalDate) = " + nextWeekLocalDate);

        System.out.println("d13 (pastWeekLocalDateTime) = " + pastWeekLocalDateTime);
        System.out.println("d13 (nextWeekLocalDateTime) = " + nextWeekLocalDateTime);

        System.out.println("d14 (pastWeekInstant) = " + pastWeekInstant);
        System.out.println("d14 (nextWeekInstant) = " + nextWeekInstant);

        // Duração entre duas Data-hora
        Duration t1 = Duration.between(pastWeekLocalDate.atStartOfDay(), d12.atStartOfDay());
        Duration t2 = Duration.between(pastWeekLocalDateTime, d13);
        Duration t3 = Duration.between(pastWeekInstant, d14);
        Duration t4 = Duration.between(d14, pastWeekInstant);

        System.out.println("t1 dias = " + t1.toDays()); // LocalDate (por não ter informação de tempo) -> convert para LocalDateTime (atTime ou atStartOfDay)
        System.out.println("t2 dias = " + t2.toDays()); // LocalDateTime
        System.out.println("t3 dias = " + t3.toDays()); // Instant
        System.out.println("t4 dias = " + t4.toDays()); // Instant
    }
}