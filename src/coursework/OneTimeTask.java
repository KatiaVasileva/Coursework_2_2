package coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class OneTimeTask extends Task{
    private LocalDate timeOfTask;

    public OneTimeTask(String title,
                       String description,
                       Type type,
                       LocalDateTime localDateTime,
                       LocalDate timeOfTask) {
        super(title, description, type, localDateTime);
        this.timeOfTask = timeOfTask;
    }

    public LocalDate getTimeOfTask() {
        return timeOfTask;
    }

    public void setTimeOfTask(LocalDate timeOfTask) {
        this.timeOfTask = timeOfTask;
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return timeOfTask.equals(localDate);
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Повторяемость: однократная. Срок выполнения - " + timeOfTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OneTimeTask that = (OneTimeTask) o;
        return Objects.equals(timeOfTask, that.timeOfTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timeOfTask);
    }
}
