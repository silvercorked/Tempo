package cist4830.unomaha.tempo.goal;

import cist4830.unomaha.tempo.RecurrenceCalculator;
import cist4830.unomaha.tempo.model.Goal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestGoal {

    private final Goal goal = new Goal(10000L, 20000L, "Test", "TestDescription",
            30000L, 40000L, "2020-07-09", 1, "WEEK",
            50000L, "2020-07-09", "2020-07-09");

    @Test
    public void testGetID() {
        Assertions.assertEquals(10000L, goal.getId());
    }

    @Test
    public void testGetParentId() {
        Assertions.assertEquals(20000L, goal.getParentId());
    }

    @Test
    public void testGetGoalName() {
        Assertions.assertEquals("Test", goal.getGoal());
    }

    @Test
    public void testGetGoalDescription() {
        Assertions.assertEquals("TestDescription", goal.getDescription());
    }

    @Test
    public void testGetProgress() {
        Assertions.assertEquals(30000L, goal.getProgress());
    }

    @Test
    public void testGetTarget() {
        Assertions.assertEquals(40000L, goal.getTarget());
    }

    @Test
    public void testGetDueDate() {
        Assertions.assertEquals("2020-07-09", goal.getDueDate());
    }

    @Test
    public void testGetUserId() {
        Assertions.assertEquals(50000L, goal.getUserId());
    }

    @Test
    public void testGetCreatedAt() {
        Assertions.assertEquals("2020-07-09", goal.getCreatedAt());
    }

    @Test
    public void testGetModifiedAt() {
        Assertions.assertEquals("2020-07-09", goal.getModifiedAt());
    }

    @Test
    public void testGetRecurrenceNum() {
        Assertions.assertEquals(1, goal.getRecurrence_num());
    }

    @Test
    public void testGetRecurrenceFrequency() {
        Assertions.assertEquals("WEEK", goal.getRecurrence_freq());
    }

    @Test
    public void testRecurrenceCalculator() {
        LocalDate creationDate = LocalDate.parse(goal.getCreatedAt()); // 2020-07-09
        Assertions.assertEquals("2020-07-16", RecurrenceCalculator.getRecurrenceDate(goal.getCreatedAt(), goal.getRecurrence_num(), goal.getRecurrence_freq()));
    }

}
