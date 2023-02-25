package coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {

    public WeeklyTask(String title,
                      String description,
                      Type type,
                      LocalDateTime localDateTime) {
        super(title, description, type, localDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return getLocalDateTime().getDayOfWeek().equals(localDate.getDayOfWeek());
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Повторяемость: еженедельная";
    }
}