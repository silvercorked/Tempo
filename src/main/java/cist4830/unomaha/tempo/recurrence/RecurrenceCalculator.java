package cist4830.unomaha.tempo.recurrence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.repository.GoalRepository;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.sql.Date;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * https://spring.io/guides/gs/scheduling-tasks/
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling
 * https://www.baeldung.com/cron-expressions
 */
public class RecurrenceCalculator {

    @Autowired
    private GoalRepository goalRepository;

    @Scheduled(cron = "0 0 0 * * ? *") // at 00:00:00 every day (i think)
    public void checkForRecurrence() {
        System.out.println("ran recurrence check");
        Date now = new Date(new java.util.Date().getTime());
        List<Goal> goalsToRecur = goalRepository.findAll().stream()
            .filter((goal) -> {
                if (goal.getRecurrenceFreq().equals("NEVER") || goal.getRecurrenceDate() == null)
                    return false;
                return now.after(Date.valueOf(goal.getRecurrenceDate()));
            })
            .collect(Collectors.toList());
        for (Goal goal : goalsToRecur) {
            goal.setProgress((long) 0);
            goal.setRecurrenceDate(getRecurrenceDate(goal.getRecurrenceDate(), goal.getRecurrenceNum(), goal.getRecurrenceFreq()));
            goalRepository.update(goal);
        }
    }

    public static String getRecurrenceDate(String recurrence_date, Integer recurrence_num, String recurrence_freq) {
        LocalDate recur_date = LocalDate.parse(recurrence_date);
        return Date.valueOf(switch (recurrence_freq) {
            case "DAY" -> recur_date.plusDays(recurrence_num);
            case "WEEK" -> recur_date.plusWeeks(recurrence_num);
            case "MONTH" -> recur_date.plusMonths(recurrence_num);
            case "YEAR" -> recur_date.plusYears(recurrence_num);
            default -> throw new UnsupportedOperationException("Unknown frequency!");
        }).toString();
    }
}
