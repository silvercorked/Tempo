package cist4830.unomaha.tempo.goal;

import java.util.concurrent.TimeUnit;

public class RecurringGoal extends Goal {

    private TimeUnit frequency;

    public RecurringGoal(String name) {
        super(name);
    }

    public void setFrequency(TimeUnit frequency) {
        this.frequency = frequency;
    }

    public TimeUnit getFrequency(){
        return this.frequency;
    }
}
