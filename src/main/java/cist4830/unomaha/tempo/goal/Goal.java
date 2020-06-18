package cist4830.unomaha.tempo.goal;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Goal {

    private String name;
    private boolean completed = false;
    private List<Goal> subTasks = new ArrayList<>();
    private LocalDate dueDate;

    public Goal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Goal> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Goal> subTasks) {
        this.subTasks = subTasks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate);
    }

}
