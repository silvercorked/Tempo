package cist4830.unomaha.tempo.recurrence;

import java.time.LocalDate;

public class RecurrenceCalculator {


    public static String getRecurrenceDate(String createdAt, Integer recurrence_num, String recurrence_freq) {
        LocalDate created = LocalDate.parse(createdAt);
        LocalDate recurrenceDate = switch (recurrence_freq) {
            case "DAY" -> created.plusDays(recurrence_num);
            case "WEEK" -> created.plusWeeks(recurrence_num);
            case "MONTH" -> created.plusMonths(recurrence_num);
            case "YEAR" -> created.plusYears(recurrence_num);
            default -> throw new UnsupportedOperationException("Unknown frequency!");
        };
        return recurrenceDate.toString();
    }
}
