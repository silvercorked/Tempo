package cist4830.unomaha.tempo.goal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cist4830.unomaha.tempo.model.Goal;

import java.time.LocalDate;
import java.time.Month;


public class TestGoal {
/*
    @Test
    public void testNewGoalState() {
        Goal goal = new Goal("Write Code");
        Assertions.assertEquals(goal.getName(), "Write Code");
        Assertions.assertFalse(goal.isCompleted());
    }

    @Test
    public void testGoalCompleted() {
        Goal goal = new Goal("Write Unit Tests");
        goal.setCompleted(true);
        Assertions.assertTrue(goal.isCompleted());
    }

    @Test
    public void testSubtasks() {
        Goal goal = new Goal("Write Unit Tests");
        Goal subtask = new Goal("Add JUnit");
        goal.getSubTasks().add(subtask);
        Assertions.assertTrue(goal.getSubTasks().contains(subtask));
    }

    @Test
    public void testRenameGoal() {
        Goal goal = new Goal("Write Tests");
        goal.setName("Actually, Eat Cheetos");
        Assertions.assertTrue(goal.getName().equalsIgnoreCase("Actually, Eat Cheetos"));
    }

    @Test
    public void testDueDate() {
        Goal goal = new Goal("graduate");
        goal.setDueDate(LocalDate.of(2021, Month.MAY, 1));
        Assertions.assertEquals(goal.getDueDate().getDayOfMonth(), 1);
        Assertions.assertEquals(goal.getDueDate().getYear(), 2021);
        Assertions.assertEquals(goal.getDueDate().getMonth(), Month.MAY);
    }

    @Test
    public void testOverDue() {
        Goal goal = new Goal("graduate");
        goal.setDueDate(LocalDate.now().plusYears(1));
        Assertions.assertFalse(goal.isCompleted());
        Assertions.assertFalse(goal.isOverdue());

        goal.setDueDate(LocalDate.now().minusYears(2));
        Assertions.assertTrue(goal.isOverdue());
    }*/
}
